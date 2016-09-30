package util;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Owen
 */

public class Location {
	private int x, y;
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * 
	 * @param check Location to compare with
	 * @return whether or not given location has the same x,y coordinates
	 */
	public boolean equals(Location check){
		return (this.getX()==check.getX() && this.getY() == check.getY());
	}
	
	
	/**
	 * @return X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x sets X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	
	/**
	 * @return Y coordinate
	 */
	public int getY() {
		return y;
	}

	
	/**
	 * @param y sets Y coordinates
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * @param pos Location to compare with
	 * @return whether or not given location is adjacent (up, right, left, or down)
	 * To-Do: could add functionality to allow for diagonals to be considering adjacent
	 */
	public boolean isAdjacent(Location pos){
		if ((this.getX() == pos.getX()) && ((this.getY() == (pos.getY()-1)) || (this.getY() == (pos.getY()+1)))){
			return true;	
		}
		else if ((this.getY() == pos.getY()) && ((this.getX() == (pos.getX()-1)) || this.getX() == (pos.getX()+1))){
				return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param possible list of locations
	 * @return a list of locations that are adjacent (up, right, left, down)
	 */
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
