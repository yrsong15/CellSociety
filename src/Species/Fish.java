package Species;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.Location;
import util.Neighborhood;

public class Fish extends Species {
	private static int breedTime = 3;

	private static int numofFish = 0;
	
	private int timeuntilBreed;
	
	public Fish(){
		super();
		numofFish++;
		timeuntilBreed = breedTime;
	}
	
	public boolean isEdible(){
		return true;
	}
	
	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors){
		List<Location> spaces = emptyCells;
		this.setNeighborhood(myneighbors);
		if (gettimeuntilBreed() != 0) {
			timeuntilBreed--;
		}
		if (!spaces.isEmpty()){
			Collections.shuffle(spaces);
		}
		
		return spaces.get(0);
	}
	
	public int gettimeuntilBreed(){
		return this.timeuntilBreed;
	}
	
	
	public void setTimeuntilBreed(int timeuntilBreed) {
		this.timeuntilBreed = timeuntilBreed;
	}

	public static void setBreedTime(int newBreedTime){
		  breedTime = newBreedTime;
	}
	
	public static int getBreedTime() {
		return breedTime;
	}

	

}
