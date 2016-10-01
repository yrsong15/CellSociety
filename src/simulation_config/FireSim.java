package simulation_config;


import org.w3c.dom.Element;
import species.Species;
import species.Tree;


/**
 * @author Chalena Scholl
 */
public class FireSim extends SimulationConfig{
	

	public FireSim() {
		setNeighborhoodType("PlusNeighbors");
	}

	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		float probCatchFire = Float.parseFloat(super.getElement(speciesInfo, "probCatchFire"));
		((Tree) mySpecies).setProbabilityBurn(probCatchFire);
	}

	
	
}
