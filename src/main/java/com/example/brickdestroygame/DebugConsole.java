package com.example.brickdestroygame;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class DebugConsole {

    @FXML
    private Slider levelSlider;

    @FXML
    private Slider ballCountSlider;

    protected int level = 0;
    protected int ballCount = 0;

    public void setLevel(int level){
        this.level = level;
        levelSlider.setValue(level);
    }

    public void setBallCount(int ballCount){
        this.ballCount = ballCount;
        ballCountSlider.setValue(ballCount);
    }
    @FXML
    private void skipLevelButton(){
        if(level < 6){
            level++;
            levelSlider.setValue(level);
        }
    }

    @FXML
    private void resetBallCountButton(){
        setBallCount(3);
    }

    @FXML
    private void skipLevelSlider(){
        setLevel((int)levelSlider.getValue());
    }

    @FXML
    private void ballSlider(){
        setBallCount((int)ballCountSlider.getValue());
    }

}
