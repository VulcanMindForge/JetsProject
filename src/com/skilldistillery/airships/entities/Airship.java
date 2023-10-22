/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public abstract class Airship {
	private String model;
	private double speed;
	private int range;
	private long price;
	private boolean isFlying;

	public Airship(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public double getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public long getPrice() {
		return price;
	}

	public boolean getIsFlying() {
		return isFlying;
	}

	public abstract void fly();

	public abstract void dock();

	public abstract double getSpeedInKnots();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Airship [");
		if (model != null) {
			builder.append("model=");
			builder.append(model);
			builder.append(", ");
		}
		builder.append("speed=");
		builder.append(speed);
		builder.append(", range=");
		builder.append(range);
		builder.append(", price=");
		builder.append(price);
		builder.append(", isFlying=");
		builder.append(isFlying);
		builder.append("]");
		return builder.toString();
	}

}
