/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adonahue.battleship.dao;

import com.adonahue.battleship.dto.Ship;
import java.util.List;

/**
 *
 * @author allison
 */
public interface BattleShipDao {

    void saveBoard1(String[][] board) throws BattleShipDaoException;

    void saveBoard2(String[][] board) throws BattleShipDaoException;

    void saveShip1(List<Ship> shipList) throws BattleShipDaoException;

    void saveShip2(List<Ship> shipList) throws BattleShipDaoException;

    void newBoard();

    void loadBoard1() throws BattleShipDaoException;

    void loadBoard2() throws BattleShipDaoException;

    void loadShip1() throws BattleShipDaoException;

    void loadShip2() throws BattleShipDaoException;

    void loadGame() throws BattleShipDaoException;

    void saveGame(List<Ship> p1ShipList, List<Ship> p2ShipList, String[][] board1, String[][] board2) throws BattleShipDaoException;

    String marshallBoard(String[][] board);

    String marshallShip(Ship ship);

    String[][] unmarshallBoard(String boardAsText);

    Ship unmarshallShip(String shipAsText);

    Object getP1Ships();
}
