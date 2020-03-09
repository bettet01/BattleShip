package com.adonahue.battleship.controller;

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

    public void execute() throws BattleShipDaoException{
        boolean gameOn = true;
        p1Turn = true;
        boolean newGame = true;

        if (newGame) {
            setUp();
        } else {
            dao.loadGame();
        }
        


        while(gameOn){
            view.displayBeginBanner();
            view.displayBoard(dao.getBoard(p1Turn));
            gameOn = false;
        }

    }

    private void makeShot(Board board) {
        while(true){
            int[] array = view.makeShot();
            if(board.checkBoard(array)){
               view.displayAlreadyChosen();
            } else {
                if(board.checkHit(array)){
                    view.displayHit();
                } else {
                    view.displayMiss();
                }
            }
        }

        // TODO: check if already shot or if it's a hit or miss

    }

    private boolean checkWin(Board board) {
        return !board.checkWin();
    }

    private Board displayTurn(boolean p1Turn, Board p1Board, Board p2Board) {
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
            dao.newBoard();
            for (Ship s : dao.getP1Ships()) {
                dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
            }
            p1Turn = !p1Turn;
            view.printTurn(p1Turn);
            for (Ship s : dao.getP2Ships()) {
                dao.setShipPosition(view.placeShip(s), s.getName(), p1Turn);
            }
        } catch (Exception e) {
            System.out.println("ruh roh");
            System.out.println(e.getMessage());
        }
    }
}