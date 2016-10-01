package simulation_config;
import org.w3c.dom.Element;
import species.Species;

public class ForagingAnts extends SimulationConfig{
	
	public ForagingAnts() {
		setNeighborhoodType("WholeNeighbors");
	}

	@Override
	public void setParameters(Element speciesInfo, Species mySpecies) {
	}

	


}
