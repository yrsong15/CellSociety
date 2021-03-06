package neighborhood;
import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import species.Species;
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
	
	public List<Cell> findNeighborsWithSpace(){
		List<Cell> haveSpace = new ArrayList<Cell>();
		for (Cell currCell : myNeighbors){
			if (currCell.hasFreeSpace()){
				haveSpace.add(currCell);
			}
		}
		return haveSpace;
	}

	protected void removeEmptyCells(List<Cell> cellstocheck) {
		for (int i = 0; i < cellstocheck.size(); i++){
			if (!cellstocheck.get(i).hasOccupants()){
				cellstocheck.remove(i);

			}
		}
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
