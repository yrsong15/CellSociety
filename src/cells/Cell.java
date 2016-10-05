package cells;

import java.util.ArrayList;
import java.util.List;
import species.Species;
import util.Location;

/***
 * @author Chalena Scholl, Owen Chung
 */

public class Cell {
	protected static int EMPTY_STATE = 3;
	private List<Species> myOccupants;
	private int maxOccupants;
	private Location myLocation;
	
	/**
	 * constructor to create a cell
	 * @param loc location of the cell
	 */
	public Cell(Location loc){
		myOccupants = new ArrayList<Species>();
		maxOccupants = 1;
		myLocation = loc;
	}
	
	/**
	 * @param occupants lists of species objects occupying the cell
	 * @param max maximum occupants allowed
	 * @param loc location of the cell
	 */
	
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
	/**
	 * Returns state of cell that can then be printed 
	 * on front end. If not empty return the state of 
	 * the first occupants of the list.
	 * @return state of the cell
	 */
	
	public int getState(){
		if (hasOccupants()){
			return myOccupants.get(0).getCurrState();
		}
		else{
			return EMPTY_STATE;
		}
	}

	public int getMaxOccupants() {
		return maxOccupants;
	}
	
	public void setMaxOccupants(int max){
		maxOccupants = max;
	}
	/**
	 * applies effects from the incoming species 
	 * or anything species should about cell.
	 * e.g., if a shark is coming into the cell,
	 * fish in the cell will die.
	 * @param incoming species coming into the cell
	 */
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
	/**
	 * carries out changes of a cell per time step
	 */
	public void step(){
		
	}


}
