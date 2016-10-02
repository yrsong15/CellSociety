package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cells.AntCell;
import cells.Cell;
import neighborhood.WholeNeighborhood;
import neighborhood.HexagonNeighborhood;
import neighborhood.Neighborhood;
import neighborhood.PlusNeighborhood;
import species.*;


/***
 * @author Chalena Scholl, Owen Chung
 */
public class Grid {
		private Cell[][] myGrid;
		private int numRows;
		private int numCols;
		private String neighbType;
		private String cellType;
		
		public Grid(int width, int height, String neighbType, String cellType){
			this.cellType = cellType;
			this.neighbType = neighbType;
			myGrid = create2DArray(width, height);
			this.numRows = width;
			this.numCols = height;
			initializeGrid();
		}
		
		
		public Grid(Cell[][] mainGrid, int width, int height, String neighbType, String cellType) {
			this.cellType = cellType;
			this.neighbType = neighbType;
			this.myGrid = create2DArray(width, height);
			numRows = width;
			numCols = height;
			makeCopy(mainGrid);
		}
		
		public void initializeGrid(){
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++){
					Cell init = createCell(new Location(i,j));
					this.myGrid[i][j] = init;
				}
			}
		}
		
		public void makeCopy(Cell[][] myOrig){
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j <numCols; j++){
					Cell oldCell = myOrig[i][j];
					this.myGrid[i][j] = createCell(oldCell.getOccupants(), oldCell.getMaxOccupants(), oldCell.getLocation());
				}
			}
		}
		
		protected String getNeighbType(){
			return neighbType;
		}
		
		protected String getCellType(){
			return cellType;
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
			moving.setCurrLocation(to);
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
			currSpecies.setCurrLocation(new Location(row, col));

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
			if (neighbType.equals("WholeNeighborhood")){
				myNeighb = new WholeNeighborhood(this, myPos);
			}
			
			else if(neighbType.equals("HexagonNeighborhood")){
				myNeighb = new HexagonNeighborhood(this, myPos);
			}
			
			else if(neighbType.equals("PlusNeighborhood")){
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
		 * ONLY WORK FOR SIMULATION THAT ALLOWS ONLY ONE SPECIES PER CELL
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
		public Cell[][] getMyGrid() {
			return myGrid;
		}

		public void setMyGrid(Cell[][] myGrid) {
			this.myGrid = myGrid;
		}
		
		public Cell[][] create2DArray(int width, int height){
			if (cellType.equals("AntCell")){
				return new AntCell[width][height];
			}
			else{
				return new Cell[width][height];
			}
			
		}
		
		public Cell createCell(Location toPut){
			if (cellType.equals("AntCell")){
				return new AntCell(toPut);
			}
			else{
				return new Cell(toPut);
			}
			
			
		}
		public Cell createCell(List<Species> occupants, int max, Location oldLocation){
			if (cellType.equals("AntCell")){
				return new AntCell(occupants, max, oldLocation);
			}
			else{
				return new Cell(occupants, max, oldLocation);
			}
			
		}
}
