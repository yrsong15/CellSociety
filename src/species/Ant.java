package species;

import java.util.ArrayList;
import java.util.List;
import neighborhood.Neighborhood;
import util.AntCell;
import util.Cell;
import util.Location;
import util.Orientation;

public class Ant extends Species {

	private Orientation myOrientation = null;
	private boolean hasFoodItem;
	private boolean atFoodSource;
	private boolean atNest;

	
	public Ant(){
		super();
		hasFoodItem = false;
	}
	
	@Override
	public void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors) {
		if (hasFoodItem){
			returnToNest(neighbors);
		}
		else{
			findFoodSource(neighbors);
		}
	}
	
	private void returnToNest(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		if (atFoodSource){
			Cell maxNeighbor = findMaxPheromones("home", neighborCells);
			myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			maxNeighbor = findMaxPheromones("home", findForward(neighborCells));
			if (maxNeighbor == null){
				maxNeighbor = findMaxPheromones("home", neighborCells);
			}
			if (maxNeighbor != null){
				dropFoodPheromones();
				myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
				setNextLocation(maxNeighbor.getLocation());
				return;
			}
			else{
				setNextLocation(getCurrLocation());
				return;
			}
		}
	}
	
	private void findFoodSource(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		if (atNest){
			Cell maxNeighbor = findMaxPheromones("food", neighborCells);
			myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
		}
		
		Cell maxNeighbor = selectLocation(findForward(neighborCells));
		if (maxNeighbor==null){
			maxNeighbor = selectLocation(findForward(neighborCells));
		}
		if(maxNeighbor!=null){
			dropHomePheromones();
			myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			setNextLocation(maxNeighbor.getLocation());
			return;
			
		}
	}
	
	private Cell findMaxPheromones(String pheromone, List<Cell> neighborCells){
		int max = 0;
		Cell maxNeighbor = null;
		for (Cell currNeighbor: neighborCells){
			AntCell temp = (AntCell)currNeighbor;
			if (pheromone.equals("food")){
				if (temp.getFoodPheromones() > max){
					maxNeighbor = currNeighbor;
					max = temp.getFoodPheromones();
				}
			}
			else{
				if (temp.getHomePheromones() > max){
					maxNeighbor = currNeighbor;
					max = temp.getHomePheromones();
				}
			}
		}
		return maxNeighbor;
	}
	
	//To-Do: fix method if going to use it
	private Cell selectLocation(List<Cell> choices){
		List<Cell> viableChoices = new ArrayList<Cell>();
		for (Cell choice : choices){
			if (choice.hasFreeSpace())
				viableChoices.add(choice);
		}
		if (viableChoices == null){
			//return null;
		}
		return null;
		//return location from viableChoices, where each location is chosed with probability
		//K + foodPhereomonesatLocation to the power of n
	}
	
	private void dropHomePheromones(){
		
	}
	
	private void dropFoodPheromones(){
		
	}
	
	private List<Cell> findForward(List<Cell> neighborCells){
		List<Location> forwardLocations = myOrientation.getForwardLocations(getCurrLocation());
		List<Cell> forwardNeighbors = new ArrayList<Cell>();
		for (Cell neighbor : neighborCells){
			if (forwardLocations.contains(neighbor.getLocation())){
				forwardNeighbors.add(neighbor);
			}
		}
		return forwardNeighbors;
	}
	
	public void setAtFoodSource(boolean atFoodSource) {
		this.atFoodSource = atFoodSource;
	}


	public void setAtNest(boolean atNest) {
		this.atNest = atNest;
	}
	@Override
	public boolean toBreed() {
		return false;
	}
	@Override
	public Species clone(Location pos) {
		return null;
	}
	
	@Override
	public boolean isPrey() {
		return false;
	}
	@Override
	public boolean isPredator() {
		return false;
	}

	

}
