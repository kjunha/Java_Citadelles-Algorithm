package util;

import java.util.ArrayList;

public class ScoreToken {
	private static Player gameover;
	private static ArrayList<Player> alltype;
	private static ArrayList<Player> otherfinisher;
	private static boolean isgame;
	
	public ScoreToken() {
		isgame = true;
		alltype = new ArrayList<>();
		otherfinisher = new ArrayList<>();
		gameover = null;
	}
	
	public static void callGameOver(Player p) {
		gameover = p;
		isgame = false;
	}
	
	public static boolean getIsGame() {
		return isgame;
	}
	
}
