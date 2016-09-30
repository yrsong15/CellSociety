package util;

import java.util.ArrayList;
import java.util.List;
import species.Species;

public class Cell {
	private List<Species> myOccupants;
	private int maxOccupants;
	private Location myLocation;
	
	public Cell(){
		myOccupants = new ArrayList<Species>();
		maxOccupants = 1;
	}
	
	
	public List<Species> getOccupants(){
		return myOccupants;
	}

	
	public void addOccupant(Species toAdd){
		
	}
	
	public void removeOccupant(Species toRemove){
		
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
}
