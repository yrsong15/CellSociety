import javafx.application.Application;
import javafx.stage.Stage;
import user_interface.UserInterface;
import util.GameLoop;
import util.Grid;
import util.Location;
import util.Simulation;

public class Main extends Application{

    public static void main (String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage s) throws Exception {


    	
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
	//	while (true){
			//UI.displayGrid(myGrid);
	//	}
    	
		

	}
	
}