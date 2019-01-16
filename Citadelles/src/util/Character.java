package util;

public class Character implements Comparable<util.Character> {
	private String name;
	private String Description;
	private Player occupied;
	private Type type;
	private int jobno;
	
	public Character(String n, String des, int i, Type t) {
		this.name = n;
		this.jobno = i;
		this.type = t;
		this.Description = des;
		this.occupied = null;
	}
	
	public void setPlayer(Player p) {
		occupied = p;
	}
	
	public void unsetPlayer() {
		occupied = null;
	}
	
	public int getJobno() {
		return jobno;
	}
	
	public String getInfo() {
		String s = Integer.toString(jobno) + ". " + name;
		return s;
	}

	@Override
	public int compareTo(util.Character c) {
		return (this.jobno - c.getJobno());
	}
}
