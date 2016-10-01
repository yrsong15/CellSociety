package simulation_config;

import org.w3c.dom.Element;

import species.Species;

/**
 * @author Chalena Scholl
 */
public class GameofLifeSim extends SimulationConfig{
	
	
	public GameofLifeSim(){
		setNeighborhoodType("AllNeighbors");
	}


	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		// TODO Auto-generated method stub
		
	}




}
