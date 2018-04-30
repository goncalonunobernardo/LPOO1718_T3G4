# LPOO1718_T3G4

LPOO – Final Project
Check Point Delivery

Architecture Design



Package UML 

















Class UML





State UML














Design Patterns

•	Singleton: This design pattern is used to ensure that there’s only one instance of the classes GameController, GameModel and GameView. Given that this classes represent the physics controller, the logic of the game and what is print on the screen, it makes sense to create and have only one instance of each one throughout the life of the application.

•	Observer: This design pattern is used to check collisions between blocks and the water or coins. By using the pattern, there’s no need to repeat code and to check at every frame the collision between the referred objects.


•	Flyweight: This design pattern makes sure there’s no need to save the same sprite several times for different blocks. Given that the game has several blocks falling at the same time, it wouldn’t make sense to maintain a sprite for each block.

•	State: This design pattern makes sure the correct view is printed on the screen (the main menu, the options menu, the game and the pause). There’s a ScreenAdapter saved on the GameView that may change to PlayView, MainMenu or OptionMenu according to the user’s inputs.


•	Update Method: This design pattern is implement on the classes that extends the ScreenAdpater class that updates all the entities belonging to the view. It makes the code much more readable and allows some animations like the coins to spin and the water to move without having infinite loops.

•	Factory: This design pattern allows the GameView to create an EntityView without knowing which one to create (it depends on the Model); the EntityView to create a Sprite without knowing which to create (it depends on the subclasses) and the GameModel to create blocks without knowing if it will be a block that will increase or decrease the user’s score (it depends on the BlockModel itself).

GUI design



Test Design


•	Verifica o movimento do barco tanto para a direita como para a esquerda 

•	Verifica a perda vida quando não apanha um dos blocos

•	Verifica a perda vida quando é atingido pelos inimigos

•	Verifica a perda de um número de camadas de blocos dependendo do tipo de inimigo que o atinge

•	Verifica a perda de jogo quando perde as 2 vidas dadas

•	Apanha as moedas

•	Verifica o aumento da pontuação ao apanhar moeda

•	Verifica o incremento de tempo dado durante o jogo

•	Verifica a vitória ao acertar na meta com os blocos (singleplayer)

•	Verifica a vitória ao conseguir maior pontuação em menor tempo (multiplayer)

•	Verifica que o barco não ultrapassa o limite do ecrã

