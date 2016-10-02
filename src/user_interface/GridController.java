package user_interface;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import simulation_parser.FireSimulation;
import simulation_parser.GameOfLifeSimulation;
import simulation_parser.PredatorPreySimulation;
import simulation_parser.SegregationSimulation;
import simulation_parser.SimulationParser;
import util.GameEngine;
import util.Grid;
import util.Location;

/*** @author Ray Song(ys101)
 * 
*/
public class GridController {

	private SimulationParser mySim;
	private GameEngine myEngine;

	private final int GRID_SIZE = 420;
	private final Color COLORONE = Color.RED;
	private final Color COLORTWO = Color.YELLOW;
	
	private double FRAMES_PER_SECOND = 1;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    private int numOfTypeOne;
    private int numOfTypeTwo;
    private int numOfTotalCells;
    
	
	public Grid startGridReader(Group g, ResourceBundle rb, int margin, String path, Grid grid){
		setSimConfig(rb, path);
		mySim.prepareXMLDoc(path);
		grid = mySim.populateGrid();
		myEngine = new GameEngine(grid);
		return grid;
	}
	
	public Grid resetGridReader(Group g, ResourceBundle rb, int margin, String path, Grid grid, int input){
		setSimConfig(rb, path);
		updateCellSize(input);
		mySim.prepareXMLDoc(path);
		grid = mySim.repopulateGrid();
		myEngine = new GameEngine(grid);
		return grid;
	}
	
	public void setSimConfig(ResourceBundle rb, String path){
		if(path.equals(rb.getString("GameOfLifeXMLPath"))){
			mySim = new GameOfLifeSimulation();
		}
		else if(path.equals(rb.getString("SpreadingFireXMLPath"))){
			mySim = new FireSimulation();
		}
		else if(path.equals(rb.getString("FishSharkXMLPath"))){
			mySim = new PredatorPreySimulation();
		}
		else if(path.equals(rb.getString("SegregationXMLPath"))){
			mySim = new SegregationSimulation();
		}
	}
	
	public void displayGrid(Group g, Grid grid, int margin){
		int cellSize = GRID_SIZE / grid.getWidth();
		numOfTypeOne = 0;
		numOfTypeTwo = 0;
		numOfTotalCells = grid.getHeight() * grid.getWidth();
		
		for(int i=0;i<grid.getWidth();i++){
			for(int j=0;j<grid.getHeight();j++){
				Rectangle r = new Rectangle(cellSize*i + margin, cellSize*j + margin, cellSize, cellSize);
				Location curr = new Location(i,j);
				if(grid.getCell(curr).hasOccupants()){
					if(grid.getCell(curr).getState()==1){
						r.setFill(COLORONE);
						numOfTypeOne++;
					}
					else{
						r.setFill(COLORTWO);
						numOfTypeTwo++;
					}
				}
				g.getChildren().add(r);
			}
		}
		return;
	}
	
	public GameEngine getGameEngine(){
		return myEngine;
	}
	
	public void updateCellSize(int input){
		mySim.setCellSize(input);
	}
	
	public void updateDelay(double fps){
		MILLISECOND_DELAY = 1000 / fps;
	    SECOND_DELAY = 1.0 / fps;
	}
	
	public int getGridSize(){
		return GRID_SIZE;
	}
	
	public double getMilliSecondDelay(){
		return MILLISECOND_DELAY;
	}
	
	public double getSecondDelay(){
		return SECOND_DELAY;
	}
	
	public int getNumOfTypeOne(){
		return numOfTypeOne;
	}
	
	public int getNumOfTypeTwo(){
		return numOfTypeTwo;
	}
	
	public int getNumOfTotalCells(){
		return numOfTotalCells;
	}
}
