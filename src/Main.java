import javafx.application.Application;
import javafx.stage.Stage;
import util.Simulation;

/*** @author Chalena
 *
 */

public class Main extends Application{


	@Override
	public void start(Stage arg0) throws Exception {
    	Simulation mySim = new Simulation();
    	mySim.getXMLDoc("Segregation.xml");
    	Grid myGrid = mySim.populateGrid();
		//UserInterface UI = new UserInterface();
		//UI.startUI(s); //returns when user presses a button?  
		Simulation mySim = new Simulation();
    	mySim.getXMLDoc("GameofLife.xml");
    	Grid myGrid = mySim.populateGridTest();
    	myGrid.outputGridValues();
    	GameLoop myLoop = new GameLoop();
    	myLoop.updateWorld(myGrid);
    	System.out.println("-------");
    	myGrid.outputGridValues();
	}
	

    public static void main (String[] args) {
        launch(args);
    }

}