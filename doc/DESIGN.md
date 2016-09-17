# Design for Cell Society

### Introduction

+ The objective of this project is to come up with a fluid, flexible platform on which various Cellular Automata programs - including, but not limited to Model of Segregation, Predator-Prey Relationships, and Spreading of Fire - can be run on.

+ Abstraction is the key to our project, in that the platform must be able to account for different types of CA models. As such, we have come up with three key components to the project, which are as follows:
	* **Grid Template**: This is the basic template on which the *Game Engine* and each *Species* will operate.
	* **Game Engine** : This is the *Game Loop* of our project that will not only give the start/stop signs for each step of the game, but also store the *status quo* of each state so that individual species know whether to change their state or not once the Game Loop begins.
	* **Species** : This is the abstract model on which individual species will be based. Each individual species (ex: Sharks, Fire, different races) will work on the module provided by the Species superclass and manipulate the module so that specific details inherent to each model can be provided. The species class will be open to extension but closed to algorithms.

	

### Overview

+ **Grid Template**
	* The **"map"** on which each individual cell operates
	* Could be implemented with a 2D array ***or*** a map, should be prone to switching between one or the other
	* Runs algorithm that update the cells locations
	* Should be open to changes of the shape of the grid but the interfaces should be closed

+ **Game Engine**
	* Gets satisfaction from species and marks cells to be updated
	* Updates grids and species
	* Interfaces should be closed to changes in algorithm and should be open extensions for other new features
	
+ **Game Loop** 
	* Runs the *Game Loop* including the start/stop command for each step of the process
	* Initializes *Game Engine*

+ **Species**
	* Subclasses that represent different states of the cell, as well as the algorithms that are in charge of how each cell responds given its current state & surroundings
	* Interacts with neighbors based on its status at the given point in time
	* Different kinds of species will extend from the Species superclass and could have different characteristics. In other words, the structure will be open to extension. On the other hand, it will be closed to changes in algorithms. For example, different ways of defining satisfaction of a species should not lead to code changes in other classes. The other classes should follow the same open closed principle. 
### Design Overview Diagram

	![Design Overview Diagram](Design_Overview.png "Design Overview Diagram")

	

### User Interface

+ When Main.java is executed, a short splash message displaying our team name and individual members' names will be shown. After the message disappears, the Main Page will feature an empty grid on the left-hand side, as well as four buttons on the right-hand side that each lead to different simulations: **Segregation, Fish-Shark, Spreading Fire, and Other**.
	* The grid will always be in the same location, regardless of what page the user is on.
	* **Please refer to the picture at the end of this section for visual reference.**

+ Each simulation model page will contain the following features in common:
	* **Reset** (button): The grid will be initialized to its starting state.
	* **Start** (button): The game engine will run the game loop indefinitely, until the end conditions are reached or until the stop button is clicked.
	* **Stop** (button): The game engine will halt the game loop.
	* **Play** (button): The game engine will run a single iteration of the game loop.
	
	* **Size of Cell** (text box): The user will input an integer that determines the size of each cell on the grid.
	* **Delay** (text box): The user will input an integer that determines the delay between each iteration of the game loop.
	
	* **Run Another Simulation** (button): The user will be moved back to the **Main Page**, the initial page that contains the empty grid and the four buttons that each lead to different simulations.

+ **Segregation** model will also contain the following:
	* **Similar** (text box): User will input an integer between 0-100 to denote the percentage of similar neighbors that are required for happiness.
	*  **Ratio** (text box): User will input an integer between 0-100 to denote the initial ratio between the two ethnicities represented.
	* **Empty** (text box): User will input an integer between 0-100 to denote the percentage of empty cells.
	
+ **Fish-Shark** model will also contain the following:
	* **Fish %** (text box): User will input an integer between 0-100 to denote the initial percentage of fish.
	* **Shark %** (text box): User will input an integer between 0-100 to denote the initial percentage of shark.
	* **Fish Breed %** (text box): User will input an integer to denote the number of turns it takes for a single fish to breed.
	* **Shark Breed %** (text box): User will input an integer to denote the number of turns it takes for a single shark to breed.
	* **Shark Starve %** (text box): User will input an integer to denote the number of turns it takes for a single shark to starve, i.e. the number of consecutive turns it takes for the shark to die without consuming any fish.

+ **Spreading Fire** model will also contain the following:
	* **Fish %** (text box): User will input an integer between 0-100 to denote the percentage of a tree catching fire.
	
+ **Other** model will also contain the following:
	* Specific details required for simulation( ex: Game of Life model)
	

![User Interface Diagram](user_interface_diagram.jpg "User Interface Diagram")

### Design Details

+ **Grid Template**
	+ The grid template keeps track of each species location on the grid and has functions such as getEmptyCells()
	  that will return a list of cells that are empty and can be moved to. This will allow each species to implement
	  its own algorithm of movement/reaction without having to gain access to the entire grid.
	+ When initialized, it is also responsible for positioning each species on the grid.
	+ The resources this class uses is the Species class.



+ **Game Engine**
	+ Responsible for checking whether each species is satisfied in its current position and saving the unsatisfied
	  species. species.move() can then be called on each of these, thus updating the grid and its species positions.
	

	
+ **Game Loop** 
	+ Initializes the game engine and the grid template (based on input from the user/XML file), so these are also what its resources are and what is has access to.
	+ Also controls how quickly the simulation advances through the state of the world. Each cycle updates the state of the world until the loop hits its ending point, which could either be a timer, a certain state of cells, or the stop button.
	+ Each cycle calls a run method in the game engine, which causes it to complete checking for species
	  satisfaction and updating their states once more.


+ **Species**
	+ Species will be an abstract super class so that subclasses can be create that each know their own state and 
	  the rules that dictate how it responds to its environment, which is dependent on what other species it is closely surrounded by. 
	+ Each subclass will have a function (stateOfSatisfaction(lNeighb, rNeighb, uNeighb, dNeighb) that, given its surrounding neighbors as parameters, will return a value specifying whether or not it is satisfied with its state. If the cell is an edge cell, those neighbor directions that are over the edge boundary will simply be null. Before it returns this value it will also update its own knowledge about whether or not it was satisfied with its last position, or the last information it was given about its neighbors. 
	+ Each subclass	will also have its own algorithm of movement or reaction to a certain state, so each subclass
	also needs to implement a move function that decides where to move on the grid. The move function will be passed the result of the getEmptySpaces() function in the grid template so that each species can make its decision about where to move based on its own specific algorithm but still without gaining access to the entire grid itself.
	
+ **Use Cases**
	1. Grid template class would call stateOfSatisfaction(...) on the species subclass, which would update the state of the cell in addition to returning a value about whether it is satisfied. If the result is false, it would also call .move() on the species subclass while passing the open cells in the grid as a parameter. However, since the game of life simulation does not require movement to another cell but rather just the switching of a live/dead state, nothing would happen in this .move() function as implemented in the species subclass. 	
	2. Same as above.
	3. Call the run method in the game engine, which loops through and updates each cell. After this, the UI would need to be called in order to perform the graphical/visual updates needed.
	4. Need to discuss where simulation parameters are being saved.
	5. Also need to discuss where UI interface/class is comging from. Is it the game engine? Is it in main?
	
### Design Considerations

### Team Responsibilities

+ One person in charge of Species - Owen
+ One person in charge of Game Engine - Chalena
+ One person in charge of grid/miscellaneous components - Ray


