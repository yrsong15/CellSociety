package species;

import java.util.List;


import neighborhood.Neighborhood;
import util.Location;

/***
 * @author Owen
 */

public class Tree extends Species {
	/**
	 * State0 = healthy tree, State1 = burning tree;
	 */
	private static float probabilityBurn = (float) 0.3;
	public Tree(){
		super();
		
	}

	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood neighbors) {
		int hasFire = 0;
		this.setNeighborhood(neighbors);
		if (this.getCurrState() == 1){
			this.setNextState(0);
			return null;
		}
		for (Species s : this.getNeighborhood().getMyNeighbors()){
			if (s.getCurrState() == 1){
				hasFire = 1;
				if(Math.random() < this.getProbabilityBurn()){
					this.setNextState(1);
				}
				break;
			}
		}
		return this.getMyLocation();
	}
	
	public float getProbabilityBurn() {
		return probabilityBurn;
	}

	public void setProbabilityBurn(float probabilityBurn) {
		Tree.probabilityBurn = probabilityBurn/100;
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

}
