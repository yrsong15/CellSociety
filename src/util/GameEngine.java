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
	private Grid copyGrid;
	
	public GameEngine(Grid myGrid){
		this.myGrid = myGrid;
	}


	/**
	 * Responsible for updating the state of the world by looping through the grid and passing each species instance
	 * the information required for their update algorithm. If the species returns a new location, the grid moves
	 * them to it, and also queries each species to see if it needs to reproduce. If so, it completes the necessary
	 * actions to do this.
	 */
	public void updateWorld(){
		copyGrid = new Grid(myGrid.myGrid, myGrid.getWidth(), myGrid.getHeight(), myGrid.getNeighbType());
		List<Location> availableCells = copyGrid.getAvailableCells();
		List<Species> alreadyVisited = new ArrayList<Species>();
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Cell currCell = myGrid.getCell(currLoc);
				if (currCell.hasOccupants()){
					updateCell(currCell, alreadyVisited, availableCells);
				}
			}
		}
		updateStates();
	}
	
	public void updateCell(Cell currCell, List<Species> alreadyVisited, List<Location> availableCells){
		List<Species> occupants = currCell.getOccupants();
		for (Species currSpecies : occupants){
			if (!alreadyVisited.contains(currSpecies)){
				alreadyVisited.add(currSpecies);
				Location currLoc = currCell.getLocation();
				
				Location moveTo = currSpecies.performTask(availableCells, copyGrid.createNeighborhood(currLoc));
				if (moveTo == null){
					currCell.removeOccupant(currSpecies);
				}
				else if(!moveTo.equals(currLoc)){
					myGrid.moveSpecies(currLoc, moveTo, currSpecies);
					Cell newCell = myGrid.getCell(moveTo);
					if (!newCell.hasFreeSpace()){
						availableCells.remove(moveTo);
					}
					if (currSpecies.toBreed()){
						//stopping point, check from here
						myGrid.addToGrid(currCell.getLocation(), currSpecies.clone(currLoc));
					}
				}
			}
		}
	}
		
	
	//do we really need this function since we are now making a deep copy of grid?
	public void updateStates(){
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Cell currCell = myGrid.getCell(currLoc);
				if (currCell.hasOccupants()){
					List<Species> occupants = currCell.getOccupants();
					for (int k = 0; k < currCell.getSize(); k++){
						Species currSpecies = occupants.get(k);
						if (currSpecies != null){
							currSpecies.updateToLatestState();
						}
					}
				}
			}
		}
	}
}
