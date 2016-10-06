package species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cells.AntCell;
import cells.Cell;
import neighborhood.Neighborhood;
import util.Location;
import util.Orientation;

public class Ant extends Species {

	private Orientation currOrientation = new Orientation("N");
	private boolean hasFoodItem;
	private boolean atFoodSource;
	private boolean atNest;
	private int currHomePheromones;
	private int currFoodPheromones;
	private int desiredPheromones;
	private int maxPheromones;
	private int standardLifeTime = 500;
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
	public void updateNextLocation(List<Location> emptyCells, Neighborhood neighbors, Cell currCell) {
		AntCell currAntCell = (AntCell) currCell;
		if (reachedLifeTime()){
			setNextLocation(null);
			return;
		}
		if (atNest || atFoodSource){
			setDesiredPheromones(maxPheromones);
			if (atNest && hasFoodItem){
				currAntCell.setFoodAmount(currAntCell.getFoodAmount() + 1);
				hasFoodItem = false;
				//System.out.println("just dropped food");
			}
			if (atFoodSource){
				
				hasFoodItem = true;
				//System.out.println("just picked up food");
			}
		}
	
		turnsSinceBorn++;
		if (hasFoodItem){
			returnToNest(neighbors, currCell);
		}
		else{
			findFoodSource(neighbors, currCell);
		}
	}


	private boolean reachedLifeTime() {
		return ((standardLifeTime - turnsSinceBorn) <= 0);
		
	}

	private void returnToNest(Neighborhood neighbors, Cell currCell){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		if (atFoodSource){
			Cell maxNeighbor = findMaxPheromones("home", neighborCells);
			if (maxNeighbor != null){
				currOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			}
			else{
				currOrientation.updateOrientation(getCurrLocation(), neighborCells.get(0).getLocation());
			}	
		}
		Cell maxNeighbor = findMaxPheromones("home", findForward(neighborCells));
		if (maxNeighbor == null){
			maxNeighbor = findMaxPheromones("home", findOther(neighborCells));
		}
		if (maxNeighbor != null){
			dropFoodPheromones(neighbors, currCell);
			currOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			setNextLocation(maxNeighbor.getLocation());
		}
		else{
			Location next;
			// could change to random selection later
			if (findForward(neighborCells).isEmpty()){
				next = findOther(neighborCells).get(0).getLocation();
			}
			else{
				next = findForward(neighborCells).get(0).getLocation();
			}
			dropFoodPheromones(neighbors, currCell);
			currOrientation.updateOrientation(getCurrLocation(), next);
			setNextLocation(next);
		}
	}
	
	
	private void findFoodSource(Neighborhood neighbors, Cell currCell){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Collections.shuffle(neighborCells);
		if (atNest){
			Cell maxNeighbor = findMaxPheromones("food", neighborCells);
			if (maxNeighbor != null){
				currOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			}
			else{
				currOrientation.updateOrientation(getCurrLocation(), neighborCells.get(0).getLocation());
			}	
		}
		Cell maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		if (maxNeighbor == null){
			maxNeighbor = findMaxPheromones("food", findOther(neighborCells));
		}
		if (maxNeighbor!=null){
			dropHomePheromones(neighbors, currCell);
			currOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
			setNextLocation(maxNeighbor.getLocation());
		}
		else{
			Location next;
			// could change to random selection later
			if (findForward(neighborCells).isEmpty()){
				next = findOther(neighborCells).get(0).getLocation();
			}
			else{
				next = findForward(neighborCells).get(0).getLocation();
			}
			dropHomePheromones(neighbors, currCell);
			currOrientation.updateOrientation(getCurrLocation(), next);
			setNextLocation(next);
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
	
	
	private void dropHomePheromones(Neighborhood neighbors, Cell currCell){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("home", neighborCells);
		if ((desiredPheromones-currHomePheromones) > 0){
			((AntCell)currCell).setHomePheromones(desiredPheromones);
		}
		if (maxNeighbor != null){
			setDesiredPheromones(((AntCell)maxNeighbor).getHomePheromones() - 5);
		}
	}
	
	private void dropFoodPheromones(Neighborhood neighbors, Cell currCell){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		if ((desiredPheromones-currHomePheromones) > 0){
			((AntCell)currCell).setFoodPheromones(desiredPheromones);
		}
		if (maxNeighbor != null){
			setDesiredPheromones(((AntCell)maxNeighbor).getFoodPheromones() - 5);
		}
	}
	
	private List<Cell> findForward(List<Cell> neighborCells){
		List<Location> forwardLocations = currOrientation.getForwardLocations(getCurrLocation());
		List<Cell> forwardNeighbors = new ArrayList<Cell>();
		for (Cell neighbor : neighborCells){
			if (forwardLocations.contains(neighbor.getLocation())){
				forwardNeighbors.add(neighbor);
			}
		}
		return forwardNeighbors;
	}
	private List<Cell> findOther(List<Cell> neighborCells){
		List<Location> forwardLocations = currOrientation.getForwardLocations(getCurrLocation());
		List<Cell> otherNeighbors = new ArrayList<Cell>();
		for (Cell neighbor : neighborCells){
			if (!forwardLocations.contains(neighbor.getLocation())){
				otherNeighbors.add(neighbor);
			}
		}
		return otherNeighbors;
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
		this.maxPheromones = maxPheromones;
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
