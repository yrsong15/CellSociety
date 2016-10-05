package species;
import java.util.List;

import cells.Cell;
import neighborhood.Neighborhood;
import util.Location;

/***
 * @author  Owen Chung, Chalena Scholl
 */

public abstract class Species {

	private int currState; // 0 is initial state
	private int nextState;

	private Location currLocation;
	private Location nextLocation;


	public Species(){
		currState = 0;
		currLocation = null;
		
	}
	public Species(Location currLoc){
		currState = 0;
		currLocation = currLoc;
		nextLocation = currLoc;
	}
	/**
	 * whether the species should breed
	 * @return
	 */
	public abstract boolean toBreed();
	/**
	 * clone the species at location pos
	 * useful if want to breed
	 * @param pos
	 * @return
	 */
	public abstract Species clone(Location pos);

	public abstract boolean isPrey();
	
	public abstract boolean isPredator();
	/**
	 * update next location of the species
	 * @param availableCells cells that the species could move to
	 * @param neighbors neighborhood the species in 
	 * @param currCell current cell species in
	 */
	public abstract void updateNextLocation(List<Location> availableCells, Neighborhood neighbors, Cell currCell);
	
	public Location getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(Location myLocation) {
		this.currLocation = myLocation;
	}
	
	public Location getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(Location nextLocation) {
		this.nextLocation = nextLocation;
	}
	
	public void setCurrState(int mystate){
		this.currState = mystate;
	}
	
	public int getCurrState(){
		return this.currState;
	}
	
	public void setNextState(int mystate){
		this.nextState = mystate;
	}
	
	public int getNextState(){
		return this.nextState;
	}
}