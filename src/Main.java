import javafx.application.Application;
import javafx.stage.Stage;
import user_interface.Controller;



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