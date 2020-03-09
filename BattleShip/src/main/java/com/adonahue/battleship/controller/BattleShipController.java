package com.adonahue.battleship.controller;

import com.adonahue.battleship.dao.BadPlacementException;
import com.adonahue.battleship.dao.BattleShipDao;
import com.adonahue.battleship.dao.BattleShipDaoException;
import com.adonahue.battleship.dto.Board;
import com.adonahue.battleship.dto.Ship;
import com.adonahue.battleship.ui.BattleshipView;

/**
 * BattleShipController
 */
public class BattleShipController {

    BattleshipView view;
    BattleShipDao dao;
    boolean p1Turn;

    public BattleShipController(BattleshipView view, BattleShipDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void execute() throws BattleShipDaoException {
        boolean gameOn = true;
        p1Turn = true;
        boolean newGame = true;

        newGame = view.displaymainMenu();

        if (newGame) {
            setUp();
        } else {
            dao.loadGame();
        }

        view.displayBeginBanner();
        view.saveGameInstructions();
        while (gameOn) {
            Board currentBoard = getPlayersBoard(p1Turn);
            makeShot(currentBoard);
            gameOn = checkWin(currentBoard);
            p1Turn = !p1Turn;
        }
        
        view.displayVictor(p1Turn);

    }

    private Board getPlayersBoard(boolean turn) {
        view.printTurn(p1Turn);
        Board board = dao.getBoard(p1Turn);
        view.displayBoard(board);
        return board;
    }

    private void makeShot(Board board) throws BattleShipDaoException {
        boolean keepChoosing = true;
        while (keepChoosing) {
            String choice = view.getShotChoice();
            if (choice.equalsIgnoreCase("save")) {
                dao.saveGame();
                System.exit(0);
            } else {
                int[] array = view.makeShot(choice);
                if (array != null) {
                    if (board.checkBoard(array)) {
                        view.displayAlreadyChosen();
                    } else {
                        if (board.checkHit(array)) {
                            view.displayHit();
                            keepChoosing = false;
                        } else {
                            view.displayMiss();
                            keepChoosing = false;
                        }
                    }
                }
            }
        }
    }

    private boolean checkWin(Board board) {
        return !board.checkWin();
    }

    public void setUp() {
        Board showBoard = new Board();
        
        try {
            view.displayBeginBanner();
            view.getNames();
            view.printTurn(p1Turn); // Print the current player's turn
            dao.newBoard();
            boolean placed = false;
            for (Ship s : dao.getP1Ships()) {
                while (!placed) {
                    try {
                        dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
                        view.displayShipPositions(showBoard,s);
                        placed = true;
                    } catch (BadPlacementException e) {
                        view.printError(e.getMessage());
                    }
                }
                placed = false;
            }

            p1Turn = !p1Turn;
            view.printTurn(p1Turn);
            for (Ship s : dao.getP1Ships()) {
                while (!placed) {
                    try {
                        dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
                        placed = true;
                    } catch (BadPlacementException e) {
                        view.printError(e.getMessage());
                    }
                }
                placed = false;
            }

        } catch (Exception e) {
            System.out.println("ruh roh");
            System.out.println(e.getMessage());
        }
    }
}
