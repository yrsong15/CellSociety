package simulation_parser;
import org.w3c.dom.Element;

import cells.AntCell;
import species.Ant;
import species.Shark;
import species.Species;
import species.Tree;
import util.Grid;
import util.Location;

public class ForagingAntsSimulation extends SimulationParser{
	
	public ForagingAntsSimulation() {
		setNeighborhoodType("WholeNeighborhood");
		setCellType("AntCell");
	}

	@Override
	public void setSpeciesParameters(Element speciesInfo, Species mySpecies) {
		int lifeSpan = Integer.parseInt(getElement("lifeSpan"));
		((Ant) mySpecies).setStandardLifeTime(lifeSpan);
		
		int maxPheromones = Integer.parseInt(getElement("maxPheromones"));
		((Ant) mySpecies).setMaxPheromones(maxPheromones);
		
		

	}

	@Override
	protected void setGeneralParameters() {
		Grid mainGrid = getGrid();
		String[] nest= getElement("nestLocation").split(" ");
		Location nestLocation = new Location(Integer.parseInt(nest[0]), Integer.parseInt(nest[1]));
		((AntCell)mainGrid.getCell(nestLocation)).setIsNest(true);
		
		String[] food= getElement("foodLocation").split(" ");
		Location foodLocation = new Location(Integer.parseInt(food[0]), Integer.parseInt(food[1]));
		((AntCell)mainGrid.getCell(foodLocation)).setIsFoodSource(true);
		
	}

	


}
