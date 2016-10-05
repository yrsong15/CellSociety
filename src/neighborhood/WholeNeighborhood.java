package neighborhood;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import util.Grid;
import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
 */
public class WholeNeighborhood extends Neighborhood{
	
	public WholeNeighborhood(Grid mainGrid, Location currLoc) {
		super(mainGrid, currLoc);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc) {
		int row = currLoc.getX();
		int col = currLoc.getY();
	    List<Cell> neighbors = new ArrayList<>();
	    for( int changeRow = -1; changeRow <= 1; ++changeRow) {
	        for( int changeCol = -1; changeCol <= 1; ++changeCol) {
	            if( changeRow == 0 && changeCol == 0 ) {continue; }         
            	int newRow = changeRow + row;
	            int newCol = changeCol + col;
	            Location newLoc = new Location(newRow, newCol);
	            if(mainGrid.isValidCell(newRow, newCol)) {
	            	neighbors.add(mainGrid.getCell(newLoc));
	            }
	        }
	    }
	    return neighbors;
	}
}
