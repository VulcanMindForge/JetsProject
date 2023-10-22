/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

import java.util.List;

public class DockingTower {
	private List<Airship> airships;

	public DockingTower(List<Airship> airships) {
		this.airships = airships;
	}

	public List<Airship> getAirships() {
		return airships;
	}

	public void displayAirships() {
		int shipNumber = 1;
		for (Airship airship : airships) {
			System.out.println(shipNumber + " " + airship);
			shipNumber++;
		}
	}

	public int getAvailableCapacity() {
		int availableCapacity = 0;
		for (Airship airship : airships) {
			if (airship instanceof CargoShip) {
				CargoShip cargo = (CargoShip) airship;
				availableCapacity = availableCapacity + cargo.getCargoCapacity();
			}
			if (airship instanceof MultiShip) {
				MultiShip multi = (MultiShip) airship;
				availableCapacity = availableCapacity + multi.getCargoCapacity();
			}
		}
		return availableCapacity;
	}

	public int getNumberWeapons() {
		int numWeapons = 0;
		for (Airship airship : airships) {
			if (airship instanceof CombatShip) {
				CombatShip combat = (CombatShip) airship;
				numWeapons = numWeapons + combat.getNumWeapons();
			}
			if (airship instanceof MultiShip) {
				MultiShip multi = (MultiShip) airship;
				numWeapons = numWeapons + multi.getNumWeapons();
			}
		}
		return numWeapons;
	}

	public void flyAll() {
		for (Airship airship : airships) {
			airship.fly();
		}
	}

	public void dockAll() {
		for (Airship airship : airships) {
			airship.dock();
		}
	}

	public void displayFlightStatus() {
		for (Airship airship : airships) {
			if (airship.getIsFlying()) {
				System.out.println(airship + " is in flight.");
			} else {
				System.out.println(airship + " is still docked.");
			}
		}
	}

	public void rechargeShips() {
		for (Airship airship : airships) {
			if (!airship.getIsFlying()) {
				airship.recharge();
			}
		}
	}

	public void addShipToTower(String shipLine) {
		String[] airship = shipLine.split(", ");
		String type = airship[0];
		String model = airship[1];
		double speed = Double.parseDouble(airship[2]);
		int range = Integer.parseInt(airship[3]);
		long price = Long.parseLong(airship[4]);
		double energyCap = Double.parseDouble(airship[5]);
		int weapons = 0;
		int cargoCap = 0;
		if (airship.length == 7) {
			weapons = Integer.parseInt(airship[6]);
			cargoCap = Integer.parseInt(airship[6]);
		}
		if (airship.length == 8) {
			weapons = Integer.parseInt(airship[6]);
			cargoCap = Integer.parseInt(airship[7]);
		}

		if (type.toLowerCase().equals("multiship")) {
			Airship p = new MultiShip(model, speed, range, price, energyCap, weapons, cargoCap);
			airships.add(p);
		} else if (type.toLowerCase().equals("combatship")) {
			Airship p = new CombatShip(model, speed, range, price, energyCap, weapons);
			airships.add(p);
		} else if (type.toLowerCase().equals("cargoship")) {
			Airship p = new CargoShip(model, speed, range, price, energyCap, cargoCap);
			airships.add(p);
		} else {
			Airship p = new TourShip(model, speed, range, price, energyCap);
			airships.add(p);
		}
	}

	public void removeShipFromTower(int shipChoice) {
		if (shipChoice >= 1) {
			shipChoice = shipChoice - 1;
			airships.remove(shipChoice);
		} else {
			System.out.println("There are no more ships to remove");
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DockingTower [");
		if (airships != null) {
			builder.append("airships=");
			builder.append(airships);
		}
		builder.append("]");
		return builder.toString();
	}

}
