package cells;

import java.util.ArrayList;
import java.util.List;
import species.Species;
import util.Location;

//This entire file is part of my masterpiece.
//Chalena Scholl

/**
 * This class's purpose is to represent everything at a specific location on the grid, since each location can have different things going on.
 * It allows there to be multiple occupants in one space and it allows each space to have it's own attributes or states that it goes through.
 * This class adds a lot of flexibility to adding more simulations, as a simulation can have different rules for their locations on the grid.
 * The applyEffect function takes in a species and applies the effect of the species entering the cell to that species and the other species in the cell.
 * On the other hand, the step() function represents a moment in time for the cell and reflects how things change at a location over time (i.e. ForagingAnts needs the nest to breed at each time step).
 */

/**
 * I believe it is well designed because it allows such flexibility and models the real world well.
 * Code-wise, the variables and functions have the correct access modifiers, variables needed by other classes have getters and setters, and the class is easily extendable.
 * Lastly, an abstract class was used instead of an interface since these cell objects are inter-related and only need to implement two methods differently.
 **/

/**
 * @author Chalena Scholl, Owen Chung
 */

public abstract class Cell {
	private List<Species> myOccupants;
	private int maxOccupants;
	private Location myLocation;
	
	
	public Cell(Location where){
		myOccupants = new ArrayList<Species>();
		maxOccupants = 1;
		myLocation = where;
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

	
	public int getMaxOccupants() {
		return maxOccupants;
	}
	
	
	public void setMaxOccupants(int max){
		maxOccupants = max;
	}
	
	
	public Location getLocation(){
		return myLocation;
	}
	
	
	public int getState(){
		if (hasOccupants()){
			return myOccupants.get(0).getCurrState();
		}
		else{
			return -1;
		}
	}
	
	
	public abstract void applyEffect(Species incoming);
	
	
	public abstract void step();
}
