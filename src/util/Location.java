package util;

public class Location {
	private int x, y;
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Location check){
		return (this.getX()==check.getX() && this.getY() == check.getY());
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
