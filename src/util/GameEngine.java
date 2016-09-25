package util;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Grid;

/***
 * @author Chalena
 */
public class GameEngine {
	
	private Grid myGrid;
	
	public GameEngine(Grid myGrid){
		this.myGrid = myGrid;
	}


	public void updateWorld(){
		Grid copyGrid = new Grid(myGrid.myGrid, myGrid.getWidth(), myGrid.getHeight(), myGrid.getNeighbType());
		List<Location> toDelete = new ArrayList<Location>();
		List<Location> emptyCells = copyGrid.getEmptyCells();
		List<Species> alreadyVisited = new ArrayList<Species>();
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Species currSpecies= myGrid.getCell(currLoc);
				if (currSpecies != null && !alreadyVisited.contains(currSpecies)){
					alreadyVisited.add(currSpecies);
					Location moveTo = currSpecies.performTask(emptyCells, copyGrid.getNeighborhood(currLoc));
					if (moveTo == null){
						toDelete.add(currLoc);
					}
					else if(!moveTo.equals(currLoc)){
						emptyCells.remove(moveTo);
						move(currLoc, moveTo, currSpecies);
						if (currSpecies.toBreed()){
							myGrid.setCell(currLoc, currSpecies.clone(currLoc));
						}
					}
				}
			}
		}
		clearFallenSpecies(toDelete);
		updateStates();
	}
	
	private void move(Location from, Location to, Species moving){
		moving.setMyLocation(to);
		myGrid.setCell(to, moving);
		myGrid.setCell(from, null);
		
	}
	
	
	private void clearFallenSpecies(List<Location> toDelete){
		for (int i = 0; i < toDelete.size(); i++){
			myGrid.setCell(toDelete.get(i), null);
		}
	}
	
	public void updateStates(){
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Species currSpecies= myGrid.getCell(currLoc);
				if (currSpecies != null){
					currSpecies.updateToLatestState();
				}
			}
		}
	}
}
