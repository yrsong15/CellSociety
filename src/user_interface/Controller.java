// This entire file is part of my masterpiece.
// Ray Song (ys101)
// methods: resetGrid(), createAnimation(), step() : lines 147-181
/**
 * My Code Masterpiece is one of the additional features that I added during Sprint Two,
 * which is to create a text field that accepts an integer input that represents the 
 * number of cells for the new simulation. 
 * 
 * Before, the number of cells was a static input
 * that was solely determined by the XML file, but this feature allows the user to dynamically
 * change the number of cells even after the initial configuration. 
 * 
 * Due to the nature of our program,
 * I had to make changes in not only the main controller, but also the GridController and SimulationParser 
 * classes as well. In addition, I also created a TextFieldController class to account for the use
 * of an entirely new JavaFX feature that had not been used before.
 * 
 * I am especially proud of my masterpiece in that during Sprint Two, I was able to divide my UI 
 * into smaller classes based on functionalities, as well as display my understanding of the MVC model.
 * This is why I was able to add a separate class (TextFieldController) that seamlessly integrates
 * with the rest of the UI, as well as add such a large change just by tweak existing methods, 
 * without having to overhaul the entire project.
 * 
 * I believe that my Code Masterpiece not only shows my fluent use of diverse methods and classes to keep code as 
 * concise as possible, but also account for edge cases in order to proactively prevent bugs and errors.
 * 
 * Also, my Code Masterpiece displays my all-around understanding
 * of not only the code I had written, but also those written by my teammates (SimulationParser was 
 * originally written by Chalena, but I made significant changes to its layout to account for 
 * dynamic changes in number of Cells. Here is the link to the changes that I made:
 *  https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team17/commit/005f5d43e953a98b040ec090e80db7c1ffee8345
 *  ).
 */

package user_interface;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Grid;

/*** @author Ray Song(ys101)
 * 	 This is the platform that controls the front-end side of our application.
 * 	 All other classes within this package are called from this class.
*/
public class Controller{
	
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private final String PROPERTIES_TITLE = "UILabels";

	private Stage myStage;
	private ResourceBundle myResources;
	private SceneController mySC;
	private ButtonController myBC;
	private GridController myGC;
	private ScrollbarController mySBC;
	private TextFieldController myTFC;
	private LineGraphController myLGC;
	private Timeline myAnimation;
	private Grid myGrid;
  
    
    public void startController(Stage s){
    	myStage = s;
    	myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+PROPERTIES_TITLE);
    	myBC = new ButtonController();
    	myGC = new GridController();
    	mySC = new SceneController(myStage, myGC);
    	mySBC = new ScrollbarController();
    	myGrid = new Grid(myGC.getGridSize(), myGC.getGridSize(), myResources.getString("NeighborhoodLabel"), 
    			myResources.getString("CellLabel"));
    	myTFC = new TextFieldController();
    	myLGC = new LineGraphController();
    	
    	setNewInitScene(myStage, mySC.startScene());
		
