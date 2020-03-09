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
        boolean gameOn = true;
        Board p1Board = new Board();
        Board p2Board = new Board();
        boolean p1Turn = true;

        // try {
        // view.displayBeginBanner();
        // String p1Name = view.getName();
        // view.printTurn(); //Print the current player's turn
        // for (Ship s : p1Board.getShips()) {
        // p1Board.setShipPosition(view.placeShip(s), s.getName());
        // }
        // view.printTurn();
        // for (Ship s : p2Board.getShips()) {
        // p2Board.setShipPosition(view.placeShip(s), s.getName());
        // }

        while (gameOn) {
            Board currentBoard = displayTurn(p1Turn, p1Board, p2Board);
            makeShot(currentBoard);
            gameOn = checkWin(currentBoard);
            p1Turn = !p1Turn;
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
}