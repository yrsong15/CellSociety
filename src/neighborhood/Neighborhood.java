package neighborhood;
import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;

/***
 * @author Owen
 */

public abstract class Neighborhood {
	private List<Species> myNeighbors;

	public Neighborhood(List<Species> neighborhood, Location location){
		setMyNeighbors(findMyNeighbors(neighborhood, location));
	}
	
	public abstract List<Species> findMyNeighbors(List<Species> neighborhood, Location mylocation);
	public void setMyNeighbors(List<Species> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}
	
	public List<Species> getMyNeighbors() {
		return myNeighbors;
	}

	public List<Location> getEmptySpaces(){
		List <Location> ret = new ArrayList<Location>();
		for (Species s : myNeighbors){
			if (s.getMyLocation().equals(null)){
				ret.add(s.getMyLocation());
			}
		}
		return ret;
	}
	
	
	
}
