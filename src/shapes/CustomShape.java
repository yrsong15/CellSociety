package shapes;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/***
 * @author Chalena Scholl
 */
public abstract class CustomShape {
	private Shape myShape;
	
	private int myRow;
	private int myCol;
	private int mySideLength;
	private int gridMargin;
	
	public CustomShape(int row, int col, int length, int margin){
		myRow = row;
		myCol = col;
		mySideLength = length;
		gridMargin = margin;
	}
	
	public abstract void setPosition();
	
	public void setMyRow(int myRow) {
		this.myRow = myRow;
	}

	public void setMyCol(int myCol) {
		this.myCol = myCol;
	}

	public void setMySideLength(int mySideLength) {
		this.mySideLength = mySideLength;
	}
	
	public int getMySideLength(){
		return this.mySideLength;
	}
	
	public int getMyRow(){
		return this.myRow;
	}
	
	public int getMyCol(){
		return this.myCol;
	}
	
	public boolean isEven(int num){
		return num%2 == 0;
	}
	
	public int getGridMargin(){
		return this.gridMargin;
	}
	
	public void setShape(Shape obj){
		this.myShape = obj;
	}

	public Node getShape(){
		return myShape;
	}
	public abstract void setFill(Color red);
}
