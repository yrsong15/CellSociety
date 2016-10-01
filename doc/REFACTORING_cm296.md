# Lab: Peer Code Review
+ Chalena Scholl (cm296)

### Link to Commit
[Here is the commit](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team17/commit/bb5e73b94b42d49ee377c20db42720a19dfbfbb2) 
that contains my re-factored code.

### Design Issue
+ Each species was being passed the actual copy of the the list of species which represented the neighbors. This
  means that each species could manipulate it's neighbors however it wanted. Moreover, each species was just looping 
  over this copy of the list to find neighbors of a certain state. Therefore, I re-factored the code to 
  pass each species the actual instance of the Neighborhood class and added a function to the Neighborhood superclass
  that takes in an integer (representing a state) and returns a list of locations that have that state.
  
+ I believe this is better because it reduces duplicated code (each species having code that does the same thing by looping over copy of neighbors) and also implements better encapsulation.