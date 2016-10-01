# Refactoring for Cell Society

### Introduction

We realized that our implementation of the grid and game engine would not be able to support foraging ant simulation. Hence, Chalena and I conducted a series of refactoring of the code. The original code directly stored the species onto the grid and therefore could not handle multiple species(actors) in one grid. We thought about putting a List of Species on the grid instead, but realized that it would be a bad design because of mutliple layers of Lists in the code and complicated implementation. After spending some time on the overall design and other implementation, we decided to create a new class called Cell that keeps track of the species on the grid. 

### Details

By making a Cell class, we added a layer of abstraction in our implementation of the grid. Grid now stores a 2D array of cells instead of just a species(agent). This allows us to hide the implementation of different details of what a Cell should do. For example, before refactoring we had to check whether or not the species on the cell was null. This is considered a bad design because of readability and complicated code. The new Cell class allows us to have getFreeSpaces() method for example on a Cell that checks whether or not the Cell has free spaces. The implementation is hidden now and the code looks a lot cleaner than before.