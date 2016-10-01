package species;

import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/**
 * @author Owen Chung, Chalena Scholl
 */ 
public class GameOfLife extends Species{
	public GameOfLife(){
		super();
	}
	@Override
	/**
	 * 0 state = live cells, 1 state = dead cells
	 */
	public void performTask(List<Location> emptyCells, Neighborhood neighbors){
		List<Location> liveNeighbors = neighbors.findNeighborsOfState(0);
		int numLiveNeighbors = liveNeighbors.size();
		
		if (isAlive() && numLiveNeighbors < 2 || numLiveNeighbors > 3){
				this.setNextState(1);
		}
		
		else if(isDead() && numLiveNeighbors==3){
			this.setNextState(0);
		}
		
		setNextLocation(getCurrLocation());
	}
	
	public boolean isDead(){
		return this.getCurrState()==1;
	}
	
	public boolean isAlive(){
		return this.getCurrState()==0;
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
	public boolean isPrey() {
		return false;
	}

	@Override
	public boolean isPredator() {
		return false;
	}
}
