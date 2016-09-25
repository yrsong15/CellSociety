package util;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Grid;

/***
 * @author Chalena
 */
public class GameEngine {


	public void updateWorld(Grid myGrid){
		Grid copyGrid = new Grid(myGrid.myGrid, myGrid.getWidth(), myGrid.getHeight(), myGrid.getNeighbType());
		List<Location> toDelete = new ArrayList<Location>();
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Species currSpecies= copyGrid.getCell(currLoc);
				if (currSpecies != null){
					Location moveTo = currSpecies.performTask(myGrid.getEmptyCells(), copyGrid.getNeighborhood(currLoc));
					if (moveTo == null){
						toDelete.add(currLoc);
					}
					else if(!moveTo.equals(currLoc)){
						move(currLoc, moveTo, currSpecies, myGrid);
					}
				}
			}
		}
		clearFallenSpecies(toDelete, myGrid);
		updateStates(myGrid);
	}
	
	private void move(Location from, Location to, Species moving, Grid myGrid){
		moving.setMyLocation(to);
		myGrid.setCell(to, moving);
		myGrid.setCell(from, null);
		
	}
	
	
	private void clearFallenSpecies(List<Location> toDelete, Grid myGrid){
		for (int i = 0; i < toDelete.size(); i++){
			myGrid.setCell(toDelete.get(i), null);
		}
	}
	
	public void updateStates(Grid myGrid){
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
