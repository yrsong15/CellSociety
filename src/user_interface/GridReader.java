package user_interface;
import java.lang.reflect.InvocationTargetException;

import util.*;
import Species.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridReader {
	private Simulation sim;
	private Grid myGrid;
	private Stage myStage;
	
	private static final int GRID_SIZE = 420;
	private static final int CELL_SIZE = 50;
	
//	public void startGrid(Stage s) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		myStage = s;
//		testReader();
//		s.setScene(displayGrid(myGrid));
//		s.show();
//	}
	
	public void displayGrid(Group g, Grid grid, int margin){
		int cellSize = GRID_SIZE / grid.getWidth();
		
		for(int i=0;i<grid.getWidth();i++){
			for(int j=0;j<grid.getHeight();j++){
				Rectangle r = new Rectangle(cellSize*i + margin, cellSize*j + margin, cellSize, cellSize);
				Location curr = new Location(i,j);
				if(grid.getCell(curr) != null){
					if(grid.getCell(curr).getState() == 1){
						r.setFill(Color.RED);
					}
					else{
						r.setFill(Color.YELLOW);
					}
				}
				g.getChildren().add(r);
			}
		}
		return;
	}
	
	public void testReader() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sim = new Simulation();
		sim.getXMLDoc("GameofLife.xml");
		myGrid = sim.populateGrid();
	}
	
	public Grid getGrid(){
		return myGrid;
	}
	
//	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		testReader();
//		myGrid.outputGridValues();
//	}
}
