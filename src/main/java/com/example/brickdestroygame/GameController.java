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
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;


public class GameController implements Initializable {

    private GameBall gameBall;
    private final ArrayList<Rectangle> bricks = new ArrayList<>();
    private int level = 1;
    private int ballCount = 3;
    private int healthBrick = 3;
    private int score = 0;
    private Stage stage;

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
        createLevel();

    }

    public void singleTypeLayout() {
        int i, j;
        int size = 3;
        int k = 0;

        Brick brick = new Brick();
        Color singleColour = brick.getSingleColourLayout(level);

        for (i = 0; i < size; i++) {
            if (i % 2 != 0) {
                Rectangle rectangle = new Rectangle(0, k, 59, 20);
                rectangle.setFill(singleColour);
                rectangle.setStroke(Color.BLACK);
                scene.getChildren().add(rectangle);
                bricks.add(rectangle);

                for (j = 0; j < 10; j++) {
                    Rectangle rectangleA = new Rectangle((32 + (j * 60)), k, 59, 20);
                    rectangleA.setFill(singleColour);
                    rectangleA.setStroke(Color.BLACK);
                    scene.getChildren().add(rectangleA);
                    bricks.add(rectangleA);
                }
            } else {
                for (j = 0; j < 10; j++) {
                    Rectangle rectangle = new Rectangle((j * 60), k, 59, 20);
                    rectangle.setFill(singleColour);
                    rectangle.setStroke(Color.BLACK);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);
                }
            }
            k += 20;
        }

    }

    public void chessBoardLayout() {

        int i, j;
        int k = 0;
        int size = 3;

        Brick brick = new Brick();
        Color chessColourA = brick.getChessColourLayoutA(level);
        Color chessColourB = brick.getChessColourLayoutB(level);

        for (i = 0; i < size; i++) {
            for (j = 0; j < 10; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60), k, 59, 20);
                    if ((i + j) % 2 == 0) rectangle.setFill(chessColourA);
                    else rectangle.setFill(chessColourB);
                    rectangle.setStroke(Color.BLACK);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);

                    for (j = 0; j < 10; j++) {
                        Rectangle rectangleA = new Rectangle((32 + (j * 60)), k, 59, 20);
                        if ((i + j) % 5 == 0) rectangleA.setFill(chessColourA);
                        else rectangleA.setFill(chessColourB);
                        rectangleA.setStroke(Color.BLACK);
                        scene.getChildren().add(rectangleA);
                        bricks.add(rectangleA);
                    }
                } else {
                    for (j = 0; j < 10; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 59, 20);
                        if ((i + j) % 2 == 0) rectangle.setFill(chessColourA);
                        else rectangle.setFill(chessColourB);
                        rectangle.setStroke(Color.BLACK);
                        scene.getChildren().add(rectangle);
                        bricks.add(rectangle);
                    }
                }
                k += 20;
            }
        }
    }

    public void createLevel() {

        bricks.clear();

        if (level == 1 || level == 5 || level == 6) {
            singleTypeLayout();
        } else if (level == 2 || level == 3 || level == 4) {
            chessBoardLayout();
        } else {
            GameOver();
        }
    }

    public boolean brickImpact(Rectangle Brick) {
        if (ball.getBoundsInParent().intersects(Brick.getBoundsInParent())) {
            boolean rightBorder = ball.getLayoutX() >= ((Brick.getX() + Brick.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= ((Brick.getX() + ball.getRadius()));
            boolean downBorder = ball.getLayoutY() >= ((Brick.getY() + Brick.getHeight() - ball.getRadius()));
            boolean upBorder = ball.getLayoutY() <= (Brick.getY() + ball.getRadius());

            if (leftBorder && downBorder) {
                if (gameBall.x_direction < 0) {
                    gameBall.reverse_y_direction();
                } else if (gameBall.y_direction < 0) {
                    gameBall.reverse_x_direction();
                } else {
                    gameBall.reverse_x_direction();
                    gameBall.reverse_y_direction();
                }
            } else if (rightBorder && upBorder) {
                if (gameBall.x_direction > 0) {
                    gameBall.reverse_y_direction();
                } else if (gameBall.y_direction > 0) {
                    gameBall.reverse_x_direction();
                } else {
                    gameBall.reverse_x_direction();
                    gameBall.reverse_y_direction();
                }
            } else if (upBorder && leftBorder) {
                if (gameBall.x_direction < 0) {
                    gameBall.reverse_y_direction();
                } else if (gameBall.y_direction > 0) {
                    gameBall.reverse_x_direction();
                } else {
                    gameBall.reverse_x_direction();
                    gameBall.reverse_y_direction();
                }
            } else if (rightBorder && downBorder) {
                if (gameBall.x_direction > 0) {
                    gameBall.reverse_y_direction();
                } else if (gameBall.y_direction < 0) {
                    gameBall.reverse_x_direction();
                } else {
                    gameBall.reverse_x_direction();
                    gameBall.reverse_y_direction();
                }
            } else {

                if (rightBorder || leftBorder) {
                    gameBall.reverse_x_direction();
                }
                if (downBorder || upBorder) {
                    gameBall.reverse_y_direction();
                }
            }
            switch (getHealthBrick(Brick)) {
                case 3:
                    Brick.setFill(Color.GRAY); //Cement brick
                    score = score + 3;
                    System.out.println("Score:" + score);
                    break;

                case 2:
                    Brick.setFill(Color.RED); //Clay brick
                    score = score + 2;
                    System.out.println("Score:" + score);
                    break;

                case 1:
                    scene.getChildren().remove(Brick);
                    score = score + 1;
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
                score = score - 1;
                System.out.println("\nTotal Score now :" + score);
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
                if (paddle.getLayoutX() < 460) {
                    paddle.setLayoutX(paddle.getLayoutX() + movementPaddle);
                }
            }

            if (spaceBarKey) {
                gameBall.movementBall(true);
            }
            if (!bricks.isEmpty()) {
                bricks.removeIf(bricks -> brickImpact(bricks));
            } else {
                gameBall.movementBall(false);
                spaceBarKey = false;
                levelLabel.setVisible(true);
                proceedNextLevel.setVisible(true);
                if (level == 6) {
                    GameOver();
                }
            }
        }
    };

    public void nextGameLevel() {
        timer.start();
        gameBall.movementBall(false);
        ball.setLayoutX(300);
        ball.setLayoutY(419);
        paddle.setLayoutX(225);
        System.out.println("Level is cleared....");
        bricks.clear();
        level++;
        createLevel();

        levelLabel.setVisible(false);
        proceedNextLevel.setVisible(false);
    }

    private int getHealthBrick(Rectangle Brick) {

        if (Brick.getFill() == Color.SILVER) {
            healthBrick = 3; //Steel brick
        } else if (Brick.getFill() == Color.GRAY) {
            healthBrick = 2; //Cement brick
        } else if (Brick.getFill() == Color.RED) {
            healthBrick = 1; //Clay brick
        }
        return healthBrick;
    }

    public void openConsolePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BrickMain.class.getResource("ConsolePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene consoleScene = new Scene(root, 300, 100);

        DebugConsole debugConsole = fxmlLoader.getController();
        debugConsole.setLevel(level);
        debugConsole.setBallCount(ballCount);
        Stage console = new Stage();

        console.initModality(Modality.APPLICATION_MODAL);
        console.initOwner(stage);
        console.setScene(consoleScene);
        console.centerOnScreen();
        console.show();
        console.setOnCloseRequest(event ->{
                ballCount = debugConsole.ballCount;
        if (level != debugConsole.level) {
            timer.stop();
            level = debugConsole.level;
            scene.getChildren().removeAll(bricks);
            createLevel();
        }
    });
}

    @FXML
    public void KeyPressed(KeyEvent event) {
        paddle.setFocusTraversable(true);
        if (event.getCode() == KeyCode.A) {
            aKey.set(true);
        }
        if (event.getCode() == KeyCode.D) {
            dKey.set(true);
        }
        if (event.getCode() == KeyCode.SPACE) {
            spaceBarKey = true;
            timer.start();
        }
        if (event.getCode() == KeyCode.P) {
            pKey.set(true);
            timer.stop();
            System.out.println("Game is Paused!");
        }
        if(event.getCode() == KeyCode.F1){
            timer.stop();
            try {
                openConsolePage();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void KeyReLeased(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            aKey.set(false);
        }
        if (event.getCode() == KeyCode.D) {
            dKey.set(false);
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