import javafx.application.Application;
import javafx.stage.Stage;
import simulation_config.FireSim;
import simulation_config.GameofLifeSim;
import simulation_config.PredatorPreySim;
import simulation_config.SegregationSim;
import simulation_config.SimulationConfig;
import util.*;


/*** @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		//UserInterface UI = new UserInterface();
		//UI.startUI(s); //returns when user presses a button?  
		SimulationConfig mySim = new GameofLifeSim();
    	mySim.getXMLDoc("data/GameofLife.xml");
    	Grid myGrid = mySim.populateGrid();
    	myGrid.outputGridValues();
    	GameEngine myLoop = new GameEngine();
    	myLoop.updateWorld(myGrid);
    	System.out.println("--------");
    	myGrid.outputGridValues();
//    	
		while (true){
	    	myLoop.updateWorld(myGrid);
	    	System.out.println("-------");
	    	myGrid.outputGridValues();
		}
	}
	
	
	public static void main (String[] args) {
	    launch(args);
	}
}