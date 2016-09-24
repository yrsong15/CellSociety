package user_interface;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation_config.GameofLifeSim;
import simulation_config.SimulationConfig;
import species.*;

public class TestGridReader {
	private static SimulationConfig sim;
	private static Grid myGrid;
	private static Stage myStage;
	private static Group myRoot;
	private static GameEngine myEngine;
	
	private static final int GRID_SIZE = 420;
	private static final int CELL_SIZE = 50;
	
	private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000;
    private static final double SECOND_DELAY = 1.0;
	
	public static void startGrid(Stage s) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException{
		myStage = s;
		myRoot = new Group();
		sim = new GameofLifeSim();
		sim.getXMLDoc("data/GameofLife.xml");

		myGrid = sim.populateGrid();
		myEngine = new GameEngine();
		Scene scene = new Scene(myRoot, 600, 600);
		displayGrid(myRoot, myGrid, 50);
		s.setScene(scene);
		s.show();
		myGrid.outputGridValues();
		
		
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
		
	}
	
	
	public static void step(double elapsedTime){
		myRoot.getChildren().clear();
    	myEngine.updateWorld(myGrid);
    	displayGrid(myRoot, myGrid, 50);
    	System.out.println("-------");
    	myGrid.outputGridValues();
	}
	
	public static void displayGrid(Group g, Grid grid, int margin){
//		Scene scene = new Scene(g, 600, 600);
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
//		return scene;
	}
	
//	public static void testReader() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		sim = new Simulation();
//		sim.getXMLDoc("data/GameofLife.xml");
//		myGrid = sim.populateGrid();
//		myLoop = new GameLoop();
//	}
	
	public Grid getGrid(){
		return myGrid;
	}
	
//	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		testReader();
//		myGrid.outputGridValues();
//		Stage s = new Stage();
//		startGrid(s);
//	}
}
