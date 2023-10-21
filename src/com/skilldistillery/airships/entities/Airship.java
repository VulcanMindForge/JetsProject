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
	private double energyCap;
	
	public Airship(String model, double speed, int range, long price, double energyCap) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.energyCap = energyCap;
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



	public double getEnergyCap() {
		return energyCap;
	}



	public abstract void fly();
	public abstract double recharge();
	public abstract void dock();
	public abstract double getSpeedInKnots();
}
