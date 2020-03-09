package com.adonahue.battleship.ui;

import com.adonahue.battleship.dto.Board;

/**
 * BattleshipView
 */
public class BattleshipView {

    UserIO io;

    public BattleshipView(UserIO io){
        this.io = io;
    }

    //Returns int[] where int[0] is row 0-9 and int[1] is column 0-9
    public int[] getLocChoice() {
        String location = io.readString("Where you you like to take your shot?");
        char colChar = location.charAt(0);
        int[] locArr = new int[2];
        locArr[1] = location.charAt(0) - 'A';
        if (location.length() == 3){
            locArr[0] = 9;
        } else {
            locArr[0] = location.charAt(0) - '0' - 1;
        }
        return null;
    }


    public void displayBoard(Board board){
    }
}