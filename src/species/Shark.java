package species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public class Shark extends WatorSpecies{
	private static int standardBreedTime = 10;
	private int standardStarveTime = 3;
	
	private int  turnsSinceLastAte;
	


	public Shark(){
		super();
		this.setTimeUntilBreed(standardBreedTime + (int) (Math.random() * 10));
		this.setStandardStarveTime(standardStarveTime + (int) (Math.random() * 5));
		turnsSinceLastAte = 0;
	}
	@Override
	public boolean isEdible(){
		return false;
	}

	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors) {
		
		if (toDie()){
			return null;
		}
		 
		turnsSinceLastAte++;
		if (getTimeUntilBreed() != 0) {
			setTimeUntilBreed(getTimeUntilBreed()-1);
		}
		this.setNeighborhood(myneighbors);
		List<Location> possiblemoves = new ArrayList<Location>();
		for (Species s : this.getNeighborhood().getMyNeighbors()){
			if (s instanceof Fish){
				possiblemoves.add(s.getMyLocation());
			}
		}
		
		if(possiblemoves.isEmpty()){
			possiblemoves.addAll(emptyCells);
			turnsSinceLastAte++;
		}
		else{//can eat a fish
			turnsSinceLastAte = 0;
		}
		
		if (!possiblemoves.isEmpty()){
			Collections.shuffle(possiblemoves);
			setRoomToBreed(true);
			return possiblemoves.get(0);
		}
		setRoomToBreed(false);
		return this.getMyLocation();

	}
	public boolean toDie(){
		if ((standardStarveTime - turnsSinceLastAte) == 0){
			return true;
		}
		return false;
	}
	
	public void setStandardBreedTime(int breedTime){
		standardBreedTime = breedTime;
	}
	
	public void setStandardStarveTime(int starveTime){
		standardBreedTime = starveTime;
	}
	


}
