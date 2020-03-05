package com.adonahue.battleship.dto;

/**
 *
 * @author allison
 */
public class Ship {
    
    private String name;
    private int length;
    private int[][] position;

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

    public int[][] getPosition() {
        return position;
    }

    public void setPosition(int[][] position) {
        this.position = position;
    }
    
    
}
