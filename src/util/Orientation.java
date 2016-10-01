package util;
public class Orientation {
	//possible orientations are N, NE, E, SE, S, SW, W, NW
	private String Orientation;
	
	public Orientation(String orientation){
		this.setOrientation(orientation);
	}
	
	public String getOrientation() {
		return Orientation;
	}
	public void setOrientation(String orientation) {
		Orientation = orientation;
	}
	
	public void updateOrientation(Location in, Location facing){
		int diffX = (in.getX() - facing.getX());
		int diffY = (in.getY() - facing.getY());
		if (diffX == 1 && diffY == 0){
			setOrientation("N");
		}
		else if (diffX  == 1 && diffY == -1){
			setOrientation("NE");
		}
		else if (diffX  == 1 && diffY == 1){
			setOrientation("NW");
		}
		else if (diffX  == 0 && diffY == -1){
			setOrientation("E");
		}
		else if (diffX  == 0 && diffY == 1){
			setOrientation("W");
		}
		else if (diffX  == -1 && diffY == 0){
			setOrientation("S");
		}
		else if (diffX  == -1 && diffY == -1){
			setOrientation("SE");
		}
		else if (diffX  == -1 && diffY == 1){
			setOrientation("SW");
		}
	}
}
