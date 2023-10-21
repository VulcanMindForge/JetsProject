/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class CargoShip extends Airship implements Cargo {
	private int cargoCapacity;

	public CargoShip(String model, double speed, int range, long price, double energyCap, int cargoCapacity) {
		super(model, speed, range, price, energyCap);
		this.cargoCapacity = cargoCapacity;
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
