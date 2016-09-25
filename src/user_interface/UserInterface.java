package user_interface;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface {
	
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
	
	private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	protected Stage myStage;
	private GridReader gr;
	private ScrollbarController sbc;
	private ButtonController bc;
	private ResourceBundle myResources;
	private String state;
	
	
	public void startUI(Stage s){
		myStage = s;
		gr = new GridReader(GRID_SIZE, MARGIN);
		sbc = new ScrollbarController();
		bc = new ButtonController();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"UILabels");
		s.setScene(startScene());
		s.setTitle(myResources.getString("UITitle"));
		s.show();
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		gr.initGrid(temp);
		initButtons(temp, myStage);
//		bc.initButtons(temp, myResources);
		return scene;
	}
	
	public Scene segregationScene(){
		//make another scene that's attached to another root, change scene using setScene
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		gr.initGrid(temp);
		simButtons(temp, myStage);
		sbc.simScrollBar(temp, myResources, UI_WIDTH, MARGIN);
		return scene;
	}
	
	public Scene fishSharkScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		gr.initGrid(temp);
		simButtons(temp, myStage);
		sbc.simScrollBar(temp, myResources, UI_WIDTH, MARGIN);
		return scene;
	}
	
	public Scene fireScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		gr.initGrid(temp);
		simButtons(temp, myStage);
		sbc.simScrollBar(temp, myResources, UI_WIDTH, MARGIN);
		return scene;
	}
	
	public Scene gameOfLifeScene() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		startGrid(temp);
		simButtons(temp, myStage);
		sbc.simScrollBar(temp, myResources, UI_WIDTH, MARGIN);
		return scene;
	}
	
	public void startGrid(Group g) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		gr.startGridReader(g, gr.getGrid(), MARGIN);

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> gr.step(g, gr.getGrid(), MARGIN, sbc, bc, myResources, SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	public void initButtons(Group g, Stage stage){
		Button btnOne = addButtons(g, myResources.getString("SegregationLabel"), 
				UI_WIDTH/2 + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	setState(myResources.getString("SegregationLabel"));
		        stage.setScene(segregationScene());
		    }
		});
		
		Button btnTwo = addButtons(g, myResources.getString("FishSharkLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	setState(myResources.getString("FishSharkLabel"));
		    stage.setScene(fishSharkScene());
		    }
		});
		
		Button btnThree = addButtons(g, myResources.getString("SpreadingFireLabel"), UI_WIDTH/2 + MARGIN, 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnThree.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	setState(myResources.getString("SpreadingFireLabel"));
		    	stage.setScene(fireScene());
		    }
		});
		
		Button btnFour = addButtons(g, myResources.getString("GameOfLifeLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnFour.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
		    		setState(myResources.getString("GameOfLifeLabel"));
					stage.setScene(gameOfLifeScene());
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
	}
	
	public void simButtons(Group g, Stage stage){
		Button reset = addButtons(g, myResources.getString("ResetLabel"), UI_WIDTH/2 + MARGIN, MARGIN, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(getState().equals(myResources.getString("GameOfLifeLabel"))){
		    		try {
						myStage.setScene(gameOfLifeScene());
					} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}
		    	}
		    	else if(getState().equals(myResources.getString("SegregationLabel"))){
		    		System.out.println(sbc.getScrollBar("two").getValue());
		    	}
		    }
		});
		
		addButtons(g, myResources.getString("StartLabel"), UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, myResources.getString("StopLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		Button step = addButtons(g, myResources.getString("StepLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		step.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	stage.setScene(startScene());
		    }
		});
		
		Button anotherSim = addButtons(g, "Run Another Simulation", 
				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	stage.setScene(startScene());
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
	
	public String getTitle(){
		return myResources.getString("UITitle");
	}
}
