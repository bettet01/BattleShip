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