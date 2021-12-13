package com.example.brickdestroygame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
class DebugConsoleTest {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    @Start
    public void start(Stage stage) throws IOException{
        this.stage = stage;
        fxmlLoader = new FXMLLoader(BrickMain.class.getResource("ConsolePage.fxml"));
        Parent Pane = fxmlLoader.load();
        Scene scene = new Scene(Pane, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Check ball count in console tab")
    void setBallCount(FxRobot robot) {
        GameController gameController = new GameController();
        robot.clickOn("#ballCountSlider");

        robot.sleep(50);
        Assertions.assertEquals(3, gameController.ballCount);
    }
}