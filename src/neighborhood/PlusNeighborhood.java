package neighborhood;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import util.Grid;
import util.Location;


/***
 * @author Chalena Scholl, Owen Chung
 */
public class PlusNeighborhood extends Neighborhood {

	public PlusNeighborhood(Grid mainGrid, Location currLoc) {
		super(mainGrid, currLoc);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc) {
		List<Cell> ret = getPlusNeighbors(mainGrid, currLoc);
		return ret;
	}
	/**
	 * returns plus neighbors
	 * @param mainGrid
	 * @param currLoc
	 * @return
	 */
	protected List<Cell> getPlusNeighbors(Grid mainGrid, Location currLoc) {
		List<Cell> possibleneighbors = new ArrayList<Cell>();
		int currX = currLoc.getX();
		int currY = currLoc.getY();
		if (mainGrid.isValidCell(currX-1, currY)){
			possibleneighbors.add(mainGrid.getCell(new Location(currX-1, currY)));
		}
		if (mainGrid.isValidCell(currX+1, currY)){
			possibleneighbors.add(mainGrid.getCell(new Location(currX+1, currY)));
		}
		if (mainGrid.isValidCell(currX, currY-1)){
			possibleneighbors.add(mainGrid.getCell(new Location(currX, currY-1)));
		}
		if (mainGrid.isValidCell(currX, currY+1)){
			possibleneighbors.add(mainGrid.getCell(new Location(currX, currY+1)));
		}
		return possibleneighbors;
	}


}
