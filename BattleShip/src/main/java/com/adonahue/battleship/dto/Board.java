package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Board
 */
public class Board {
	private List<Ship> aliveShips = new ArrayList<>();
	private String[][] board = new String[10][10];

	public Board() {
		aliveShips.add(new Ship("patrol",2));
		aliveShips.add(new Ship("destroyer",3));
		aliveShips.add(new Ship("submarine",3));
		aliveShips.add(new Ship("aircraft carrier",5));
		aliveShips.add(new Ship("battleship",4));
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

	public void setBoard(String[][] board){
		this.board = board;
	}

	//Recieves [A4, H] (Space A4, position horizonally)
	public void setShipPosition(String[] pos, String name) {
		ArrayList<Integer> xy = new ArrayList<>();
		xy.add(pos[0].charAt(0) - 'A');
		xy.add(pos[0].charAt(1) - '1');
		List<Ship> singleShip = aliveShips.stream().filter(s -> s.getName().equals(name)).collect(Collectors.toList());
		singleShip.get(0).setPosition(xy, pos[1]);
	}

	// for testing purposes
	public void setShip(Ship ship){
		aliveShips.add(ship);
	}

	public List<Ship> getShips(){
		return aliveShips;
	}

	public String[][] getBoard(){
		return board;
	}

	public boolean checkBoard(int[] location){
		int letter = location[0];
		int number = location[1];
		if(board[letter][number].equals("X") || board[letter][number].equals("O")){
			return true;
		} else {
			return false;
		}
	}

	public boolean checkHit(int[] location){
		int letter = location[0];
		int number = location[1];
		for(Ship ship: aliveShips){
			HashMap<Integer, ArrayList<Integer>> positions = ship.getPosition();
			for(ArrayList<Integer> shipPositions: positions.values()){
				if(shipPositions.get(0) == letter && shipPositions.get(1) == number){
					board[letter][number] = "X";
					checkDeadShip(ship);
					return true;
				}
			}
		}
		board[letter][number] = "O";
		return false;
	}

	private void checkDeadShip(Ship ship) {
		int count = 0;
		Set<Integer> keys = ship.getPosition().keySet();
		for(Integer key: keys){
			ArrayList<Integer> coord = ship.getPosition().get(key);
			if(board[coord.get(0)][coord.get(1)].equals("X")){
				count++;
			}
		}
		if(count == ship.getPosition().size()){
			aliveShips.remove(ship);
		}
	}

	public void initBoard() {
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				board[i][j] = "_";
			}
		}
	}

	public boolean checkWin(){
		return aliveShips.isEmpty();
	}


}