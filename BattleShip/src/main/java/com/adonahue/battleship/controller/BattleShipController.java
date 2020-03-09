package com.adonahue.battleship.controller;

import com.adonahue.battleship.dto.Board;
import com.adonahue.battleship.dto.Ship;
import com.adonahue.battleship.ui.BattleshipView;

/**
 * BattleShipController
 */
public class BattleShipController {

    BattleshipView view;

    public BattleShipController(BattleshipView view) {
        this.view = view;
    }

    public void execute() {
        Board p1Board = new Board();
        Board p2Board = new Board();
        boolean p1Turn = true;

        try {
            view.displayBeginBanner();
            String p1Name = view.getName();
            view.printTurn(); //Print the current player's turn
            for (Ship s : p1Board.getShips()) {
                p1Board.setShipPosition(view.placeShip(s), s.getName());
            }
            view.printTurn();
            for (Ship s : p2Board.getShips()) {
                p2Board.setShipPosition(view.placeShip(s), s.getName());
            }

        }
    }
}