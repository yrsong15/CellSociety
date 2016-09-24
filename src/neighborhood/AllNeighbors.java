package neighborhood;

import java.util.List;

import species.Species;
import util.Location;

public class AllNeighbors extends Neighborhood{

	public AllNeighbors(List<Species> neighborhood, Location location) {
		super(neighborhood, location);
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Species> findMyNeighbors(List<Species> neighborhood, Location location) {
		return neighborhood;
	}
}
