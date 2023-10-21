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
