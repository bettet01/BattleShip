package com.adonahue.battleship.dao;

import com.adonahue.battleship.dto.Ship;
import com.adonahue.battleship.ui.UserIO;
import com.adonahue.battleship.ui.UserIOImp;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author allison
 */
public class BattleShipDaoImpl implements BattleShipDao {

    private static final String BOARD_FILE = "battleshipBoard.txt";
    private static final String SHIP_FILE = "battleshipShip.txt";
    private UserIO io = new UserIOImp();

    @Override
    public void saveBoard(String[][] board) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(BOARD_FILE));
        } catch (IOException e) {
            throw new BattleShipDaoException(
                    "Could not save DVD data.", e);
        }
        String boardAsText = marshallBoard(board);
        out.println(boardAsText);
        out.flush();
        out.close();
    }

    @Override
    public void saveShip(List<Ship> shipList) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(SHIP_FILE));
        } catch (IOException e) {
            throw new BattleShipDaoException(
                    "Could not save DVD data.", e);
        }
        String shipAsText;
        for (Ship ship : shipList) {
            shipAsText = marshallShip(ship);
            out.println(shipAsText);
            out.flush();
        }
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

        HashMap<Integer, ArrayList<Integer>> shipMap = ship.getPosition();
        for (Integer i : shipMap.keySet()) {
            shipAsText += i + ":" + shipMap.get(i) + "/";
        }
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
        String[] shipTokens = shipAsText.split("::");
        String name = shipTokens[0];
        int length = Integer.parseInt(shipTokens[1]);

        Ship ship = new Ship(name, length);
        HashMap<Integer,ArrayList<Integer>> positionMap = new HashMap<>();

        String[] position = shipTokens[2].split("/");
        for (int i = 0; i < position.length; i++) {
            String[] positionSplit1 = position[i].split(":");
            String[] positionSplit2 = positionSplit1[1].split(",");
            int entry1 = Integer.parseInt(positionSplit2[0].substring(1));
            int entry2 = Integer.parseInt(positionSplit2[1].substring(0,1));
            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(entry1);
            myList.add(entry2);
            
            positionMap.put(Integer.parseInt(positionSplit1[0]), myList);
        }
        ship.setPosition(positionMap);
        return ship;
    }

}
