# JetsProject

# Hi 👋, I'm Jacob Stuart
### Programming student with Skill Distillery

- I’m currently working on [JetsProject](https://github.com/VulcanMindForge/JetsProject)

- I’m currently learning **Java**

- All of my projects are available at [https://github.com/VulcanMindForge](https://github.com/VulcanMindForge)

# Description of this project
Alternatives allowed in place of Jets. This program will use a fleet of Airships. Information about the airships can be pulled, methods called, and ships created or removed. 

# How to use this project
The program starts with a greeting and a menu welcoming you to fleet administration. Follow the menu prompts to retrieve data or perform actions to effect the fleet. You can enter combat, take off or dock all ships, start a pirate battle, load cargo for transport, and add or remove ships from your fleet.

# Technologies Used
Overall I am learning and using Eclipse IDE to program in Java 1.8. Prior to this assignment we covered: Variables and Constants, Methods, Screen Output and Keyboard Input, Expressions, If/Else, Switch, While loops, For Loops, Cast Numerals, Packages, Stringbuilder, Objects, Classes, Superclasses, JUnit testing, TDD or Testing Driven Developement, UML class diagrams, Encapsulation, Inheritance in Java, Visibility, ASCII and Unicode Data.

**New since last project:** Abstract classes, Primitive Wrapper Classes, Interfaces, ArrayLists, Lists, Sets, Exceptions, Exceptions with JUnit, Input/Output Streams, Garbage Collection, Debugging Programs Overview.

# Current Progress
Made initial UML and planned states and behaviors(Likely missed some)
Created all classes
Created method stubs, contructors, and overrides
Created Menu and menu switch 
File in on program start
Display all ships
Initiate fly command on all ships
Display ship(s) with best speed
Display ship(s) with longest range
Load cargo
	Classes handles how much cargo can be loaded
	Call cargo method on any class implementing cargo interface
Pirate defense implemented
	Create pirate tower/airfield
	Populate with pirates from txt file
	Announce battle
	Call attack method on any class with combat interface
	compare number of weapons total between both groups
	more weapons = winner
Implemented add ship method
Implemented remove ship method
Implemented quit to stop program and escape infinite loop
Fixed toString to print out information in easier way
Edited menu to be more easily changed in the future
Set up menu to flip between docked or in flight
Set up menu to flip between interface methods depending on flight status
Added a tour menu option to make sure tour ships are accounted for
Add ability to take out a single ship
Add menu option to save ship list to new txt file
Created pilot class
Haven't implemented Pilot class to app.

# Next Steps
Update UML to finalized version.
Add ability to fight a ship one on one
Randomly assign pilots
Add option to change initial list to user desired list
Implement offload Cargo option
Fix error handling so only one error shows depending on exceptions thrown

# Lessons learned or reinforced on this project
Start getting in the habit of least privelage. Keep private what you can, it can be made public later if needed with less chance of causing issues with code.
Abstract methods, interfaces, and classes help to make code more organized and improve readability.
Airships are fun!
ASCII alt codes are much harder on a mac
It is easy to end up duplicating code, must be more mindful of that.
Plan out methods better, take a second run through plan after first coding session, make adjustments.
File Input/Output can be incredibly useful


# Lessons to research
Menu/Switch class to use with future projects
Sort interface or class to use with future projects


# Link to initial UML Files
[Class Diagrams UML](https://github.com/VulcanMindForge/JetsProject/tree/main/UML%20Diagrams)