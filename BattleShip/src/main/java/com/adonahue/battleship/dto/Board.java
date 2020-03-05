package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Board
 */
public class Board {
	private List<Ship> aliveShips = new ArrayList<>();
	private String[][] board = new String[10][10];


	public List<Ship> getShips(){
		return aliveShips;
	}

	public String[][] getBoard(){
		return board;
	}

	public void setBoardSquare(int x, int y){
		//TODO: add code that puts a X O or _;
	}


	public void initBoard(){
		//TODO: add code to init board with underscores
	}


}