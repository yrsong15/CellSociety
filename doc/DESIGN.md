# Design for Cell Society

### Introduction

+ The objective of this project is to come up with a fluid, flexible platform on which various Cellular Automata programs - including, but not limited to Model of Segregation, Predator-Prey Relationships, and Spreading of Fire - can be run on.

+ Abstraction is the key to our project, in that the platform must be able to account for different types of CA models. As such, we have come up with three key components to the project, which are as follows:
	* **Grid Template**: This is the basic template on which the Game Engine and each Species will operate.
	* **Game Engine** : This is the *Game Loop* of our project that will not only give the start/stop signs for each step of the game, but also store the *status quo* of each state so that individual species know whether to change their state or not once the Game Loop begins.
	* **Species** : This is the abstract model on which individual species will be based. Each individual species (ex: Sharks, Fire, different races) will work on the module provided by the Species superclass and manipulate the module so that specific details inherent to each model can be provided.

+ While we plan to keep the Grid Template 

	

### Overview

+ Grid Template
	* Made of Cells
	* Could be implemented with a 2D array **or** a map, should be prone to switch

+ Species superclass
	* Subclasses that represent different algorithms
	* Save *"neighbors"* in each species instance
	* Interacts with neighbors based on its status at the given point in time

+ Game Engine class
	* Runs the *Game Loop* 
	* Stores "cells to be changed" 
	

### User Interface

### Design Details

### Design Considerations

### Team Responsibilities

+ One person in charge of Species
+ One person in charge of Game Engine
+ One person in charge of grid/miscellaneous components


