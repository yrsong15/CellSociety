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
	public void setAtFoodSource(boolean atFoodSource) {
		this.atFoodSource = atFoodSource;
	}


	public void setAtNest(boolean atNest) {
		this.atNest = atNest;
	}
	private boolean atNest;
	
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
	
	private void returnToNest(Neighborhood neighbors){
		List<Cell> neighborCells = neighbors.getMyNeighbors();
		if (atFoodSource){
			Cell maxNeighbor = findMaxPheromones("home", neighborCells);
			myOrientation.updateOrientation(getCurrLocation(), maxNeighbor.getLocation());
		}
		
	}
	//private List<Cell> findForward(Neighborhood neighbors, Orientation facing){
		
		
	//}
	
	private void findFoodSource(Neighborhood neighbors){
		
	}
	
	private void selectLocation(){
		
	}
	
	private void dropHomePheromones(){
		
	}
	
	private void dropFoodPheromones(){
		
	}
	
	@Override
	public boolean toBreed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Species clone(Location pos) {
		// TODO Auto-generated method stub
		return null;
	}
	private void checkNeighbors(Neighborhood neighbors) {
		// TODO Auto-generated method stub
		List<Location> forward = new ArrayList<Location>();
		List<Location> other = new ArrayList<Location>();
	
		
	}
	private Location returnToNest(List<Location> emptyCells, Neighborhood neighbors){
		
		return null;
	}
	private Location findFood(List<Location> emptyCells, Neighborhood neighbors){
		return null;
	}
	
	@Override
	public boolean isPrey() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isPredator() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
