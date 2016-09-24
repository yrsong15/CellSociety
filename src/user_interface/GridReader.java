package user_interface;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import util.*;
import simulation_config.*;

public class GridReader {
	private SimulationConfig sim;
	private Grid myGrid;
	private static GameEngine myEngine;
	
	private static int GRID_SIZE;
	
	public GridReader(int gridSize) {
		myGrid = new Grid(gridSize, gridSize);
		GRID_SIZE = gridSize;
	}
	
	public void startGridReader(Group g, Grid grid, int margin) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		sim = new GameofLifeSim();
		sim.getXMLDoc("data/GameofLife.xml");
		myGrid = sim.populateGrid();
		myEngine = new GameEngine();
	}
	
	public void step(Group g, Grid grid, int margin, ScrollbarController sbc, ResourceBundle rb, double elapsedTime){
		g.getChildren().clear();
		sbc.simScrollBar(g, rb, 1000, 50);
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
	
	
	public Grid getGrid(){
		return myGrid;
	}
	
}
