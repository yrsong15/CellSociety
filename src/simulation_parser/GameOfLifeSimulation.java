package simulation_parser;

import org.w3c.dom.Element;

import species.Species;

/**
 * @author Chalena Scholl
 */
public class GameOfLifeSimulation extends SimulationParser{
	
	
	public GameOfLifeSimulation(){
		setNeighborhoodType("AllNeighbors");
	}


	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
		// TODO Auto-generated method stub
		
	}




}
