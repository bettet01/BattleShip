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
    
    void saveBoard(String[][] board) throws BattleShipDaoException;
    void saveShip(List<Ship> shipList) throws BattleShipDaoException;
    
    void loadGame();
    
    String marshallBoard(String[][] board);
    String marshallShip(Ship ship);
    String[][] unmarshallBoard(String boardAsText);
    Ship unmarshallShip(String shipAsText);
}
