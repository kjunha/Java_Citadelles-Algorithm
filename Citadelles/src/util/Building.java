package util;

public class Building {
	private int price;
	private String name;
	private Type type;
	private boolean isbuilt; //false, In deck / true, Built
	
	public Building(String s, int p, Type t) {
		this.price = p;
		this.name = s;
		this.type = t;
		isbuilt = false;
	}
	
	public int getPrice() {
		return price;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		String out = name + " " + Integer.toString(price) + " " + type.toString();
		return out;
	}
	
	public void buildBldg() {
		isbuilt = true;
	}

}
