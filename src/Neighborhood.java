import java.util.ArrayList;
import java.util.List;

import Species.Species;

public class Neighborhood {
	private List<Species> myNeighbors;

	Neighborhood(List<Species> neighbors){
		this.myNeighbors = neighbors;
	}
	
	public List<Species> getMyNeighbors() {
		return myNeighbors;
	}
	public void setMyNeighbors(List<Species> myNeighbors) {
		this.myNeighbors = myNeighbors;
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
