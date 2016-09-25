package species;
import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public class Fish extends WatorSpecies {
	private static int standardBreedTime = 3;
	
	
	public Fish(){
		super();
		this.setTimeUntilBreed(standardBreedTime + (int) (Math.random() * 3));
	}
	@Override
	public boolean isEdible(){
		return true;
	}
	
	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors){
		if (getTimeUntilBreed() != 0) {
			setTimeUntilBreed(getTimeUntilBreed() - 1);
		}
		List<Location> spaces = emptyCells;
		this.setNeighborhood(myneighbors);
		
		if (!spaces.isEmpty()){
			Collections.shuffle(spaces);
			setRoomToBreed(true);
			return spaces.get(0);
		}
		setRoomToBreed(false);
		return this.getMyLocation();
	}
	
	public static void setStandardBreedTime(int breedTime){
		standardBreedTime = breedTime;
	}
}
