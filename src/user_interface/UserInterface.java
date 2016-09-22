package user_interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class UserInterface {
	
	private static final int UI_WIDTH = 1000;
	private static final int UI_HEIGHT = 500;
	private static final String UI_TITLE = "CellSociety Simulator - Group 17";
	private static final Color BG_COLOR = Color.LIGHTGRAY;
	private static final int BUTTON_SIZE = 200;
	private static final int GRID_SIZE = 420;
	private static final int MARGIN = 50;
	
	private static final int SMALL_BUTTON_WIDTH = 70;
	private static final int SMALL_BUTTON_LENGTH = 30;
	private static final int RESET_BUTTON_WIDTH = 200;
	
	private Group root;
	private Stage myStage;
	private Scene myScene;
	
	public void startUI(Stage s){
		root = new Group();
		myStage = s;
		myScene = new Scene(root, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		Scene trythis = startScene();
		myStage.setScene(trythis);
		myStage.setTitle(UI_TITLE);
		myStage.show();
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		initButtons(temp, myStage, scene);
		return scene;
	}
	
	public void initGrid(Group g){
		Rectangle r = new Rectangle(MARGIN, MARGIN, GRID_SIZE, GRID_SIZE);
		r.setStroke(Color.ROYALBLUE);
		g.getChildren().add(r);
	}
	
	public void initButtons(Group g, Stage stage, Scene scene){
		Button btnOne = addButtons(g, scene, "Segregation", UI_WIDTH/2 + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        stage.setScene(segregationScene());
		    }
		});
		
		addButtons(g, scene, "Fish-Shark", UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		addButtons(g, scene, "Spreading Fire", UI_WIDTH/2 + MARGIN, 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		addButtons(g, scene, "Game of Life", UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
	}
	
	public Button addButtons(Group root, Scene s, String name, double xPos, double yPos, double width, double height){
		Button btn = new Button(name);
		btn.setPrefWidth(width);
		btn.setPrefHeight(height);
		btn.setLayoutX(xPos);
		btn.setLayoutY(yPos);
		root.getChildren().add(btn);
		return btn;
	}
	
	public Scene segregationScene(){
		//make another scene that's attached to another root, change scene using setScene
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		addButtons(temp, myScene, "Reset", UI_WIDTH/2 + MARGIN, MARGIN, SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(temp, myScene, "Start", UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(temp, myScene, "Stop", UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(temp, myScene, "Play", UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(temp, myScene, "Play", UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(temp, myScene, "Run Another Simulation", 
				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		
		return scene;
	}
	
	public Scene getScene(){
		return myScene;
	}
}
