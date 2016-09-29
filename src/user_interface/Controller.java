package user_interface;

import java.util.ResourceBundle;

import javafx.stage.Stage;

public class Controller{
	
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	private Stage myStage;
	private ResourceBundle myResources;
	private SceneController mySC;
  
    
    public void startController(Stage s){
    	myStage = s;
    	myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"UILabels");
    	mySC = new SceneController(myStage, myResources);
		myStage.setScene(mySC.startScene());
		myStage.setTitle(myResources.getString("UITitle"));
		myStage.show();
    }
    
    

}
