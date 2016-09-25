package species;

import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public abstract class WatorSpecies extends Species{
	
	private int timeUntilBreed;
	private boolean roomToBreed;

	@Override
	public abstract Location performTask(List<Location> emptyCells, Neighborhood neighbors);
	public abstract boolean isEdible();
	
	public boolean toBreed(Location location){
		return (roomToBreed && getTimeUntilBreed() == 0);
	}
	public int getTimeUntilBreed(){
		return this.timeUntilBreed;
	}
	
	public void setRoomToBreed(boolean room){
		roomToBreed = room;
	}
	
	
	public void setTimeUntilBreed(int timeuntilBreed) {
		this.timeUntilBreed = timeuntilBreed;
	}
	
}
