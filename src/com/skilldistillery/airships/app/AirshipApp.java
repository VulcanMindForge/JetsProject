/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.airships.entities.Airship;
import com.skilldistillery.airships.entities.DockingTower;

public class AirshipApp {
DockingTower dockingTower;
Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		AirshipApp app = new AirshipApp();
		app.run();
	}
	
	private void run() {
		
	}
	
	private List<Airship> getShipsFromFile(String fileName){
		List<Airship> airships = new ArrayList<>();
		return airships;
	}
	
	private void displayMenu() {
		
	}
	
	private void menuSwitch(int choice) {
		
	}
	
	private void fastestShip() {
		
	}
	
	private void longestRangeShip() {
		
	}
	
	private void addNewShip() {
		
	}
	
	private void removeShip() {
		
	}
	
	private void farewell() {
		
	}
}
