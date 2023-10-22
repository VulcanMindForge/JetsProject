/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class TourShip extends Airship {

	public TourShip(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void fly() {
		double flightTime = this.getRange() / this.getSpeed();
		System.out.printf("%s \n This ship can fly for %.2f hours.\n", toString(), flightTime);
	}

	@Override
	public void dock() {
		System.out.println("We hope you enjoyed the tour. Next one is leaving in 30 minutes.");
	}

	@Override
	public double getSpeedInKnots() {
		double speedInKnots = 0.86898 * this.getSpeed();
		return speedInKnots;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This is a tour ship: \n");
		if (getModel() != null) {
			builder.append("Model is: ");
			builder.append(getModel());
			builder.append(", ");
		}
		builder.append("Speed is: ");
		builder.append(String.format("%.2f", getSpeedInKnots()));
		builder.append(" knots, Range is: ");
		builder.append(getRange());
		builder.append(" miles, Ship price is: ");
		builder.append(getPrice());
		builder.append(" dollars.");
		return builder.toString();
	}

}
