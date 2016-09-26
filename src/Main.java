import javafx.application.Application;
import javafx.stage.Stage;
import simulation_config.FireSim;
import simulation_config.GameofLifeSim;
import simulation_config.PredatorPreySim;
import simulation_config.SegregationSim;
import simulation_config.SimulationConfig;
import util.GameEngine;
import util.Grid;

/*** @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		//UserInterface UI = new UserInterface();
		//UI.startUI(s); //returns when user presses a button?  
		SimulationConfig mySim = new SegregationSim();
    	mySim.getXMLDoc("data/Segregation.xml");
    	Grid myGrid = mySim.populateGrid();
    	myGrid.outputGridValues(true);
    	GameEngine myLoop = new GameEngine(myGrid);
    	myLoop.updateWorld();
    	System.out.println("--------");
    	myGrid.outputGridValues(true);
    	
	}
	
	
	public static void main (String[] args) {
	    launch(args);
	}
}