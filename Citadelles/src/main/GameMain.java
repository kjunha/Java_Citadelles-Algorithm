package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import util.Player;
import util.ScoreToken;
import util.Type;

public class GameMain {
	private static ArrayList<util.Character> jobcardset = new ArrayList<>();
	private static ArrayList<util.Player> playerlist = new ArrayList<>();
	private static ArrayList<util.Building> bcarddeck = new ArrayList<>();
	private static ArrayList<Integer>jobingame = new ArrayList<>();
	private static ScoreToken scoretk;
	private static util.Player banker;
	
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);
		//TODO register each player
		//Keep registering until meet ""
		playerlist.add(new util.Player("Player1", 1));
		playerlist.add(new util.Player("Player2", 2));
		playerlist.add(new util.Player("Player3", 3));
		playerlist.add(new util.Player("Player4", 4));
		banker = new util.Player("BANKER", -1);
		//TODO select Game Mode Original, Expansion...
		String gamemode = "original";
		setGame(gamemode);
		System.out.println("Ready to Play.");
		
		//TODO Main Game Loop, until someone call "Game Over"
		//
		//
		while(util.ScoreToken.getIsGame()) {
			//Assign a Job card (Start from Crown)
			int jobasgn = -1;
			util.Player current = null;
			for(util.Player p : playerlist) {
				if(p.callKing() == true) {
					System.out.println(p.getName() + " has Crown. Start Job selecting.");
					current = p;
					jobasgn = p.getRegistered();
					break;
				}
			}
			//Job Selection (King)
			int jobrand = 0;
			while(jobrand == 0) {
				jobrand = (int)Math.ceil(Math.random() * 8);
			}
			assignJob(banker, jobrand);
			int selection = -1;
			while (true) {
				projectAvl();
				System.out.println("Press number and press enter.");
				selection = userinput.nextInt();
				userinput.nextLine();
				int check = assignJob(current, selection);
				if(check == -1) {
					System.out.println(selection + " is not in the list. Choose again.");
				} else {
					break;
				}
			}
			jobingame.add(selection);
			
			for(int i = 0; i < playerlist.size() - 1; i++) {
				jobasgn++;
				if(jobasgn > playerlist.size()) {
					jobasgn = 1;
				}
				for(util.Player p : playerlist) {
					if(p.getRegistered() == jobasgn) {
						System.out.println(p.getName() + " will select a job.");
						current = p;
						while(true) {
							projectAvl();
							System.out.println("Press number and press enter.");
							selection = userinput.nextInt();
							userinput.nextLine();
							int check = assignJob(current, selection);
							if(check == -1) {
								System.out.println(selection + " is not in the list. Choose again.");
							} else {
								break;
							}
						}
						jobingame.add(selection);
					}
				}
			}
			/*//Job selection checking
			System.out.println("Job Selection Check");
			for(util.Player p : playerlist) {
				System.out.println(p.getJob().getInfo());
			}
			System.out.println("Done.");*/
			//**Regular Turn Sequence**
			Collections.sort(jobingame);
			int playercnt = 1;
			while(playercnt < playerlist.size()) {
				for(Integer i : jobingame) {
					for(util.Player p : playerlist) {
						if(p.getJob().getJobno() == i) {
							p.setTurn();
							System.out.println("it is " + p.getName() + "'s turn");
							System.out.println("His job is " + p.getJob().getInfo());
							
							System.out.println("Get Gold or Building Card?");
							System.out.println("Type gold or bldg");
							System.out.println("Your Command: ");
							String inp2 = userinput.nextLine();
							inp2.toLowerCase();
							switch(inp2) {
							case "gold" :
								p.giveGold(2);
								break;
							case "bldg" :
								pickBldgcard(2);
								System.out.println("Which card you would like to pick? (Type number)");
								int[] pick = new int[1]; 
								pick[0] = userinput.nextInt();
								chooseBldgcard(p, 2, pick);
								userinput.nextLine();
								break;
								default :
									System.out.println("Command is not correct.");
									break;
							}
							boolean b_avl = true;
							while(p.getTurn() == true) {
								System.out.println("Type your command: ");
								System.out.println("mystat | reqstat | build | tsum(Turn Summary) |pable(Player Ability) | tover(Turn Over)");
								String inp3 = userinput.nextLine();
								switch(inp3) {
								case "mystat":
									p.reportStat();
									break;
								case "reqstat":
									System.out.print("Search User Name: ");
									String inp4 = userinput.nextLine();
									projectStat(inp4);
									break;
								case "build":
									p.reportOnhand();
									if(b_avl == true) {
										System.out.println("Which Building you would like to construct?");
										System.out.println("Type *cancel* if you would like to build nothing.");
										String inp5 = userinput.nextLine();
										if(inp5.equals("cancel")) {
											System.out.println("");
										} else {
											boolean success = p.Construction(p.searchOnhand(inp5));
											if(success == true) {
												b_avl = false;
											}
										}
									} else {
										System.out.println("You've already built this turn.");
									}
									break;
								case "tsum":
									for(Player l : playerlist) {
										if(l.gethasPlayed() == false) {
											if(l.getName().equals(p.getName())) {
												System.out.println("You are playing now.");
											} else {
												System.out.println(l.getName() + " has not played.");
											}
										} else {
											System.out.println(l.getName() + " had already played.");
											System.out.println("  Previous job: " + l.getJob().getInfo());
										}
									}
									break;
								case "pable":
									//Player ability
									
									break;
								case "tover":
									p.callTurnOver();
									p.sethasPlayed();
									break;
									default:
										System.out.println("Command is not correct.");
										break;
								}
							}
							
							playercnt++;
						}
					}
				}
				//un-assigning all jobs
				unassignJob(banker);
				for(util.Player p : playerlist) {
					unassignJob(p);
				}
				Collections.sort(jobcardset);
			}
		}
		
		//TODO Score Count and get winner, Program Exit
		userinput.close();

		
	}
	
	private static void setGame(String m) {
		String mode = m.toLowerCase();
		switch(mode) {
			case "original":
				BuildingCardSetter bs = new BuildingCardSetter("original");
				bcarddeck = bs.exportDeck();
				
				jobcardset.add(new util.Character("Assassin", "des", 1, Type.NULL));
				jobcardset.add(new util.Character("Thief", "des", 2, Type.NULL));
				jobcardset.add(new util.Character("Magician", "des", 3, Type.NULL));
				jobcardset.add(new util.Character("King", "des", 4, Type.NOBLE));
				jobcardset.add(new util.Character("Bishop", "des", 5, Type.RELIGIOUS));
				jobcardset.add(new util.Character("Merchant", "des", 6, Type.COMMERCIAL));
				jobcardset.add(new util.Character("Architect", "des", 7, Type.NULL));
				jobcardset.add(new util.Character("Warlord", "des", 8, Type.MILITARY));
				initiateCrown();
				
				//Building Card Distribution
				for(util.Player p : playerlist) {
					chooseBldgcardall(p, 4);
				}
				
				scoretk = new ScoreToken();
				
				break;
			default:
				System.out.println("Nothing has been set.");
				break;
		}
	}
	
	private static void initiateCrown() {
		int randno = 0;
		if(playerlist.size() > 0) {
			while(randno == 0) {
				double r = Math.random() * playerlist.size();
				randno = (int)Math.ceil(r);
			}
			for(util.Player p : playerlist) {
				if(p.getRegistered() == randno) {
					p.assignKing();
					break;
				}
			}
		} else {
			System.out.println("Register the player first!");
		}
		//TODO set more player warning when 1 or 2 player.
		
	}
	/**
	 * 
	 * @param p player will assign a job
	 * @param i job number
	 * @return 1 for success, -1 for failure
	 */
	private static int assignJob(util.Player p, int i) {
		util.Character selected = null;
		for(util.Character c : jobcardset) {
			if(c.getJobno() == i) {
				selected = c;
				break;
			}
		}
		if(selected == null) {
			return -1;
		} else {
			p.setJob(selected);
			jobcardset.remove(selected);
			return 1;
		}
	}
	
	private static void unassignJob(util.Player p) {
		jobcardset.add(p.getJob());
		p.unsetJob();
		p.resethasPlayed();
	}
	
	private static void projectAvl() { //Availability
		System.out.println("Available Job card:");
		for(util.Character c : jobcardset) {
			System.out.println(c.getInfo());
		}
	}
	
	private static void projectStat(String s) {
		boolean found = false;
		for(util.Player p : playerlist) {
			if(p.getName().equals(s)) {
				p.reportStat();
				found = true;
			}
		}
		if(found == false) {
			System.out.println("No name is matching.");
		}
	}
	
	private static void pickBldgcard(int see) {
		System.out.println("Here is the top " + see + " from the Deck.");
		for(int i = 0; i < see; i++) {
			System.out.print(i + 1 + " ");
			System.out.println(bcarddeck.get(i).getInfo());
		}
	}
	
	private static void chooseBldgcardall(util.Player p, int see) {
		System.out.println("All card has been added to the deck.");
		for(int i = 0; i < see; i++) {
			p.pickBcard(bcarddeck.get(0));
			bcarddeck.remove(0);
		}
	}
	
	private static void chooseBldgcard(util.Player p, int see, int[] sel) {
		ArrayList<util.Building> temp = new ArrayList<>();
		int[] options = new int[see];
		for(int i = 0; i < see; i++) {
			options[i] = i + 1;
		}
		for(int i = 0; i < see; i++) {
			temp.add(bcarddeck.get(0));
			bcarddeck.remove(0);
		}
		for(int i : sel) {
			System.out.print(i + " " + "has been selected.");
			p.pickBcard(temp.get(i-1));
			options[i-1] = -1;
		}
		for(int i = 0; i < see; i++) {
			if(options[i] != -1) {
				bcarddeck.add(temp.get(i));
			}
		}
		temp.clear();
	}

}
