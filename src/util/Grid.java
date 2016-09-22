package util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Location;
import Species;

public class Grid {
		
		private Species[][] myGrid;
		private int numRows;
		private int numCols;
		
		public Grid(int width, int height){
			myGrid = new Species[width][height];
			this.numRows = width;
			this.numCols = height;
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
		
		public Species getCell(Location pos){
			return myGrid[pos.getX()][pos.getY()];
		}
		
		public void outputGridValues(){
			for (int i = 0; i < myGrid.length; i++){
				String rowVal = "";
				for (int j = 0; j < myGrid[i].length; j++){
					Species curr= myGrid[i][j];
					if (curr != null){
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
