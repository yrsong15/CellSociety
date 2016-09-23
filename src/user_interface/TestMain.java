package user_interface;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application{

	@Override
	public void start(Stage s){
		UserInterface UI = new UserInterface();
		UI.startUI(s);
	}
	public static void main(String[] args){
		launch(args);
	}

}
