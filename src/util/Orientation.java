package util;


import java.util.ArrayList;
import java.util.List;
/***
 * @author Chalena Scholl, Owen Chung
 */
public class Orientation {
	//possible orientations are N, NE, E, SE, S, SW, W, NW
	private String Orientation;
	private List<Location> forwardLocations;
	
	public Orientation(String orientation){
		forwardLocations = new ArrayList<Location>();
		this.setOrientation(orientation);
	}
	
	public String getOrientation() {
		return Orientation;
	}
	public void setOrientation(String orientation) {
		Orientation = orientation;
	}

	
	
	public void updateOrientation(Location in, Location facing){
		int inX = in.getX();
		int inY = in.getY();
		int diffX = (inX - facing.getX());
		int diffY = (inY - facing.getY());
		if (diffX == 1 && diffY == 0){
			setForwardLocations(new Location(inX-1, inY-1), new Location(inX-1, inY), new Location(inX-1, inY+1));
			setOrientation("N");
		}
		else if (diffX  == 1 && diffY == -1){
			setForwardLocations(new Location(inX-1, inY), new Location(inX-1, inY+1), new Location(inX, inY+1));
			setOrientation("NE");
		}
		else if (diffX  == 1 && diffY == 1){
			setForwardLocations(new Location(inX, inY-1), new Location(inX-1, inY-1), new Location(inX-1, inY));
			setOrientation("NW");
		}
		else if (diffX  == 0 && diffY == -1){
			setForwardLocations(new Location(inX-1, inY+1), new Location(inX, inY+1), new Location(inX+1, inY+1));
			setOrientation("E");
		}
		else if (diffX  == 0 && diffY == 1){
			setForwardLocations(new Location(inX+1, inY-1), new Location(inX, inY-1), new Location(inX-1, inY-1));
			setOrientation("W");
		}
		else if (diffX  == -1 && diffY == 0){
			setForwardLocations(new Location(inX+1, inY+1), new Location(inX+1, inY), new Location(inX+1, inY-1));
			setOrientation("S");
		}
		else if (diffX  == -1 && diffY == -1){
			setForwardLocations(new Location(inX, inY+1), new Location(inX+1, inY+1), new Location(inX+1, inY));
			setOrientation("SE");
		}
		else if (diffX  == -1 && diffY == 1){
			setForwardLocations(new Location(inX+1, inY), new Location(inX+1, inY-1), new Location(inX, inY-1));
			setOrientation("SW");
		}
	}
	
	public List<Location> getForwardLocations(Location in){
		return forwardLocations;
	}
	
	public void setForwardLocations(Location left, Location front, Location right){
		forwardLocations.clear();
		forwardLocations.add(left);
		forwardLocations.add(front);
		forwardLocations.add(right);
	}
}

