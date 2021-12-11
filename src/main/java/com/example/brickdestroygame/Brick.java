package com.example.brickdestroygame;

import javafx.scene.paint.Color;

public class Brick {

    Color singleColour;
    Color chessColourA = Color.SILVER;
    Color chessColourB = Color.SILVER;

    public Color getSingleColourLayout(int level){
        if(level == 1){
            singleColour = Color.RED; //clay brick
        }
        else if(level == 5){
            singleColour = Color.GRAY; //Cement brick
        }
        else if(level == 6){
            singleColour = Color.SILVER; //Steel brick
        }
        return singleColour;
    }

    public Color getChessColourLayoutA(int level){
        if(level == 2){
            chessColourA = Color.RED; //Clay brick
        }
        else if(level == 3){
            chessColourA = Color.RED; //Clay brick
        }
        else if(level == 4){
            chessColourA = Color.GRAY; //Cement brick
        }
        return chessColourA;
    }

    public Color getChessColourLayoutB(int level){
        if(level == 2){
            chessColourB = Color.GRAY; //Cement brick
        }
        else if(level == 3){
            chessColourB = Color.SILVER; //Steel brick
        }
        else if(level == 4){
            chessColourB = Color.SILVER; //Steel brick
        }
        return chessColourB;
    }

}
