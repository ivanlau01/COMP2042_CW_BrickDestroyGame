package com.example.brickdestroygame;

import javafx.scene.paint.Color;

public class Brick {

    Color singleColour;
    Color chessColourA = Color.SILVER;
    Color chessColourB = Color.SILVER;

    public Color getSingleColourLayout(int level){
        if(level == 1){
            singleColour = Color.RED; //clay
        }
        else if(level == 5){
            singleColour = Color.GRAY; //Cement
        }
        else if(level == 6){
            singleColour = Color.SILVER; //Steel
        }
        return singleColour;
    }

    public Color getChessColourLayoutA(int level){
        if(level == 2){
            chessColourA = Color.RED; //Clay
        }
        else if(level == 3){
            chessColourA = Color.RED; //Clay
        }
        else if(level == 4){
            chessColourA = Color.GRAY; //Cement
        }
        return chessColourA;
    }

    public Color getChessColourLayoutB(int level){
        if(level == 2){
            chessColourB = Color.GRAY; //Cement
        }
        else if(level == 3){
            chessColourB = Color.SILVER; //Steel
        }
        else if(level == 4){
            chessColourB = Color.SILVER; //Steel
        }
        return chessColourB;
    }

}
