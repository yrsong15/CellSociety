package util;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import neighborhood.Neighborhood;
import neighborhood.PlusNeighbors;
import species.Species;


public class Grid {
		Species[][] myGrid;
		private int numRows;
		private int numCols;
		private String neighbType;
		
		
		public Grid(int width, int height, String neighbType){
			myGrid = new Species[width][height];
			this.numRows = width;
			this.numCols = height;
			this.neighbType = neighbType;
		}
		
		public Grid(Species[][] myGrid2, int width, int height, String neighbType) {
			this.myGrid = myGrid2;
			numRows = width;
			numCols = height;
			this.neighbType = neighbType;
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
		
		public void setCell(Location pos, Species mySpecies){
			myGrid[pos.getX()][pos.getY()] = mySpecies;
		}
		
		public void addCell(Species mySpecies){
			Random rand = new Random(); 
			int row = rand.nextInt(numRows); 
			int col = rand.nextInt(numCols);
			while (myGrid[row][col]!= null){
				row = rand.nextInt(numRows); 
				col = rand.nextInt(numCols);
			}
			myGrid[row][col] = mySpecies;
			mySpecies.setMyLocation(new Location(row, col));
		}
		
		public List<Location> getEmptyCells(){
			List<Location> emptyCells = new ArrayList<Location>();
			for (int i = 0; i < myGrid.length; i++){
				for (int j = 0; j < myGrid[i].length; j++){
					Species curr= myGrid[i][j];
					if (curr == null){
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
			
		
		
		public Neighborhood getNeighborhood(Location pos){
			int row = pos.getX();
			int col = pos.getY();
		    List<Species> neighbors = new ArrayList<>();
		    for( int changeRow = -1; changeRow <= 1; ++changeRow) {
		        for( int changeCol = -1; changeCol <= 1; ++changeCol) {
		            if( changeRow == 0 && changeCol == 0 ) {
		            	continue;
		            }
		            
	            	int newRow = changeRow + row;
		            int newCol = changeCol + col;
		            if(isValidCell(newRow, newCol) && myGrid[newRow][newCol]!=null) {
		            	neighbors.add(myGrid[newRow][newCol]);
		            
		            }
		        }
		    }
			return createNeighborhood(neighbors, pos);
		}
		
		
		public Neighborhood createNeighborhood(List<Species> aroundMe, Location myPos) {
			Neighborhood myNeighb = null;
			try {
				Class<?> neighbClass = Class.forName("neighborhood." + neighbType);
				Constructor<?> constructor = null;
				try {
					constructor = neighbClass.getConstructor(List.class, Location.class);
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				myNeighb = (Neighborhood) constructor.newInstance(aroundMe, myPos);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.out.println("Neighborhood provided in simulation configuration class cannot be found, does not have a proper constructor, or is not a valid class");
				e.printStackTrace();
			} 
			
			return myNeighb;
		}
		
		
		
		public Species getCell(Location pos){
			return myGrid[pos.getX()][pos.getY()];
		}
		
		public void outputGridValues(){
			for (int i = 0; i < myGrid.length; i++){
				String rowVal = "";
				for (int j = 0; j < myGrid[i].length; j++){
					Species curr= myGrid[i][j];
					if (curr != null){
						rowVal+= curr.getCurrState() + " "; //for GameOfLife
					//	rowVal+= curr.getClass().toString().substring(14, 15) + " "; //for Predator-Prey
						
					}
					else{
						rowVal+=". ";
					}
				}
				System.out.println(rowVal);
			}
		}
}
