/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class MultiShip extends Airship implements Combat, Cargo {
	private int numWeapons;
	private int cargoCapacityMax;
	private int cargo;
	
	public MultiShip(String model, double speed, int range, long price, double energyCap, int numWeapons,
			int cargoCapacityMax) {
		super(model, speed, range, price, energyCap);
		this.numWeapons = numWeapons;
		this.cargoCapacityMax = cargoCapacityMax;
	}
	
	public int getCargoCapacityMax() {
		return cargoCapacityMax;
	}
	public int getNumWeapons() {
		return numWeapons;
	}
	public void setNumWeapons(int numWeapons) {
		this.numWeapons = numWeapons;
	}
	public int getCargo() {
		return cargo;
	}
	public int getCargoCapacity() {
		int cargoCapacity = cargoCapacityMax - cargo;
		return cargoCapacity;
	}
	public void setCargo(int cargoCapacity) {
		this.cargo = cargoCapacity;
	}
	@Override
	public int loadCargo(int amt) {
		int canLoad = cargoCapacityMax - cargo;
		if (amt < canLoad) {
			System.out.println("Loading " + amt + " units of cargo to cargo ships.");
			cargo = cargo + amt;
			return 0;
		} else {
			System.out.println("Loading " + canLoad + " units of cargo to multi-purpose ships.");
			cargo = cargo + canLoad;
			return amt - canLoad;
		}
	}
	@Override
	public int offloadCargo(int amt) {
		int remaining = cargo - amt;
		if (remaining > 0) {
			System.out.println("There is " + remaining + " units left in this ship.");
		} else if (remaining == 0) {
			System.out.println("We have emptied the ship");
		} else {
			System.out.println("We emptied the ship, the rest of must be pulled from another ship.");
		}
		return Math.abs(remaining);
	}
	
	@Override
	public void attack() {
		System.out.println("Manuevering to support.");
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
		builder.append("MultiShip [numWeapons=");
		builder.append(numWeapons);
		builder.append(", cargoCapacityMax=");
		builder.append(cargoCapacityMax);
		builder.append(", cargo=");
		builder.append(cargo);
		builder.append(", ");
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
