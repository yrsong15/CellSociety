package Species;

import java.util.List;

import util.Location;
import util.Neighborhood;

public abstract class WatorSpecies extends Species{
	
	private int timeuntilBreed;
	@Override
	public abstract Location performTask(List<Location> emptyCells, Neighborhood neighbors);
	public abstract boolean isEdible();
	public boolean toBreed(Location location){
		//To be fixed, check whether a location is empty
		if (location.equals(null) && gettimeuntilBreed() == 0){
			return true;
		}
		return false;
	}
	public int gettimeuntilBreed(){
		return this.timeuntilBreed;
	}
	
	
	public void setTimeuntilBreed(int timeuntilBreed) {
		this.timeuntilBreed = timeuntilBreed;
	}



}
