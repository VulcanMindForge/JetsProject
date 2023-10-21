/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

import java.util.List;

public class DockingTower {
	private List<Airship> airships;
	private final int maxBerths;
	
	public DockingTower(List<Airship> airships, int maxBerths) {
		super();
		this.airships = airships;
		this.maxBerths = maxBerths;
	}
	
	
	public List<Airship> getAirships() {
		return airships;
	}



	public void setAirships(List<Airship> airships) {
		this.airships = airships;
	}



	public void flyAll(List<Airship> airships) {
		
	}
	
	public void dockAll(List<Airship> airships) {
		
	}
	
	public void displayFlying(List<Airship> airships) {
		
	}
	
	public void displayDocked(List<Airship> airships) {
		
	}
	
	public void rechargeShips(List<Airship> airships) {
		
	}
	
}
