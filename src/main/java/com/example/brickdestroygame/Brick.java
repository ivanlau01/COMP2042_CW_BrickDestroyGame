package com.example.brickdestroygame;

import javafx.scene.paint.Color;

public class Brick {

    Color singleColour;
    Color chessColourA = Color.SILVER;
    Color chessColourB = Color.SILVER;

    public Color getSingleColourLayout(int level){
        if(level == 1){
            singleColour = Color.RED; //clay
            System.out.println("Single1");
        }
        else if(level == 5){
            System.out.println("Single2");
            singleColour = Color.GRAY; //Cement
        }
        else if(level == 6){
            System.out.println("Single3");
            singleColour = Color.SILVER; //Steel
        }
        return singleColour;
    }

    public Color getChessColourLayoutA(int level){
        if(level == 2){
            System.out.println("Chess1");
            chessColourA = Color.RED; //Clay
        }
        else if(level == 3){
            System.out.println("Chess2");
            chessColourA = Color.RED; //Clay
        }
        else if(level == 4){
            System.out.println("Chess3");
            chessColourA = Color.GRAY; //Cement
        }
        return chessColourA;
    }

    public Color getChessColourLayoutB(int level){
        if(level == 2){
            System.out.println("Chess1");
            chessColourB = Color.GRAY; //Cement
        }
        else if(level == 3){
            System.out.println("Chess2");
            chessColourB = Color.SILVER; //Steel
        }
        else if(level == 4){
            System.out.println("Chess3");
            chessColourB = Color.SILVER; //Steel
        }
        return chessColourB;
    }

}
