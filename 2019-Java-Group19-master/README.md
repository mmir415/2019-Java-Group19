# 2019-Java-Group19
Unicorn Dragon Studios

Java game repository for M.Hassaan Mirza and Kimberley Evans-Parker
---
**To run the game:**

cd into the src folder: `$ cd src`

compile all java files: `$ javac *.java`

run from Game.java: `$ java Game`


`Game` Starting point of the code. Initialises everything.

`World` Loads all the different rooms and objects within the rooms. 

`Handler` allows everything to connect to everything else

`Display`, `FontLoader`, `ImageLoader` help with the displays. 


`Assets` holds all images used in the game. Initialised at launch. 

`Animation` used by player and creatures to load animation

`Spritesheet` loads and crops spritesheets to tiles

`Tile` Based on the room's terrain and type of tile needed, will grab an image from `Assets`. 

`WallTile` implements `Tile`, but cannot be moved through


`Building`, `Tree`, `Rock` and `Gargoyle` are all `StaticEnities` that drop items when destroyed

`MorningStar` is a static entity that wins you the game when you get it.

`PuzzleSwitch` turns on when stepped on, when all are on, `Door` allows you to walk through.

`Portal` sends the player to a given location.

`StaticEntities` impliments `Enitiy`

`Player` is an entity. This also implements attacks and tracks movements for collision detection and new world loading

`Creature` is also an entity, but moves and potentially causes damage

`EntityManager` keeps track of all `Enities` 


`Item` is an object that can be picked up (rock, wood, etc.)

`ItemManager`keeps track of all `Items` that haven't been added to `Inventory`

`Inventory` keeps track of everything the player has picked up


`ClickListener`, `KeyManager`, `MouseInput` & `MouseManager` checks and deals with for inputs

`GameCamera` allows the screen to move about


`CommandList` is the menu shown which displays all available actions the player can do

`Menu` is the main menu, which is displayed when the game first runs


maps contains the different map layouts as txt files

sprites contains the different spritesheets as png files


**Map design**

1 -> 2 -> 3 -> 4

    |

    V

    5 -> 6 -> 7

    |

    V

    8 -> 9 -> 10 -> 11

