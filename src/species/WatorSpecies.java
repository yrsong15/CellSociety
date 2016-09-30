package species;

import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public abstract class WatorSpecies extends Species{
	
	private int timeUntilBreed;
	private boolean roomToBreed;
	
	public abstract boolean isEdible();
	
	public abstract boolean toBreed();
	
	
	public int getTimeUntilBreed(){
		return this.timeUntilBreed;
	}
	
	public void setRoomToBreed(boolean room){
		roomToBreed = room;
	}
	
	public boolean getRoomToBreed() {
		return roomToBreed;
	}
	
	public void setTimeUntilBreed(int timeuntilBreed) {
		timeUntilBreed = timeuntilBreed;
	}
	
}
