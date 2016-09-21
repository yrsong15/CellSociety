package user_interface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserInterface {
	
	private static final int UI_WIDTH = 1000;
	private static final int UI_HEIGHT = 500;
	private static final Color BG_COLOR = Color.LIGHTGRAY;
	
	public void startUI(Stage s){
		Group root = new Group();
		Scene scene = new Scene(root, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		s.setScene(scene);
		s.setTitle("Test UI");
		s.show();
	}
}
