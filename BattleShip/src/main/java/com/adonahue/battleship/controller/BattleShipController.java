package com.adonahue.battleship.controller;

import com.adonahue.battleship.dao.BattleShipDao;
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

    public void execute() {
        boolean gameOn = true;
        p1Turn = true;
        boolean newGame = view.getGameChoice();

        if (newGame) {
            setUp();
        } else {
            loadGame();
        }
        


        while(gameOn){
            view.displayBeginBanner();
            view.displayBoard(p1Board);
            gameOn = false;
        }

    }

    private void makeShot(Board board) {
        int[] array = view.makeShot();
        // TODO: check if already shot or if it's a hit or miss

    }

    private boolean checkWin(Board board) {
        return !board.checkWin();
    }

    private Board displayTurn(boolean p1Turn, Board p1Board, Board p2Board) {
        // TODO: these may need to return the other players board depending how we set
        // it up
        view.printTurn(p1Turn);
        if (p1Turn) {
            view.displayBoard(p1Board);
            return p1Board;
        } else {
            view.displayBoard(p2Board);
            return p2Board;
        }
    }

    public void setUp(){
        try {
            view.displayBeginBanner();
            view.getNames();
            view.printTurn(p1Turn); //Print the current player's turn
            dao.buildNewBoard();
            for (Ship s : dao.getP1Board.getShips()) {
                dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
            }
            p1Turn = !p1Turn;
            view.printTurn(p1Turn);
            for (Ship s : p2Board.getShips()) {
                dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
            }
        } catch (Exception e) {
            System.out.println("ruh roh");
        }
    }
}