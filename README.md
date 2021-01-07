# Rogue-Game
This is a text-based game written in Java for my Object Oriented Programming class. The game is based off of the Rogue Game which was originally developed in the 1980s.

## Relevant Files 
### Action.java
A parent class consisting of functions which assist actions of the player and monster

### Armor.java
A class dedicated to all the armours available in the game

### Char.java
Class that displays all the characters in a message to the user interface 

### Creature.java
A parent class that consists of functions describing the characteristics of monsters and player

### CreatureAction.java
Child class of the Action class for all monsters and player

### Displayable.java
Parent class consisting of functions dedicated to displayable objects

### DropPack.java
A class dedicated to the curse of dropping items from the player's pack each time player attacks a monster

### Dungeon.java
Class to build and add all the monsters, player, rooms, and passages 

### DungeonXMLHandler.java
File that parses xml files to gather all information regarding the dungeon 

### EndGame.java
Ends the game when player wants to quit or player dies

### Hallucinate.java
A class dedicated to having the player hallucinate once they read the scroll

### Item.java
Parent class that encompasses all the necessary information for each item available in the game

### ItemAction.java
Child class of the Action class for all items in the game

### KeyStrokePrinter.java
Class that executes all the functions related to the keyboard inputs

### Monster.java
Child class for the creature class and is dedicated to the monsters

### ObjectDisplayGrid.java
Displays all the objects onto the user interface

### Passage.java
Class responsible for storing information about all the passages in the game

### Player.java
Child class for the creature class and is dedicated to the player

### Rogue.java
Main file

###Room.java
Functions dedicated to storing information about the items and creatures in the room

### Scroll.java
Child class of items dedicated to scrolls

### Sword.java
Child class of items dedicated to swords

### Teleport.java
Monster teleports each time you attack him

### Final.mp4
Video demonstrates the functionality of the game
