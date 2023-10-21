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
	}

	@Override
	public void defend() {
	}

	@Override
	public void standDown() {
	}

	@Override
	public void fly() {
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
	
}
