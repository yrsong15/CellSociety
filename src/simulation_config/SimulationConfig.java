package simulation_config;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import species.Species;
import util.Grid;
import util.Location;

import org.w3c.dom.Node;
import org.w3c.dom.Element;



public abstract class SimulationConfig {
	protected Document myXML;
	protected int speciesAdded;
	
	
	/**
	 * prepares given xml document for parsing
	 * @param filename xml file to be parsed
	 */
	public void getXMLDoc(String filename){
		
		try{
		    File inputFile = new File(filename);
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(inputFile);
		    doc.getDocumentElement().normalize();
		    myXML = doc;
		 }			
			
		catch (Exception e) {
			System.err.println("Invalid XML file");
			e.printStackTrace();
            throw new RuntimeErrorException(new Error());
     }
	}
	
	
	/**
	 * 
	 * @param parent parent tag of the tag to retrieve string value from
	 * @param element name of the tag in the xml file
	 * @return returns the string value content of the tag in the xml file
	 */
	public String getElement(Element parent, String tagName ){
		return parent.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	
	/**
	 * @param element name of the tag in the xml file
	 * @return returns the string value content of the tag in the xml file
	 */
	public String getElement(String tagName ){
		return myXML.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	
	public abstract void setParameters(Element speciesInfo, Species mySpecies);
	
	public Grid populateGrid(){		
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
	    NodeList speciesList = myXML.getElementsByTagName("species");
	    int numCells = Integer.parseInt(getElement("numCells"));
	    for (int curr = 0; curr < speciesList.getLength(); curr++) {//for each species
            Element currSpecies= (Element) speciesList.item(curr);
            String speciesType = currSpecies.getAttribute("type");	            
            NodeList percentList = currSpecies.getElementsByTagName("percent");
    	    for (int i = 0; i < percentList.getLength(); i++) {//for each percent of state in species
	            int percent = Integer.parseInt(percentList.item(i).getTextContent());
	            int createNum = (int) Math.ceil((numCells*(percent/100.0)));//number need to create of species w/ this state
	            for (int created = 0; created < createNum; created++){
	            	if (speciesAdded < numCells){
		            	Species mySpecies = createSpecies(speciesType);
		            	mySpecies.setCurrState(Integer.parseInt(((Element) percentList.item(i)).getAttribute("state")));
		            	mySpecies.setNextState(Integer.parseInt(((Element) percentList.item(i)).getAttribute("state")));
		            	this.setParameters(currSpecies, mySpecies);
	            		myGrid.addCell((Species) mySpecies);
	            		speciesAdded++;
	            	}	            	
	            }
    	    }
	    }
	    return myGrid;
	}
	
	//handle these errors better; meaning catch them
	public Grid populateGridTest() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String speciesType = getElement("speciesType");
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
		NodeList nList = myXML.getElementsByTagName("row");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			String[] rowVals = nNode.getTextContent().split(" ");
			for (int i = 0; i < rowVals.length; i++){
				Location pos = new Location(temp, i);
				Species mySpecies = createSpecies(speciesType);
				mySpecies.setCurrState(Integer.parseInt(rowVals[i]));
				mySpecies.setNextState(Integer.parseInt(rowVals[i]));
				mySpecies.setMyLocation(pos);
				myGrid.setCell(pos, mySpecies);
			}
		}
		
		return myGrid;
		
	}
	
	//to-do: handle errors better
	public Species createSpecies(String speciesType) {
		Species mySpecies = null;
		try {
			Class<?> speciesClass = Class.forName("species." + speciesType);
			Constructor<?> constructor = null;
			try {
				constructor = speciesClass.getConstructor();
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			mySpecies = (Species) constructor.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Species provided in XML file cannot be found, does not have a proper constructor, or is not a valid class");
			e.printStackTrace();
		} 
		
		return mySpecies;
	}

	
	public int getGridHeight(){
		return Integer.parseInt(getElement("height"));
		
	}
	
	public int getGridWidth(){
		return Integer.parseInt(getElement("width"));
		
		
	}
}
