package user_interface;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneController {
	
	private final int UI_WIDTH = 1000;
	private final int UI_HEIGHT = 500;
	private final Color BG_COLOR = Color.LIGHTGRAY;
	private final int GRID_SIZE = 420;
	private final int MARGIN = 60;
	
	private Stage scStage;
	private ResourceBundle scResources;
	private ButtonController scBC;
	
	public SceneController(Stage s, ResourceBundle rb){
		scStage = s;
		scResources = rb;
		scBC = new ButtonController();
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		initButtons(temp);
		return scene;
	}
	
	public Scene gameOfLifeScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
//		startGrid(temp, myResources.getString("GameOfLifeXMLPath"));
		simButtons(temp);
		return scene;
	}
	
	public void initGrid(Group g){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/duvall.jpg"));
		ImageView theMan = new ImageView(image);
		theMan = setPosition(theMan, GRID_SIZE, GRID_SIZE, MARGIN, MARGIN);
		g.getChildren().add(theMan);
	}
	
	public ImageView setPosition(ImageView temp, int width, int height, double d, double e){
		temp.setFitWidth(width);
		temp.setFitHeight(height);
		temp.setX(d);
		temp.setY(e);
		return temp;
	}
	
	public void initButtons(Group g){
		Button btnOne = scBC.addStartButton(g, scResources.getString("SegregationLabel"), UI_WIDTH/2 + MARGIN, MARGIN);
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        scStage.setScene(gameOfLifeScene());
		    }
		});
		Button btnTwo = scBC.addStartButton(g, scResources.getString("FishSharkLabel"), UI_WIDTH/2 + MARGIN + scBC.getButtonSize(), MARGIN);
		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        scStage.setScene(gameOfLifeScene());
		    }
		});
		Button btnThree = scBC.addStartButton(g, scResources.getString("SpreadingFireLabel"), UI_WIDTH/2 + MARGIN, MARGIN + scBC.getButtonSize());
		btnThree.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        scStage.setScene(gameOfLifeScene());
		    }
		});
		Button btnFour = scBC.addStartButton(g, scResources.getString("GameOfLifeLabel"), 
				UI_WIDTH/2 + MARGIN + scBC.getButtonSize(), MARGIN + scBC.getButtonSize());
		btnFour.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        scStage.setScene(gameOfLifeScene());
		    }
		});
	}
	
	public void simButtons(Group g){
		
		Button play = scBC.addSimButton(g, scResources.getString("StartLabel"), UI_WIDTH/2 + MARGIN, MARGIN + scBC.getSmallButtonLength());
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	animation.play();
		    }
		});
		
		
		Button stop = scBC.addSimButton(g, scResources.getString("StopLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 2 * scBC.getSmallButtonLength());
		stop.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	animation.stop();
		    }
		});
		
		Button step = scBC.addSimButton(g, scResources.getString("StepLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 3 * scBC.getSmallButtonLength());
		step.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	step(g, getGrid(), MARGIN, myResources, SECOND_DELAY);
		    }
		});
		
		Button delay = scBC.addSimButton(g, scResources.getString("DelayLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 4 * scBC.getSmallButtonLength());
		delay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	setState(myResources.getString("DelayLabel"));
//		    	animation.stop();
//		    	simScrollBar(g, myResources, UI_WIDTH, MARGIN);
		    }
		});
		
		
		
		Button anotherSim = scBC.addBackButton(g, scResources.getString("AnotherSimLabel"), 
				UI_WIDTH-scBC.getResetButtonWidth() , UI_HEIGHT-scBC.getSmallButtonLength());
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	animation.play();
		    	scStage.setScene(startScene());
		    }
		});
	}
}
