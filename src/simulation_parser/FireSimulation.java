package simulation_parser;


import org.w3c.dom.Element;
import species.Species;
import species.Tree;


/**
 * @author Chalena Scholl
 */
public class FireSimulation extends SimulationParser{
	

	public FireSimulation() {
		setNeighborhoodType("PlusNeighborhood");
		setCellType("BasicCell");
	}

	

	@Override
	public void setSpeciesParameters(Element speciesInfo, Species mySpecies) {
		float probCatchFire = Float.parseFloat(getElement(speciesInfo, "probCatchFire"));
		((Tree) mySpecies).setProbabilityBurn(probCatchFire);
	}



	@Override
	protected void setGeneralParameters() {
		// TODO Auto-generated method stub
		
	}

	
	
}
