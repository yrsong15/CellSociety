package user_interface;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation_config.FireSim;
import simulation_config.GameofLifeSim;
import simulation_config.PredatorPreySim;
import simulation_config.SegregationSim;
import simulation_config.SimulationConfig;
import util.GameEngine;
import util.Grid;
import util.Location;

public class OneFileUI {
	
	protected static final int UI_WIDTH = 1000;
	protected static final int UI_HEIGHT = 500;
	protected static final Color BG_COLOR = Color.LIGHTGRAY;
	protected static final int BUTTON_SIZE = 200;
	protected static final int GRID_SIZE = 420;
	protected static final int MARGIN = 60;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	protected static final int SMALL_BUTTON_WIDTH = 70;
	protected static final int SMALL_BUTTON_LENGTH = 30;
	protected static final int RESET_BUTTON_WIDTH = 200;
	
	private static double FRAMES_PER_SECOND = 1;
    private static double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	protected Stage myStage;
	private ResourceBundle myResources;
	private String state;
	private ScrollBar delayBar;
	
	private SimulationConfig sim;
	private Grid myGrid;
	private static GameEngine myEngine;
	private Timeline animation;
	
	
	public void startUI(Stage s){
		myStage = s;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"UILabels");
		s.setScene(startScene());
		s.setTitle(myResources.getString("UITitle"));
		s.show();
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		initButtons(temp, myStage);
		return scene;
	}
	
	public Scene segregationScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		startGrid(temp,  myResources.getString("SegregationXMLPath"));
		simButtons(temp, myStage);
		return scene;
	}
	
	public Scene fishSharkScene() {
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		startGrid(temp,  myResources.getString("FishSharkXMLPath"));
		simButtons(temp, myStage);
		return scene;
	}
	
	public Scene fireScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		startGrid(temp,  myResources.getString("SpreadingFireXMLPath"));
		simButtons(temp, myStage);
		return scene;
	}
	
	public Scene gameOfLifeScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		startGrid(temp, myResources.getString("GameOfLifeXMLPath"));
		simButtons(temp, myStage);
		return scene;
	}
	
	public void startGrid(Group g, String path){
		startGridReader(g, myResources, MARGIN, path);
		
		if(delayBar!=null){
			updateDelay(delayBar.getValue());
		}

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(g, myGrid, MARGIN, myResources, SECOND_DELAY));
        
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
	}
	
	public void startGridReader(Group g, ResourceBundle rb, int margin, String path){
		if(path.equals(rb.getString("GameOfLifeXMLPath"))){
			sim = new GameofLifeSim();
		}
		else if(path.equals(rb.getString("SpreadingFireXMLPath"))){
			sim = new FireSim();
		}
		else if(path.equals(rb.getString("FishSharkXMLPath"))){
			sim = new PredatorPreySim();
		}
		else if(path.equals(rb.getString("SegregationXMLPath"))){
			sim = new SegregationSim();
		}
		sim.getXMLDoc(path);
		myGrid = sim.populateGrid();
		myEngine = new GameEngine(myGrid);
	}
	
	public void step(Group g, Grid grid, int margin, ResourceBundle rb, double elapsedTime){
		g.getChildren().clear();
		simButtons(g, myStage);
		myEngine.updateWorld();
    	displayGrid(g, myGrid, margin);
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
	    		setState(myResources.getString("GameOfLifeLabel"));
				stage.setScene(gameOfLifeScene());
		    }
		});
		
	}
	
	public void simButtons(Group g, Stage stage){
		Button reset = addButtons(g, myResources.getString("ResetLabel"), UI_WIDTH/2 + MARGIN, MARGIN, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(getState().equals(myResources.getString("GameOfLifeLabel"))){
	    			animation.stop();
					myStage.setScene(gameOfLifeScene());
		    	}
		    	else if(getState().equals(myResources.getString("SpreadingFireLabel"))){
	    			animation.stop();
					myStage.setScene(fireScene());
		    	}
		    	else if (getState().equals(myResources.getString("FishSharkLabel"))){
		    		animation.stop();
		    		myStage.setScene(fishSharkScene());
		    	}
		    	else if (getState().equals(myResources.getString("SegregationLabel"))){
		    		animation.stop();
		    		myStage.setScene(segregationScene());
		    	}
		    }
		});
		
		Button play = addButtons(g, myResources.getString("StartLabel"), UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	animation.play();
		    }
		});
		
		
		Button stop = addButtons(g, myResources.getString("StopLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		stop.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	animation.stop();
		    }
		});
		
		Button step = addButtons(g, myResources.getString("StepLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 3 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		step.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	step(g, getGrid(), MARGIN, myResources, SECOND_DELAY);
		    }
		});
		
		Button delay = addButtons(g, myResources.getString("DelayLabel"), UI_WIDTH/2 + MARGIN, MARGIN + 4 * SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		delay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
//		    	setState(myResources.getString("DelayLabel"));
		    	animation.stop();
		    	simScrollBar(g, myResources, UI_WIDTH, MARGIN);
		    }
		});
		
		Button anotherSim = addButtons(g, "Run Another Simulation", 
				UI_WIDTH-RESET_BUTTON_WIDTH, UI_HEIGHT-SMALL_BUTTON_LENGTH, 
				RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	animation.stop();
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
	

	
	public void displayGrid(Group g, Grid grid, int margin){
		int cellSize = GRID_SIZE / grid.getWidth();
		
		for(int i=0;i<grid.getWidth();i++){
			for(int j=0;j<grid.getHeight();j++){
				Rectangle r = new Rectangle(cellSize*i + margin, cellSize*j + margin, cellSize, cellSize);
				Location curr = new Location(i,j);
				if(grid.getCell(curr) != null){
					if(grid.getCell(curr).getCurrState() == 1){
						r.setFill(Color.RED);
					}
					else{
						r.setFill(Color.YELLOW);
					}
				}
				g.getChildren().add(r);
			}
		}
		return;
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
	
	public ScrollBar addScrollBar(Group g, double min, double max, double base, double xPos, double yPos){
		ScrollBar sc = new ScrollBar();
		sc.setMin(min);
		sc.setMax(max);
		sc.setValue(base);
		sc.setLayoutX(xPos);
		sc.setLayoutY(yPos);
		g.getChildren().add(sc);
		return sc;
	}
	
	public void addText(Group g, String msg, double xPos, double yPos){
		Text t = new Text(xPos, yPos, msg);
		t.setFont(Font.font ("Verdana", 15));
		t.setFill(Color.ROYALBLUE);
		g.getChildren().add(t);
	}
	
	public void simScrollBar(Group g, ResourceBundle bundle, int width, int margin){
		addText(g, bundle.getString("DelayLabel"), width * 7/10, 2*margin + 10);
		delayBar = addScrollBar(g, 0.1, 10, 1, width * 3/4 + margin, 2 * margin);
	}
	
	
	public Grid getGrid(){
		return myGrid;
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
	
	public void updateDelay(double fps){
		MILLISECOND_DELAY = 1000 / fps;
	    SECOND_DELAY = 1.0 / fps;
	}
}
