package com.adonahue.battleship.controller;

import com.adonahue.battleship.dao.BattleShipDao;
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

    public void execute() {
        boolean gameOn = true;
        p1Turn = true;
        boolean newGame = true;

        if (newGame) {
            setUp();
        } else {
            // loadGame();
        }
        

        view.displayBeginBanner();
        while(gameOn){
            Board currentBoard = getPlayersBoard(p1Turn);
            makeShot(currentBoard);
            gameOn = checkWin(currentBoard);
            p1Turn = !p1Turn;
        }

    }

    private Board getPlayersBoard(boolean turn){
        view.printTurn(p1Turn);
        Board board = dao.getBoard(p1Turn);
        view.displayBoard(board);
        return board;
    }


    private void makeShot(Board board) {
        boolean keepChoosing = true;
        while(keepChoosing){
            int[] array = view.makeShot();
            if(board.checkBoard(array)){
               view.displayAlreadyChosen();
            } else {
                if(board.checkHit(array)){
                    view.displayHit();
                    keepChoosing = false;
                } else {
                    view.displayMiss();
                    keepChoosing = false;
                }
            }
        }
    }

    private boolean checkWin(Board board) {
        return !board.checkWin();
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