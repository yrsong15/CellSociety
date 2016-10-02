import javafx.application.Application;
import javafx.stage.Stage;
import simulation_parser.FireSimulation;
import simulation_parser.ForagingAntsSimulation;
import simulation_parser.GameOfLifeSimulation;
import simulation_parser.PredatorPreySimulation;
import simulation_parser.SegregationSimulation;
import simulation_parser.SimulationParser;
import user_interface.Controller;
import util.GameEngine;
import util.Grid;




/*** @author Chalena Scholl, Ray Song
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		Controller ctrl = new Controller();
		ctrl.startController(s);

	
//		SimulationParser mySim = new ForagingAntsSimulation();
//		mySim.prepareXMLDoc("data/ForagingAnts.xml");
//		Grid myGrid = mySim.populateGrid();
//		GameEngine myEngine = new GameEngine(myGrid);
//		myGrid.outputGridValues();
//		System.out.println("-----");
//		int i = 0;
//		while(i < 100){
//			myEngine.updateWorld();
//			myGrid.outputGridValues();
//			System.out.println("-----");
//			i++;
//		}

		
	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}