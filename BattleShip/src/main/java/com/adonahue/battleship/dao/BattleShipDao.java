/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adonahue.battleship.dao;

import com.adonahue.battleship.dto.Board;

/**
 *
 * @author allison
 */
public interface BattleShipDao {
    
    void saveGame() throws BattleShipDaoException;
    
    void loadGame();
    
    String marshallBoard(String[][] board);
    String[][] unmarshallBoard(String boardAsText);
}
