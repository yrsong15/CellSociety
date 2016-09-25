package user_interface;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ButtonController extends UserInterface{
	
	protected static final int UI_WIDTH = 1000;
	protected static final int UI_HEIGHT = 500;
	protected static final Color BG_COLOR = Color.LIGHTGRAY;
	protected static final int BUTTON_SIZE = 200;
	protected static final int GRID_SIZE = 420;
	protected static final int MARGIN = 60;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	protected static final int SMALL_BUTTON_WIDTH = 70;
	protected static final int SMALL_BUTTON_LENGTH = 30;
	protected static final int RESET_BUTTON_WIDTH = 200;
	
	private String state;
	
	
	public void initButtons(Group g, Stage stage, ResourceBundle rb){
		Button btnOne = addButtons(g, rb.getString("SegregationLabel"), 
				UI_WIDTH/2 + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		        stage.setScene(segregationScene());
		    }
		});
		
		Button btnTwo = addButtons(g, rb.getString("FishSharkLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	setState(rb.getString("FishSharkLabel"));
//		    	myStage.setScene(fishSharkScene());
		    }
		});
		
		Button btnThree = addButtons(g, rb.getString("SpreadingFireLabel"), UI_WIDTH/2 + MARGIN, 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnThree.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	setState(rb.getString("SpreadingFireLabel"));
		    	try {
					stage.setScene(fireScene());
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		Button btnFour = addButtons(g, rb.getString("GameOfLifeLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnFour.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    		setState(rb.getString("GameOfLifeLabel"));
					try {
						myStage.setScene(gameOfLifeScene());
					} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}
		    }
		});
		
	}
	
	public void simButtons(Group g, ResourceBundle rb){
		Button reset = addButtons(g, rb.getString("ResetLabel"), UI_WIDTH/2 + MARGIN, MARGIN, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	if(state.equals(rb.getString("GameOfLifeLabel"))){
//		    		try {
//						myStage.setScene(gameOfLifeScene());
//					} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
//							| IllegalArgumentException | InvocationTargetException e1) {
//						e1.printStackTrace();
//					}
//		    	}
//		    	else if(getState().equals(myResources.getString("SegregationLabel"))){
//		    		System.out.println(sbc.getScrollBar("two").getValue());
//		    	}
		    }
		});
		
		addButtons(g, rb.getString("StartLabel"), UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, rb.getString("StopLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		Button play = addButtons(g,rb.getString("StepLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	stage.setScene(startScene());
		    }
		});
		
		Button anotherSim = addButtons(g, "Run Another Simulation", 
				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	stage.setScene(startScene());
		    }
		});
	}
	
	public Button addButtons(Group root, String name, double xPos, double yPos, double width, double height){
		Button btn = new Button(name);
		btn.setPrefWidth(width);
		btn.setPrefHeight(height);
		btn.setLayoutX(xPos);
		btn.setLayoutY(yPos);
		root.getChildren().add(btn);
		return btn;
	}
	
	public void setState(String str){
		state = str;
	}
	
	public String getState(){
		return state;
	}
}
