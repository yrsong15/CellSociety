package util;

import species.Ant;
import species.Species;

public class AntCell extends Cell{
	private boolean isFoodSource;
	private boolean isNest;
	private int foodPheromones;
	private int homePheromones;

	
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

}
