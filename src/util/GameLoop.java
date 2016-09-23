package util;
import java.util.ArrayList;
import java.util.List;

import Species.Species;
import util.Grid;

/***
 *

 * @author Chalena
 *
 */
public class GameLoop {
	
	public void updateWorld(Grid myGrid){
		Grid copyGrid = new Grid(myGrid.myGrid, myGrid.getWidth(), myGrid.getHeight());
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Species currSpecies= myGrid.getCell(currLoc);
				if (currSpecies != null){
					
					Location moveTo = currSpecies.performTask(copyGrid.getEmptyCells(), copyGrid.getNeighborhood(currLoc));
					if (moveTo != null){
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
