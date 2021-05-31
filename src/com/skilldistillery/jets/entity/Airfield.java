package com.skilldistillery.jets.entity;
import java.util.ArrayList;
import java.util.List;

//My air field to get and set jets in a list
public class AirField {
	private List<Jet> jets;

	public AirField() {
		jets = new ArrayList<>();
	}

	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}
}
