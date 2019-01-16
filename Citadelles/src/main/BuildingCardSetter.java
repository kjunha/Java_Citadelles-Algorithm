package main;

import java.util.ArrayList;
import java.util.Collections;

import util.Type;

public class BuildingCardSetter {
	private ArrayList<util.Building> deck = new ArrayList<>();
	
	public BuildingCardSetter(String mode) {
		mode.toLowerCase();
		switch(mode) {
		case "original" :
			setOriginal();
			shuffleDeck();
			break;
		case "expansion" :
			setExpansion();
			shuffleDeck();
			break;
		default :
			System.out.println("Mode has not set correct.");
			break;
				
		}
	}
	
	private void setOriginal() {
		addCard(5, new util.Building("Tavern", 1, Type.COMMERCIAL));
		addCard(2, new util.Building("Cathedral", 5, Type.RELIGIOUS));
		addCard(3, new util.Building("Watch Tower", 1, Type.MILITARY));
		addCard(3, new util.Building("Prison", 2, Type.MILITARY));
		addCard(3, new util.Building("Battle Field", 3, Type.MILITARY));
		addCard(2, new util.Building("Fortress", 5, Type.MILITARY));
		addCard(5, new util.Building("Town House", 3, Type.NOBLE));
		addCard(4, new util.Building("Mansion", 4, Type.NOBLE));
		addCard(3, new util.Building("Palace", 5, Type.NOBLE));
		addCard(2, new util.Building("Market", 2, Type.COMMERCIAL));
		addCard(3, new util.Building("Trading Post", 2, Type.COMMERCIAL));
		addCard(3, new util.Building("Docks", 3, Type.COMMERCIAL));
		addCard(3, new util.Building("Harbour", 4, Type.COMMERCIAL));
		addCard(2, new util.Building("City Hall", 5, Type.COMMERCIAL));
		addCard(3, new util.Building("Temple", 1, Type.RELIGIOUS));
		addCard(3, new util.Building("Church", 2, Type.RELIGIOUS));
		addCard(3, new util.Building("Monastery", 3, Type.RELIGIOUS));
		addCard(1, new util.Building("School of Magic", 6, Type.SPECIAL));
		addCard(1, new util.Building("Library", 6, Type.SPECIAL));
		addCard(1, new util.Building("Dragon Gate", 6, Type.SPECIAL));//8 points
		addCard(1, new util.Building("University", 6, Type.SPECIAL));//8 points
		addCard(1, new util.Building("Great Wall", 6, Type.SPECIAL));
		addCard(1, new util.Building("Observatory", 5, Type.SPECIAL));
		addCard(1, new util.Building("Labatory", 5, Type.SPECIAL));
		addCard(1, new util.Building("Smithy", 5, Type.SPECIAL));
		addCard(1, new util.Building("Graveyard", 5, Type.SPECIAL));
		addCard(1, new util.Building("Floating City", 2, Type.SPECIAL));
		addCard(2, new util.Building("Keep", 2, Type.SPECIAL));
	}
	
	private void setExpansion() {
		
	}
	
	//Sub-Method for set Original and set Expansion
	private void addCard(int count, util.Building b) {
		for(int c = 0; c < count; c++) {
			deck.add(b);
		}
		
	}
	
	private void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	public ArrayList<util.Building> exportDeck() {
		return deck;
	}
}
