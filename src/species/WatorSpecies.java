package species;

/***
 * @author Chalena Scholl, Owen Chung
 */
public abstract class WatorSpecies extends Species{
	
	private int timeUntilBreed;
	private boolean roomToBreed;
	
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
