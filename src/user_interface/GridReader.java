package user_interface;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import util.*;
import simulation_config.*;

public class GridReader {
	private SimulationConfig sim;
	private Grid myGrid;
	private static GameEngine myEngine;
	
	private static int GRID_SIZE;
	private static int MARGIN;
	
	public GridReader(int gridSize, int margin) {
//		myGrid = sim.populateGrid();
		myGrid = new Grid(gridSize, gridSize);
		GRID_SIZE = gridSize;
		MARGIN = margin;
		
	}
	
	public void startGridReader(Group g, Grid grid, ResourceBundle rb, int margin, String path) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(path.equals(rb.getString("GameOfLifeXMLPath"))){
			sim = new GameofLifeSim();
		}
		else if(path.equals(rb.getString("SpreadingFireXMLPath"))){
			sim = new FireSim();
		}
		
		
		sim.getXMLDoc(path);
		myGrid = sim.populateGrid();
		myEngine = new GameEngine();
	}
	
	public void step(Group g, Grid grid, int margin, ScrollbarController sbc, ButtonController bc, ResourceBundle rb, double elapsedTime){
		g.getChildren().clear();
		sbc.simScrollBar(g, rb, 1000, 50);
		bc.simButtons(g, rb);
		myEngine.updateWorld(myGrid);
    	displayGrid(g, grid, margin);
	}
	
	public void displayGrid(Group g, Grid grid, int margin){
		int cellSize = GRID_SIZE / grid.getWidth();
		
		for(int i=0;i<grid.getWidth();i++){
			for(int j=0;j<grid.getHeight();j++){
				Rectangle r = new Rectangle(cellSize*i + margin, cellSize*j + margin, cellSize, cellSize);
				Location curr = new Location(i,j);
				if(grid.getCell(curr) != null){
					if(grid.getCell(curr).getCurrState() == 1){
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
	
	public void initGrid(Group g){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/duvall.jpg"));
		ImageView theMan = new ImageView(image);
		theMan = setPosition(theMan, GRID_SIZE, GRID_SIZE, MARGIN, MARGIN);
		g.getChildren().add(theMan);
	}
	
	public ImageView setPosition(ImageView temp, int width, int height, double d, double e){
		temp.setFitWidth(width);
		temp.setFitHeight(height);
		temp.setX(d);
		temp.setY(e);
		return temp;
	}
	
	
	public Grid getGrid(){
		return myGrid;
	}
	
}
