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

import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    private GameBall gameBall;
    private boolean pause = false;
    private final ArrayList<Rectangle> bricks = new ArrayList<>();
    private int gameLevel = 1;

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
    private final BooleanProperty spaceBarKey = new SimpleBooleanProperty();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timer.start();
        paddle.setFocusTraversable(true);
        gameBall = new GameBall(scene, ball, paddle);
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

        for(double i = 0; i < 1; i++) {
            for(double j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle((j * 60), k, 60, 30);
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
            scene.getChildren().remove(Brick);
            return true;
        }
        return false;
    }
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            int movementPaddle = 5;
            if (aKey.get()) {
                if (paddle.getLayoutX() > 0) {
                    paddle.setLayoutX(paddle.getLayoutX() - movementPaddle);
                }
            }

            if (dKey.get()) {
                if (paddle.getLayoutX() < (540 - 75)) {
                    paddle.setLayoutX(paddle.getLayoutX() + movementPaddle);
                }
            }

            if(spaceBarKey.get()) {
                gameBall.movementBall();
            }
            if(pKey.get()){
                pauseGame();
            }
            if(!bricks.isEmpty()){
                bricks.removeIf(bricks -> brickImpact(bricks));
            }
            else{
                System.out.println("CONGRATULATIONS!");
                pauseGame();
                levelLabel.setVisible(true);
                proceedNextLevel.setVisible(true);

            }
        }
    };
    public void nextGameLevel(ActionEvent event){
        ball.setLayoutX(300);
        ball.setLayoutY(387);
        paddle.setLayoutX(225);
        System.out.println("Level is cleared....");
        bricks.clear();
        gameLevel++;
        createBricks();
        levelLabel.setVisible(false);
        proceedNextLevel.setVisible(false);
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
            spaceBarKey.set(true);
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


}