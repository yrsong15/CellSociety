package species;
import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/**
 * @author Owen Chung, Chalena Scholl
 */

public class Fish extends WatorSpecies {
	private int standardBreedTime = 3;
	
	
	public Fish(){
		super();
		this.setTimeUntilBreed(standardBreedTime + (int) (Math.random() * 3));
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
	public boolean isPredator() {
		return false;
	}
	
	@Override
	public void updateNextLocation(List<Location> emptyCells, Neighborhood myneighbors){
		List<Location> spaces = this.getCurrLocation().getAdjacentCells(emptyCells);
		
		if (!spaces.isEmpty()){
			Collections.shuffle(spaces);
			setRoomToBreed(true);
			setNextLocation(spaces.get(0));
			return;
		}
		setRoomToBreed(false);
		setNextLocation(getCurrLocation());
	}
	
	public void setStandardBreedTime(int breedTime){
		this.standardBreedTime = breedTime;
		this.setTimeUntilBreed(breedTime);
	}
	
	@Override
	public Species clone(Location pos) {
		Species baby = new Fish();
		((Fish) baby).setStandardBreedTime(this.standardBreedTime);
		baby.setCurrLocation(pos);
		baby.setCurrState(this.getCurrState());
		baby.setNextState(this.getCurrState());
		return baby;
	}
	
	@Override
	public boolean isPrey() {
		return true;
	}

}
