package neighborhood;

import java.util.ArrayList;
import java.util.List;
import util.Cell;
import util.Grid;
import util.Location;

public class HexagonNeighborhood extends Neighborhood{

	
	public HexagonNeighborhood(Grid mainGrid, Location currLoc) {
		super(mainGrid, currLoc);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Cell> findMyNeighbors(Grid mainGrid, Location currLoc) {
		List<Cell> ret = new ArrayList<Cell>();
		ret.add(mainGrid.getCell(new Location(currLoc.getX()-1, currLoc.getY())));
		ret.add(mainGrid.getCell(new Location(currLoc.getX()+1, currLoc.getY())));
		ret.add(mainGrid.getCell(new Location(currLoc.getX(), currLoc.getY()-1)));
		ret.add(mainGrid.getCell(new Location(currLoc.getX(), currLoc.getY()+1)));
		ret.add(mainGrid.getCell(new Location(currLoc.getX()-1, currLoc.getY()-1)));
		ret.add(mainGrid.getCell(new Location(currLoc.getX()+1, currLoc.getY()-1)));
		
		for (int i = 0; i < ret.size(); i++){
			if (!ret.get(i).hasOccupants() || ret.get(i) == null){
				ret.remove(i);
			}
		}
		return ret;
	}


}
