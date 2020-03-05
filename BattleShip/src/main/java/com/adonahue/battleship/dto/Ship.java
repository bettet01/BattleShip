package com.adonahue.battleship.dto;

import java.util.ArrayList;

/**
 *
 * @author allison
 */
public class Ship {
    
    private String name;
    private int length;
    private ArrayList<ArrayList<Integer>> position;
    
    

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

    public ArrayList<ArrayList<Integer>> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<ArrayList<Integer>> position) {
        this.position = position;
    }
   
}
