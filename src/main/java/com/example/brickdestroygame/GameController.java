package com.example.brickdestroygame;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private GameBall gameBall;
    private boolean pause = false;
    private final ArrayList<Rectangle> bricks = new ArrayList<>();
    private int gameLevel = 1;
    private int ballCount = 3;
    private int healthBrick = 3;
    private int score = 0;

    @FXML
    private Rectangle paddle;

    @FXML
    private AnchorPane scene;

    @FXML
    private Circle ball;

    @FXML
    private Label levelLabel;

    @FXML
    private Button proceedNextLevel;

    private final BooleanProperty aKey = new SimpleBooleanProperty();
    private final BooleanProperty dKey = new SimpleBooleanProperty();
    private final BooleanProperty pKey = new SimpleBooleanProperty();
    private Boolean spaceBarKey = false;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timer.start();
        paddle.setFocusTraversable(true);
        gameBall = new GameBall(ball);
        createBricks();

    }
    public void createBricks() {

        int k = 0;
        Color colour = Color.SILVER;

        if(gameLevel == 1){
            colour = Color.RED;
        }
        else if(gameLevel == 2){
            colour = Color.GRAY;
        }
        else if(gameLevel == 3){
            colour = Color.SILVER;
        }
        int i;
        int j;
        for(i = 0; i < 3; i++) {
            for( j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle((j * 61), k, 60, 28);
                rectangle.setFill(colour);
                scene.getChildren().add(rectangle);
                bricks.add(rectangle);
            }
            k += 30;
        }
    }

    public boolean brickImpact(Rectangle Brick){
        if(ball.getBoundsInParent().intersects(Brick.getBoundsInParent())){
            boolean rightBorder = ball.getLayoutX() >= ((Brick.getX() + Brick.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= ((Brick.getX() + ball.getRadius()));
            boolean downBorder = ball.getLayoutY() >= ((Brick.getY() + Brick.getHeight() -ball.getRadius()));
            boolean upBorder = ball.getLayoutY() <= (Brick.getY() + ball.getRadius());

            if(rightBorder || leftBorder){
                gameBall.reverse_x_direction();
            }
            if(downBorder || upBorder){
                gameBall.reverse_y_direction();
            }
            switch(getHealthBrick(Brick)) {
                case 3:
                    Brick.setFill(Color.GRAY);
                    score++;
                    System.out.println("Score:" + score);
                    break;

                case 2:
                    Brick.setFill(Color.RED);
                    score++;
                    System.out.println("Score:" + score);
                    break;

                case 1:
                    scene.getChildren().remove(Brick);
                    score++;
                    System.out.println("Score:" + score);
                    return true;
                }
            }
        return false;
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            gameBall.checkImpact();
            gameBall.BallImpactGamePaddle(paddle);

            if (gameBall.BallImpactLowestBorder()) {
                gameBall.movementBall(false);
                paddle.setLayoutX(225);
                spaceBarKey = false;
                ballCount--;
                System.out.println("\nLife:\n" + ballCount);
                if (ballCount == 0) {
                    GameOver();
                }
            }
            int movementPaddle = 5;
            if (aKey.get() && spaceBarKey) {
                if (paddle.getLayoutX() > 0) {
                    paddle.setLayoutX(paddle.getLayoutX() - movementPaddle);
                }
            }

            if (dKey.get() && spaceBarKey) {
                if (paddle.getLayoutX() < (540 - 75)) {
                    paddle.setLayoutX(paddle.getLayoutX() + movementPaddle);
                }
            }

            if (spaceBarKey) {
                gameBall.movementBall(true);
            }
            if (pKey.get()) {
                pauseGame();
            }
            if (!bricks.isEmpty()) {
                bricks.removeIf(bricks -> brickImpact(bricks));
            }
            else {
                System.out.println("CONGRATULATIONS!");
                gameBall.movementBall(false);
                spaceBarKey = false;
                pauseGame();
                levelLabel.setVisible(true);
                proceedNextLevel.setVisible(true);
                if (gameLevel == 3) {
                    GameOver();
                }
            }
        }
    };

        public void nextGameLevel(ActionEvent event) {
            timer.start();
            gameBall.movementBall(false);
            ball.setLayoutX(300);
            ball.setLayoutY(386);
            paddle.setLayoutX(225);
            System.out.println("Level is cleared....");
            bricks.clear();
            gameLevel++;
            createBricks();

            levelLabel.setVisible(false);
            proceedNextLevel.setVisible(false);
        }

    private int getHealthBrick(Rectangle Brick) {

        if(Brick.getFill() == Color.SILVER){
            healthBrick = 3;
        }
        else if(Brick.getFill() == Color.GRAY){
            healthBrick = 2;
        }
        else if(Brick.getFill() == Color.RED) {
            healthBrick = 1;
        }
            return healthBrick;
    }


    @FXML
    public void KeyPressed(KeyEvent event){
        paddle.setFocusTraversable(true);
        if (event.getCode() == KeyCode.A){
            aKey.set(true);
        }
        if (event.getCode() == KeyCode.D){
            dKey.set(true);
        }
        if (event.getCode() == KeyCode.SPACE){
            spaceBarKey = true;
        }
        if (event.getCode() == KeyCode.P){
            pKey.set(true);
        }
    }

    @FXML
    public void KeyReLeased(KeyEvent event){
        if (event.getCode() == KeyCode.A){
            aKey.set(false);
        }
        if (event.getCode() == KeyCode.D){
            dKey.set(false);
        }
    }

    public void pauseGame(){
        pause = !pause;
        if(pause){
            timer.stop();
            System.out.println("Game is Paused!");
        }
        else{
            timer.start();
            System.out.println("Game is Continued...");
        }
    }
    public void GameOver(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(BrickMain.class.getResource("GameOverPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) this.scene.getScene().getWindow();
            GameOver gameOver = fxmlLoader.getController();
            gameOver.totalScore(score);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}