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

    public void setPosition(HashMap<Integer, ArrayList<Integer>> positionMap) {
        this.position = positionMap;       
    }
}
