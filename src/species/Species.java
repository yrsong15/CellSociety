package species;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public abstract class Species {
	private Neighborhood myNeighbors;

	private int currState; // 0 is initial state
	private int nextState;

	private Location myLocation;
	
	public Species(){
		myNeighbors = null;
		currState = 0;
		myLocation = null;
		
	}
	
	public abstract boolean toBreed();
	
	public abstract Species clone(Location pos);
	
	public abstract Location performTask(List<Location> emptyCells, Neighborhood neighbors);
	
	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}
	
	public void setNeighborhood(Neighborhood myneighborhood){
		this.myNeighbors = myneighborhood;
	}
	public Neighborhood getNeighborhood(){
		return this.myNeighbors;
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
	
	public void updateToLatestState(){
		setCurrState(getNextState());

	}


}