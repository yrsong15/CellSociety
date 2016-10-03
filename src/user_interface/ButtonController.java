package user_interface;

import javafx.scene.Group;
import javafx.scene.control.Button;

/*** @author Ray Song(ys101)
 *   
*/
public class ButtonController{
	
	private final int BUTTON_SIZE = 130;
	private final int SMALL_BUTTON_WIDTH = 100;
	private final int SMALL_BUTTON_LENGTH = 30;
	private final int RESET_BUTTON_WIDTH = 200;
	
	
	public Button addStartButton(Group root, String name, double xPos, double yPos){
		Button btn = new Button(name);
		setButton(btn, xPos, yPos, BUTTON_SIZE, BUTTON_SIZE);
		root.getChildren().add(btn);
		return btn;
	}
	
	public Button addSimButton(Group root, String name, double xPos, double yPos){
		Button btn = new Button(name);
		setButton(btn, xPos, yPos, SMALL_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		root.getChildren().add(btn);
		return btn;
	}
	
	public Button addBackButton(Group root, String name, double xPos, double yPos){
		Button btn = new Button(name);
		setButton(btn, xPos, yPos, RESET_BUTTON_WIDTH, SMALL_BUTTON_LENGTH);
		root.getChildren().add(btn);
		return btn;
	}
	
	public void setButton(Button btn, double xPos, double yPos, int width, int length){
		btn.setLayoutX(xPos);
		btn.setLayoutY(yPos);
		btn.setPrefWidth(width);
		btn.setPrefHeight(length);
	}
	
	public int getResetButtonWidth(){
		return RESET_BUTTON_WIDTH;
	}
	
	public int getSmallButtonWidth(){
		return SMALL_BUTTON_WIDTH;
	}
	
	public int getSmallButtonLength(){
		return SMALL_BUTTON_LENGTH;
	}
	
	public int getButtonSize(){
		return BUTTON_SIZE;
	}
	
}
