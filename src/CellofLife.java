
public class CellofLife extends Species{
	public CellofLife(){
		super();
	}
	@Override
	public void move(){
		int numberofliveneighbors = 0;
		Iterable<Species> iter = super.getNeighborhood().getMyNeighbors();
		for (Species tmpspecies : iter ){
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
	}
	
}
