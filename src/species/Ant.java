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
	private int currHomePheromones;
	private int currFoodPheromones;

	
	public Ant(){
		super();
		hasFoodItem = false;
	}
	
	@Override
	public void performTask(List<Location> emptyCells, Neighborhood neighbors) {
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
	
	private void dropHomePheromones(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("home", findForward(neighborCells));
		int neighborAmt = ((AntCell)maxNeighbor).getHomePheromones();
		int pheromones = neighborAmt-2-currHomePheromones;
		if (pheromones > 0){
			((AntCell)maxNeighbor).setHomePheromones(neighborAmt + pheromones);
		}
	}
	
	private void dropFoodPheromones(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		Cell maxNeighbor = findMaxPheromones("food", findForward(neighborCells));
		int neighborAmt = ((AntCell)maxNeighbor).getFoodPheromones();
		int pheromones = neighborAmt-2-currFoodPheromones;
		if (pheromones > 0){
			((AntCell)maxNeighbor).setFoodPheromones(neighborAmt + pheromones);
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
