package neighborhood;

import java.util.List;

import species.Species;
import util.Location;
import util.Orientation;

public class AntNeighborhood extends Neighborhood {
	private List<List<Species>> forwardlocation;

	public AntNeighborhood(List<List<Species>> neighborhood, Location location, Orientation orientation) {
		
		super(neighborhood, location, orientation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<List<Species>> findMyNeighbors(List<List<Species>> neighborhood, Location mylocation, Orientation orientation) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
