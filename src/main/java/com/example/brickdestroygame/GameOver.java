package com.example.brickdestroygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class GameOver implements Initializable{

    public String fileName = "C:\\Users\\qm579\\IdeaProjects\\brickdestroygame\\src\\main\\java\\com\\example\\brickdestroygame\\FinalHighestScore";
    public int highScoreText;
    @FXML
    private Label highScore;

    @FXML
    private Label totalScore;

    void totalScore(int score) throws IOException{
        totalScore.setText(String.valueOf(score));

        if(score > highScoreText){
            highScore.setText(String.valueOf(score));
            writeHighScoreFile(score);
        }
        else{
            highScore.setText(String.valueOf(highScoreText));
        }
    }
    public void readHighScoreFile() throws IOException{
        File file = new File(fileName);
        FileReader file_reader = new FileReader(file);
        BufferedReader buffer_reader = new BufferedReader(file_reader);

        String textScore;

        while ((textScore = buffer_reader.readLine()) != null){
            highScoreText = Integer.parseInt(textScore);
        }
    }
    public void writeHighScoreFile(int score) throws IOException{
        File file = new File(fileName);
        FileWriter file_writer = new FileWriter(file,false);
        file_writer.write(String.valueOf(score));
        file_writer.close();
    }

    public void HomePage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader (BrickMain.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try{
            readHighScoreFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
