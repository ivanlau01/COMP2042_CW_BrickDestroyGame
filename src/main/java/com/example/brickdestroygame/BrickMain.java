package com.example.brickdestroygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class BrickMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        System.out.println(System.getProperty("user.dir"));
        Parent root = FXMLLoader.load(BrickMain.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(root, 450, 300);

        Image icon = new Image("brick.jpg");
        stage.getIcons().add(icon);
        stage.setTitle("Brick Destroy Game");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}