package com.skilldistillery.jets.app;

import com.skilldistillery.jets.entity.AirField;
import com.skilldistillery.jets.entity.CargoCarrier;
import com.skilldistillery.jets.entity.CargoPlane;
import com.skilldistillery.jets.entity.CombatReady;
import com.skilldistillery.jets.entity.FighterJet;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.JetImpl;
import java.io.*;
import java.util.*;


public class JetsApplication {
	AirField base;
	Scanner kb;

	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();
		jetApp.launch();
	}

	public JetsApplication() {
		base = new AirField();
		kb = new Scanner(System.in);
	}
	
	
	//launch makes use of parsing so that way I can have the correct data type after conversion
	public void launch() {
		List<String[]> jetsInformation = parseJets();

		base.getJets().add(new FighterJet(jetsInformation.get(0)[0], Double.parseDouble(jetsInformation.get(0)[1]),
			Integer.parseInt(jetsInformation.get(0)[2]), Long.parseLong(jetsInformation.get(0)[3])));
		base.getJets().add(new FighterJet(jetsInformation.get(1)[0], Double.parseDouble(jetsInformation.get(1)[1]),
			Integer.parseInt(jetsInformation.get(1)[2]), Long.parseLong(jetsInformation.get(1)[3])));
		base.getJets().add(new CargoPlane(jetsInformation.get(2)[0], Double.parseDouble(jetsInformation.get(2)[1]),
			Integer.parseInt(jetsInformation.get(2)[2]), Long.parseLong(jetsInformation.get(2)[3])));
		base.getJets().add(new CargoPlane(jetsInformation.get(3)[0], Double.parseDouble(jetsInformation.get(3)[1]),
			Integer.parseInt(jetsInformation.get(3)[2]), Long.parseLong(jetsInformation.get(3)[3])));
		base.getJets().add(new JetImpl(jetsInformation.get(4)[0], Double.parseDouble(jetsInformation.get(4)[1]),
			Integer.parseInt(jetsInformation.get(4)[2]), Long.parseLong(jetsInformation.get(4)[3])));

		
		//loop to keep menu open unless user quits
		char option = '0';
		while(option != '9'){
			option = dispayUserMenu();
			processChoice(option);
		}
	}
		
	
		//user menu display
	public char dispayUserMenu() {
		System.out.println("\n1. List The Fleet");
		System.out.println("2. Fly all jets in fleet");
		System.out.println("3. View the fastest jet in the fleet");
		System.out.println("4. View the jet with longest range(that can fly the farthest");
		System.out.println("5. Load all of the cargo jets");
		System.out.println("6. Lets rumble and attack!");
		System.out.println("7. Add a jet to the fleet");
		System.out.println("8. Remove a jet from the fleet");
		System.out.println("9. Exit\n");
		System.out.print("Option: ");
		return kb.next().charAt(0);
	}
	
	//switch to contain the user input and show their choice
	public void processChoice(char c){
		switch(c){
			case '1': listFleet();
				break;
			case '2': fly();
				break;
			case '3': viewFastestJet();
				break;
			case '4': viewLongestRange();
				break;
			case '5': loadAllCargo();
				break;
			case '6': letsRumble();
				break;
			case '7': addJet();
				break;
			case '8': scrapJet();
				break;
			case '9':
				System.out.println("Thanks for flying!");
				kb.close();
				break;
			default:
				System.out.println("Not a valid option\n\n");
		}
	}

	public List<String[]> parseJets() {
		List<String[]> jetsInformation = new ArrayList<>();

		try(BufferedReader buffReader = new BufferedReader(new FileReader("jets.txt"))){
			String jet;

			while((jet = buffReader.readLine()) != null) {
				String[] jetAttributess= jet.split(",");
				jetsInformation.add(jetAttributess);
			}

		} catch (IOException e) {
			System.err.println("File not found");
		}

		return jetsInformation;
	}

	public void listFleet() {
		List<Jet> jets = base.getJets();
		for(Jet jet: jets) {
			System.out.println(jet);
		}
	}

	public void fly() {
		for(Jet jet : base.getJets()){
			System.out.println(jet);
			jet.fly();
		}
	}

	public void viewFastestJet() {
		Jet fastest = null;
		for(Jet jet: base.getJets()){
			if (fastest == null) {
				fastest = jet;
			} else if (jet.getSpeed() > fastest.getSpeed()){
				fastest = jet;
			}
		}
		System.out.println("The fastest jet is: " + fastest);
	}

	public void viewLongestRange() {
		Jet longestRange = null;
		for(Jet jet: base.getJets()) {
			if(longestRange == null) {
				longestRange = jet;
			} else if (jet.getRange() > longestRange.getRange()) {
				longestRange = jet;
			}
		}
		System.out.println("The jet with the longest range is: " + longestRange);
	}

	public void loadAllCargo(){
		for(Jet jet : base.getJets()){
			if(jet instanceof CargoCarrier){
				((CargoCarrier) jet).loadCargo();
			}
		}
	}

	public void letsRumble(){
		boolean initialJet = true;
		int fighters = 0;
		for(Jet jet : base.getJets()){
			if(jet instanceof CombatReady){
				((CombatReady) jet).attack(initialJet);
				fighters ++;
				initialJet = false;
			}
		}
		if (fighters == 1){
			System.out.println("Fighters out of commission...");
		} else if (fighters == 0){
			System.out.println("There are no remaining jets with combat skills");
		}
	}

	public void addJet(){
		try {
			System.out.print("Enter the jets model: ");
			String model = kb.next();
			System.out.print("Enter the jets speed: ");
			double speed = kb.nextDouble();
			System.out.print("Enter the jets range: ");
			int range = kb.nextInt();
			System.out.print("Enter the jets price: ");
			long price = kb.nextLong();

			base.getJets().add(new JetImpl(model, speed, range, price));
			System.out.println("\nNew Jet Added\n");
		} catch (InputMismatchException e){
			System.out.println("Input could not be recognized");
			kb.nextLine();
		}
	}

	public void scrapJet(){
		List<Jet> jets = base.getJets();

		for(int i = 0; i < jets.size(); i++){
			System.out.println(i + 1 + ". " + jets.get(i));
		}

		System.out.print("\nSelect the number of the jets you would like to scrap: ");
		try{
			int choice = kb.nextInt();
			if(choice < jets.size() + 1 && choice > 0){
				System.out.println("\n" + jets.get(choice - 1).getModel() + " scrapped and placed in the junk yard\n");
				jets.remove(choice - 1);
			} else{
				System.out.println("Not a valid option");
			}

		} catch (InputMismatchException e){
			System.out.println("Input could not be recognized");
			kb.nextLine();
		}
	}
}
