package util;

import Species.Species;
import util.Grid;

/***
 *

 * @author Chalena
 *
 */
public class GameEngine {
	
	public void updateWorld(Grid myGrid){
		Grid copyGrid = new Grid(myGrid.myGrid, myGrid.getWidth(), myGrid.getHeight());
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Species currSpecies= myGrid.getCell(currLoc);
				if (currSpecies != null){
					Location moveTo = currSpecies.performTask(copyGrid.getEmptyCells(), copyGrid.getNeighborhood(currLoc));
					if (moveTo != null){
						myGrid.setCell(currLoc, null);
						myGrid.setCell(moveTo, currSpecies);
						
					}
				}
			}
		}
		updateStates(myGrid);
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
