package neighborhood;

import java.util.List;

import species.Species;
import util.Cell;
import util.Grid;
import util.Location;
import util.Orientation;

public class AntNeighborhood extends Neighborhood {
	private List<Cell> forwardlocation;

	
	public AntNeighborhood(Grid mainGrid, Location currLoc) {
		super(mainGrid, currLoc);
		// TODO Auto-generated constructor stub
	}



	


	@Override
	public List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc) {
		// TODO Auto-generated method stub
		return null;
	}

}
