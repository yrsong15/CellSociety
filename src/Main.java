

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



/***
 * Main is responsible for initializing the UI and initializing the grid. Once the user presses a UI simulation 
 * button, thus making a decision about which simulation to complete, Main will instantiate a simulation that reads 
 * in the corresponding XML file for that specific simulation (each simulation will have an XML file that describes 
 * the simulation and its global configuration parameters). Main will also call a function in the Grid class that 
 * populates the grid with a number of species based on the values of the simulation (given by the XML file).
Once the start button is pressed for the simulation, a game loop will start in which main calls the game engine. 
The game loop controls how quickly the simulation advances through the state of the world. Each cycle of the game 
loop will use the game engine, which has the function of updating each species state based on the algorithm specified
 in each species subclass.
The game loop ends if the stop button is pressed, otherwise it runs indefinitely.
To summarize, Main interacts with the user interface, the grid, and the game engine, in order to direct the flow of 
the simulation. However, it doesn't actually do any of the real simulation work (updating states of species) itself.
 * @author Chalena
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		
    	Simulation mySim = new Simulation();
    	mySim.getXMLDoc("Segregation.xml");
    	mySim.populateGrid();

		// TODO Auto-generated method stub
		
	}
	
    public static void main (String[] args) {
        launch(args);
    }
	

}
