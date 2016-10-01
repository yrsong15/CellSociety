package species;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
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
	}
	
	public abstract boolean toBreed();
	
	public abstract Species clone(Location pos);
	
	public abstract boolean isPrey();
	
	public abstract boolean isPredator();
	
	public abstract void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors);
	
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