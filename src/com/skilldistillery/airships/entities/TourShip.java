/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class TourShip extends Airship {

	public TourShip(String model, double speed, int range, long price, double energyCap) {
		super(model, speed, range, price, energyCap);
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
		builder.append("TourShip [");
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
