package species;

import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/**
 * @author Owen, Chalena
 */

public class Agent extends Species {
	//agent X is satisfied if at least thresholdPercentage of its neighbors are also X
	//different state means different agents
	private static double thresholdPercentage;

	public Agent(){
		super();
	}

	@Override
	public void performTask(List<Location> emptyCells, Neighborhood neighbors) {
		List<Location> sameAgents = neighbors.findNeighborsOfState(this.getCurrState());
		int numberofneighbors = neighbors.getTotalNeighbors();
		int numberofsameagent = sameAgents.size();
		float satisfaction = 0;

		if (numberofneighbors != 0){
			satisfaction = numberofsameagent / (float) numberofneighbors;
		}
		if(satisfaction < thresholdPercentage && !emptyCells.isEmpty()){
			Collections.shuffle(emptyCells);
			setNextLocation(emptyCells.get(0));
			return;
		}
		setNextLocation(getCurrLocation());
	}
	
	public double getThresholdPercentage() {
		return thresholdPercentage;
	}

	public void setThresholdPercentage(int thresholdPercentage) {
		Agent.thresholdPercentage = thresholdPercentage/100.0;
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
