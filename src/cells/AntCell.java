package cells;

import java.util.List;

import species.Ant;
import species.Species;
import util.Location;

public class AntCell extends Cell{
	private boolean isFoodSource;
	private boolean isNest;
	private int foodPheromones;
	private int homePheromones;
	private float evaporationRatio = (float) 0.001;


	
	public AntCell(Location where) {
		super(where);
		setMaxOccupants(10);
	}
	
	
	public AntCell(List<Species> occupants, int max, Location oldLocation) {
		super(occupants, max, oldLocation);
	}


	public boolean isFoodSource() {
		return isFoodSource;
	}

	
	public boolean isNest() {
		return isNest;
	}
	
	public void setIsFoodSource(boolean isFoodSource) {
		this.isFoodSource = isFoodSource;
	}


	public void setIsNest(boolean isNest) {
		this.isNest = isNest;
	}
	
	
	public int getFoodPheromones() {
		return foodPheromones;
	}


	public void setFoodPheromones(int foodPheromones) {
		this.foodPheromones = foodPheromones;
	}


	public int getHomePheromones() {
		return homePheromones;
	}


	public void setHomePheromones(int homePheromones) {
		this.homePheromones = homePheromones;
	}


	@Override
	public void applyEffect(Species incoming) {
		Ant incomingAnt = (Ant)incoming;
		incomingAnt.setAtNest(isNest);
		incomingAnt.setAtFoodSource(isFoodSource);
		incomingAnt.setCurrHomePheromones(getHomePheromones());
		incomingAnt.setCurrFoodPheromones(getFoodPheromones());
	}
	
	@Override
	public void step(){
		if(isNest){
			for (int i = 0; i < 1; i++){
				if (hasFreeSpace()){
					Ant toAdd = new Ant();
					toAdd.setAtNest(true);
					toAdd.setCurrLocation(getLocation());
					toAdd.setNextLocation(getLocation());
					addOccupant(toAdd);
				}
			}
		}
		setHomePheromones((int)(getHomePheromones() * (1 - evaporationRatio)));
		setFoodPheromones((int)(getFoodPheromones() * (1 - evaporationRatio)));
	}

	@Override
	public int getState(){
		if (isNest){
			return 2;
		}
		
		if (isFoodSource){
			return 1;
		}
		if (hasOccupants()){
			return super.getState();
		}
		else{
			return 3;
		}
	}
}
