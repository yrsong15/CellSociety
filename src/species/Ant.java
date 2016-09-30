package species;

import java.util.ArrayList;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;
import util.Orientation;

public class Ant extends Species {
	private Location NestLocation = null;
	private Location FoodLocation = null;
	private Orientation Orientation = null;
	private Neighborhood ForwardLocation = null;
	
	public Ant(){
		super();
		
	}


	@Override
	public boolean toBreed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Species clone(Location pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location selectLocation(List<Location> emptyCells, Neighborhood neighbors) {
		checkNeighbors(neighbors);
		if (getCurrState() == 1){
			return returnToNest(emptyCells, neighbors);
		}
		return findFood(emptyCells, neighbors);
	}
	private void checkNeighbors(Neighborhood neighbors) {
		// TODO Auto-generated method stub
		List<Location> forward = new ArrayList<Location>();
		List<Location> other = new ArrayList<Location>();
	
		
	}


	private Location returnToNest(List<Location> emptyCells, Neighborhood neighbors){
		
		return null;
	}
	private Location findFood(List<Location> emptyCells, Neighborhood neighbors){
		return null;
	}

	public Location getNestLocation() {
		return this.NestLocation;
	}

	public void setNestLocation(Location nestLocation) {
		this.NestLocation = nestLocation;
	}


}
