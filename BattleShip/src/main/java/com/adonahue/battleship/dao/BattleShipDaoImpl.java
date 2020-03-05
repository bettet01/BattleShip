package com.adonahue.battleship.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author allison
 */
public class BattleShipDaoImpl implements BattleShipDao{
    
    private static final String GAME_FILE = "battleship.txt";

    @Override
    public void saveGame() throws BattleShipDaoException{
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(GAME_FILE));
        } catch (IOException e) {
            throw new BattleShipDaoException(
                    "Could not save DVD data.", e);
        }
        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public void loadGame() {
        
    }
    
}
