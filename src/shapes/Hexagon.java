package shapes;

import javafx.scene.shape.Polygon;

public class Hexagon extends ShapeCreator {
	
	private Polygon myPolygon;

	public Hexagon(int row, int col, int length, int margin) {
		super(row, col, length, margin);
		myPolygon = new Polygon();
		super.setShape(myPolygon); 
	}

	@Override
	public void setPosition() {
		int mySideLength = getMySideLength();
		int myRow = getMyRow();
		int myCol = getMyCol();
		int margin = getGridMargin();
		
		double widthRemain = Math.sin(Math.toRadians(30))*mySideLength;
		double halfHeight = Math.cos(Math.toRadians(30.0))*mySideLength;
		
	    double leftStartX = 0;
	    double leftStartY = 0;
	    if(isEven(myCol)){
			leftStartX = (margin + (3*mySideLength)*(myCol/2));
			leftStartY = (margin + (halfHeight*2) + (halfHeight*2)*myRow);
	    }
	    else{
			leftStartX =  (margin + widthRemain + mySideLength +(3*mySideLength)*(myCol/2));
			leftStartY =  (margin + halfHeight + (halfHeight*2)*myRow);
	    }
	    myPolygon.getPoints().addAll(new Double[]{
	        leftStartX, leftStartY,
	        leftStartX + widthRemain, leftStartY +halfHeight,
	        leftStartX + widthRemain + mySideLength, leftStartY + halfHeight,
	        leftStartX + 2*mySideLength, leftStartY,
	        leftStartX + widthRemain + mySideLength, leftStartY - halfHeight,
	        leftStartX + widthRemain, leftStartY - halfHeight
	        });

			
		return;
		
		
	}
}
