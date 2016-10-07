package simulation_parser;

import org.w3c.dom.Element;

import species.Agent;
import species.Species;


/**
 * @author Chalena Scholl
 */
public class SegregationSimulation extends SimulationParser {
	
	public SegregationSimulation(){
		setNeighborhoodType("WholeNeighborhood");
		setCellType("BasicCell");
	}
	

	@Override
	public void setSpeciesParameters(Element speciesInfo, Species mySpecies) {
		int threshold = Integer.parseInt(getElement(speciesInfo, "threshold"));
		((Agent) mySpecies).setThresholdPercentage(threshold);
		
		
	}


	@Override
	protected void setGeneralParameters() {
		// TODO Auto-generated method stub
		
	}


}
