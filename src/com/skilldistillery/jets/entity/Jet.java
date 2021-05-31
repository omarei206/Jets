package com.skilldistillery.jets.entity;


//Abstract Jet class to get and set jet attributes

public abstract class Jet {
	//fields
	
	protected String model;
	protected double speed;
	protected int range;
	protected long price;

	//jet constructor
	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	//fly method to check if a jet is available to fly
	public void fly() {
		if (this.range == 0 || this.speed == 0){
			System.out.println("\tThis jet is down for the count");
		} else{
			double flightTime = this.range / this.speed;
			System.out.println("\tThis jets max flight time is: " + flightTime + " hours.");
		}
	}
	
	
	//getter and setter methods
	public double getSpeedInMach() {
		return 0;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	//toString to display jet data
	@Override
	public String toString() {
		return "Jet information [jet model=" + model + ", jet speed=" + speed + "mph , jet range=" + range + "miles until empty , jet price=" + price + "]";
	}
}
