package cells;

import java.util.ArrayList;
import java.util.List;

import species.Species;
import util.Location;

public class PredatorPreyCell extends Cell{

	public PredatorPreyCell(Location where) {
		super(where);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyEffect(Species incoming){
		if (incoming.isPredator()){
			List<Species>copyOccupants = new ArrayList<Species>(getOccupants());
			for (Species inCell : copyOccupants){
				if (inCell.isPrey()){
					getOccupants().remove(inCell);
				}
			}
		}
	}

}
