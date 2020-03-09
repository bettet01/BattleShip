package com.adonahue.battleship.ui;

import java.util.List;

import com.adonahue.battleship.dto.Board;
import com.adonahue.battleship.dto.Ship;

/**
 * BattleshipView
 */
public class BattleshipView {

    UserIO io;
    String p1Name;
    String p2Name;

    public BattleshipView(UserIO io) {
        this.io = io;
    }

    // Returns int[] where int[0] is row 0-9 and int[1] is column 0-9
    public int[] getLocChoice() {
        String location = io.readString("Where you you like to take your shot?");
        int[] locationArr = new int[2];
        locationArr[1] = location.charAt(0) - 'A';
        if (location.length() == 3) {
            locationArr[0] = 9;
        } else {
            locationArr[0] = location.charAt(0) - '0' - 1;
        }
        return null;
    }

    public void displayBeginBanner() {
        io.print("--- Starting Game ---");
    }

    public String getName(String player) {
        return io.readString("Please Enter Name for Player" + player + ": ");
    }

    public void printTurn(boolean p1Turn) {
        if(p1Turn){
            io.print("It's player 1's turn.");
        } else{
            io.print("It's player 2's turn.");
        }
    }

    public void displayBoard(Board board) {
        io.print("   A B C D E F G H I J");
        for (int i = 0; i < board.getBoard().length; i++) {
            if(i != 0){
                System.out.println("");
            } 
            // should print out row numbers
            if (i + 1 < 10) {
                System.out.print(i + 1 + ". ");
            } else {
                System.out.print(i + 1 + ".");
            }

            for (int j = 0; j < board.getBoard().length; j++) {
                System.out.print(board.getBoard()[i][j] + "|");
            }
        }
    }

    public String[] placeShip(Ship ship) {
        String[] position = new String[2];
        position[0] = io.readString("Where would you like to place your " + ship.getName());
        position[1] = io.readString(("Would you like your placement vertical or horizontal? (v/h)"));
        return position;
    }

    public int[] makeShot() {
        while (true) {
            io.print("");
            String choice = io.readString("Enter Shot Position (A1): ");
            int letter = choice.charAt(0) - 'A';
            try {
                int number = Integer.parseInt(choice.substring(1))-1;
                if((letter >= 0 && letter < 10) && (number >= 0 && number < 10)){
                    return new int[] {letter, number};
                } else{
                    io.print("Invalid coordinates.");
                }
            } catch (Exception e) {
                io.print("Invalid coordinates.");
            }

        }
    }

	public void getNames() {
	}
}
