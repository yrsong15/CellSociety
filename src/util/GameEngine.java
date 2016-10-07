package util;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import species.Species;
import util.Grid;

/***
 * @author Chalena Scholl, Owen Chung
 */
public class GameEngine {

	private Grid myGrid;

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
		List<Location> availableCells = myGrid.getAvailableCells();
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
		applyDecisions();

	}

	public void updateCell(Cell currCell, List<Species> alreadyVisited, List<Location> availableCells){
		List<Species> occupants = currCell.getOccupants();
		for (Species currSpecies : occupants){
			if (!alreadyVisited.contains(currSpecies)){
				alreadyVisited.add(currSpecies);
				Location currLoc = currCell.getLocation();
				currSpecies.updateNextLocation(availableCells, myGrid.createNeighborhood(currLoc), currCell);
			}
		}
	}


	public void applyDecisions(){

		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				Cell currCell = myGrid.getCell(currLoc);
				if (currCell.hasOccupants()){
					List<Species> occupants = currCell.getOccupants();
					List<Species> copyOccupants = new ArrayList<Species>(occupants);
					updateSpecies(currLoc, currCell, copyOccupants);
				}
				currCell.step();
			}
		}
	}


	private void updateSpecies(Location currLoc, Cell currCell, List<Species> copyOccupants) {
		for (int k = 0; k < copyOccupants.size(); k++){
			Species currSpecies = copyOccupants.get(k);
			Location moveTo = currSpecies.getNextLocation();
			if (moveTo == null){
				currCell.removeOccupant(currSpecies);
			}

			else if(!moveTo.equals(currLoc)){
				myGrid.getCell(moveTo).applyEffect(currSpecies);
				if (myGrid.getCell(moveTo).hasFreeSpace()){
					myGrid.moveSpecies(currLoc, moveTo, currSpecies);
				}

				if (currCell.hasFreeSpace() && currSpecies.toBreed()){
					myGrid.addToGrid(currCell.getLocation(), currSpecies.clone(currLoc));
				}
			}

			currSpecies.setCurrState(currSpecies.getNextState());
			currSpecies.setCurrLocation(currSpecies.getNextLocation());
		}
	}


}
