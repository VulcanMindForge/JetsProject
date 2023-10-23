/*
Synopsis:
Author:
Version: 
 */
package com.skilldistillery.airships.entities;

public class Pilot implements Combat, Cargo{
	private String name;
	private String callSign;
	private String combatRank;
	
	public Pilot (String name, String callSign, String combatRank) {
		this.name = name;
		this.callSign = callSign;
		this.combatRank = combatRank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCallSign() {
		return callSign;
	}

	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}

	public String getCombatRank() {
		return combatRank;
	}

	public void setCombatRank(String combatRank) {
		this.combatRank = combatRank;
	}

	@Override
	public int loadCargo(int amt) {
		System.out.println("Loading " + amt + " units of cargo, sir.");
		return amt;
	}

	@Override
	public int offloadCargo(int amt) {
		System.out.println("Dropping off " + amt + " units of cargo, sir.");
		return amt;
	}

	@Override
	public void attack() {
		System.out.println("Going on the Offensive, sir");
	}

	@Override
	public void defend() {
	}

	@Override
	public void standDown() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pilot");
		if (name != null) {
			builder.append(": ");
			builder.append(name);
			builder.append(", ");
		}
		if (callSign != null) {
			builder.append("Call sign: ");
			builder.append(callSign);
			builder.append(", ");
		}
		if (combatRank != null) {
			builder.append("Combat rank: ");
			builder.append(combatRank);
		}
		builder.append(".");
		return builder.toString();
	}
	
	

}
