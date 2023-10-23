/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class CombatShip extends Airship implements Combat {
	private int numWeapons;

	public CombatShip(String model, double speed, int range, long price, int numWeapons) {
		super(model, speed, range, price);
		this.numWeapons = numWeapons;
	}
	
	public int getNumWeapons() {
		return numWeapons;
	}

	public void setNumWeapons(int numWeapons) {
		this.numWeapons = numWeapons;
	}

	public String getShipType() {
		String type = "CombatShip";
		return type;
	}
	
	@Override
	public void attack() {
		System.out.println("We're headed over to blow them out of the sky!");
	}

	@Override
	public void defend() {
	}

	@Override
	public void standDown() {
	}

	@Override
	public void fly() {
		double flightTime = this.getRange() / this.getSpeed();
		System.out.printf("%s \n This ship can fly for %.2f hours.\n", toString(), flightTime);
	}

	@Override
	public void dock() {
		System.out.println("To the pub while they restock the ammunition.");
	}

	@Override
	public double getSpeedInKnots() {
		double speedInKnots = 0.86898 * this.getSpeed();
		return speedInKnots;
	}

	
	
	@Override
	public String shipToFile() {
		StringBuilder build = new StringBuilder();
		build.append(getShipType());
		build.append(", ");
		build.append(getModel());
		build.append(", ");
		build.append(getSpeed());
		build.append(", ");
		build.append(getRange());
		build.append(", ");
		build.append(getPrice());
		build.append(", ");
		build.append(getNumWeapons());		
		return build.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This is a combat ship,");
		builder.append(" " + (getIsFlying() ? "and is currently in flight. " : "and is currently docked at tower. "));
		if (getModel() != null) {
			builder.append("Model is: ");
			builder.append(getModel());
			builder.append(", ");
		}
		builder.append(" units, Speed is: ");
		builder.append(String.format("%.2f", getSpeedInKnots()));
		builder.append(" knots, Range is: ");
		builder.append(getRange());
		builder.append(" miles, This ship has ");
		builder.append(getNumWeapons());
		builder.append(" weapons, Ship price is: ");
		builder.append(getPrice());
		builder.append(" dollars.");
		return builder.toString();
	}

}
