package com.adonahue.battleship.ui;

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

    public void getNames(boolean p1Turn) {
        if (p1Turn) {
            p1Name = io.readString("Enter a name for Player One");
        } else {
            p2Name = io.readString("Enter a name for Player Two");
        }
    }

    public void printTurn(boolean p1Turn) {
        if (p1Turn) {
            io.print("It's " + p1Name + "'s turn.");
        } else {
            io.print("It's " + p2Name + "'s turn.");
        }
    }

    public void displayBoard(Board board) {
        io.print("   A B C D E F G H I J");
        for (int i = 0; i < board.getBoard().length; i++) {
            if (i != 0) {
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
        io.print("");
    }

    public String[] placeShip(Ship ship) {
        String[] position = new String[2];
        position[0] = io.readString("Where would you like to place your " + ship.getName());
        position[1] = io.readString(("Would you like your placement vertical or horizontal? (v/h)"));
        return position;
    }

    public String getShotChoice() {
        while (true) {
            io.print("");
            String choice = io.readString("Enter Shot Position (A1): ");
            return choice;
        }
    }

    public int[] makeShot(String choice) {
        int column = choice.charAt(0) - 'A';
        try {
            int row = Integer.parseInt(choice.substring(1)) - 1;
            if ((column >= 0 && column < 10) && (row >= 0 && row < 10)) {
                return new int[]{row, column};
            } else {
                io.print("Invalid coordinates.");
            }
        } catch (Exception e) {
            io.print("Invalid coordinates.");
        }
        return null;
    }

    public void displayAlreadyChosen() {
        io.print("That location has already been tried.");
    }

    public void displayHit() {
        io.print("Hit! Let's go!");
    }

    public void displayMiss() {
        io.print("Miss :(");
    }

    public void getNames() {
    }

    public void printError(String s) {
        io.print(s);
    }

    public boolean displaymainMenu() {
        while (true) {
            io.print("___ Welcome to BattleShip! ___");
            io.print("1. New Game");
            io.print("2. Load Game");
            int x = io.readInt("Choice: ", 1, 2);
            if (x == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void displayVictor(boolean p1Turn) {
        if (p1Turn) {
            io.print("Player 1 wins!! congrats :)");
        } else {
            io.print("Player 2 is victorious! slay on champ.");
        }
    }

    public void saveGameInstructions() {
        io.print("Type 'save' at any time if you would like to save");
    }
    
    public void displayShipPositions(Board showBoard, Ship ship){
        ship.getPosition().values().stream().forEach(location -> showBoard.setLocation(location));
        displayBoard(showBoard);
        io.print("");
    }

	public void displaySunk(String check) {
        io.print("You Sunk My " + check + "!");
    }
    
    public void hitEnter(){
        io.readString("Click enter to continue to the next turn");
    }
}
