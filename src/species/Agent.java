package species;

import java.util.Collections;

import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/***
 * @author Owen
 */

public class Agent extends Species {
	//agent X is satisfied if at least thresholdPercentage of its neighbors are also X
	//different state means different agents
	private static double thresholdPercentage;

	public Agent(){
		super();
	}

	@Override
	public Location selectLocation(List<Location> emptyCells, Neighborhood neighbors) {
		int numberofneighbors = 0;
		int numberofsameagent = 0;
		float satisfaction = 0;
		List<Location> spaces = emptyCells;
		this.setNeighborhood(neighbors);
		for (int i = 0; i < this.getNeighborhood().getMyNeighbors().size(); i++){
			Species s = this.getNeighborhood().getMyNeighbors().get(i).get(0);
			if (!s.equals(null)){
				numberofneighbors++;
				if (s.getCurrState() == this.getCurrState()){
					numberofsameagent++;
				}
			}
		}
		
		if (numberofneighbors != 0){
			satisfaction = numberofsameagent / (float) numberofneighbors;
		}
		if(satisfaction < thresholdPercentage && !spaces.isEmpty()){
			Collections.shuffle(spaces);
			return spaces.get(0);
		}
		return this.getMyLocation();
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
