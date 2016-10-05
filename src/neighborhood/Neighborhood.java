package neighborhood;
import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import species.Species;
import util.Grid;
import util.Location;
import util.Orientation;


/***
 * @author Chalena Scholl, Owen Chung
 */
public abstract class Neighborhood {
	private List<Cell> myNeighbors;

	public Neighborhood(Grid mainGrid, Location currLoc){
		setMyNeighbors(findMyNeighbors(mainGrid, currLoc));
	}
	/**
	 * returns neighbors of currLoc
	 * allows different definitions of neighbors
	 * @param mainGrid
	 * @param currLoc
	 * @return
	 */
	public abstract List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc);
	/**
	 * find the neighbors of the same
	 * useful for segregation for example
	 * @param state :state of a species
	 * @return list of locations
	 */
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
	/**
	 * returns neighbor cells with space
	 * @return
	 */
	public List<Cell> findNeighborsWithSpace(){
		List<Cell> haveSpace = new ArrayList<Cell>();
		for (Cell currCell : myNeighbors){
			if (currCell.hasFreeSpace()){
				haveSpace.add(currCell);
			}
		}
		return haveSpace;
	}
	/**
	 * returns number of neighbors within
	 * the neighborhood
	 * @return 
	 */
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
