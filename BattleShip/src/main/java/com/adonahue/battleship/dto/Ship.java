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
    // Ship ship = new Ship("battleship", 4);
    // ArrayList<Integer> list = new ArrayList<>();
    // list.add(0);
    // list.add(1);
    // ship.setPosition(list, "h");
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

    public void setPosition(HashMap<Integer, ArrayList<Integer>> position) {
        this.position = position;
    }

    // takes in ArrayList with xy coord and orientation, sets position based off
    // input
    public void setNewPosition(ArrayList<Integer> location, String orientation) throws BadPlacementException {
        switch (orientation) {
            case "h":
                if (location.get(0) + this.length > 9) {
                    throw new BadPlacementException("That ship falls off the board");
                }
                this.position.put(0, location);
                for (int i = 1; i < this.length; i++) {
                    ArrayList<Integer> newLocation = new ArrayList<>();
                    newLocation.add(location.get(0) + i);
                    newLocation.add(location.get(1));

                    this.position.put(i, newLocation);
                }
                break;

            case "v":
                if (location.get(1) + this.length > 9) {
                    throw new BadPlacementException("That ship falls off the board");
                }
                this.position.put(0, location);
                for (int i = 1; i < this.length; i++) {
                    ArrayList<Integer> newLocation = new ArrayList<>();
                    newLocation.add(location.get(0));
                    newLocation.add(location.get(1) + i);

                    this.position.put(i, newLocation);
                }
                break;

            default:
                break;
        }
    }

}
