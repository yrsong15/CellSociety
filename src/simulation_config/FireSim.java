package simulation_config;

import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import species.Species;
import species.Tree;
import util.Grid;



public class FireSim extends SimulationConfig{

	public FireSim() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Grid populateGrid() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
        int numCells = Integer.parseInt(getElement("numCells"));
	    Element speciesType = (Element) myXML.getElementsByTagName("species").item(0);
	    NodeList states = speciesType.getElementsByTagName("percent");
        for (int i = 0; i < states.getLength(); i++) {//state of species
	            Element percentState = (Element) states.item(i);
	            int percent = Integer.parseInt(percentState.getTextContent());
	            int state = Integer.parseInt(percentState.getAttribute("state"));
        		float probCatchFire = Integer.parseInt(speciesType.getElementsByTagName("probCatchFire").item(0).getTextContent());
        		int createNum = (int) Math.ceil((numCells*(percent/100.0)));//number need to create of species w/ this state
	            for (int created = 0; created < createNum; created++){
	            	Species mySpecies = createSpecies(speciesType.getAttribute("type"));
	            	((Tree) mySpecies).setProbabilityBurn(probCatchFire);
	            	 mySpecies.setCurrState(state);
	            	if (speciesAdded < numCells){
	            		myGrid.addCell(mySpecies);
	            		speciesAdded++;
	            	}
	            }
	}
      return myGrid;
	}
}
