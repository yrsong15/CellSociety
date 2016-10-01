package util;

import java.util.ArrayList;
import java.util.List;
import species.Species;

/***
 * @author Chalena Scholl, Owen Chung
 */

public class Cell {
	private List<Species> myOccupants;
	private int maxOccupants;
	private Location myLocation;
	
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
		return myOccupants.get(0).getCurrState();
	}

	public int getMaxOccupants() {
		return maxOccupants;
	}
	
	public void applyEffect(Species incoming){
		
	}
}
