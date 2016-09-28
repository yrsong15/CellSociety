import javafx.application.Application;
import javafx.stage.Stage;
import simulation_config.GameofLifeSim;
import simulation_config.SimulationConfig;
import user_interface.OneFileUI;
import util.*;


/*** @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		OneFileUI UI = new OneFileUI();
		UI.startUI(s);

//		SimulationConfig mySim = new GameofLifeSim();
//    	mySim.getXMLDoc("data/GameofLife.xml");
//
//    	Grid myGrid = mySim.populateGrid();
//    	myGrid.outputGridValues(false);
//    	GameEngine myLoop = new GameEngine(myGrid);
//    	myLoop.updateWorld();
//    	System.out.println("--------");
//    	myGrid.outputGridValues(false);
    	
    	
//    	
//		while (true){
//	    	myLoop.updateWorld(myGrid);
//	    	System.out.println("-------");
//	    	myGrid.outputGridValues();
//		}
	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}