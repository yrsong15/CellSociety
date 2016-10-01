package simulation_parser;

import org.w3c.dom.Element;

import species.Agent;
import species.Species;


/**
 * @author Chalena Scholl
 */
public class SegregationSimulation extends SimulationParser {
	
	public SegregationSimulation(){
		setNeighborhoodType("AllNeighbors");
	}
	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		int threshold = Integer.parseInt(super.getElement(speciesInfo, "threshold"));
		((Agent) mySpecies).setThresholdPercentage(threshold);
		
		
	}


}
