package species;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

/***
 * @author Owen
 */

public class Shark extends WatorSpecies{
	private int standardBreedTime = 10;
	private int standardStarveTime = 3;
	
	private int  turnsSinceLastAte;
	


	public Shark(){
		super();
		this.setTimeUntilBreed(standardBreedTime + (int) (Math.random() * 10));
		this.setStandardStarveTime(standardStarveTime + (int) (Math.random() * 5));
		turnsSinceLastAte = 0;
	}
	
	/**
	 * @return true if it is time to breed and there is also space to breed
	 */
	public boolean toBreed(){
		boolean breed = getRoomToBreed() && getTimeUntilBreed() <= 0;
		if (breed){
			setTimeUntilBreed(standardBreedTime);
		}
		else{
			setTimeUntilBreed(getTimeUntilBreed()-1);
		}
		return breed;
	}
	@Override
	public boolean isEdible(){
		return false;
	}

	@Override
	public Location selectLocation(List<Location> emptyCells, Neighborhood myneighbors) {
		
		if (toDie()){
			return null;
		}
		 
		this.setNeighborhood(myneighbors);
		List<Location> possiblemoves = new ArrayList<Location>();
		for (int i = 0; i < this.getNeighborhood().getMyNeighbors().size(); i++){
			Species s = this.getNeighborhood().getMyNeighbors().get(i).get(0);
			if (s instanceof Fish){
				possiblemoves.add(s.getMyLocation());
			}
		}
		
		if(possiblemoves.isEmpty()){
			List<Location> spaces = this.getMyLocation().getAdjacentCells(emptyCells);
			possiblemoves.addAll(spaces);
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
		if ((standardStarveTime - turnsSinceLastAte) <= 0){
			return true;
		}
		return false;
	}
	
	public void setStandardBreedTime(int breedTime){
		standardBreedTime = breedTime;
		setTimeUntilBreed(breedTime);
	}
	
	public void setStandardStarveTime(int starveTime){
		standardStarveTime = starveTime;
	}
	@Override
	public Species clone(Location pos) {
		Species baby = new Shark();
		((Shark) baby).setStandardStarveTime(this.standardStarveTime);
		((Shark) baby).setStandardBreedTime(this.standardBreedTime);
		baby.setMyLocation(pos);
		baby.setCurrState(this.getCurrState());
		baby.setNextState(this.getNextState());
		return baby;
	}
	


}
