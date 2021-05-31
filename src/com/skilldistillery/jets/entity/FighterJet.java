package com.skilldistillery.jets.entity;

//this class was created to inherit characteristics of jet
//and implement combat ready to eliminate redundancy

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void attack(boolean startAttack) {
		if (startAttack){
			System.out.println(this.model + " has begun its attack!");
		} else {
			System.out.println(this.model + " has joined the battle!");
		}
	}
}