package species;

import java.util.List;

import cells.Cell;
import neighborhood.Neighborhood;
import util.Location;

/**
 * @author Owen Chung, Chalena Scholl
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

	public void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors, Cell currCell) {		
		if (isBurning()){

			this.setNextState(0);
			setNextLocation(null);
			return;
		}
		List<Location> burningNeighbors = neighbors.findNeighborsOfState(1);
		for(int i = 0; i< burningNeighbors.size(); i++){
			if(Math.random() < this.getProbabilityBurn()){
				this.setNextState(1);

			}
			break;
		}
		setNextLocation(getCurrLocation());
	}
	
	public float getProbabilityBurn() {
		return probabilityBurn;
	}

	public void setProbabilityBurn(float probabilityBurn) {
		Tree.probabilityBurn = probabilityBurn/100;
	}
	
	public boolean isBurning(){
		return this.getCurrState()==1;
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
	
	public boolean isPrey(){
		return false;
	}
	
	@Override
	public boolean isPredator() {
		return false;
	}

}
