package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import neighborhood.WholeNeighborhood;
import neighborhood.HexagonNeighborhood;
import neighborhood.Neighborhood;
import neighborhood.PlusNeighborhood;
import species.*;


public class Grid {
		Cell[][] myGrid;
		private int numRows;
		private int numCols;
		private String neighbType;
		
		public Grid(int width, int height){
			myGrid = new Cell[width][height];
			this.numRows = width;
			this.numCols = height;
			initializeGrid();
		}
		
		public Grid(int width, int height, String neighbType){
			myGrid = new Cell[width][height];
			this.numRows = width;
			this.numCols = height;
			this.neighbType = neighbType;
			initializeGrid();
		}
		
		public void initializeGrid(){
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++){
					this.myGrid[i][j] = new Cell();
				}
			}
		}
		
		public Grid(Cell[][] myGrid2, int width, int height, String neighbType) {
			this.myGrid = new Cell[width][height];
			numRows = width;
			numCols = height;
			this.neighbType = neighbType;
			copyFill(myGrid2);
		}
		
		public void copyFill(Cell[][] myOrig){
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++){
					this.myGrid[i][j] = myOrig[i][j];
				}
			}
		}
		
		public String getNeighbType(){
			return neighbType;
		}

		public int getWidth(){
			return numRows;
		}
		
		public int getHeight(){
			return numCols;
		}
		
		/**
		 * @param from Location species object is moving from
		 * @param to Location species object is moving to
		 * @param moving species object that would like to move
		 */
		public void moveSpecies(Location from, Location to, Species moving){
			moving.setMyLocation(to);
			myGrid[from.getX()][from.getY()].removeOccupant(moving);
			myGrid[to.getX()][to.getY()].addOccupant(moving);
		}
		
		
		public void addToGrid(Location to, Species toAdd){
			myGrid[to.getX()][to.getY()].addOccupant(toAdd);
		}
		
		
		
		public void addRandomly(Species currSpecies){
			Random rand = new Random(); 
			int row = rand.nextInt(numRows); 
			int col = rand.nextInt(numCols);
			
			while (!myGrid[row][col].hasFreeSpace()){
				row = rand.nextInt(numRows); 
				col = rand.nextInt(numCols);
			}
			myGrid[row][col].addOccupant(currSpecies);
			currSpecies.setMyLocation(new Location(row, col));
		}
		
		
		public List<Location> getAvailableCells(){
			List<Location> emptyCells = new ArrayList<Location>();
			for (int i = 0; i < myGrid.length; i++){
				for (int j = 0; j < myGrid[i].length; j++){
					Cell curr= myGrid[i][j];
					if (curr.hasFreeSpace()){
						emptyCells.add(new Location(i, j));
					}
				}
			}
			return emptyCells;
		}
		
		public boolean isValidCell(int row, int col){
			return (row >= 0 && row < numRows &&
	                col >= 0 && col < numCols);
		}

		
		
		/**
		 * @param myPos
		 * @return neighborhood of given position depending on neighborhood type for this simulation
		 */
		public Neighborhood createNeighborhood(Location myPos) {
			Neighborhood myNeighb = null;
			if (neighbType.equals("AllNeighbors")){
				myNeighb = new WholeNeighborhood(this, myPos);
			}
			
			else if(neighbType.equals("HexagonNeighbors")){
				myNeighb = new HexagonNeighborhood(this, myPos);
			}
			
			else if(neighbType.equals("PlusNeighbors")){
				myNeighb = new PlusNeighborhood(this, myPos);
			}
			return myNeighb;
		}
		
		
		/**
		 * @param pos
		 * @return
		 */
		public Cell getCell(Location pos){
			return myGrid[pos.getX()][pos.getY()];
		}
		
		
		/**
		 * Outputs the current grid; print output based on state parameter
		 * @param state if true, outputs state of each species. Else, outputs first letter of each species type
		 */
		public void outputGridValues(){
			for (int i = 0; i < myGrid.length; i++){
				String rowVal = "";
				for (int j = 0; j < myGrid[i].length; j++){
					Cell curr= myGrid[i][j];
					if (curr.hasOccupants()){
						rowVal+= curr.getState() + " "; 
					}
					else{
						rowVal+=". ";
					}
				}
				System.out.println(rowVal);
			}
		}
}
