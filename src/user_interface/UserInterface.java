package user_interface;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

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

public class UserInterface {
	
	protected static final int UI_WIDTH = 1000;
	protected static final int UI_HEIGHT = 500;
	private static final String UI_TITLE = "CellSociety Simulator - Group 17";
	protected static final Color BG_COLOR = Color.LIGHTGRAY;
	protected static final int BUTTON_SIZE = 200;
	protected static final int GRID_SIZE = 420;
	protected static final int MARGIN = 60;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	protected static final int SMALL_BUTTON_WIDTH = 70;
	protected static final int SMALL_BUTTON_LENGTH = 30;
	protected static final int RESET_BUTTON_WIDTH = 200;
	
	protected Stage myStage;
	private GridReader gr;
	private ResourceBundle myResources;
	
	public void startUI(Stage s){
		myStage = s;
		gr = new GridReader();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"UILabels");
		s.setScene(startScene());
		s.setTitle(UI_TITLE);
		s.show();
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		initGrid(temp);
		initButtons(temp, myStage, scene);
		return scene;
	}
	
	public void initGrid(Group g){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/duvall.jpg"));
		ImageView theMan = new ImageView(image);
		theMan = setPosition(theMan, GRID_SIZE, GRID_SIZE, MARGIN, MARGIN);
//		Rectangle r = new Rectangle(MARGIN, MARGIN, GRID_SIZE, GRID_SIZE);
//		r.setStroke(Color.ROYALBLUE);
		g.getChildren().add(theMan);
	}
	
	public ImageView setPosition(ImageView temp, int width, int height, double d, double e){
		temp.setFitWidth(width);
		temp.setFitHeight(height);
		temp.setX(d);
		temp.setY(e);
		return temp;
	}
	
	public void startGrid(Group g) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		gr.testReader();
		gr.displayGrid(g, gr.getGrid(), MARGIN);	
	}
	
	public void initButtons(Group g, Stage stage, Scene scene){
		Button btnOne = addButtons(g, scene, myResources.getString("SegregationLabel"), 
				UI_WIDTH/2 + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnOne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        myStage.setScene(segregationScene());
		    }
		});
		
		Button btnTwo = addButtons(g, scene, myResources.getString("FishSharkLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnTwo.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myStage.setScene(fishSharkScene());
		    }
		});
		
		Button btnThree = addButtons(g, scene, myResources.getString("SpreadingFireLabel"), UI_WIDTH/2 + MARGIN, 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnThree.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	myStage.setScene(fireScene());
		    }
		});
		
		Button btnFour = addButtons(g, scene, myResources.getString("GameOfLifeLabel"), UI_WIDTH/2 + 
				BUTTON_SIZE + MARGIN, BUTTON_SIZE + MARGIN, BUTTON_SIZE, BUTTON_SIZE);
		btnFour.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
					myStage.setScene(gameOfLifeScene());
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
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
	
	public Scene gameOfLifeScene() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		startGrid(temp);
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
		addText(g, myResources.getString("CellSizeLabel"), UI_WIDTH * 7/10, MARGIN + 10);
		addText(g, myResources.getString("DelayLabel"), UI_WIDTH * 7/10, 2*MARGIN + 10);
		addText(g, myResources.getString("BlankLabel"), UI_WIDTH * 7/10, 3*MARGIN + 10);
		addText(g, myResources.getString("BlankLabel"), UI_WIDTH * 7/10, 4*MARGIN + 10);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 2 * MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 3 * MARGIN);
		addScrollBar(g, 0, 100, 50, UI_WIDTH * 3/4 + MARGIN, 4 * MARGIN);
	}
	
	public void simButtons(Group g, Stage stage, Scene scene){
		Button reset = addButtons(g, scene, "Reset", UI_WIDTH/2 + MARGIN, MARGIN, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
					myStage.setScene(gameOfLifeScene());
				} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		addButtons(g, scene, "Start", UI_WIDTH/2 + MARGIN, MARGIN + SMALL_BUTTON_WIDTH, 
				SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		addButtons(g, scene, "Stop", UI_WIDTH/2 + MARGIN, MARGIN + 2 * SMALL_BUTTON_WIDTH, 
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
	
	public String getTitle(){
		return UI_TITLE;
	}
}
