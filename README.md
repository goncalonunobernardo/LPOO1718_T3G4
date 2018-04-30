# LPOO1718_T3G4

LPOO – Final Project
Check Point Delivery

Authors:
Ana Sá e Sousa Carneiro da Silva
Gonçalo Nuno Bernardo


Architecture Design


Package UML 


Class UML

![blank diagram-3](https://user-images.githubusercontent.com/31972761/39451355-50c67602-4cc6-11e8-937e-2c9da3fd19c3.png)



State UML

![blank diagram-2](https://user-images.githubusercontent.com/31972761/39451357-50e78630-4cc6-11e8-890d-a0d12088dab7.png)



Design Patterns

•	Singleton: This design pattern is used to ensure that there’s only one instance of the classes GameController, GameModel and GameView. Given that this classes represent the physics controller, the logic of the game and what is print on the screen, it makes sense to create and have only one instance of each one throughout the life of the application.

•	Observer: This design pattern is used to check collisions between blocks and the water or coins. By using the pattern, there’s no need to repeat code and to check at every frame the collision between the referred objects.

•	Flyweight: This design pattern makes sure there’s no need to save the same sprite several times for different blocks. Given that the game has several blocks falling at the same time, it wouldn’t make sense to maintain a sprite for each block.

•	State: This design pattern makes sure the correct view is printed on the screen (the main menu, the options menu, the game and the pause). There’s a ScreenAdapter saved on the GameView that may change to PlayView, MainMenu or OptionMenu according to the user’s inputs.

•	Update Method: This design pattern is implement on the classes that extends the ScreenAdpater class that updates all the entities belonging to the view. It makes the code much more readable and allows some animations like the coins to spin and the water to move without having infinite loops.

•	Factory: This design pattern allows the GameView to create an EntityView without knowing which one to create (it depends on the Model); the EntityView to create a Sprite without knowing which to create (it depends on the subclasses) and the GameModel to create blocks without knowing if it will be a block that will increase or decrease the user’s score (it depends on the BlockModel itself).



GUI design

![mockups](https://user-images.githubusercontent.com/31972761/39451354-50939a16-4cc6-11e8-85b7-a9485827a1ec.png)



Test Design

•	Checks the boat's movement to the right and to the left

•	Checks life loss when a block is not catched

•	Checks life loss when a enemie block is catched

•	Checks the loss of blocks depending of the enemie that hits it

•	Checks the loss of the game when it has lost its 2 lives

•	Catches the coins

•	Checks the score increasement when a coin is catched

•	Checks the time increasement during the game

•	Checks the victory when the heigth is reached (singleplayer)

•	Checks the victory with greater score in less time (multiplayer)

•	Checks if the boat does not pass through the screen limits

