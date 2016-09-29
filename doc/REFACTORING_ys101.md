# Lab: Peer Code Review

### Link to Commit
[Here is the commit](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team17/commit/913f68a28567924eb6aca49f152b8ff3e2d664c9) that contains my refactored code.

### Refactoring Explanation
+ During my first sprint, I combined all of my methods into a single file called **OneFileUI.java** because I had been unable to figure out the communication system between my classes within the **user_interface** package. Upon thinking, I decided to place the bulk of the code in a **Controller** class, then place all other methods in other classes sorted according to function: **ButtonController**, **GridController, SceneController**, and **ScrollbarController**.

+ This refactoring not only gets rid of the 400+ line file that was **OneFileUI.java**, but also gets rid of a lot of dependency on global variables, which was a glaring issue in the code I had put out during Sprint One.

+ Moreover, by implementing and utilizing getter/setter methods for many private variables contained within different classes, I could further reduce dependency on global constants, variables, and object references.

+ As a minor add-on, I was able to add more String values to my properties file, so that I now have no global constant values that represent different strings.