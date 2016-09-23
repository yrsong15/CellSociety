import javafx.application.Application;
import javafx.stage.Stage;
import util.Simulation;
import util.GameLoop;
import util.Grid;

/*** @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage s) throws Exception {
		//UserInterface UI = new UserInterface();
		//UI.startUI(s); //returns when user presses a button?  
		Simulation mySim = new Simulation();
    	mySim.getXMLDoc("GameofLife.xml");
    	Grid myGrid = mySim.populateGrid();
    	myGrid.outputGridValues();
    	GameLoop myLoop = new GameLoop();
    	
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