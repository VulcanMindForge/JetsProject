/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class MultiShip extends Airship implements Combat, Cargo {
	private int numWeapons;
	private int cargoCapacity;
	
	public MultiShip(String model, double speed, int range, long price, double energyCap, int numWeapons,
			int cargoCapacity) {
		super(model, speed, range, price, energyCap);
		this.numWeapons = numWeapons;
		this.cargoCapacity = cargoCapacity;
	}
	public int getNumWeapons() {
		return numWeapons;
	}
	public void setNumWeapons(int numWeapons) {
		this.numWeapons = numWeapons;
	}
	public int getCargoCapacity() {
		return cargoCapacity;
	}
	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	@Override
	public void loadCargo() {
	}
	@Override
	public void offloadCargo() {
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
