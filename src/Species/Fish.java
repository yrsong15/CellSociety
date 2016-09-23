package Species;
import java.util.List;

import util.Location;

public class Fish extends Species {
	private static int breedTime = 3;

	private static int numofFish = 0;
	
	private int timeuntilbreed;
	
	public Fish(){
		super();
		numofFish++;
		timeuntilbreed = breedTime;
	}
	
	public boolean isEdible(){
		return true;
	}
	
	@Override
	public Location performTask(List<Location> emptyCells){
		timeuntilbreed--;
		List<Location> openspaces = super.getNeighborhood().getEmptySpaces();
		if (!openspaces.isEmpty()){
			Location oldLocation = getMyLocation();
			
		}
		
		return null;
	}
	
	
	
	public static void setBreedTime(int newBreedTime){
		  breedTime = newBreedTime;
	}
	
	public static int getBreedTime() {
		return breedTime;
	}

	

}
