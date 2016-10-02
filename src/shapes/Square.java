package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/***
 * @author Chalena Scholl
 */
public class Square extends CustomShape{
	Rectangle mySquare;

	public Square(int row, int col, int length, int margin) {
		super(row, col, length, margin);
		mySquare = new Rectangle();
		super.setShape(mySquare);
	}

	@Override
	public void setPosition() {
		int mySideLength = getMySideLength();
		mySquare.setX(mySideLength*getMyRow() + getGridMargin());
		mySquare.setY(mySideLength*getMyCol() + getGridMargin());
		mySquare.setWidth(mySideLength);
		mySquare.setHeight(mySideLength);
	}

	@Override
	public void setFill(Color choice) {
		mySquare.setFill(choice);
	}

}
 