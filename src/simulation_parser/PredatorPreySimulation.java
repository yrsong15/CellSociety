package simulation_parser;


import org.w3c.dom.Element;
import species.Fish;
import species.Shark;
import species.Species;


/**
 * @author Chalena Scholl
 */
public class PredatorPreySimulation extends SimulationParser{

	public PredatorPreySimulation() {
		setNeighborhoodType("PlusNeighborhood");
		setCellType("Cell");
	}
	

	@Override
	public void setSpeciesParameters(Element speciesInfo, Species mySpecies) {
		int breedTurns = Integer.parseInt(getElement(speciesInfo, "breedTurns"));
    	if (speciesInfo.getAttribute("type").equals("Shark")){
    		int starveTurns = Integer.parseInt(getElement(speciesInfo, "starveTurns"));
    		((Shark) mySpecies).setStandardStarveTime(starveTurns);
    		((Shark) mySpecies).setStandardBreedTime(breedTurns);
    	}
    	else{
    		((Fish) mySpecies).setStandardBreedTime(breedTurns);
    	}
	}


	@Override
	protected void setGeneralParameters() {
		// TODO Auto-generated method stub
		
	}
}
