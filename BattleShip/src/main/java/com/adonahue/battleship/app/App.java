package com.adonahue.battleship.app;

import com.adonahue.battleship.controller.BattleShipController;
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
        BattleShipController controller = new BattleShipController(view);
        controller.execute();
    }
}
