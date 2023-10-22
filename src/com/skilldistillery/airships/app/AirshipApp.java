/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.airships.entities.Airship;
import com.skilldistillery.airships.entities.CargoShip;
import com.skilldistillery.airships.entities.CombatShip;
import com.skilldistillery.airships.entities.DockingTower;
import com.skilldistillery.airships.entities.MultiShip;
import com.skilldistillery.airships.entities.TourShip;

public class AirshipApp {
	DockingTower fleetTower;
	DockingTower pirateTower;
	Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		AirshipApp app = new AirshipApp();
		app.run();
	}

	private void run() {
		List<Airship> airships = new ArrayList<>();
		Integer menuChoice = 0;
		String userEntry = "";

		fleetTower = new DockingTower(airships);
		getShipsFromFile("airships.txt", fleetTower);
		greeting();
		while (menuChoice != 9) {
			displayMenu();
			userEntry = kb.nextLine();
			try {
				menuChoice = Integer.parseInt(userEntry);
			} catch (NumberFormatException e) {
				System.err.println("Invalid entry");
			}
			menuSwitch(menuChoice);
		}
	}

	private void getShipsFromFile(String fileName, DockingTower tower) {

		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				tower.addShipToTower(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void greeting() {
		System.out.println();
		System.out.println("Welcome, Admiral, to Airship fleet command.");
		System.out.println();
		System.out.println();
	}

	// had to copy/paste the border, couldn't find how to add alt codes in mac
	private void displayMenu() {
		System.out.println();
		System.out.println();
		System.out.println("╔══════════════════════════════════╗");
		System.out.println("║        Airship Fleet Menu        ║");
		System.out.println("║  Please choose one menu option.  ║");
		System.out.println("╚══════════════════════════════════╝");
		System.out.println("║ 1. List fleet 		   ║");
		System.out.println("║ 2. Fly all ships		   ║");
		System.out.println("║ 3. View fastest ship		   ║");
		System.out.println("║ 4. View ship with longest range  ║");
		System.out.println("║ 5. Load cargo			   ║");
		System.out.println("║ 6. Defend against pirates	   ║");
		System.out.println("║ 7. Add a ship to Fleet  	   ║");
		System.out.println("║ 8. Remove a jet from Fleet  	   ║");
		System.out.println("║ 9. Quit   			   ║");
		System.out.println("╚══════════════════════════════════╝");
		System.out.println();
		System.out.println("$>");
	}

	private void menuSwitch(int choice) {
		switch (choice) {
		case 1:
			fleetTower.displayAirships();
			break;
		case 2:
			fleetTower.flyAll();
			break;
		case 3:
			fastestShip();
			break;
		case 4:
			longestRangeShip();
			break;
		case 5:
			addCargo();
			break;
		case 6:
			fightEnemies();
			break;
		case 7:
			addNewShip();
			break;
		case 8:
			removeShip();
			break;
		case 9:
			farewell();
			break;
		default:
			break;
		}
	}

	private void fastestShip() {
		List<Airship> fastest = fleetTower.getAirships();
		double fastestSpeed = 0;
		for (Airship airship : fastest) {
			if (fastestSpeed < airship.getSpeed()) {
				fastestSpeed = airship.getSpeed();
			}
		}
		for (Airship airship : fastest) {
			if (airship.getSpeed() == fastestSpeed) {
				System.out.println(airship);
			}
		}
		System.out.println();
	}

	private void longestRangeShip() {
		List<Airship> furthest = fleetTower.getAirships();
		double furthestRange = 0;
		for (Airship airship : furthest) {
			if (furthestRange < airship.getRange()) {
				furthestRange= airship.getRange();
			}
		}
		for (Airship airship : furthest) {
			if (airship.getRange() == furthestRange) {
				System.out.println(airship);
			}
		}
		System.out.println();
	}

	private void addCargo() {
		Integer amount = 0;
		System.out.println("Please enter how much cargo you would like to add. You have "
				+ fleetTower.getAvailableCapacity() + " units of empty space left.");
		String userEntry = kb.nextLine();
		try {
			amount = Integer.parseInt(userEntry);
		} catch (NumberFormatException e) {
			System.err.println();
		}
		if (amount > 0) {
			for (Airship airship : fleetTower.getAirships()) {
				if (airship instanceof CargoShip && amount != 0) {
					CargoShip cargo = (CargoShip) airship;
					amount = cargo.loadCargo(amount);
				} else if (airship instanceof MultiShip && amount != 0) {
					MultiShip multi = (MultiShip) airship;
					amount = multi.loadCargo(amount);
				} else {
					continue;
				}
			}
			if (amount == 0) {
				System.out.println("We are all loaded up");
			} else {
				System.out.println(amount + " will not fit and is left on the dock.");
			}
		} else {
			System.err.println(
					"I am sorry, that is not a valid entry. Please enter go back to the main menu and try again");
		}
	}

	private void fightEnemies() {
		List<Airship> airships = new ArrayList<>();
		DockingTower pirateTower = new DockingTower(airships);
		getShipsFromFile("pirates.txt", pirateTower);
		System.out.println("Pirates on the horizon");
		pirateTower.displayAirships();
		int fleetWeapons = fleetTower.getNumberWeapons();
		int pirateWeapons = pirateTower.getNumberWeapons();

		for (Airship airship : fleetTower.getAirships()) {
			if (airship instanceof CombatShip) {
				CombatShip combat = (CombatShip) airship;
				combat.attack();
			} else if (airship instanceof MultiShip) {
				MultiShip multi = (MultiShip) airship;
				multi.attack();
			} else {
				continue;
			}
		}
		System.out.println();
		if (fleetWeapons > pirateWeapons) {
			System.out.println("We burned em Admiral");
		} else if (fleetWeapons == pirateWeapons) {
			System.out.println("They didn't beat us, but they got the cargo.");
		} else {
			System.out.println("The fleet has lost, we must ransom back our ships");
		}
	}

	private void addNewShip() {
		addNewShipMenu();		
		Integer shipChoice = 0;
		String userEntry = "";

		try {
			userEntry = kb.nextLine();
			shipChoice = Integer.parseInt(userEntry);
		} catch (Exception e) {
			System.err.println("That was not a valid option. Please go back to the main menu and try again.");
		}

		if (shipChoice > 0 && shipChoice < 5) {
			fleetTower.addShipToTower(shipLineBuilder(shipChoice));
		} else {
			System.err.println("That is not a valid number. Please go back to the main menu and try again.");
		}
	}
	
	public String shipLineBuilder(int shipChoice) {
		StringBuilder ship = new StringBuilder();
		
		System.out.println("Please provide the model for the new ship: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the speed of the new ship in MPH: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the range the new ship can go for: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the price of the new ship: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the energy or fuel capacity of the new ship: ");
		ship.append(kb.nextLine() + ", ");
		if (shipChoice == 3) {
			ship.insert(0, "CombatShip, ");
			System.out.println("Please provide the number of weapons on the new ship: ");
			ship.append(kb.nextLine() + ", ");
		} else if (shipChoice == 4) {
			ship.insert(0, "CargoShip, ");
			System.out.println("Please provide the maximum cargo space available on the new ship: ");
			ship.append(kb.nextLine() + ", ");
		} else if (shipChoice == 2) {
			ship.insert(0, "MultiShip, ");
			System.out.println("Please provide the number of weapons on the new ship: ");
			ship.append(kb.nextLine() + ", ");
			System.out.println("Please provide the maximum cargo space available on the new ship: ");
			ship.append(kb.nextLine() + ", ");			
		}
		
		return ship.toString();
	}

	private void addNewShipMenu() {
		System.out.println();
		System.out.println("I will be happy to help you build a new ship.");
		System.out.println("Please choose the type of ship you wish to build");
		System.out
				.println("1. Tour ship, no weapons, no cargo space, but great for transporting small groups of people");
		System.out.println(
				"2. General purpose ship, some weapons, some cargo space, great for getting the most versatility out of your new ship.");
		System.out.println(
				"3. Combat ship, all weapons, no cargo space, great for protection or questionable acquisitioning.");
		System.out.println(
				"4. Cargo ship, no weapons, tons of cargo space, great for transporting anything and everything");
		System.out.println();

	}

	private void removeShip() {
		System.out.println("Please choose which ship to remove.");
		Integer shipChoice = 0;
		String userEntry = "";
		fleetTower.displayAirships();
		
		try {
			userEntry = kb.nextLine();
			shipChoice = Integer.parseInt(userEntry);
		} catch (Exception e) {
			System.err.println("That was not a valid option. Please go back to the main menu and try again.");
		}
		if (shipChoice > 0 && shipChoice <= fleetTower.getAirships().size()) {
			fleetTower.removeShipFromTower(shipChoice);
		} else {
			System.err.println("That is not a valid number. Please go back to the main menu and try again.");
		}
	}

	private void farewell() {
		System.out.println("Thank you for flying with us today, Admiral!");
		kb.close();
		System.exit(0);
	}
}
