package simulation_config;


import org.w3c.dom.Element;

import species.Fish;
import species.Shark;
import species.Species;

public class PredatorPreySim extends SimulationConfig{

	public PredatorPreySim() {
		setNeighborhoodType("PlusNeighbors");
	}
	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		int breedTurns = Integer.parseInt(super.getElement(speciesInfo, "breedTurns"));
    	if (speciesInfo.getAttribute("type").equals("Shark")){
    		int starveTurns = Integer.parseInt(super.getElement(speciesInfo, "starveTurns"));
    		((Shark) mySpecies).setStandardStarveTime(starveTurns);
    		((Shark) mySpecies).setStandardBreedTime(breedTurns);
    	}
    	else{
    		((Fish) mySpecies).setStandardBreedTime(breedTurns);
    	}
	}
}
