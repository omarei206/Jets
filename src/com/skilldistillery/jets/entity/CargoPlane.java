package com.skilldistillery.jets.entity;

//this class was created to inherit characteristics of jet
//and implement cargo carrier to eliminate redundancy

public class CargoPlane extends Jet implements CargoCarrier{

	public CargoPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void loadCargo() {
		System.out.println("All cargo ready and loaded for " + this.model);
	}
}
