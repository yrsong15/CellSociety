package user_interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimScene extends UserInterface{
	public Scene segregationScene(){
		//make another scene that's attached to another root, change scene using setScene
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		simButtons(temp, myStage, scene);
		simScrollBar(temp, myStage, scene);
		return scene;
	}
	
	public Scene fishSharkScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		simButtons(temp, myStage, scene);
		simScrollBar(temp, myStage, scene);
		return scene;
	}
	
	public Scene fireScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		simButtons(temp, myStage, scene);
		simScrollBar(temp, myStage, scene);
		return scene;
	}
	
	public Scene gameOfLifeScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		simButtons(temp, myStage, scene);
		simScrollBar(temp, myStage, scene);
		return scene;
	}
	
	public void addScrollBar(Group g, int min, int max, int base, double xPos, double yPos){
		ScrollBar sc = new ScrollBar();
		sc.setMin(min);
		sc.setMax(max);
		sc.setValue(base);
		sc.setLayoutX(xPos);
		sc.setLayoutY(yPos);
		g.getChildren().add(sc);
//		return sc;
	}
	
	public void addText(Group g, String msg, double xPos, double yPos){
		Text t = new Text(xPos, yPos, msg);
		t.setFont(Font.font ("Verdana", 15));
		t.setFill(Color.ROYALBLUE);
		g.getChildren().add(t);
	}
	
	public void simScrollBar(Group g, Stage stage, Scene scene){
		addText(g, "Similar", UI_WIDTH * 3/4, MARGIN + 10);
		addText(g, "R/B", UI_WIDTH * 3/4, 2*MARGIN + 10);
		addText(g, "Empty", UI_WIDTH * 3/4, 3*MARGIN + 10);
		addText(g, "Size", UI_WIDTH * 3/4, 4*MARGIN + 10);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 2 * MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 3 * MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 4 * MARGIN);
	}
	
	public void simButtons(Group g, Stage stage, Scene scene){
		addButtons(g, scene, "Reset", UI_WIDTH/2 + MARGIN, MARGIN, SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, scene, "Start", UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, scene, "Stop", UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, scene, "Play", UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, scene, "Play", UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		Button anotherSim = addButtons(g, scene, "Run Another Simulation", 
				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        stage.setScene(startScene());
		    }
		});
	}
}
