# CellSociety Team 17

Duke CompSci 308 Cell Society Project

### Group Members (in alphabetical order)
+ Owen Chung (tc153)
	+ Implemented back-end aspect of program, including Species and Neighborhood for each simulation
+ Chalena Maess-Scholl (cm296)
	+ Implemented back-end aspect of program, including simulation configuration for each scenario , Game Engine/Grid model, and Cell Shape variation
+ Ray Song (ys101)
	+ Implemented front-end aspect of program, including UX/UI and Grid Visualization

### Project Date
+ Project Design: September 14-17, 2016
+ Project Implementation/Engineering: September 18-October 2, 2016
+ End of Analysis: October 5th, 2016

+ Approximate Number of Hours Spent: ~90 hours

### How to Start Program
+ Run **Main.java** (located in default package of the source directory)

### How to Use Program/Configure Settings
1. Using the UI
	+ Pressing button from Starting Page will take user to Simulation Page corresponding to the label of the button.
	
	+ From Simulation Page, pressing **Run Another Simulation** button will stop simulation and take user back to Starting Page. 
	+ Pressing **Reset** will initialize grid back to original settings.
	+ Pressing **Start** will begin automated simulation that runs until otherwise instructed by user.
	+ Pressing **Stop** will halt simulation.
	+ Pressing **Step** will run a single iteration of the simulation.
	+ Pressing **Delay** will stop animation and toggle a ScrollBar, with which the user can change the simulation speed of the program (moving left will slow down speed, moving right will increase speed). After moving the scrollbar, the user can click **Reset**, and the ensuing simulation will have a new Delay value.
	+ Pressing **Cell Size** will stop animation and toggle a Text Field, on which the user will input the number of cells. This input must be a perfect square of integers, so that the grid can be configured accordingly. After typing in a value, the use can click **Reset**, and the ensuing simulation will have a new Number of Cells.


2. Configuring Settings through XML File
	+ Shape of displayed cell can be configured by toggling the 'cellShape' setting in the XML file, which is under the details tag.
	+ The initial percentage of each species for each simulation can be configured by toggling the configuration -> species -> percent settings in each XML file.
	+ Each simulation can also be set to a specific initial configuration by adding an 'initialization' tag under the corresponding species that you wish to set. For example:
		\<species type ="Ant">
    		<<initialization state = "0">>		
    			<<row>0 0 0</row>>
    			<<row>0 0 0</row>>
    			<<row>0 0 2</row>>
    		<</initialization>>
    	 <</species>>
        + The above puts 2 ants with state 0 in the bottom right position.
	+ Extra settings for each simulation (ex) probCatch for Spreading Fire model, # of turns to breed/die for Fish-Shark model) can be configured in the XML file under the general tag.
	+ The initial width and height of the grid can also be set but needs to correspond to the number given in 'numCells' i.e. if width is 3 and height is 3 numCells needs to be 9.
 
### Known Bugs
+ After 1000+ iterations of the Fish-Shark model on a sped-up version of the simulation (by changing the Delay value), the sharks start to move around less than they did before.

### Possible Improvements
+ Implementation of additional simulation scenarios
+ Implementation of extra UI features (ex: display of multiple scenarios simultaneously)
+ Implementation of more diverse cell shape configurations/dynamic interaction with cells using mouse click

### Data/Resources Required for Project
+ One image file and two Properties files (all located in the resources package of the source directory)
+ 5 XML files, each for a different simulation scenario (all located in the data directory)

### Additional Resources Consulted
+ CS308 TA: Vishnu
+ Online resources including ORACLE JavaFX Documentation
+ Example code in online forums including Stack Overflow

