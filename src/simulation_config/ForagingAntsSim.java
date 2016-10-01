package simulation_config;

import org.w3c.dom.Element;

import species.Species;

public class ForagingAntsSim extends SimulationConfig {

	public ForagingAntsSim(){
		setNeighborhoodType("AllNeighbors");
	}
	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		// TODO Auto-generated method stub

	}

}
