package Species;

import java.util.List;

import util.Location;
import util.Neighborhood;

public class CellofLife extends Species{
	public CellofLife(){
		super();
	}
	@Override
	/**
	 * 0 state = live cells, 1 state = dead cells
	 */
	public Location performTask(List<Location> emptyCells, Neighborhood myneighbors){
		this.setNeighborhood(myneighbors);
		int numberofliveneighbors = 0;
		for (Species tmpspecies : this.getNeighborhood().getMyNeighbors()){
			if (tmpspecies.getCurrState() == 0){
				numberofliveneighbors++;
			}
		}
		if (super.getCurrState() == 0){
			if (numberofliveneighbors < 2 || numberofliveneighbors > 3){
				super.setNextState(1);
			}
		}
		else{
			if (numberofliveneighbors == 3){
				super.setNextState(0);
			}
		}
		return null;
	}
}
