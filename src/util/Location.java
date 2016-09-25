package util;

import java.util.ArrayList;
import java.util.List;

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
	
	//could add an isDiagonal method, this only checks for plus type adjacent
	public boolean isAdjacent(Location pos){
		if ((this.getX() == pos.getX()) && ((this.getY() == (pos.getY()-1)) || (this.getY() == (pos.getY()+1)))){
			return true;	
		}
		else if ((this.getY() == pos.getY()) && ((this.getX() == (pos.getX()-1)) || this.getX() == (pos.getX()+1))){
				return true;
		}
		return false;
	}
	
	public List<Location> getAdjacentCells(List<Location> possible){
		List<Location> adjacent = new ArrayList<Location>();
		for (int i = 0; i < possible.size(); i++){
			Location otherLoc = possible.get(i);
			if (this.isAdjacent(otherLoc)){
				adjacent.add(possible.get(i));
			}
		}
		return adjacent;
	}
}
