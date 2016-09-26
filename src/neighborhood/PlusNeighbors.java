package neighborhood;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;

/***
 * @author Owen
 */

public class PlusNeighbors extends Neighborhood {

	public PlusNeighbors(List<Species> neighborhood, Location location) {
		
		super(neighborhood, location);
		
		// TODO Auto-generated constructor stub
	}
	//to-do: implement using isAdjacent function in location
	@Override
	public List<Species> findMyNeighbors(List<Species> neighborhood, Location mylocation) {
		// TODO Auto-generated method stub
		List<Species> ret = new ArrayList<Species>();
		for (Species s : neighborhood){
			if (s.getMyLocation().getX() == mylocation.getX()){
				if (s.getMyLocation().getY() == (mylocation.getY()-1)
				|| s.getMyLocation().getY() == (mylocation.getY()+1)){
					ret.add(s);
				}
			}
			else if(s.getMyLocation().getY() == mylocation.getY()){
				if (s.getMyLocation().getX() == (mylocation.getX()-1)
				|| s.getMyLocation().getX() == (mylocation.getX()+1)){
					ret.add(s);
				}
			}
		}
		return ret;
	}

}
