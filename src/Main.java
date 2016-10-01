import javafx.application.Application;
import javafx.stage.Stage;

import simulation_parser.ForagingAntsSimulation;
import simulation_parser.GameOfLifeSimulation;
import simulation_parser.PredatorPreySimulation;
import simulation_parser.SegregationSimulation;
import simulation_parser.SimulationParser;
import user_interface.Controller;




/*** @author Chalena Scholl, Ray Song
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		Controller ctrl = new Controller();
		ctrl.startController(s);
		
//		
//		SimulationConfiguration mySim = new ForagingAntsSimulation();
//		mySim.getXMLDoc("data/ForagingAnts.xml");
//		Grid myGrid = mySim.populateGrid();
//		GameEngine myEngine = new GameEngine(myGrid);
//		myGrid.outputGridValues();
//	//	myEngine.updateWorld();
//		//myGrid.outputGridValues();
//		
//		System.out.println("-----");
		
	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}