package shapes;

import javafx.scene.shape.Rectangle;

/***
 * @author Chalena Scholl
 */
public class Square extends ShapeCreator{
	Rectangle myRectangle;

	public Square(int row, int col, int length, int margin) {
		super(row, col, length, margin);
		myRectangle = new Rectangle();
		super.setShape(myRectangle);
	}

	@Override
	public void setPosition() {
		int mySideLength = getMySideLength();
		myRectangle.setX(mySideLength*getMyRow() + getGridMargin());
		myRectangle.setY(mySideLength*getMyCol() + getGridMargin());
		myRectangle.setWidth(mySideLength);
		myRectangle.setHeight(mySideLength);
	}

}
 