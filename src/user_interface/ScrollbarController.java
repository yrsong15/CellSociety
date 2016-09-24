package user_interface;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScrollbarController {
	
	private ScrollBar one, two, three, four;
//	ScrollbarController(){
//		one = new ScrollBar();
//	}
	
	public ScrollBar addScrollBar(Group g, int min, int max, int base, double xPos, double yPos){
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
//		addText(g, bundle.getString("CellSizeLabel"), width * 7/10, margin + 10);
		addText(g, bundle.getString("DelayLabel"), width * 7/10, 2*margin + 10);
//		addText(g, bundle.getString("BlankLabel"), width * 7/10, 3*margin + 10);
//		addText(g, bundle.getString("BlankLabel"), width * 7/10, 4*margin + 10);
//		one = addScrollBar(g, 0, 100, 50, width * 3/4 + margin, margin);
		two = addScrollBar(g, 0, 100, 50, width * 3/4 + margin, 2 * margin);
//		three = addScrollBar(g, 0, 100, 50, width * 3/4 + margin, 3 * margin);
//		four = addScrollBar(g, 0, 100, 50, width * 3/4 + margin, 4 * margin);
	}
	
	public ScrollBar getScrollBar(String input){
		if(input.equals("one")) return one;
		else if(input.equals("two")) return two;
		else if(input.equals("three")) return three;
		else return four;
		
	}
}
