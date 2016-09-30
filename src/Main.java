import javafx.application.Application;
import javafx.stage.Stage;
import simulation_config.GameofLifeSim;
import simulation_config.PredatorPreySim;
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
		
		
	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}