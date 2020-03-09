package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author allison
 */
public class Ship {

    private String name;
    private int length;
    private HashMap<Integer, ArrayList<Integer>> position;

    public static void main(String args[]) {
        Ship ship = new Ship("battleship", 4);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        ship.setPosition(list, "h");
    }

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

    public void setPosition(ArrayList<Integer> position, String orientation) {
        for (int i = 0; i < this.length; i++) {

        }

        switch (orientation) {
            case "h":
                for (int i = 0; i < this.length; i++) {
                    position.set(0, position.get(0) + i);
                    this.position.put(i, position);
                }
                break;

            case "y":
                for (int i = 0; i < this.length; i++) {
                    position.set(0, position.get(1) + i);
                    this.position.put(i, position);
                }
                break;

            default:
                break;
        }
    }
}
