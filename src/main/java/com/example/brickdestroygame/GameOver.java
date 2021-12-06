package com.example.brickdestroygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameOver {

    public void HomePage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader (BrickMain.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
