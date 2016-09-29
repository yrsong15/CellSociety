package user_interface;

import javafx.application.Application;
import javafx.stage.Stage;

/*** @author Ray Song(ys101)
 * 
*/
public class TestMain extends Application{

	@Override
	public void start(Stage s){
		Controller ctrl = new Controller();
		ctrl.startController(s);
	}
	public static void main(String[] args){
		launch(args);
	}

}
