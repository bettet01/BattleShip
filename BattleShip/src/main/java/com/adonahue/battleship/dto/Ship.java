package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.adonahue.battleship.dao.BadPlacementException;

/**
 *
 * @author allison
 */
public class Ship {

    private String name;
    private int length;
    private HashMap<Integer, ArrayList<Integer>> position = new HashMap<>();

    // TESTING
    // public static void main(String args[]) {
    //     Ship ship = new Ship("battleship", 4);
    //     ArrayList<Integer> list = new ArrayList<>();
    //     list.add(0);
    //     list.add(1);
    //     ship.setPosition(list, "h");
    // }

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public HashMap<Integer, ArrayList<Integer>> getPosition() {
        return position;
    }

    

    //takes in ArrayList with xy coord and orientation, sets position based off input
    public void setNewPosition(ArrayList<Integer> position, String orientation) throws BadPlacementException{
        switch (orientation) {
            case "h":
                this.position.put(0, position);
                for (int i = 1; i < this.length; i++) {
                    position.set(0, position.get(0) + 1);
                    if (position.get(0) > 9) {
                        throw new BadPlacementException("That ship falls off the board");
                    }
                    this.position.put(i, position);
                }
                break;

            case "v":
            this.position.put(0, position);
            for (int i = 1; i < this.length; i++) {
                position.set(0, position.get(1) + 1);
                if (position.get(1) > 9) {
                    throw new BadPlacementException("That ship falls off the board");
                }
                this.position.put(i, position);
            }
            break;

            default:
                break;
        }
    }
    
}
