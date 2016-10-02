package species;


import java.util.Collections;
import java.util.List;

import cells.Cell;
import neighborhood.Neighborhood;
import util.Location;

/**
 * @author Owen Chung, Chalena Scholl
 */

public class Shark extends PredatorPreySpecies{
	private int standardBreedTime = 10;
	private int standardStarveTime = 3;
	private int  turnsSinceLastAte;
	
	
	public Shark(){
		super();
		this.setTimeUntilBreed(standardBreedTime + (int) (Math.random() * 10));
		this.setStandardStarveTime(standardStarveTime + (int) (Math.random() * 5));
		turnsSinceLastAte = 0;
	}
	
	/**
	 * @return true if it is time to breed and there is also space to breed
	 */
	public boolean toBreed(){
		boolean breed = getRoomToBreed() && getTimeUntilBreed() <= 0;
		if (breed){
			setTimeUntilBreed(standardBreedTime);
		}
		else{
			setTimeUntilBreed(getTimeUntilBreed()-1);
		}
		return breed;
	}
	

	@Override
	public void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors, Cell currCell) {
		if (reachedStarvation()){
			setNextLocation(null);
			return;

		}
		List<Location> possibleMoves = neighbors.findNeighborsOfState(0);
		
		if(possibleMoves.isEmpty()){
			possibleMoves.addAll(this.getCurrLocation().getAdjacentCells(emptyCells));
			turnsSinceLastAte++;
		}
		else{//can eat a fish
			turnsSinceLastAte = 0;
		}
		
		if (!possibleMoves.isEmpty()){
			Collections.shuffle(possibleMoves);
			setRoomToBreed(true);
			setNextLocation(possibleMoves.get(0));
			return;
		}
		setRoomToBreed(false);
		setNextLocation(getCurrLocation());

	}
	public boolean reachedStarvation(){
		return ((standardStarveTime - turnsSinceLastAte) <= 0);
		
	}
	
	public void setStandardBreedTime(int breedTime){
		standardBreedTime = breedTime;
		setTimeUntilBreed(breedTime);
	}
	
	public void setStandardStarveTime(int starveTime){
		standardStarveTime = starveTime;
	}
	@Override
	public Species clone(Location pos) {
		Species baby = new Shark();
		((Shark) baby).setStandardStarveTime(this.standardStarveTime);
		((Shark) baby).setStandardBreedTime(this.standardBreedTime);
		baby.setCurrLocation(pos);
		baby.setCurrState(this.getCurrState());
		baby.setNextState(this.getNextState());
		return baby;
	}
	
	@Override
	public boolean isPrey() {
		return false;
	}

	
	@Override
	public boolean isPredator() {
		return true;
	}
	


}
