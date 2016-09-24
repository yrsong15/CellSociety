package util;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import species.Species;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;



public abstract class Simulation {
	protected Document myXML;
	protected int speciesAdded;
	
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
	
	
	public String getElement(String element){
		return myXML.getElementsByTagName(element).item(0).getTextContent();
	}
	
	
	
	public Grid popGrid() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
	       NodeList nList = myXML.getElementsByTagName("speciesType");
	       
         for (int temp = 0; temp < nList.getLength(); temp++) {
             Node nNode = nList.item(temp);
             System.out.println("\nCurrent Element :" + nNode.getNodeName());
         }
         return myGrid;
		
		
	}
	
	
	public abstract Grid populateGrid() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	//to-do: handle errors in call to createSpecies function
	public Grid populateGrid3() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String speciesType = getElement("speciesType");
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
	       NodeList nList = myXML.getElementsByTagName("percentType");
	       int numCells = Integer.parseInt(getElement("numCells"));
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Element speciesConfig = (Element) nList.item(temp);
	            int percent = Integer.parseInt(speciesConfig.getTextContent());
                int state = Integer.parseInt(speciesConfig.getAttribute("state"));
                System.out.println(speciesType);
                int createNum = (int) (numCells*(percent/100.0));//number need to create of species w/ this state
                for (int created = 0; created < createNum; created++){
    //            	myGrid.addCell(createSpecies(speciesType, state));
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
				Species mySpecies = null;
				Location pos = new Location(temp, i);
				try {
					Class<?> speciesClass = Class.forName("Species." + speciesType);
					Constructor<?> constructor = speciesClass.getConstructor();
					mySpecies = (Species) constructor.newInstance();
					mySpecies.setCurrState(Integer.parseInt(rowVals[i]));
					mySpecies.setNextState(Integer.parseInt(rowVals[i]));
					mySpecies.setMyLocation(pos);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				myGrid.setCell(pos, mySpecies);
			}
		}
		
		return myGrid;
		
	}
	
	//to-do: handle errors
	public Species createSpecies(String speciesType) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Species mySpecies = null;
		try {
			Class<?> speciesClass = Class.forName("species." + speciesType);
			Constructor<?> constructor = speciesClass.getConstructor();
			mySpecies = (Species) constructor.newInstance();
			
		} catch (ClassNotFoundException e) {
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
