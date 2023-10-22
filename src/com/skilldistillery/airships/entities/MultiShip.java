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

	public MultiShip(String model, double speed, int range, long price, int numWeapons, int cargoCapacityMax) {
		super(model, speed, range, price);
		this.numWeapons = numWeapons;
		this.cargoCapacityMax = cargoCapacityMax;
		this.cargo = 0;
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
		double flightTime = this.getRange() / this.getSpeed();
		System.out.printf("%s \n This ship can fly for %.2f hours.\n", toString(), flightTime);
	}

	@Override
	public void dock() {
		System.out.println("Coming in to repair, restock, reload, and recharge!");
	}

	@Override
	public double getSpeedInKnots() {
		double speedInKnots = 0.86898 * this.getSpeed();
		return speedInKnots;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This is a general purpose ship: \n");
		if (getModel() != null) {
			builder.append("Model is: ");
			builder.append(getModel());
			builder.append(", ");
		}
		builder.append("Maximum cargo space is: ");
		builder.append(getCargoCapacityMax());
		builder.append(" units, Current used cargo space is: ");
		builder.append(getCargo());
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
