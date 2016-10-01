package neighborhood;

import java.util.ArrayList;
import java.util.List;
import util.Cell;
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
		List<Cell> ret = new ArrayList<Cell>();
		int currX = currLoc.getX();
		int currY = currLoc.getY();
		if (mainGrid.isValidCell(currX-1, currY)){
			ret.add(mainGrid.getCell(new Location(currX-1, currY)));
		}
		if (mainGrid.isValidCell(currX+1, currY)){
			ret.add(mainGrid.getCell(new Location(currX+1, currY)));
		}
		if (mainGrid.isValidCell(currX, currY-1)){
			ret.add(mainGrid.getCell(new Location(currX, currY-1)));
		}
		if (mainGrid.isValidCell(currX, currY+1)){
			ret.add(mainGrid.getCell(new Location(currX, currY+1)));
		}
		for (int i = 0; i < ret.size(); i++){
			if (!ret.get(i).hasOccupants()){
				ret.remove(i);

			}
		}
		return ret;
	}

}
