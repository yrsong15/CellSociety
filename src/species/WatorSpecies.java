package species;

import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/***
 * @author Owen
 */

public abstract class WatorSpecies extends Species{
	
	private int timeUntilBreed;
	private boolean roomToBreed;

	@Override
	public abstract Location performTask(List<Location> emptyCells, Neighborhood neighbors);
	public abstract boolean isEdible();
	
	/**
	 * @return true if it is time to breed and there is also space to breed
	 */
	public boolean toBreed(){
		return (roomToBreed && getTimeUntilBreed() <= 0);
	}
	public int getTimeUntilBreed(){
		return this.timeUntilBreed;
	}
	
	public void setRoomToBreed(boolean room){
		roomToBreed = room;
	}
	
	
	public void setTimeUntilBreed(int timeuntilBreed) {
		timeUntilBreed = timeuntilBreed;
	}
	
}
