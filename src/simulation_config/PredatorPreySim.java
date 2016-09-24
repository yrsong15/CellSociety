package simulation_config;


import org.w3c.dom.Element;

import species.Shark;
import species.Species;
import species.WatorSpecies;

public class PredatorPreySim extends SimulationConfig{

	public PredatorPreySim() {
		setNeighborhoodType("AllNeighbors");
	}
	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		int breedTurns = Integer.parseInt(super.getElement(speciesInfo, "breedTurns"));
		((WatorSpecies) mySpecies).setTimeuntilBreed(breedTurns);
    	if (speciesInfo.getAttribute("type").equals("Shark")){
    		int starveTurns = Integer.parseInt(super.getElement(speciesInfo, "starveTurns"));
    		((Shark) mySpecies).setMyStarveTime(starveTurns);
    	}
	}
}
