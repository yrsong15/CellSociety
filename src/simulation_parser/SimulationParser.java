package simulation_parser;
import java.io.File;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import species.Agent;
import species.Ant;
import species.GameOfLife;
import species.Fish;
import species.Shark;
import species.Species;
import species.Tree;
import util.Grid;
import util.Location;

import org.w3c.dom.Node;
import org.w3c.dom.Element;


/** @author Chalena Scholl, Ray Song(ys101)
*/
public abstract class SimulationParser {
	private Document myXML;
	private String neighborhoodType;
	protected int speciesAdded;
	
	private final int DEFAULT_CELL_NUMBER = 100;
	private int numRows;
	private int numCols;
	private int numCells;

	
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
	 * @return populated grid based on values and configuration settings in given XML file
	 */
	public Grid populateGrid(){		
		Grid firstGrid = new Grid(getGridHeight(), getGridWidth(), neighborhoodType);
    	initNumCells();
    	firstGrid = thePopulationLoop(firstGrid);
	    return firstGrid;
	}
	
	public Grid repopulateGrid(){
		Grid newGrid = new Grid(numRows, numCols, neighborhoodType);
		newGrid = thePopulationLoop(newGrid);
		return newGrid;
	}
	
	public Grid thePopulationLoop(Grid grid){
		NodeList speciesList = myXML.getElementsByTagName("species");
	    for (int curr = 0; curr < speciesList.getLength(); curr++) {//for each species
            Element currSpecies= (Element) speciesList.item(curr);     
        	if (initialConfigurationGiven(currSpecies)){
        		specificConfiguration(grid, currSpecies);
        	}
        	else{
        		randomConfiguration(grid, currSpecies);
        	}
	    }
	    return grid;
	}
	
	public boolean initialConfigurationGiven(Element currSpecies){
		return (currSpecies.getElementsByTagName("initialization")).getLength() > 0;
	}
	
	public void specificConfiguration(Grid grid, Element currSpecies){ 
		NodeList allRows = currSpecies.getElementsByTagName("row");
		for (int row = 0; row < allRows.getLength(); row++) {
			Node rowNode = allRows.item(row);
			String[] rowVals = rowNode.getTextContent().split(" ");
			initializeRow(row, rowVals, grid, currSpecies);
		}
	}
			
	public void initializeRow(int row, String[] rowVals, Grid mainGrid, Element currSpecies){
		String speciesType = currSpecies.getAttribute("type");	
		int state = Integer.parseInt(((Element)currSpecies.getElementsByTagName("initialization").item(0)).getAttribute("state"));
		for (int cell = 0; cell<rowVals.length; cell++){
			int numInCell = Integer.parseInt(rowVals[cell]);
			for (int j = 0; j<numInCell; j++){
            	Species toAdd = createSpecies(speciesType);
            	toAdd.setCurrState(state);
            	toAdd.setNextState(state);
            	this.setParameters(currSpecies, toAdd);
            	mainGrid.addToGrid(new Location(row, cell), toAdd);
			}
		}
		
	}
	
	public void randomConfiguration(Grid grid, Element currSpecies){
        NodeList percentList = currSpecies.getElementsByTagName("percent");
        String speciesType = currSpecies.getAttribute("type");	 
	    for (int i = 0; i < percentList.getLength(); i++) {//for each percent of state in species
            int percent = Integer.parseInt(percentList.item(i).getTextContent());
            int createNum = (int) Math.ceil((numCells*(percent/100.0)));//number need to create of species w/ this state
            for (int created = 0; created < createNum; created++){
            	if (speciesAdded < numCells){
	            	Species mySpecies = createSpecies(speciesType);
	            	mySpecies.setCurrState(Integer.parseInt(((Element) percentList.item(i)).getAttribute("state")));
	            	mySpecies.setNextState(Integer.parseInt(((Element) percentList.item(i)).getAttribute("state")));
	            	this.setParameters(currSpecies, mySpecies);
            		grid.addRandomly(mySpecies);
            		speciesAdded++;
            	}	            	
            }
	    }
	}
	
	
	/**  
	 * @param speciesType String representing object that needs to be initialized; needs to exactly
	 * match spelling and be a subclass of Species in the species package
	 * @return instance of species given as String
	 */
	public Species createSpecies(String speciesType) {
		Species mySpecies = null;
		if (speciesType.equals("Agent")){
			mySpecies = new Agent();
		}
		else if(speciesType.equals("CellofLife")){
			mySpecies = new GameOfLife();
		}
		else if(speciesType.equals("Fish")){
			mySpecies = new Fish();
		}
		else if(speciesType.equals("Shark")){
			mySpecies = new Shark();
		}
		else if(speciesType.equals("Tree")){
			mySpecies = new Tree();
		}
		else if(speciesType.equals("Ant")){
			mySpecies = new Ant();
			
		}
			
			
		return mySpecies;
	}

	
	/**
	 * @return height of the grid, or how many rows it contains
	 */
	public int getGridHeight(){
		if(numRows != 0) return numRows;
		return Integer.parseInt(getElement("height"));
	}
	
	/**
	 * @return width of the grid, or how many columns it contains
	 */
	public int getGridWidth(){
		if(numCols != 0) return numCols;
		return Integer.parseInt(getElement("width"));
	}
	
	public void initNumCells(){
		numRows = Integer.parseInt(getElement("height"));
		numCols = Integer.parseInt(getElement("width"));
		numCells = Integer.parseInt(getElement("numCells"));
	}
	
	public void setCellSize(int input){
		if(input == 0){
			input = DEFAULT_CELL_NUMBER;
		}
		numCells = input;
		numRows = (int)Math.sqrt(input);
		numCols = (int)Math.sqrt(input);
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
	
	
	/** 
	 * @return type of neighborhood this simulation takes into account (how many neighbors and which ones)
	 */
	public String getNeighborhoodType() {
		return this.neighborhoodType;
	}
	
	
	/**
	 * @param type sets type of neighborhood this simulation takes into account (how many neighbors and which ones)
	 * parameter has to exactly correspond to subclass of Neighborhood super class
	 */
	protected void setNeighborhoodType(String type){
		neighborhoodType = type;
	}
	
	
	/**
	 * Sets any additional parameters needed for each specific simulation
	 */
	public abstract void setParameters(Element speciesInfo, Species mySpecies);
	
	
}
