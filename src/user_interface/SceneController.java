package user_interface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*** @author Ray Song(ys101)
 * 
*/
public class SceneController {
	
	private final int UI_WIDTH = 1000;
	private final int UI_HEIGHT = 500;
	private final Color BG_COLOR = Color.LIGHTGRAY;
	private final int MARGIN = 60;
	
	private GridController scGC;
	private String state;
	
	public SceneController(Stage s, GridController gc){
		scGC = gc;
	}
	
	public Scene startScene(){
		Group temp = new Group();
		Scene scene = new Scene(temp, UI_WIDTH, UI_HEIGHT, BG_COLOR);
		showTheMan(temp);
		return scene;
	}
	
	
	public void showTheMan(Group g){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/duvall.jpg"));
		ImageView theMan = new ImageView(image);
		theMan = setPosition(theMan, scGC.getGridSize(), scGC.getGridSize(), MARGIN, MARGIN);
		g.getChildren().add(theMan);
	}
	
	public ImageView setPosition(ImageView temp, int width, int height, double d, double e){
		temp.setFitWidth(width);
		temp.setFitHeight(height);
		temp.setX(d);
		temp.setY(e);
		return temp;
	}
	
	
	public String getState(){
		return state;
	}
	
	public void setState(String str){
		state = str;
	}
	
	public int getUIWidth(){
		return UI_WIDTH;
	}
	
	public int getUIHeight(){
		return UI_HEIGHT;
	}
	
	public int getMargin(){
		return MARGIN;
	}
}
