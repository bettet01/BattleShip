package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Board
 */
public class Board {
	private List<Ship> aliveShips = new ArrayList<>();
	private String[][] board = new String[10][10];

	// for testing purposes
	public static void main(String[] args) {
		Board board = new Board();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				
			}
		}
	}


	public List<Ship> getShips(){
		return aliveShips;
	}

	public String[][] getBoard(){
		return board;
	}

	public void setBoardSquare(int[] coordinates){
		int x = coordinates[0];
		int y = coordinates[1];


		//TODO: need to check a ship is in that location. print X if true O if false
		
	}


	public void initBoard(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				board[i][j] = "_";
			}
		}
	}


}