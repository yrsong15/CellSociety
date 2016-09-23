package Species;

import java.util.List;

import util.Location;

public class CellofLife extends Species{
	public CellofLife(){
		super();
	}
	@Override
	public Location performTask(List<Location> emptyCells){
		int numberofliveneighbors = 0;
		for (Species tmpspecies : super.getNeighborhood().getMyNeighbors()){
			if (tmpspecies.getState() == 0){
				numberofliveneighbors++;
			}
		}
		if (super.getState() == 0){
			if (numberofliveneighbors < 2 || numberofliveneighbors > 3){
				super.setState(1);
			}
		}
		else{
			if (numberofliveneighbors == 3){
				super.setState(0);
			}
		}
		return null;
	}
	
	
}
