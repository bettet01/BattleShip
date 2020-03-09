package com.adonahue.battleship.app;

import com.adonahue.battleship.controller.BattleShipController;
import com.adonahue.battleship.dao.BattleShipDao;
import com.adonahue.battleship.dao.BattleShipDaoImpl;
import com.adonahue.battleship.ui.BattleshipView;
import com.adonahue.battleship.ui.UserIO;
import com.adonahue.battleship.ui.UserIOImp;

/**
 *
 * @author allison
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOImp();
        BattleshipView view = new BattleshipView(io);
        BattleShipDao dao = new BattleShipDaoImpl();
        BattleShipController controller = new BattleShipController(view, dao);
        controller.execute();
    }
}
