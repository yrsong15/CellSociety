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


	/**
	 * Responsible for updating the state of the world by looping through the grid and passing each species instance
	 * the information required for their update algorithm. If the species returns a new location, the grid moves
	 * them to it, and also queries each species to see if it needs to reproduce. If so, it completes the necessary
	 * actions to do this.
	 */
	public void updateWorld(){
		Grid copyGrid = new Grid(myGrid.getMyGrid(), myGrid.getWidth(), myGrid.getHeight(), myGrid.getNeighbType());
		List<Location> toDelete = new ArrayList<Location>();
		List<Location> emptyCells = copyGrid.getEmptyCells();
		List<List<Species>> alreadyVisited = new ArrayList<List<Species>>();
		performUpdate(copyGrid, toDelete, emptyCells, alreadyVisited);
		clearFallenSpecies(toDelete);
		updateStates();
	}


	private void performUpdate(Grid copyGrid, List<Location> toDelete, List<Location> emptyCells,
			List<List<Species>> alreadyVisited) {
		for (int i = 0; i < myGrid.getWidth(); i++){
			for (int j = 0; j < myGrid.getHeight(); j++){
				Location currLoc = new Location(i, j);
				for (int k = 0; k < myGrid.getCell(currLoc).size(); k++){
					
				}
				Species currSpecies= myGrid.getCell(currLoc);
				if (currSpecies != null && !alreadyVisited.contains(currSpecies)){
					alreadyVisited.add(currSpecies);
					Location moveTo = currSpecies.selectLocation(emptyCells, copyGrid.getNeighborhood(currLoc));
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
	}
	
	
	/**
	 * @param from Location species object is moving from
	 * @param to Location species object is moving to
	 * @param moving species object that would like to move
	 */
	private void move(Location from, Location to, Species moving){
		moving.setMyLocation(to);
		myGrid.setCell(to, moving);
		myGrid.setCell(from, null);
		
	}
	
	
	/**
	 * Removes species that have died from the grid
	 * @param toDelete a list of locations that species need to be removed from (or set to null)
	 */
	private void clearFallenSpecies(List<Location> toDelete){
		for (int i = 0; i < toDelete.size(); i++){
			myGrid.setCell(toDelete.get(i), null);
		}
	}
	
	/**
	 * Updates the current states of all species to their next states.
	 */
	private void updateStates(){
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
