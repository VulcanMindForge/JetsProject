/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

import java.util.List;

public class DockingTower {
	private List<Airship> airships;
	Airship sentShip;
	Airship removedShip;

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
	
	public Airship getSingleShip (int index) {
		return airships.get(index - 1);
	}
	
	public boolean getIsDocked() {
		boolean allDocked = true;
		
		for (Airship airship : airships) {
			allDocked = !airship.getIsFlying();
		}
		return allDocked;
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

	public void flyAllDocked() {
		for (Airship airship : airships) {
			if (!airship.getIsFlying()) {
				airship.setFlying(true);
				airship.fly();
				System.out.println();
			} 
		}
	}

	public void dockAllFlying() {
		for (Airship airship : airships) {
			if (airship.getIsFlying()) {
				airship.setFlying(false);
				airship.dock();
			}
		}
	}

	public void displayFlightStatus() {
		for (Airship airship : airships) {
			System.out.println(airship.getModel() + (airship.getIsFlying() ? " is in flight." : " is still docked."));
		}
	}

	public void addShipToTower(Airship airship) {
		airships.add(airship);
	}

	public void removeShipFromTower(Airship shipChoice) {
		System.out.println(shipChoice + " removed from fleet.");
		int indexToRemove = airships.indexOf(shipChoice);
		removedShip = airships.remove(indexToRemove);		
	}

	public void sendSingleShipFromTower(Airship shipChoice) {
		System.out.println(shipChoice + " headed out on patrol.");
		int indexToRemove = airships.indexOf(shipChoice);
		sentShip = airships.remove(indexToRemove);		
	}

	public void returnRemovedShipToTower() {
		System.out.println(removedShip + " removal being reversed.");
		airships.add(removedShip);
	}
	
	public void returnSingleShipFromTower() {
		System.out.println(sentShip + " returning to tower.");
		airships.add(sentShip);
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