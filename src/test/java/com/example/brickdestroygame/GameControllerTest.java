package com.example.brickdestroygame;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class GameControllerTest {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @Start
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        fxmlLoader = new FXMLLoader(BrickMain.class.getResource("GamePage.fxml"));
        Parent Pane = fxmlLoader.load();
        Scene scene = new Scene(Pane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Check the ball movement when SpaceBar key is pressed")
    void checkBallMovement(FxRobot robot) {
        Node gameBall = stage.getScene().lookup("#ball");
        double initialBallPos = gameBall.getLayoutY();
        robot.press(KeyCode.SPACE);
        robot.sleep(200);
        Assertions.assertTrue(initialBallPos > gameBall.getLayoutY());
    }

    @Test
    @DisplayName("Check the paddle movement when A Key is pressed")
    void checkPaddleMovingLeft(FxRobot robot) {
        Node paddle = stage.getScene().lookup("#paddle");
        double initialPaddlePos = paddle.getBoundsInParent().getMinX();
        robot.press(KeyCode.SPACE);
        robot.press(KeyCode.A);
        robot.sleep(50);
        Assertions.assertTrue(initialPaddlePos > paddle.getBoundsInParent().getMinX());
        robot.sleep(3000);
        Assertions.assertTrue(paddle.getBoundsInParent().getMinX() >= 0);
    }

    @Test
    @DisplayName("Check the paddle movement when D Key is pressed")
    void checkPaddleMovingRight(FxRobot robot) {
        Node paddle = stage.getScene().lookup("#paddle");
        double initialPaddlePos = paddle.getBoundsInParent().getMinX();
        robot.press(KeyCode.SPACE);
        robot.press(KeyCode.D);
        robot.sleep(50);
        Assertions.assertTrue(initialPaddlePos < paddle.getBoundsInParent().getMinX());
        robot.sleep(3000);
        Assertions.assertTrue(paddle.getBoundsInParent().getMinX() <= 600);

    }

    @Test
    @DisplayName("Check the ball count after losing a round")
    void checkBallCount(FxRobot robot) {
        GameController gameController = fxmlLoader.getController();
        robot.press(KeyCode.SPACE);
        robot.press(KeyCode.D);
        robot.sleep(5000);

        System.out.println(gameController.ballCount);
        Assertions.assertEquals(2, gameController.ballCount);
    }
}