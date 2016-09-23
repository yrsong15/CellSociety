package util;
import java.util.ArrayList;
import java.util.List;

import Species.Species;

/***
 *

 * @author Chalena
 *
 */
public class GameLoop {
	
	public void updateWorld(Grid myGrid){
		List<Location> emptyCells = new ArrayList<Location>();
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Species currSpecies= myGrid.getCell(new Location(i, j));
				if (currSpecies != null){
					//To be changed, performTask(emptyCells, neighborhood)
					Location moveTo = currSpecies.performTask(myGrid.getEmptyCells(), null);
					myGrid.setCell(moveTo, currSpecies);
				}
			}
		}
		
		
	}

}
