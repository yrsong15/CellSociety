package cells;

import species.Ant;
import species.Species;
import util.Location;

public class AntCell extends Cell{
	private boolean isFoodSource;
	private boolean isNest;
	private int foodPheromones;
	private int homePheromones;
	private float evaporationRatio = (float) 0.001;
	private float diffusionRatio = (float) 0.001;

	
	public AntCell(Location where) {
		super(where);
	}
	
	
	public boolean isFoodSource() {
		return isFoodSource;
	}

	
	public boolean isNest() {
		return isNest;
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
			for (int i = 0; i < 2; i++){
				if (hasFreeSpace()){
					addOccupant(new Ant(getLocation()));
				}
			}
		}
		setHomePheromones((int)(getHomePheromones() * (1 - evaporationRatio)));
		setFoodPheromones((int)(getFoodPheromones() * (1 - evaporationRatio)));
	}

}
