package util;

import java.util.ArrayList;

public class Player {
	private final int REGISTERED;
	private int turn;
	private boolean isturn;
	private boolean isking;
	private String name;
	private Character job;
	private int gold;
	private ArrayList<Building> onhand;
	private ArrayList<Building> built;
	private boolean hasplayed;
	
	public Player(String n, int reg) {
		this.name = n;
		this.REGISTERED = reg;
		gold = 2;
		onhand = new ArrayList<>();
		built = new ArrayList<>();
		hasplayed = false;
	}
	
	public void assignKing() {
		isking = true;
	}
	
	public void unassignKing() {
		isking = false;
	}
	
	public boolean callKing() {
		return isking;
	}
	
	public void setTurn() {
		isturn = true;
	}
	
	public void setJob(Character c) {
		job = c;
		c.setPlayer(this);
	}
	
	public boolean gethasPlayed() {
		return hasplayed;
	}
	
	public void sethasPlayed() {
		hasplayed = true;
	}
	
	public void resethasPlayed() {
		hasplayed = false;
	}
	
	public util.Character getJob() {
		return job;
	}
	
	public void unsetJob() {
		job.unsetPlayer();
		job = null;
	}
	
	public int getRegistered() {
		return REGISTERED;
	}
	
	public String getName() {
		return name;
	}
	
	public void giveGold(int i) {
		gold = gold + i;
	}
	
	public void pickBcard(Building b) {
		onhand.add(b);
	}
	//if construction was successful, return true.
	public boolean Construction(Building b) {
		if(onhand.indexOf(b) != -1 && built.indexOf(b) == -1) {
			if(b.getPrice() <= this.gold) {
				built.add(b);
				this.giveGold(-b.getPrice());
				System.out.println("You have built " + b.getName());
				onhand.remove(b);
				return true;
			} else {
				System.out.println("You have not enough money.");
				return false;
			}
		} else {
			System.out.println("This building cannot be built.");
			return false;
		}
	}
	
	public void callGameOver() {
		if(built.size() <= 7) {
			ScoreToken.callGameOver(this);
		}
	}
	
	public boolean getTurn() {
		return isturn;
	}
	
	public boolean callTurnOver() {
		isturn = false;
		return isturn;
	}
	public void reportOnhand() {
		for(Building b : onhand) {
			System.out.println(b.getInfo());
		}
	}
	public Building searchOnhand(String s) {
		for(Building b : onhand) {
			if(b.getName().equals(s)) {
				return b;
			}
		}
		System.out.println("No name is matching.");
		return null;
	}
	public void reportStat() {
		System.out.println("Player: " + this.name);
		System.out.println("Is King?: " + this.isking);
		System.out.println("Gold: " + this.gold);
		System.out.println("B.Card on hand: " + this.onhand.size());
		System.out.println("Building List: ");
		for(Building b : built) {
			System.out.println(b.getInfo());
		}
	}
}
