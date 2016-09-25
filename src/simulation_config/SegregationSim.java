package simulation_config;

import org.w3c.dom.Element;

import species.Agent;
import species.Species;

public class SegregationSim extends SimulationConfig {
	
	public SegregationSim(){
		setNeighborhoodType("AllNeighbors");
	}
	

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		int threshold = Integer.parseInt(super.getElement(speciesInfo, "threshold"));
		((Agent) mySpecies).setThresholdPercentage(threshold);
		
		
	}


}