		myStage.setTitle(myResources.getString("UITitle"));
		myStage.show();
    }
    
    public void setNewInitScene(Stage stage, Scene scene){
    	Group root = (Group)scene.getRoot();
    	initButtons(root);
		stage.setScene(scene);
    }
    
    public void setNewSimScene(Stage stage, Scene scene){
    	Group root = (Group)scene.getRoot();
    	simButtons(root);
    	if(mySC.getState().equals(myResources.getString("GameOfLifeLabel"))){
    		startGrid(root, myResources.getString("GameOfLifeXMLPath"));
    	}
    	else if(mySC.getState().equals(myResources.getString("SpreadingFireLabel"))){
    		startGrid(root, myResources.getString("SpreadingFireXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("FishSharkLabel"))){
    		startGrid(root, myResources.getString("FishSharkXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("SegregationLabel"))){
    		startGrid(root, myResources.getString("SegregationXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("ForagingAntsLabel"))){
    		startGrid(root, myResources.getString("ForagingAntsXMLPath"));
    	}
		stage.setScene(scene);
    }
    
	public void startGrid(Group g, String path){
		myGrid = myGC.startGridReader(g, myResources, mySC.getMargin(), path, myGrid);
		
		if(mySBC.delayBarExists()){
			myGC.updateDelay(mySBC.getDelayValue());
		}
		createAnimation(g);
	}
	
    public void resetSimScene(Stage stage, Scene scene){
    	Group root = (Group)scene.getRoot();
    	simButtons(root);
    	if(mySC.getState().equals(myResources.getString("GameOfLifeLabel"))){
    		resetGrid(root, myResources.getString("GameOfLifeXMLPath"));
    	}
    	else if(mySC.getState().equals(myResources.getString("SpreadingFireLabel"))){
    		resetGrid(root, myResources.getString("SpreadingFireXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("FishSharkLabel"))){
    		resetGrid(root, myResources.getString("FishSharkXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("SegregationLabel"))){
    		resetGrid(root, myResources.getString("SegregationXMLPath"));
    	}
    	else if (mySC.getState().equals(myResources.getString("ForagingAntsLabel"))){
    		resetGrid(root, myResources.getString("ForagingAntsXMLPath"));
    	}
		
		stage.setScene(scene);
    }
	
	// This method is part of my masterpiece.
	// Ray Song (ys101)
	public void resetGrid(Group g, String path){
		if(mySBC.delayBarExists()){
			myGC.updateDelay(mySBC.getDelayValue());
		}
		
		int newNumCells = 0;
		if(myTFC.cellSizeTFExists()){
			newNumCells = myTFC.getTFInput();
		}
		myGrid = myGC.resetGridReader(g, myResources, mySC.getMargin(), path, myGrid, newNumCells);
		createAnimation(g);
	}
	
	// This method is part of my masterpiece.
	// Ray Song (ys101)
	public void createAnimation(Group g){
        KeyFrame frame = new KeyFrame(Duration.millis(myGC.getMilliSecondDelay()),
                e -> step(g, myGrid, mySC.getMargin(), myResources, myGC.getSecondDelay()));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
	}
	
	// This method is part of my masterpiece.
	// Ray Song (ys101)
	public void step(Group g, Grid grid, int margin, ResourceBundle rb, double elapsedTime){
		g.getChildren().clear();
		simButtons(g);
		myGC.getGameEngine().updateWorld();
    	myGC.displayGrid(g, grid, margin);
    	myLGC.displayLineChart(g, myLGC.getChart(), mySC.getUIWidth() * 1/2, mySC.getUIHeight() * 0.6);
    	myLGC.addDataToSeries(myGC.getNumOfTypeOne(), myGC.getNumOfTypeTwo(), myGC.getNumOfTotalCells());
	}
    
	public void initButtons(Group g){
		setStartButton(g, myStage, mySC.startScene(), myResources.getString("SegregationLabel"),
				mySC.getUIWidth()/2 + mySC.getMargin(),mySC.getMargin());
		setStartButton(g, myStage, mySC.startScene(), myResources.getString("FishSharkLabel"),
				mySC.getUIWidth()/2 + mySC.getMargin() + myBC.getButtonSize(), mySC.getMargin());
		setStartButton(g, myStage, mySC.startScene(), myResources.getString("SpreadingFireLabel"),
				mySC.getUIWidth()/2 + mySC.getMargin(), mySC.getMargin() + myBC.getButtonSize());
		setStartButton(g, myStage, mySC.startScene(), myResources.getString("GameOfLifeLabel"),
				mySC.getUIWidth()/2 + mySC.getMargin() + myBC.getButtonSize(), mySC.getMargin() + myBC.getButtonSize());
		setStartButton(g, myStage, mySC.startScene(), myResources.getString("ForagingAntsLabel"),
				mySC.getUIWidth()/2 + mySC.getMargin(), mySC.getMargin() + 2* myBC.getButtonSize());
	}
    
	public void simButtons(Group g){
		setResetButton(g);
		setPlayButton(g);
		setStopButton(g);
		setStepButton(g);
		setDelayButton(g);
		setBackButton(g);
		setCellSizeButton(g);
		myLGC.initGraphSettings(g, myResources, mySC.getUIWidth() * 1/2, mySC.getUIHeight() * 0.6);
	}
	
	public void setStartButton(Group g, Stage stage, Scene scene, String btnLabel, int xPos, int yPos){
		Button btn = myBC.addStartButton(g, btnLabel, xPos, yPos);
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	mySC.setState(btnLabel);
		    	setNewSimScene(stage, scene);
		    }
		});
	}
	
	public void setResetButton(Group g){
		Button reset = myBC.addSimButton(g, myResources.getString("ResetLabel"), mySC.getUIWidth()/2 + mySC.getMargin(), mySC.getMargin());
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    	resetSimScene(myStage, mySC.startScene());
		    }
		});
	}
	
	public void setPlayButton(Group g){
		Button play = myBC.addSimButton(g, myResources.getString("StartLabel"), mySC.getUIWidth()/2 + mySC.getMargin(), 
				mySC.getMargin() + myBC.getSmallButtonLength());
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.play();
		    }
		});
	}
	
	public void setStopButton(Group g){
		Button stop = myBC.addSimButton(g, myResources.getString("StopLabel"), mySC.getUIWidth()/2 + mySC.getMargin(), 
				mySC.getMargin() + 2 * myBC.getSmallButtonLength());
		stop.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    }
		});
	}
	
	public void setStepButton(Group g){
		Button step = myBC.addSimButton(g, myResources.getString("StepLabel"), mySC.getUIWidth()/2 + mySC.getMargin(),
				mySC.getMargin() + 3 * myBC.getSmallButtonLength());
		step.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    	step(g, myGrid, mySC.getMargin(), myResources, myGC.getSecondDelay());
		    }
		});
	}
	
	public void setDelayButton(Group g){
		Button delay = myBC.addSimButton(g, myResources.getString("DelayLabel"), mySC.getUIWidth()/2 + mySC.getMargin(), 
				mySC.getMargin() + 4 * myBC.getSmallButtonLength());
		delay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    	mySBC.simScrollBar(g, myResources, mySC.getUIWidth(), mySC.getMargin());
		    }
		});
	}
	
	public void setCellSizeButton(Group g){
		Button cellSize = myBC.addSimButton(g, myResources.getString("CellSizeLabel"), mySC.getUIWidth()/2 + mySC.getMargin(), 
				mySC.getMargin() + 5 * myBC.getSmallButtonLength());
		cellSize.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    	myTFC.cellSizeTextField(g, myResources.getString("NumberOfCells"), mySC.getUIWidth(), mySC.getMargin());
		    }
		});
	}
	
	public void setBackButton(Group g){
		Button anotherSim = myBC.addBackButton(g, myResources.getString("AnotherSimLabel"), 
				mySC.getUIWidth()-myBC.getResetButtonWidth() , mySC.getUIHeight()-myBC.getSmallButtonLength());
		
		anotherSim.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myAnimation.stop();
		    	setNewInitScene(myStage, mySC.startScene());
		    }
		});
	}

}
