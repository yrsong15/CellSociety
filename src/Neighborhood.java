import java.util.List;

public class Neighborhood {
	private List<Species> myNeighbors;

	Neighborhood(List<Species> neighbors){
		this.myNeighbors = neighbors;
	}
	
	public List<Species> getMyNeighbors() {
		return myNeighbors;
	}
	public void setMyNeighbors(List<Species> myNeighbors) {
		this.myNeighbors = myNeighbors;
	}
	
	
	
}
