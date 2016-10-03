import javafx.application.Application;
import javafx.stage.Stage;

import simulation_parser.ForagingAntsSimulation;
import simulation_parser.GameOfLifeSimulation;
import simulation_parser.PredatorPreySimulation;
import simulation_parser.SegregationSimulation;
import simulation_parser.SimulationParser;
import user_interface.Controller;
import util.GameEngine;
import util.Grid;




/*** @author Chalena Scholl, Ray Song, Owen Chung
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