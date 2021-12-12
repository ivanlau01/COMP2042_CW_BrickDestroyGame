# Brick Destroy Game #
* Brick Destroy is a classic retro game which have an objective of destroying all the bricks from a ball controlled by using the game paddle. You must destroy all the bricks in order to proceed to the next level.
* This coursework is about maintaining and extending re-implementation of a brick breaker game given from this link shown : https://github.com/FilippoRanza/Brick_Destroy
----
## Refactoring Changes ##
* The codes given from the link are completely converted into JavaFX.
* The design of the game are being completed by using Scene Builder.
* MVC design patterns are applied in JavaFX. For example my model are named (name).java, view from GUI which are converted in FXML files and controller are named (name)Controller.java.
* Player.java file is merged with GameController.java.

----
## Additional Features ##
* The animation of crack is changed to colour changing of brick types when it's hit by the ball.
* Score is added and can be seen in the game page while playing the game.
* A game over screen of showing total score earned and highest score will be shown after the player ended the game.
* The rewards of the game are given when the player destroys the brick type of clay, cement and steel. If the player destroys one clay brick, the score board will add 1 score. For the cement brick, the score board will add 2 score and lastly for the steel brick, the score board will add 3.
* The penalties of the game is by losing points if the player drops the ball and didn't catch it by the paddle.
* A Guide Page was did to increase the understanding of player on how to play this game and what are the keys to control the paddle movement.
* The game is able to continue when the player press pause halfway through the game.
* Additional of levels in the game is added too.
* Added background image in Home Page, Guide Page, Game Page and Game Over Page.
