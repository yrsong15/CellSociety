package cells;

import java.util.ArrayList;
import java.util.List;
import species.Species;
import util.Location;

//This entire file is part of my masterpiece.
//Chalena Scholl  

public class PredatorPreyCell extends Cell{

	public PredatorPreyCell(Location where) {
		super(where);
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

	
	@Override
	public void step() {		
	}

}
