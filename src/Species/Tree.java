package Species;

import java.util.List;

import util.Location;
import util.Neighborhood;

public class Tree extends Species {
	/**
	 * State0 = healthy tree, State1 = burning tree;
	 */
	private static float probabilityBurn = (float) 0.3;
	Tree(){
		super();
		
	}

	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood neighbors) {
		// TODO Auto-generated method stub
		int hasFire = 0;
		this.setNeighborhood(neighbors);
		for (Species s : this.getNeighborhood().getMyNeighbors()){
			if (s.getCurrState() == 1){
				hasFire = 1;
				break;
			}
		}
		if((hasFire * Math.random()) < this.getProbabilityBurn()){
			this.setNextState(1);
		}
		return null;
	}
	
	public float getProbabilityBurn() {
		return probabilityBurn;
	}

	public void setProbabilityBurn(float probabilityBurn) {
		Tree.probabilityBurn = probabilityBurn;
	}

}
