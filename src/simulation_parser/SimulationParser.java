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
	private String cellType;
	private int speciesAdded;
	
	private final int DEFAULT_CELL_NUMBER = 100;
	private int numRows;
	private int numCols;
	private int numCells;
	private Grid mainGrid;
	private String cellShape;


	/**
	 * prepares given xml document for parsing
	 * @param filename xml file to be parsed
	 */
	public void prepareXMLDoc(String filename){
		try{
		    File inputFile = new File(filename);
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(inputFile);
		    doc.getDocumentElement().normalize();
		    myXML = doc;
		 }			
		catch (Exception e) {
            throw new RuntimeErrorException(new Error("Could not find XML file to prepare for parsing."));
     }
	}
	
	
	/**
	 * @return populated grid based on values and configuration settings in given XML file
	 */
	public Grid populateGrid(){	
		mainGrid = new Grid(getGridHeight(), getGridWidth(), neighborhoodType, cellType);
    	initNumCells();
    	mainGrid = thePopulationLoop(mainGrid);
    	setGeneralParameters();
    	setCellShape(getElement("cellShape"));
	    return mainGrid;
	}
	
	/**
	 * @return populated grid based on default values
	 */
	public Grid repopulateGrid(){
		mainGrid = new Grid(numRows, numCols, neighborhoodType, cellType);
		mainGrid = thePopulationLoop(mainGrid);
		setGeneralParameters();
		setCellShape(getElement("cellShape"));
		return mainGrid;
	}
	
	private Grid thePopulationLoop(Grid grid){
		NodeList speciesList = myXML.getElementsByTagName("species");
	    for (int curr = 0; curr < speciesList.getLength(); curr++) {//for each species, create as given
            Element currSpecies = (Element) speciesList.item(curr);     
        	if (initialConfigurationGiven(currSpecies)){
        		specificConfiguration(grid, currSpecies);
        	}
        	else{
        		randomConfiguration(grid, currSpecies);
        	}
	    }
	    return grid;
	}
	
	
	private boolean initialConfigurationGiven(Element currSpecies){
		return (currSpecies.getElementsByTagName("initialization")).getLength() > 0;
	}
	
	
	private void specificConfiguration(Grid grid, Element currSpecies){ 
		NodeList allRows = currSpecies.getElementsByTagName("row");
		for (int row = 0; row < allRows.getLength(); row++) {
			Node rowNode = allRows.item(row);
			String[] rowVals = rowNode.getTextContent().split(" ");
			initializeRow(row, rowVals, grid, currSpecies);
		}
	}
			

	private void randomConfiguration(Grid grid, Element currSpecies){
        NodeList percentList = currSpecies.getElementsByTagName("percent");
	    for (int i = 0; i < percentList.getLength(); i++) {//for each percent of state in species
            int percent = Integer.parseInt(percentList.item(i).getTextContent());
            int createNum = (int) Math.ceil((numCells*(percent/100.0)));//number need to create of species w/ this state
            int state = (Integer.parseInt(((Element) percentList.item(i)).getAttribute("state")));
            for (int created = 0; created < createNum; created++){
            	if (speciesAdded < numCells){
            		Species toAdd = createSpecies(currSpecies, state);
            		grid.addRandomly(toAdd);
            		speciesAdded++;
            	}	            	
            }
	    }
	}
	
	/**
	 * 
	 * @param row number of row currently initializing
	 * @param rowVals a string array containing the value of each thing in the row
	 * @param mainGrid a reference to the grid we are parsing into
	 * @param currSpecies XML element that contains info to create species
	 */
	private void initializeRow(int row, String[] rowVals, Grid mainGrid, Element currSpecies){
		int state = Integer.parseInt(((Element)currSpecies.getElementsByTagName("initialization").item(0)).getAttribute("state"));
		for (int cell = 0; cell<rowVals.length; cell++){
			int numInCell = Integer.parseInt(rowVals[cell]);
			for (int j = 0; j<numInCell; j++){
            	Species toAdd = createSpecies(currSpecies, state);
            	Location currLoc = new Location (row, cell);
            	toAdd.setCurrLocation(currLoc);
            	toAdd.setNextLocation(currLoc);
            	mainGrid.addToGrid(currLoc, toAdd);
			}
		}
	}
	
	/**
	 * 
	 * @param currSpecies XML Element that currently getting info to create species from
	 * @param state
	 * @return
	 */
	private Species createSpecies(Element currSpecies, int state) {
		String speciesType = currSpecies.getAttribute("type");	
		Species toAdd = getSpeciesInstance(speciesType);
		toAdd.setCurrState(state);
		toAdd.setNextState(state);
		this.setSpeciesParameters(currSpecies, toAdd);
		return toAdd;
	}
	
	/**  
	 * @param speciesType String representing object that needs to be initialized; needs to exactly
	 * match spelling and be a subclass of Species in the species package
	 * @return instance of species given as String
	 */
	private Species getSpeciesInstance(String speciesType) {
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
	
	protected void setCellType(String type){
		cellType = type;
	}
	
	
	
	/**
	 * Sets any additional parameters needed for each specific simulation
	 */
	protected abstract void setSpeciesParameters(Element speciesInfo, Species mySpecies);
	
	protected abstract void setGeneralParameters();
	
	protected Grid getGrid(){
		return mainGrid;
	}
	
	public String getCellShape() {
		return cellShape; 
	}


	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}
}
