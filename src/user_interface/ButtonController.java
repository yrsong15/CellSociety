//package user_interface;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//public class ButtonController{
//	
//	
//	public void initButtons(Group g, Stage stage, Scene scene, ResourceBundle rb, int width, int margin, int buttonSize){
//		Button btnOne = addButtons(g, scene, rb.getString("SegregationLabel"), 
//				width/2 + margin, margin, buttonSize, buttonSize);
//		btnOne.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	setState(rb.getString("SegregationLabel"));
//		    	
//		    	//TODO: fix???
//		        stage.setScene(segregationScene());
//		    }
//		});
//		
//		Button btnTwo = addButtons(g, scene, rb.getString("FishSharkLabel"), width/2 + 
//				buttonSize + margin, margin, buttonSize, buttonSize);
//		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	setState(rb.getString("FishSharkLabel"));
//		    	myStage.setScene(fishSharkScene());
//		    }
//		});
//		
//		Button btnThree = addButtons(g, scene, rb.getString("SpreadingFireLabel"), width/2 + margin, 
//				buttonSize + margin, buttonSize, buttonSize);
//		btnThree.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	setState(rb.getString("SpreadingFireLabel"));
//		    	myStage.setScene(fireScene());
//		    }
//		});
//		
//		Button btnFour = addButtons(g, scene, rb.getString("GameOfLifeLabel"), width/2 + 
//				buttonSize + margin, buttonSize + margin, buttonSize, buttonSize);
//		btnFour.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	try {
//		    		setState(rb.getString("GameOfLifeLabel"));
//					myStage.setScene(gameOfLifeScene());
//				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
//						| IllegalArgumentException | InvocationTargetException e1) {
//					e1.printStackTrace();
//				}
//		    }
//		});
//		
//	}
//	
//	public void simButtons(Group g, Stage stage, Scene scene, ResourceBundle rb){
//		Button reset = addButtons(g, scene, rb.getString("ResetLabel"), UI_WIDTH/2 + MARGIN, MARGIN, 
//				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
//		reset.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	if(getState().equals(rb.getString("GameOfLifeLabel"))){
//		    		try {
//						myStage.setScene(gameOfLifeScene());
//					} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
//							| IllegalArgumentException | InvocationTargetException e1) {
//						e1.printStackTrace();
//					}
//		    	}
//		    	else if(getState().equals(rb.getString("SegregationLabel"))){
//		    		System.out.println("aaa");
//		    	}
//		    }
//		});
//		
//		addButtons(g, scene, rb.getString("StartLabel"), UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
//				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
//		addButtons(g, scene, rb.getString("StopLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
//				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
//		Button play = addButtons(g, scene, rb.getString("PlayLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
//				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
//		play.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	stage.setScene(startScene());
//		    }
//		});
//		
//		Button anotherSim = addButtons(g, scene, "Run Another Simulation", 
//				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
//				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
//		
//		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override public void handle(ActionEvent e) {
//		    	stage.setScene(startScene());
//		    }
//		});
//	}
//	
//	
//	public Button addButtons(Group root, Scene s, String name, double xPos, double yPos, double width, double height){
//		Button btn = new Button(name);
//		btn.setPrefWidth(width);
//		btn.setPrefHeight(height);
//		btn.setLayoutX(xPos);
//		btn.setLayoutY(yPos);
//		root.getChildren().add(btn);
//		return btn;
//	}
//}
