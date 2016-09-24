package Species;

import java.util.Collections;
import java.util.List;

import util.Location;
import util.Neighborhood;

public class Agent extends Species {
	//agent X is satisfied if at least thresholdPercentage of its neighbors are also X
	//different state means different agents
	private static int thresholdPercentage;
	
	Agent(){
		super();
	}

	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood neighbors) {
		int numberofneighbors = 0;
		int numberofsameagent = 0;
		float satisfaction = 0;
		List<Location> spaces = emptyCells;
		this.setNeighborhood(neighbors);
		for (Species s : this.getNeighborhood().getMyNeighbors()){
			if (!s.equals(null)){
				numberofneighbors++;
				if (s.getCurrState() == this.getCurrState()){
					numberofsameagent++;
				}
			}
		}
		satisfaction = numberofsameagent / numberofneighbors;
		if(satisfaction < thresholdPercentage && !spaces.isEmpty()){
			Collections.shuffle(spaces);
			return spaces.get(0);
		}
		return this.getMyLocation();
	}

}
