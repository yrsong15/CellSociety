package user_interface;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/*** @author Ray Song(ys101)
 * 	
*/
public class ScrollbarController {
	
	
	private final double MIN_VALUE = 0.1;
	private final double MAX_VALUE = 10;
	private final double START_VALUE = 1;
	private ScrollBar delayBar;
	
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
		delayBar = addScrollBar(g, MIN_VALUE, MAX_VALUE, START_VALUE, width * 3/4 + margin, 2 * margin);
	}
	
	public double getDelayValue(){
		return delayBar.getValue();
	}
	
	public boolean delayBarExists(){
		return delayBar != null;
	}
	
}
