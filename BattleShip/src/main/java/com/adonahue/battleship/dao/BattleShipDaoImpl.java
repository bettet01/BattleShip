package com.adonahue.battleship.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.adonahue.battleship.dto.Board;
import com.adonahue.battleship.dto.Ship;

/**
 *
 * @author allison
 */
public class BattleShipDaoImpl implements BattleShipDao {

    private static final String BOARD1_FILE = "battleshipBoard1.txt";
    private static final String BOARD2_FILE = "battleshipBoard2.txt";
    private static final String SHIP1_FILE = "battleshipShip1.txt";
    private static final String SHIP2_FILE = "battleshipShip2.txt";
    private Board board1 = new Board();
    private Board board2 = new Board();

    @Override
    public void newBoard() {
        board1.newBoard();
        board2.newBoard();
    }

    @Override
    public void saveBoard1(String[][] board) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(BOARD1_FILE));
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
    public void saveBoard2(String[][] board) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(BOARD2_FILE));
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
    public void saveShip1(List<Ship> shipList) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(SHIP1_FILE));
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
    public void saveShip2(List<Ship> shipList) throws BattleShipDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(SHIP2_FILE));
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
    public void loadBoard1() throws BattleShipDaoException {
        Scanner scanner;
        String currentLine;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(BOARD1_FILE)));
        } catch (FileNotFoundException e) {
            throw new BattleShipDaoException(
                    "-_- Could not load data into memory.", e);
        }
        currentLine = scanner.nextLine();
        String[][] board = unmarshallBoard(currentLine);
        board1.setBoard(board);

        scanner.close();
    }

    @Override
    public void loadBoard2() throws BattleShipDaoException {
        Scanner scanner;
        String currentLine;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(BOARD2_FILE)));
        } catch (FileNotFoundException e) {
            throw new BattleShipDaoException(
                    "-_- Could not load data into memory.", e);
        }
        currentLine = scanner.nextLine();
        String[][] board = unmarshallBoard(currentLine);
        board2.setBoard(board);

        scanner.close();
    }

    @Override
    public void loadShip1() throws BattleShipDaoException {
        Scanner scanner;
        String currentLine;
        Ship currentShip;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(SHIP1_FILE)));
        } catch (FileNotFoundException e) {
            throw new BattleShipDaoException(
                    "-_- Could not load data into memory.", e);
        }
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentShip = unmarshallShip(currentLine);
            board1.setShip(currentShip);

        }
        scanner.close();
    }

    @Override
    public void loadShip2() throws BattleShipDaoException {
        Scanner scanner;
        String currentLine;
        Ship currentShip;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(SHIP2_FILE)));
        } catch (FileNotFoundException e) {
            throw new BattleShipDaoException(
                    "-_- Could not load data into memory.", e);
        }
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentShip = unmarshallShip(currentLine);
            board2.setShip(currentShip);
        }
        scanner.close();
    }

    @Override
    public void loadGame() throws BattleShipDaoException {
        loadBoard1();
        loadBoard2();
        loadShip1();
        loadShip2();
    }

    @Override
    public void saveGame() throws BattleShipDaoException {
        saveBoard1(getBoard(true).getBoard());
        saveBoard2(getBoard(false).getBoard());
        saveShip1(getP1Ships());
        saveShip2(getP2Ships());
    }

    @Override
    public String marshallBoard(String[][] board) {
        String boardString = "";
        for (int i = 0; i < 10; i++) {
            if (i != 0) {
                boardString += "//";
            }
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
        String[] firstSplit = boardAsText.split("//");
        for (int i = 0; i < 10; i++) {
            String[] secondSplit = firstSplit[i].split("::");
            for (int j = 0; j < 10; j++) {
                board[i][j] = secondSplit[j];
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
        HashMap<Integer, ArrayList<Integer>> positionMap = new HashMap<>();

        String[] position = shipTokens[2].split("/");
        for (int i = 0; i < position.length; i++) {
            String[] positionSplit1 = position[i].split(":");
            String[] positionSplit2 = positionSplit1[1].split(",");
            int entry1 = Integer.parseInt(positionSplit2[0].substring(1));
            int entry2 = Integer.parseInt(positionSplit2[1].replaceAll("]","").trim());
            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(entry1);
            myList.add(entry2);

            positionMap.put(Integer.parseInt(positionSplit1[0]), myList);
        }
        ship.setPosition(positionMap);
        return ship;
    }

    @Override
    public void setShipPosition(String[] placeShip, String name, boolean p1Turn) throws BadPlacementException {
        if (p1Turn) {
            this.board1.setShipPosition(placeShip, name);
        } else {
            this.board2.setShipPosition(placeShip, name);
        }

    }

    @Override
    public List<Ship> getP1Ships() {
        return board1.getShips();
    }

    @Override
    public List<Ship> getP2Ships() {
        return board2.getShips();
    }

    @Override
    public Board getBoard(boolean p1Turn) {
        if (p1Turn) {
            return board2;
        }
        return board1;
    }

}
