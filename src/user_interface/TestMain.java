package user_interface;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application{

	@Override
	public void start(Stage s) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		UserInterface UI = new UserInterface();
		UI.startUI(s);
		
//		GridReader gr = new GridReader();
//		gr.startGrid(s);
	}
	public static void main(String[] args){
		launch(args);
	}

}
