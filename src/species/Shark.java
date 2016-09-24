package species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neighborhood.Neighborhood;
import util.Location;

public class Shark extends WatorSpecies{
	private static int standardBreedTime = 10;
	private static int standardStarveTime = 3;
	private static int numofShark = 0;
	
	private int  turnsSinceLastAte;
	private int  myStarveTime;
	


	public Shark(){
		super();
		numofShark++;
		this.setTimeuntilBreed(standardBreedTime + (int) (Math.random() * 10));
		this.setMyStarveTime(standardStarveTime + (int) (Math.random() * 5));
		turnsSinceLastAte = 0;
	}
	@Override
	public boolean isEdible(){
		return false;
	}

	@Override
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors) {
		// TODO Auto-generated method stub
		turnsSinceLastAte++;
		if (gettimeuntilBreed() != 0) {
			this.setTimeuntilBreed(this.gettimeuntilBreed() - 1);
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
		}
		if (!possiblemoves.isEmpty()){
			Collections.shuffle(possiblemoves);
			return possiblemoves.get(0);
		}
		return this.getMyLocation();

	}
	public boolean toDie(){
		if (turnsSinceLastAte == this.getMyStarveTime()){
			return true;
		}
		return false;
	}
	public int getMyStarveTime() {
		return myStarveTime;
	}
	public void setMyStarveTime(int myStarveTime) {
		this.myStarveTime = myStarveTime;
	}
	


}
