package com.adonahue.battleship.ui;

import com.adonahue.battleship.dto.Board;

/**
 * BattleshipView
 */
public class BattleshipView {

    UserIO io;
    String p1Name;
    String p2Name;

    public BattleshipView(UserIO io){
        this.io = io;
    }

    //Returns int[] where int[0] is row 0-9 and int[1] is column 0-9
    public int[] getLocChoice() {
        String location = io.readString("Where you you like to take your shot?");
        int[] locationArr = new int[2];
        locationArr[1] = location.charAt(0) - 'A';
        if (location.length() == 3){
            locationArr[0] = 9;
        } else {
            locationArr[0] = location.charAt(0) - '0' - 1;
        }
        return null;
    }


	public void displayBeginBanner() {
	}

	public String getName() {
		return null;
	}

	public void printTurn() {
	}


    public void displayBoard(Board board){
            // TODO: add the column letters with correct spacing
        for (int i = 0; i < board.getBoard().length; i++) {
                System.out.println("");
                // should print out row numbers
                System.out.print(i+1 + ".");
            for (int j = 0; j < board.getBoard().length; j++) {
                System.out.print(board.getBoard()[i][j] + "|");
            }
        }
    }
}