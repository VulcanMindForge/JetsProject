/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

import java.util.List;

public class DockingTower {
	private List<Airship> airships;
	boolean isDocked = true;

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

	public boolean getIsDocked() {
		return isDocked;
	}

	public void flyAll() {
		for (Airship airship : airships) {
			airship.fly();
			System.out.println();
		}
		isDocked = false;
	}

	public void dockAll() {
		for (Airship airship : airships) {
			airship.dock();
		}
		isDocked = true;
	}

	public void displayFlightStatus() {
		for (Airship airship : airships) {
			System.out.println(airship.getModel() + (airship.getIsFlying() ? " is in flight." : " is still docked."));
		}
	}

	public void addShipToTower(Airship airship) {
		airships.add(airship);
	}

	public void removeShipFromTower(int shipChoice) {
		if (shipChoice > 0 && shipChoice <= airships.size()) {
			airships.remove(shipChoice - 1);
		} else {
			System.out.println("Invalid choice. No ships to remove.");
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