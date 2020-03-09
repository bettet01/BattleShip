package com.adonahue.battleship.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.adonahue.battleship.dto.Board;
import com.adonahue.battleship.dto.Ship;
import com.adonahue.battleship.ui.UserIO;
import com.adonahue.battleship.ui.UserIOImp;

/**
 *
 * @author allison
 */
public class BattleShipDaoImpl implements BattleShipDao {

    private static final String GAME_FILE = "battleship.txt";
    private UserIO io = new UserIOImp();

    // public void getTurnBoard(boolean p1Turn) {
    //     if (p1Turn){
    //         return p2Board;
    //     } else {
    //         return p1Board;
    //     }
    // }

    @Override
    public void saveGame(String[][] board) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(GAME_FILE));
        } catch (IOException e) {
            throw new BattleShipDaoException(
                    "Could not save DVD data.", e);
        }
        String boardAsText;

        boardAsText = marshallBoard(board);
        out.println(boardAsText);
        out.flush();
        out.close();
    }

    @Override
    public void loadGame() {

    }

    @Override
    public String marshallBoard(String[][] board) {
        String boardString = "";
        for (int i = 0; i < 10; i++) {
            boardString += "\\";
            for (int j = 0; j < 10; j++) {
                boardString += board[i][j] + "::";
            }
        }
        return boardString;
    }

    @Override
    public String marshallShip(Ship ship) {
        String shipAsText = ship.getName() + "::";
        shipAsText += ship.getLength() + "::";
        shipAsText += ship.getPosition();
        return shipAsText;
    }

    @Override
    public String[][] unmarshallBoard(String boardAsText) {
        String[][] board = new String[10][10];
        String[] firstSplit = boardAsText.split("\\");
        for (int i = 0; i < 10; i++) {
            String[] secondSplit = firstSplit[i].split("::");
            for (int j = 0; j < 10; j++) {
                secondSplit[j] = board[i][j];
            }
        }
        return board;
    }

    @Override
    public Ship unmarshallShip(String shipAsText) {
       
    }

}
