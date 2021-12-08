package com.example.brickdestroygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void onToGamePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root,600,450);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onToGuidePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuidePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private AnchorPane scenePane;

    public void onToExitPage() {

        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Exit");
        stage.close();
    }

}
