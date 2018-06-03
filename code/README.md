# SANDY'S FLOATING BLOCKS

## Setup

### Project

1. Download the project from the corresponding GitHub repository.
2. Import project in Android Studio.
3. When prompted to update project upon start, **don't!**
4. Include the jar dependencies:
    - gdx-freetype.jar

Gradle version: 2.14.1


### Tests

To run the tests you must include 3 jar dependencys:
- gdx-box2d-gwt.jar
- gdx-box2d.jar
- gdx-box2d-natives.jar


### Application

1. Make sure your phone allows installation of unsigned APKs.
2. Download APK to you phone.
3. Install APK.


### Class UML
![uml](https://user-images.githubusercontent.com/31972761/40887454-ea0ceed8-6740-11e8-95f8-455e0037405d.png)


Documentation available at: //https://web.fe.up.pt/~up201604105

## State Diagram
![state diagram](https://user-images.githubusercontent.com/28095045/40887980-9a7c389e-6748-11e8-8bb9-0e581df378fa.png)

## Design Patterns

Besides the design patterns already implemented by the box2d and libgdx we used several others:

• MVC: Initially, when first trying to design our game we both easily agreed physics were gonna play a major role in its internal structure. Therefore, we chose this design pattern not primarily for physics, but also for the elaborate logic and overall visual environment, thus allowing our architetural aspect to be coehisive and clean.
The implementation was made dividing 3 packages, each representing one of 3 modules. Each package will have a variety of classes. There will be, however, a set of classes outside of this pattern which will serve other non-core functionalities, such as starting the application.

• Singleton: This design pattern is used to ensure that there’s only one instance of the classes GameController and GameModel. Given that this classes represent the physics controller and the logic of the game, it makes sense to create and have only one instance of each one throughout the life of the application.

• Observer: This design pattern is used to check collisions between the entities of the game. All of the collisions are handled in the class CollisionListener (on the controller package) that implements ContactListener interface. The contact listener is added to the physics world when this one is created (on the GameController constrcutor). By using the pattern, there’s no need to repeat code and to check at every frame the collision between the referred objects.
            This design pattern is also used to check if the user has pressed a button in the menus. If the inputs were handled in the render, the image would flicker so the use of this design pattern is mandatory. The actionlistener of each button is implemented in the constructor of the menu each button belongs to.

• Flyweight: This design pattern makes sure there’s no need to save the same sprite several times for different blocks. Given that the game has several blocks falling at the same time, it wouldn’t make sense to maintain a sprite for each block. This design pattern is implemented in the class ViewFactory that has a hashmap that links a model type to the view that contains the sprite. When the GameView needs to render a class it calls the ViewFactory class that returns the view for that object that was previously saved in the hash map.

• Update Method: This design pattern is implement on GameController. With the use of this design pattern, the bodies of the world are updated and give the impression of fluid movement without having infinite loops. It makes the code much more readable and allows some animations like the coins to spin and the water to move without having infinite loops.

• Factory: This design pattern allows the GameView to create a EntityView without knowing which entity is creating. This design pattern is implemented in the class ViewFactory that has a hashmap that links a model type to the view that contains the sprite. When the GameView needs to render a class it calls the ViewFactory class that returns the view for that object that was previously saved in the hash map.
            This design pattern is also implemented in the classes NextBody (controller package) and NextModel (model package). The latter decides which entityModel to create next (a coin, a block, a powerup) and the time until the next model to be created and the first class decides the angular and linear velocity of the body that represents the model just created. With this method the GameModel knows that an entity was just created but has no idea which one and the GameController knows a body has been created but does not know which one and its velocity.




## User Manual

Our game starts with an animated Main Menu, giving the user 3 options:

![main menu](https://user-images.githubusercontent.com/28095045/40888055-a26e2070-6749-11e8-97aa-1081fe62e369.png)

* “Play” moves the user to ingame playable experience
* "Options" gives the user the chance to mute ingame sound or ingame music.
* “Exit” closes the app.

If the user pressed the first option, the game moves to the its actual gaming experience.

![playgame1](https://user-images.githubusercontent.com/28095045/40888057-a2bca6f0-6749-11e8-90d3-b05d44d201b2.png)

Ingame, Sandy's have boats and their objetive is to gather as much floating blocks as they can, alongside coins and powerups to ease the experience. Through time, blocks will gain angular and linear velocity, making it more difficult to catch and build a straight tower. There's also an incremental score in regards to time playing.
For each block caught there's a 5 point gain. For coins, that's 10 added to the score. Everytime a block falls into water, the player loses 20 points.

It was decided as a team, that a "defeat" factor made no sense if apllied to this game. Thus, we decided to make this an infinite game with an ingame background that goes quite up, from land to universe.

There are 2 powerups available and they are quite rare. First there's CHEMICAL, that allows the boat to increase its size and therefore make it easier to catch. There's also DESTRUCTION that allows
all blocks above the boat to dissapear, not affecting the score.

![playgame2](https://user-images.githubusercontent.com/28095045/40888058-a2e34490-6749-11e8-8b84-2d09e95c7709.png)

If the user pressed the second option, the game to the OptionsMenu that allows the player to mute its ingame music or sound effects(fx).

![options menu](https://user-images.githubusercontent.com/28095045/40888056-a2951c84-6749-11e8-82ad-4fd3415ee98b.png)


### Controls

There are different ways to make Sandy's boat move.

1. Android
   * To control the boat's movement, the user must touch the left or right section of the phone.

   * IF the user's phone has a gyroscope implemented, the boat's movement is influenced by the Y coordinate of phone and corresponding gyroscope. It moves according to the coordinate being bigger than 5(goes right) or smaller than -5(goes left).

### Decisions Difficulties and Overall Work
Just as reported in the User Manual, our team made the decision of having an "infinite" based game so it wouldn't need to replay the same over and over again. We wanted the user to play an original and both visually and logically effective game whenever and wherever he wanted; get a highscore and challenge other friends to beat that score. There are no 'defeat' factors such as "lives" or "timers".

Initially there were a lot of difficulties in the way we wanted our game to be structured and implemented. We felt that the information that was given about LibGdx wasn't very easy to understand at first, but with some time spent we started to understand how we could connect our idea with the design patterns and logic, and effectively getting a coehisive and working structure.

Our team worked with great open comunication and work. Everytime a team member had struggles with an issue and talked about it with the other team member, that problem would be fixed with close attention and proper time. 
