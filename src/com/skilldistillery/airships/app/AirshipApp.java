/*
Synopsis Fleet control app (Jets but with Airships)
Author: Jacob Stuart
Version: 1.5 
 */
package com.skilldistillery.airships.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	private DockingTower fleetTower;
	private Scanner kb = new Scanner(System.in);
	private boolean shipIsRemoved = false;
	private boolean shipIsSent = false;

	public static void main(String[] args) {
		AirshipApp app = new AirshipApp();
		app.run();
	}

	private void run() {
		fleetTower = new DockingTower(getShipsFromFile("airships.txt"));
		Integer menuChoice = 0;
		int quit = 12;

		greeting();

		while (menuChoice != quit) {
			displayMenu();
			menuChoice = getUserChoice();
			menuSwitch(menuChoice);
		}
	}

	private List<Airship> getShipsFromFile(String fileName) {
		List<Airship> airships = new ArrayList<>();

		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				Airship ship = buildShipFromLine(line);
				airships.add(ship);
			}
		} catch (FileNotFoundException notFound) {
			System.err.println(fileName + " was not found");
		} catch (IOException e) {
			System.err.println("Error reading from " + fileName + ": " + e.getMessage());
		}
		return airships;
	}

	public Airship buildShipFromLine(String shipLine) {
		String[] airship = shipLine.split(", ");
		String type = airship[0];
		String model = airship[1];
		double speed = Double.parseDouble(airship[2]);
		int range = Integer.parseInt(airship[3]);
		long price = Long.parseLong(airship[4]);
		int weapons = 0;
		int cargoCap = 0;

		if (airship.length == 6) {
			weapons = Integer.parseInt(airship[5]);
			cargoCap = Integer.parseInt(airship[5]);
		}
		if (airship.length == 7) {
			cargoCap = Integer.parseInt(airship[6]);
		}

		Airship ship = createAirship(type, model, speed, range, price, weapons, cargoCap);
		return ship;
	}

	private Airship createAirship(String type, String model, double speed, int range, long price, int weapons,
			int cargoCap) {
		if (type.toLowerCase().equals("multiship")) {
			return new MultiShip(model, speed, range, price, weapons, cargoCap);
		} else if (type.toLowerCase().equals("combatship")) {
			return new CombatShip(model, speed, range, price, weapons);
		} else if (type.toLowerCase().equals("cargoship")) {
			return new CargoShip(model, speed, range, price, cargoCap);
		} else {
			return new TourShip(model, speed, range, price);
		}
	}

	private void greeting() {
		System.out.println();
		System.out.println("Welcome, Admiral, to Airship fleet command.");
		System.out.println();
	}

	private int getUserChoice() {
		String userEntry = kb.nextLine();
		int menuChoice = 0;
		try {
			menuChoice = Integer.parseInt(userEntry);
		} catch (NumberFormatException e) {
			System.err.println("Invalid input. Please enter a valid number.");
		}
		return menuChoice;
	}

	private void displayMenu() {
		System.out.println();
		System.out.println("\nAirship Fleet Menu");
		System.out.println("Please choose one menu option:");
		System.out.println("1. List fleet");
		System.out.println(fleetTower.getIsDocked() ? "2. Fly all ships" : "2. Dock all ships");
		System.out.println("3. View fastest ship");
		System.out.println("4. View ship with longest range");
		System.out.println(shipIsSent ? "5. Return single ship." : "5. Choose to send out a single ship.");
		System.out.println(fleetTower.getIsDocked() ? "6. Load cargo" : "6. Defend against pirates");
		System.out.println("7. Take a tour");
		System.out.println("8. Add a ship to Fleet");
		System.out.println(shipIsRemoved ? "9. Undo most recent ship removal." : "9. Remove a ship from the Fleet");
		System.out.print(shipIsRemoved ? "10. Remove another ship.\n" : "");
		System.out
				.println((shipIsRemoved ? "11." : "10.") + " Would you like to save current Airship fleet to a file?");
		System.out.println((shipIsRemoved ? "12." : "11.") + " Quit");
		System.out.print("$> ");
	}

	private void menuSwitch(int choice) {

		if (shipIsRemoved && choice > 0 && choice < 13 || !shipIsRemoved && choice > 0 && choice < 12) {

			switch (choice) {
			case 1:
				fleetTower.displayAirships();
				break;
			case 2:
				if (fleetTower.getIsDocked()) {
					fleetTower.flyAllDocked();
				} else {
					fleetTower.dockAllFlying();
				}
				break;
			case 3:
				System.out.println("The fastest ship in the fleet currently is: ");
				findFastestShip();
				break;
			case 4:
				System.out.println("The ship in the fleet with the longest range currently is: ");
				findLongestRangeShip();
				break;
			case 5:
				if (!shipIsSent) {
					Airship sentShip = chooseSingleShip();
					fleetTower.sendSingleShipFromTower(sentShip);
					shipIsSent = true;
				} else {
					fleetTower.returnSingleShipFromTower();
					shipIsSent = false;
				}
				break;
			case 6:
				if (fleetTower.getIsDocked()) {
					addCargo();
				} else {
					fightEnemies();
				}
				break;
			case 7:
				takeTour();
				break;
			case 8:
				addNewShip();
				break;
			case 9:
				if (!shipIsRemoved) {
					Airship removedShip = chooseSingleShip();
					fleetTower.removeShipFromTower(removedShip);
					shipIsRemoved = true;
				} else {
					fleetTower.returnRemovedShipToTower();
					shipIsRemoved = false;
				}
				break;
			case 10:
				if (shipIsRemoved) {
					Airship removedShip = chooseSingleShip();
					fleetTower.removeShipFromTower(removedShip);
					shipIsRemoved = true;
				} else {
					writeAirshipFleetToFile();
				}
				break;
			case 11:
				if (!shipIsRemoved) {
					farewell();
				} else {
					writeAirshipFleetToFile();
				}
				break;
			case 12:
				farewell();
				break;
			default:
				break;
			}
		} else {
			System.err.println("I am sorry, that number was not an option, please choose 1 through 9.");
		}
	}

	private void findFastestShip() {
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

	private void findLongestRangeShip() {
		List<Airship> furthest = fleetTower.getAirships();
		double furthestRange = 0;
		for (Airship airship : furthest) {
			if (furthestRange < airship.getRange()) {
				furthestRange = airship.getRange();
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
			System.err.println("I am sorry, that is not a valid entry. Please go back to the main menu and try again.");
		}
	}

	private void fightEnemies() {
		List<Airship> enemyAirships = getShipsFromFile("pirates.txt");
		DockingTower pirateTower = new DockingTower(enemyAirships);
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

	private void takeTour() {
		for (Airship airship : fleetTower.getAirships()) {
			if (airship instanceof TourShip) {

				System.out.println("Just sit right back and you'll hear a tale,\n" + "A tale of a fateful trip\n"
						+ "That started from this tropic port\n" + "Aboard this tiny ship.\n" + "\n"
						+ "The mate was a mighty sailing man,\n" + "The skipper brave and sure.\n"
						+ "Five passengers set sail that day\n" + "For a three hour tour, a three hour tour.\n" + "\n"
						+ "The weather started getting rough,\n" + "The tiny ship was tossed,\n"
						+ "If not for the courage of the fearless crew\n"
						+ "The Minnow would be lost, the Minnow would be lost.\n" + "\n"
						+ "The ship set ground on the shore of this uncharted desert isle\n" + "With Gilligan\n"
						+ "The Skipper too,\n" + "The millionaire and his wife,\n" + "The movie star\n"
						+ "The Professor and Mary Ann,*\n" + "Here on Gilligan's Isle.\n" + "\n");
			}
		}
	}

	private void addNewShip() {
		addNewShipMenu();
		int shipChoice = getUserChoice();
		if (shipChoice > 0 && shipChoice < 5) {
			fleetTower.addShipToTower(buildShipFromLine(buildShipLine(shipChoice)));
		} else {
			System.err.println("Invalid choice. Please enter a number from 1 to 4.");
		}
	}

	public String buildShipLine(int shipChoice) {
		StringBuilder ship = new StringBuilder();

		System.out.println("Please provide the model for the new ship: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the speed of the new ship in MPH: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the range the new ship can go for: ");
		ship.append(kb.nextLine() + ", ");
		System.out.println("Please provide the price of the new ship: ");
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

	private Airship chooseSingleShip() {
		fleetTower.displayAirships();
		System.out.println("Please choose which ship to remove.");
		Integer shipChoice = getUserChoice();
		if (shipChoice > 0 && shipChoice <= fleetTower.getAirships().size()) {
			return fleetTower.getSingleShip(shipChoice);
		} else {
			System.err.println("Invalid choice. Please enter a valid number.");
			return null;
		}
	}

	private void writeAirshipFleetToFile() {
		List<Airship> airships = fleetTower.getAirships();
	    System.out.println("Please enter the name for your file (without extension)");
	    String fileName = kb.nextLine();
	    
	    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
	        for (Airship airship : airships) {
	        	String shipToFile = airship.shipToFile();
				writer.println(shipToFile);
	        }
	        System.out.println("Airships saved to " + fileName + ".txt");
	    } catch (IOException e) {
	        System.err.println("Error while saving to file: " + e.getMessage());
	    }
	}

	private void farewell() {
		System.out.println("Thank you for flying with us today, Admiral!");
		kb.close();
		System.exit(0);
	}
}