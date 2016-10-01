package simulation_parser;


import org.w3c.dom.Element;
import species.Species;
import species.Tree;


/**
 * @author Chalena Scholl
 */
public class FireSimulation extends SimulationConfiguration{
	

	public FireSimulation() {
		setNeighborhoodType("PlusNeighbors");
	}

	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		float probCatchFire = Float.parseFloat(super.getElement(speciesInfo, "probCatchFire"));
		((Tree) mySpecies).setProbabilityBurn(probCatchFire);
	}

	
	
}
