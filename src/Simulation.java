import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import Species.Species;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;



public class Simulation {
	private Document myXML;
	private String simulationName;
	private String simulationTitle;
	
	public Simulation(){
		
	}
	
	
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
	
	public void getRoot(){

	    System.out.println("Root element :" + myXML.getDocumentElement().getNodeName());
	    

	}
	
	public String getElement(String element){
		return myXML.getElementsByTagName(element).item(0).getTextContent();
	}
	
	//to-do: handle errors in call to createSpecies function
	public Grid populateGrid() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String speciesType = getElement("speciesType");
		Grid myGrid = new Grid(getGridHeight(), getGridWidth());
	       NodeList nList = myXML.getElementsByTagName("percentType");
	       int numCells = Integer.parseInt(getElement("numCells"));
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Element speciesConfig = (Element) nList.item(temp);
	            int percent = Integer.parseInt(speciesConfig.getTextContent());
                int state = Integer.parseInt(speciesConfig.getAttribute("state"));
                int createNum = (int) (numCells*(percent/100.0));//number need to create of species w/ this state
                for (int created = 0; created < createNum; created++){
                	myGrid.addCell(createSpecies(speciesType, state));
                }
	         }
	         return myGrid;
	}
	
	//to-do: handle errors
	public Species createSpecies(String speciesType, int state) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Species mySpecies = null;
		try {
			Class<?> speciesClass = Class.forName(speciesType);
			Constructor<?> constructor = speciesClass.getConstructor();
			mySpecies = (Species) constructor.newInstance();
			mySpecies.setState(state);
			
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
