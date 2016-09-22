import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
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
	//handle these errors better; meaning catch them
	public Grid populateGrid() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
					Class<?> speciesClass = Class.forName(speciesType);
					Constructor<?> constructor = speciesClass.getConstructor();
					mySpecies = (Species) constructor.newInstance();
					mySpecies.setState(Integer.parseInt(rowVals[i]));
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				myGrid.setCell(pos, mySpecies);
			}
		}
		
		return myGrid;
		
	}

	
	public int getGridHeight(){
		return Integer.parseInt(getElement("height"));
		
	}
	
	public int getGridWidth(){
		return Integer.parseInt(getElement("width"));
		
		
	}



	
	
	


}
