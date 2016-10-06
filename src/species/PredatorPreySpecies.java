package species;

import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
 */
public abstract class PredatorPreySpecies extends Species{
	
	private int timeUntilBreed;
	private boolean roomToBreed;
	
	
	public abstract Species clone(Location pos);
	
	public abstract boolean isPrey();
	
	public abstract boolean isPredator();
	
	
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
