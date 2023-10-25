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
	
	public String getShipType() {
		String type = "TourShip";
		return type;
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
		return build.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This is a tour ship,");
		builder.append(" " + (getIsFlying() ? "and is currently in flight. " : "and is currently docked at tower. "));
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
