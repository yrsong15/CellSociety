//package user_interface;
//
//import java.lang.reflect.InvocationTargetException;
//
//import javafx.scene.Group;
//import javafx.scene.Scene;
//
//public class SceneController {
//	public Scene segregationScene(){
//		//make another scene that's attached to another root, change scene using setScene
//		Group temp = new Group();
//		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
//		initGrid(temp);
//		simButtons(temp, myStage, scene);
//		sbc.simScrollBar(temp, myStage, scene, myResources, UI_WIDTH, MARGIN);
//		return scene;
//	}
//	
//	public Scene fishSharkScene(){
//		Group temp = new Group();
//		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
//		initGrid(temp);
//		simButtons(temp, myStage, scene);
//		sbc.simScrollBar(temp, myStage, scene, myResources, UI_WIDTH, MARGIN);
//		return scene;
//	}
//	
//	public Scene fireScene(){
//		Group temp = new Group();
//		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
//		initGrid(temp);
//		simButtons(temp, myStage, scene);
//		sbc.simScrollBar(temp, myStage, scene, myResources, UI_WIDTH, MARGIN);
//		return scene;
//	}
//	
//	public Scene gameOfLifeScene() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		Group temp = new Group();
//		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
//		startGrid(temp);
//		simButtons(temp, myStage, scene);
//		sbc.simScrollBar(temp, myStage, scene, myResources, UI_WIDTH, MARGIN);
//		return scene;
//	}
//}
