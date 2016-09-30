package neighborhood;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;
import util.Orientation;

/***
 * @author Owen
 */

public class PlusNeighborhood extends Neighborhood {

	public PlusNeighborhood(List<List<Species>> neighborhood, Location location, Orientation orientation) {

		super(neighborhood, location, orientation);

		// TODO Auto-generated constructor stub
	}
	//to-do: implement using isAdjacent function in location
	@Override
	public List<List<Species>> findMyNeighbors(List<List<Species>> neighborhood, Location mylocation, Orientation orientation) {
		// TODO Auto-generated method stub
		List<List<Species>> ret = new ArrayList<List<Species>>();
		for (int i = 0; i < neighborhood.size(); i++){
			Species s = neighborhood.get(i).get(0);
			if (s.getMyLocation().getX() == mylocation.getX()){
				if (s.getMyLocation().getY() == (mylocation.getY()-1)
						|| s.getMyLocation().getY() == (mylocation.getY()+1)){
					ret.add(neighborhood.get(i));
				}
			}
			else if(s.getMyLocation().getY() == mylocation.getY()){
				if (s.getMyLocation().getX() == (mylocation.getX()-1)
						|| s.getMyLocation().getX() == (mylocation.getX()+1)){
					ret.add(neighborhood.get(i));
				}
			}
		}
		return ret;
	}

}
