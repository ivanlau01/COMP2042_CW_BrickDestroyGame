package com.example.brickdestroygame;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBall {
    private final AnchorPane scene;
    private final Circle gameBall;
    private final Rectangle paddle;
    private int x_direction = 2;
    private int y_direction = 2;

    public GameBall(AnchorPane scene, Circle gameBall, Rectangle paddle) {
        this.gameBall = gameBall;
        this.scene = scene;
        this.paddle = paddle;
        startGame();
    }
    public void movementBall(){
        gameBall.setLayoutX(gameBall.getLayoutX() + x_direction);
        gameBall.setLayoutY(gameBall.getLayoutY() + y_direction);

        boolean gamePaddleBorder = gameBall.getBoundsInParent().intersects(paddle.getBoundsInParent());
        boolean rightBorder = gameBall.getLayoutX() >= (600 - gameBall.getRadius());
        boolean leftBorder = gameBall.getLayoutX() <= (0 + gameBall.getRadius());
        boolean upBorder = gameBall.getLayoutY() <= (0 + gameBall.getRadius());
        boolean downBorder = gameBall.getLayoutY() >= (400 - gameBall.getRadius());

        if(rightBorder || leftBorder){
            reverse_x_direction();
        }
        if(upBorder || gamePaddleBorder){
            reverse_y_direction();
        }
        if (downBorder){
            reverse_y_direction();
        }
    }

    public void reverse_x_direction(){
        x_direction *= -1;
    }

    public void reverse_y_direction(){
        y_direction *= -1;
    }
    private void startGame() {
        gameBall.setLayoutX(300);
        gameBall.setLayoutY(373);
        reverse_y_direction();
    }



}
