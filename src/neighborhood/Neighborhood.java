package neighborhood;
import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;
import util.Orientation;

/***
 * @author Owen
 */

public abstract class Neighborhood {
	private List<List<Species>> myNeighbors;

	public Neighborhood(List<List<Species>> neighborhood, Location location, Orientation orientation){
		setMyNeighbors(findMyNeighbors(neighborhood, location, orientation));
	}
	
	public abstract List<List<Species>> findMyNeighbors(List<List<Species>> neighborhood, Location mylocation, Orientation orientation);
	public void setMyNeighbors(List<List<Species>> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}
	
	public List<List<Species>> getMyNeighbors() {
		return myNeighbors;
	}

	public List<Location> getEmptySpaces(){
		List <Location> ret = new ArrayList<Location>();
		for (int i = 0; i < myNeighbors.size(); i++){
			Species s = myNeighbors.get(i).get(0);
			if (s.getMyLocation().equals(null)){
				ret.add(s.getMyLocation());
			}
		
		}
		
		return ret;
	}
	
	
	
}
