package species;

import java.util.ArrayList;
import java.util.List;

import cells.AntCell;
import cells.Cell;
import neighborhood.Neighborhood;
import util.Location;
import util.Orientation;

public class Ant extends Species {

	private Orientation myOrientation = null;
	private boolean hasFoodItem;
	private boolean atFoodSource;
	private boolean atNest;
	private int currHomePheromones;
	private int currFoodPheromones;
	private int desiredPheromones;
	private int maxDesiredPheromones = 100;
	private int standardLifeTime = 100;
	private int turnsSinceBorn;
	




	public Ant(){
		super();
		hasFoodItem = false;
		turnsSinceBorn = 0;
	}
	public Ant(Location currLoc){
		super(currLoc);
		hasFoodItem = false;
		turnsSinceBorn = 0;
	}
	
	@Override
	public void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors) {
		if (reachedLifeTime()){
			setNextLocation(null);
			return;
		}
		if (atNest || atFoodSource){
			setDesiredPheromones(maxDesiredPheromones);
		}
	
		turnsSinceBorn++;
		if (hasFoodItem){
			returnToNest(neighbors);
		}
		else{
			findFoodSource(neighbors);
		}
	}


	private boolean reachedLifeTime() {
		return ((standardLifeTime - turnsSinceBorn) <= 0);
		// TODO Auto-generated method stub
		
	}

	private void returnToNest(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		if (atFoodSource){
			Cell maxNeighbor = findMaxPheromones("home", neighborCells);
			myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			neighborCells = neighbors.findNeighborsWithSpace();
			maxNeighbor = findMaxPheromones("home", findForward(neighborCells));
			if (maxNeighbor == null){
				maxNeighbor = findMaxPheromones("home", neighborCells);
			}
			if (maxNeighbor != null){
				dropFoodPheromones(neighbors);
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
			neighborCells = neighbors.findNeighborsWithSpace();
		}
		
		Cell maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		if (maxNeighbor == null){
			maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		}
		if(maxNeighbor!=null){
			dropHomePheromones(neighbors);
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
			if (pheromone.equals(pheromone)){
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
	
	
	private void dropHomePheromones(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("home", findForward(neighborCells));
		setDesiredPheromones(((AntCell)maxNeighbor).getHomePheromones() - 2);
		if ((desiredPheromones-currHomePheromones) > 0){
			((AntCell)maxNeighbor).setHomePheromones(desiredPheromones);
		}
	}
	
	private void dropFoodPheromones(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		setDesiredPheromones(((AntCell)maxNeighbor).getFoodPheromones() - 2);
		if ((desiredPheromones-currFoodPheromones) > 0){
			((AntCell)maxNeighbor).setFoodPheromones(desiredPheromones);
		}
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
	
	public void setCurrHomePheromones(int currHomePheromones) {
		this.currHomePheromones = currHomePheromones;
	}

	public void setCurrFoodPheromones(int currFoodPheromones) {
		this.currFoodPheromones = currFoodPheromones;
	}
	
	public void setStandardLifeTime(int standardLifeTime) {
		this.standardLifeTime = standardLifeTime;
	}
	
	
	private void setDesiredPheromones(int pheromones) {
		this.desiredPheromones = pheromones; 
		
	}
	
	public void setMaxPheromones(int maxPheromones) {
		this.maxDesiredPheromones = maxPheromones;
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
