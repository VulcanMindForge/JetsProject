/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class CombatShip extends Airship implements Combat {
	private int numWeapons;

	public CombatShip(String model, double speed, int range, long price, double energyCap, int numWeapons) {
		super(model, speed, range, price, energyCap);
		this.numWeapons = numWeapons;
	}

	public int getNumWeapons() {
		return numWeapons;
	}

	public void setNumWeapons(int numWeapons) {
		this.numWeapons = numWeapons;
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
		System.out.println(toString() + " can fly for " + this.getRange() / this.getSpeed() + " hours.");
	}

	@Override
	public double recharge() {
		return 0;
	}

	@Override
	public void dock() {
	}

	@Override
	public double getSpeedInKnots() {
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CombatShip [numWeapons=");
		builder.append(numWeapons);
		builder.append(", ");
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
