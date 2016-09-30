import javafx.application.Application;
import javafx.stage.Stage;
import simulation_config.GameofLifeSim;
import simulation_config.SegregationSim;
import simulation_config.SimulationConfig;
import user_interface.Controller;
import util.GameEngine;
import util.Grid;



/*** @author Chalena, Ray
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		Controller ctrl = new Controller();
		ctrl.startController(s);
		
//		SimulationConfig mySim = new SegregationSim();
//    	mySim.getXMLDoc("data/Segregation.xml");
//    	Grid myGrid = mySim.populateGrid();
//    	myGrid.outputGridValues();
//    	GameEngine myLoop = new GameEngine(myGrid);
//    	int num = 0;
//    	
//		while (num < 3){
//	    	myLoop.updateWorld();
//	    	System.out.println("-------");
//	    	myGrid.outputGridValues();
//	    	num++;
//			
//			//UI.displayGrid(myGrid);
//		}
		
	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}