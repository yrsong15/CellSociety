package user_interface;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import simulation_config.FireSim;
import simulation_config.GameofLifeSim;
import simulation_config.PredatorPreySim;
import simulation_config.SegregationSim;
import simulation_config.SimulationConfig;
import util.GameEngine;
import util.Grid;
import util.Location;

/*** @author Ray Song(ys101)
 * 
*/
public class GridController {

	private SimulationConfig mySim;
	private GameEngine myEngine;

	
	private final int GRID_SIZE = 420;
	
	private double FRAMES_PER_SECOND = 1;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	public Grid startGridReader(Group g, ResourceBundle rb, int margin, String path, Grid grid){
		if(path.equals(rb.getString("GameOfLifeXMLPath"))){
			mySim = new GameofLifeSim();
		}
		else if(path.equals(rb.getString("SpreadingFireXMLPath"))){
			mySim = new FireSim();
		}
		else if(path.equals(rb.getString("FishSharkXMLPath"))){
			mySim = new PredatorPreySim();
		}
		else if(path.equals(rb.getString("SegregationXMLPath"))){
			mySim = new SegregationSim();
		}
		mySim.getXMLDoc(path);
		grid = mySim.populateGrid();
		myEngine = new GameEngine(grid);
		return grid;
	}
	
	public void displayGrid(Group g, Grid grid, int margin){
		int cellSize = GRID_SIZE / grid.getWidth();
		
		for(int i=0;i<grid.getWidth();i++){
			for(int j=0;j<grid.getHeight();j++){
				Rectangle r = new Rectangle(cellSize*i + margin, cellSize*j + margin, cellSize, cellSize);
				Location curr = new Location(i,j);
				if(grid.getCell(curr).hasOccupants()){
					if(grid.getCell(curr).getState()==1){
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
	
	public GameEngine getGameEngine(){
		return myEngine;
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
}
