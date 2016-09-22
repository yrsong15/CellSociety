import java.util.List;


public abstract class Species {
	private Neighborhood myNeighbors;
	private int myState; // 0 is initial state
	private Location myLocation;
	
	public Species(){
		myNeighbors = null;
		myState = 0;
		myLocation = null;
	}
	
	public void move(){
		
	}
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
	
	public void setState(int mystate){
		this.myState = mystate;
	}
	
	public int getState(){
		return this.myState;
	}
}