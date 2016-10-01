package neighborhood;
import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Cell;
import util.Grid;
import util.Location;
import util.Orientation;

/***
 * @author Owen
 */

/***
 * @author Chalena Scholl, Owen Chung
 */
public abstract class Neighborhood {
	private List<Cell> myNeighbors;

	public Neighborhood(Grid mainGrid, Location currLoc){
		setMyNeighbors(findMyNeighbors(mainGrid, currLoc));
	}
	
	public abstract List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc);
	
	public List<Location> findNeighborsOfState(int state){
		List<Location> matches = new ArrayList<Location>();
		for (Cell currCell: myNeighbors){
			for (Species currSpecies : currCell.getOccupants()){
				if (currSpecies.getCurrState() == state){
					matches.add(currSpecies.getCurrLocation());
				}

			}
		
		}
		return matches;

	}
	
	public int getTotalNeighbors(){
		int total = 0;
		for (Cell currCell : myNeighbors){
			total += currCell.getSize();
		}
		return total;
	}
	
	public void setMyNeighbors(List<Cell> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}
	
	public List<Cell> getMyNeighbors() { 
		return myNeighbors;
	}

}
