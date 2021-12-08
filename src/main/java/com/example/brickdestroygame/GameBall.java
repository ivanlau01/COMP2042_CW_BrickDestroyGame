package com.example.brickdestroygame;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameBall {
    private final Circle gameBall;
    public int x_direction = 3;
    public int y_direction = 3;

    public GameBall(Circle gameBall) {
        this.gameBall = gameBall;
    }

    public void movementBall(Boolean movement) {
        if (movement) {
            gameBall.setLayoutX(gameBall.getLayoutX() + x_direction);
            gameBall.setLayoutY(gameBall.getLayoutY() + y_direction);
        }
    }

    public void checkImpact(){

        boolean rightBorder = gameBall.getLayoutX() >= (600 - gameBall.getRadius());
        boolean leftBorder = gameBall.getLayoutX() <= (0 + gameBall.getRadius());
        boolean upBorder = gameBall.getLayoutY() <= (0 + gameBall.getRadius());
        boolean downBorder = gameBall.getLayoutY() >= (455 - gameBall.getRadius());

        if(rightBorder || leftBorder){
            reverse_x_direction();
        }
        if(upBorder || downBorder){
            reverse_y_direction();
        }
    }

    public void BallImpactGamePaddle(Rectangle paddle){
        if (gameBall.getBoundsInParent().intersects(paddle.getBoundsInParent())){
            boolean rightBorder = gameBall.getLayoutX() >= ((paddle.getLayoutX() + paddle.getWidth()) - gameBall.getRadius());
            boolean leftBorder = gameBall.getLayoutX() <= ((paddle.getLayoutX() + gameBall.getRadius()));
            boolean upBorder = gameBall.getLayoutY() <= (paddle.getLayoutY() + gameBall.getRadius());
            boolean downBorder = gameBall.getLayoutY() >= ((paddle.getLayoutY() + paddle.getHeight() - gameBall.getRadius()));


            if(rightBorder || leftBorder){
                reverse_x_direction();
            }
            if(upBorder || downBorder){
                reverse_y_direction();
            }
        }
    }

    public Boolean BallImpactLowestBorder(){
        if(gameBall.getLayoutY() >= (445 - gameBall.getRadius())) {
            gameBall.setLayoutX(300);
            gameBall.setLayoutY(419);
            return true;
        }
        else{
            return false;
        }
    }

    public void reverse_x_direction(){
        x_direction *= -1;
    }

    public void reverse_y_direction(){
        y_direction *= -1;
    }

}
