package neighborhood;
import java.util.List;

import util.Cell;
import util.Grid;
import util.Location;

public abstract class Neighborhood {
	private List<Cell> myNeighbors;

	public Neighborhood(Grid mainGrid, Location currLoc){
		setMyNeighbors(findMyNeighbors(mainGrid, currLoc));
	}
	
	public abstract List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc);
	
	public void setMyNeighbors(List<Cell> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}
	
	public List<Cell> getMyNeighbors() { 
		return myNeighbors;
	}

}
