package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.adonahue.battleship.dao.BadPlacementException;

/**
 * Board
 */
public class Board {
	private List<Ship> aliveShips = new ArrayList<>();
	private String[][] board = new String[10][10];

	public Board() {
		aliveShips.add(new Ship("patrol", 2));
		aliveShips.add(new Ship("destroyer", 3));
		aliveShips.add(new Ship("submarine", 3));
		aliveShips.add(new Ship("aircraft carrier", 5));
		aliveShips.add(new Ship("battleship", 4));
		initBoard();
	}
	public Board(Ship patrol, Ship destroyer, Ship submarine, Ship aircraftCarrier, Ship battleship){
		aliveShips.add(patrol);
		aliveShips.add(destroyer);
		aliveShips.add(submarine);
		aliveShips.add(aircraftCarrier);
		aliveShips.add(battleship);
		initBoard();
	}

	//Recieves [A4, H] (Space A4, position horizonally)
	public void setShipPosition(String[] pos, String name) throws BadPlacementException {
		List<Ship> singleShip = aliveShips.stream().filter(s -> s.getName().equals(name)).collect(Collectors.toList());
		singleShip.get(0).setPosition(convertLocation(pos), pos[1]);

		for (Ship s: aliveShips) {
			for (int x : singleShip.get(0).getPosition().keySet()){
				if (s.getPosition().containsValue(singleShip.get(0).getPosition().get(x))){
					throw new BadPlacementException("That ship overlaps another");
				}
			}
		}

		
	}

	//requires a String[] with x and y position (A4), returns xy coords (0,4)
	private ArrayList<Integer> convertLocation(String[] pos) {
		ArrayList<Integer> xy = new ArrayList<>();
		xy.add(pos[0].charAt(0) - 'A');
		xy.add(pos[0].charAt(1) - '1');
		return xy;
	}

	public void setShip(Ship ship){
		aliveShips.add(ship);
	}

	public List<Ship> getShips(){
		return aliveShips;
	}

	public String[][] getBoard(){
		return board;
	}

	// public String setBoardSquare(int[] coordinates){
	// 	int x = coordinates[0];
	// 	int y = coordinates[1];

	// 	// for()
		
		
	// }


	public void initBoard(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				board[i][j] = "_";
			}
		}
	}


}