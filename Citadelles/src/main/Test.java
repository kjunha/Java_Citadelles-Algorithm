package main;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		BuildingCardSetter s = new BuildingCardSetter("original");
		ArrayList<util.Building> bdeck = new ArrayList<>();
		bdeck = s.exportDeck();
		for(util.Building b : bdeck) {
			System.out.println(b.getInfo());
		}
	}

}
