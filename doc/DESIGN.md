# Design for Cell Society

### Introduction

+ The objective of this project is to come up with a fluid, flexible platform on which various Cellular Automata programs - including, but not limited to Model of Segregation, Predator-Prey Relationships, and Spreading of Fire - can be run on.

+ Abstraction is the key to our project, in that the platform must be able to account for different types of CA models. As such, we have come up with three key components to the project, which are as follows:
	* **Grid Template**: This is the basic template on which the *Game Engine* and each *Species* will operate.
	* **Game Engine** : This is the *Game Loop* of our project that will not only give the start/stop signs for each step of the game, but also store the *status quo* of each state so that individual species know whether to change their state or not once the Game Loop begins.
	* **Species** : This is the abstract superclass on which individual species will be based. Each individual species (ex: Sharks, Fire, different races) will work on the module provided by the Species superclass and manipulate the module so that specific details inherent to each model can be provided.

+ The Species class will be the most fluid of them all, in that it will have to be able to accommodate many different types of cell models.

	

### Overview

+ **Grid Template**
	* The **"map"** on which each individual cell operates
	* Could be implemented with a 2D array ***or*** a map, should be prone to switching between one or the other

+ **Game Engine**
	* Runs the *Game Loop* including the start/stop command for each step of the process
	* Stores the current state of cells so that each individual cell knows whether to change its state or not

+ **Species**
	* Subclasses that represent different states of the cell, as well as the algorithms that are in charge of how each cell responds given its current state & surroundings
	* Interacts with neighbors based on its status at the given point in time

	

### User Interface

### Design Details

### Design Considerations

### Team Responsibilities

+ One person in charge of Species - Owen
+ One person in charge of Game Engine - Chalena
+ One person in charge of grid/miscellaneous components - Ray


