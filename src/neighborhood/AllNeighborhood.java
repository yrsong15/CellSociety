package neighborhood;

import java.util.List;

import species.Species;
import util.Location;
import util.Orientation;

/***
 * @author Owen
 */

public class AllNeighborhood extends Neighborhood{

	public AllNeighborhood(List<List<Species>> neighborhood, Location location, Orientation orientation) {
		super(neighborhood, location, orientation);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<List<Species>> findMyNeighbors(List<List<Species>> neighborhood, Location location, Orientation orientation) {
		return neighborhood;
	}
}
