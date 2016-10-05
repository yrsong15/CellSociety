package cells;

import java.util.ArrayList;
import java.util.List;
import species.Species;
import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
 */

public class Cell {
	private List<Species> myOccupants;
	private int maxOccupants;
	private Location myLocation;
	private int foodAmount = 0;
	
	public Cell(Location where){
		myOccupants = new ArrayList<Species>();
		maxOccupants = 1;
		myLocation = where;
	}
	
	public Cell(List<Species> occupants, int max, Location loc){
		myOccupants = occupants;
		maxOccupants = max;
		myLocation = loc;
	}
	
	
	
	public List<Species> getOccupants(){
		return myOccupants;
	}

	
	public void addOccupant(Species toAdd){
		myOccupants.add(toAdd);
	}
	
	public void removeOccupant(Species toRemove){
		myOccupants.remove(toRemove);
	}
	
	public boolean hasFreeSpace(){
		return (maxOccupants - getSize()>0);
	}
	
	public boolean hasOccupants(){
		return (getSize() > 0);
	}
	
	public int getSize(){
		return myOccupants.size();
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public int getState(){
		if (hasOccupants()){
			return myOccupants.get(0).getCurrState();
		}
		else{
			return 3;
		}
	}

	public int getMaxOccupants() {
		return maxOccupants;
	}
	
	public void setMaxOccupants(int max){
		maxOccupants = max;
	}
	
	public void applyEffect(Species incoming){
		if (incoming.isPredator()){
			List<Species>copyOccupants = new ArrayList<Species>(getOccupants());
			for (Species inCell : copyOccupants){
				if (inCell.isPrey()){
					getOccupants().remove(inCell);
				}
			}
		}
	}
	public void step(){
		
	}

	public int getFoodAmount() {
		// TODO Auto-generated method stub
		return foodAmount;
	}

	public void setFoodAmount(int foodamount) {
		// TODO Auto-generated method stub
		foodAmount = foodamount;
	}
}
