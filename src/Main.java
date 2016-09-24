import javafx.application.Application;
import javafx.stage.Stage;
import util.Simulation;
import util.FireSim;
import util.GameEngine;
import util.Grid;
import util.PredatorPreySim;
import util.SegregationSim;

/*** @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		//UserInterface UI = new UserInterface();
		//UI.startUI(s); //returns when user presses a button?  
		Simulation mySim = new SegregationSim();
    	mySim.getXMLDoc("data/Segregation.xml");
    	Grid myGrid = mySim.populateGrid();
    	myGrid.outputGridValues();
    	GameEngine myLoop = new GameEngine();
    	myLoop.updateWorld(myGrid);
    	System.out.println("--------");
    	myGrid.outputGridValues();
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