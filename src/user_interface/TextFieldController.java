package user_interface;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TextFieldController {
	
	private TextField cellSizeTF;
	
	public HBox addHBox(Group g, double xPos, double yPos){
		HBox box = new HBox();
		box.setLayoutX(xPos);
		box.setLayoutY(yPos);
		g.getChildren().add(box);
		return box;
	}
	
	public TextField addTextField(Group g){
		TextField tf = new TextField();
		g.getChildren().add(tf);
		return tf;
	}
	
	public Label addLabel(Group g, String str){
		Label label = new Label(str);
		g.getChildren().add(label);
		return label;
	}
	
	public void cellSizeTextField(Group g, String str, int width, int margin){
		HBox box = addHBox(g, width * 7/10, 4*margin + 10);
		cellSizeTF = addTextField(g);
		box.getChildren().addAll(addLabel(g, str), cellSizeTF);	
	}
	
	public int getTFInput(){
		if(cellSizeTF.getText().isEmpty()) return 0;
		return Integer.parseInt(cellSizeTF.getText());
	}
	
	public boolean cellSizeTFExists(){
		return cellSizeTF != null;
	}

}
