package util;

import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import species.Shark;
import species.Species;
import species.WatorSpecies;

public class PredatorPreySim extends Simulation{

	public PredatorPreySim() {
		// TODO Auto-generated constructor stub
	}
	
	public Grid populateGrid() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
	    NodeList nList = myXML.getElementsByTagName("species");
        for (int temp = 0; temp < nList.getLength(); temp++) {//for each species
	            Element speciesType = (Element) nList.item(temp);
	            setProperties(speciesType, myGrid);
        }
      return myGrid;
	}
	
	public void setProperties(Element speciesType, Grid myGrid) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Species mySpecies;
        int percent = Integer.parseInt(speciesType.getElementsByTagName("percent").item(0).getTextContent());
        int breedTurns = Integer.parseInt(speciesType.getElementsByTagName("breedTurns").item(0).getTextContent());
        int numCells = Integer.parseInt(getElement("numCells"));
        String type = speciesType.getAttribute("type");
        int createNum = (int) Math.ceil((numCells*(percent/100.0)));//number need to create of species w/ this state
        for (int created = 0; created < createNum; created++){
        	mySpecies = createSpecies(type);
        	if (type.equals("Shark")){
        		int starveTurns = Integer.parseInt(speciesType.getElementsByTagName("starveTurns").item(0).getTextContent());
        		((Shark) mySpecies).setMyStarveTime(starveTurns);
        	}
        	((WatorSpecies) mySpecies).setTimeuntilBreed(breedTurns);
        	if (speciesAdded < numCells){
        		myGrid.addCell((Species) mySpecies);
        		speciesAdded++;
        	}
        }
	}
}
