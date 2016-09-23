package Species;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.Location;
import util.Neighborhood;

public class Fish extends WatorSpecies {
	private static int standardBreedTime = 3;
	private static int numofFish = 0;
	
	
	public Fish(){
		super();
		numofFish++;
		this.setTimeuntilBreed(standardBreedTime + (int) (Math.random() * 3));
	}
	@Override
	public boolean isEdible(){
		return true;
	}
	
	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors){
		if (gettimeuntilBreed() != 0) {
			this.setTimeuntilBreed(this.gettimeuntilBreed() - 1);
		}
		List<Location> spaces = emptyCells;
		this.setNeighborhood(myneighbors);
		
		if (!spaces.isEmpty()){
			Collections.shuffle(spaces);
		}
		return spaces.get(0);
	}
	


	

}
