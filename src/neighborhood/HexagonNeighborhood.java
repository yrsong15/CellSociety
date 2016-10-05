package neighborhood;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import util.Grid;
import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
 */
public class HexagonNeighborhood extends PlusNeighborhood{

	
	public HexagonNeighborhood(Grid mainGrid, Location currLoc) {
		super(mainGrid, currLoc);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc) {
		List<Cell> ret = getPlusNeighbors(mainGrid, currLoc);
		getOtherNeighbors(ret, mainGrid, currLoc);
		return ret;
	}
	/**
	 * get the neighbors other than plus neighbors
	 * because essentially hexagon neighbors are just 
	 * plus neighbors and two other locations
	 * @param toAdd plus neighbors (E,S,W,N)
	 * @param mainGrid 
	 * @param currLoc
	 */
	private void getOtherNeighbors(List<Cell> toAdd, Grid mainGrid, Location currLoc) {
		// TODO Auto-generated method stub
		int currX = currLoc.getX();
		int currY = currLoc.getY();
		if (mainGrid.isValidCell(currX - 1, currY -1)){
			toAdd.add(mainGrid.getCell(new Location(currX, currY - 1)));
		}
		if (mainGrid.isValidCell(currX + 1, currY -1)){
			toAdd.add(mainGrid.getCell(new Location(currX, currY - 1)));
		}
	}




}
