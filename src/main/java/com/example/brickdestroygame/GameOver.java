package com.example.brickdestroygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;

public class GameOver {

    @FXML
    private Label totalScore;

    void totalScore(Integer score ){
        totalScore.setText(score.toString());
    }

    public void HomePage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader (BrickMain.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
