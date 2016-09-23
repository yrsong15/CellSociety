
public class Grid {
		
		private Species[][] myGrid;
		
		public Grid(int width, int height){
			myGrid = new Species[width][height];
		}
		
		public void setCell(Location pos, Species mySpecies){
			myGrid[pos.getX()][pos.getY()] = mySpecies;
		}
		
		public Species getCell(Location pos){
			return myGrid[pos.getX()][pos.getY()];
		}
		

}

