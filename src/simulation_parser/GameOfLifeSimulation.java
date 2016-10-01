package simulation_parser;

import org.w3c.dom.Element;

import species.Species;

/**
 * @author Chalena Scholl
 */
public class GameOfLifeSimulation extends SimulationParser{
	
	
	public GameOfLifeSimulation(){
		setNeighborhoodType("WholeNeighborhood");
		setCellType("Cell");
	}


	@Override
	public void setSpeciesParameters(Element speciesInfo, Species mySpecies) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void setGeneralParameters() {
		// TODO Auto-generated method stub
		
	}




}
