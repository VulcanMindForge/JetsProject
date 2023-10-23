/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class CargoShip extends Airship implements Cargo {
	private int cargoCapacityMax;
	private int cargo;

	public CargoShip(String model, double speed, int range, long price, int cargoCapacity) {
		super(model, speed, range, price);
		this.cargoCapacityMax = cargoCapacity;
		this.cargo = 0;
	}

	public int getCargoCapacityMax() {
		return cargoCapacityMax;
	}

	public int getCargo() {
		return cargo;
	}

	public int getCargoCapacity() {
		int cargoCapacity = cargoCapacityMax - cargo;
		return cargoCapacity;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	
	public String getShipType() {
		String type = "CargoShip";
		return type;
	}

	@Override
	public int loadCargo(int amt) {
		int canLoad = cargoCapacityMax - cargo;
		if (amt < canLoad) {
			System.out.println("Loading " + amt + " units of cargo to Cargo ship.");
			cargo = cargo + amt;
			return 0;
		} else {
			System.out.println("Loading " + canLoad + " units of cargo to Cargo ship.");
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
	public void fly() {
		double flightTime = this.getRange() / this.getSpeed();
		System.out.printf("%s \n This ship can fly for %.2f hours.\n", toString(), flightTime);
	}

	@Override
	public void dock() {
		System.out.println("Prepare to unload all cargo. Please make space.");
	}

	@Override
	public double getSpeedInKnots() {
		double speedInKnots =  0.86898 * this.getSpeed();
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
		build.append(getCargoCapacityMax());		
		return build.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This is a cargo ship,");
		builder.append(" " + (getIsFlying() ? "and is currently in flight. " : "and is currently docked at tower. "));
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
		builder.append(" miles, Ship price is: ");
		builder.append(getPrice());
		builder.append(" dollars.");
		return builder.toString();
	}

}
