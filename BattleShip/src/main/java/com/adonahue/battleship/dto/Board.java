package com.adonahue.battleship.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.adonahue.battleship.dao.BadPlacementException;

/**
 * Board
 */
public class Board {
	private List<Ship> aliveShips = new ArrayList<>();
	private String[][] board = new String[10][10];

	public Board() {
	}

	public Board(Ship patrol, Ship destroyer, Ship submarine, Ship aircraftCarrier, Ship battleship) {
		aliveShips.add(patrol);
		aliveShips.add(destroyer);
		aliveShips.add(submarine);
		aliveShips.add(aircraftCarrier);
		aliveShips.add(battleship);
		initBoard();
	}

	public void newBoard() {
		aliveShips.add(new Ship("patrol", 2));
		aliveShips.add(new Ship("destroyer", 3));
		aliveShips.add(new Ship("submarine", 3));
		aliveShips.add(new Ship("battleship", 4));
		aliveShips.add(new Ship("aircraft carrier", 5));
		initBoard();
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	// Recieves [A4, H] (Space A4, position horizonally)
	public void setShipPosition(String[] pos, String name) throws BadPlacementException {
		List<Ship> tempShip = aliveShips.stream().filter(s -> s.getName().equals(name)).collect(Collectors.toList());
		Ship singleShip = tempShip.get(0);
		singleShip.setNewPosition(convertLocation(pos), pos[1]);

		List<Ship> otherShips = aliveShips.stream().filter(s -> !s.equals(singleShip)).collect(Collectors.toList());

		for (Ship s : otherShips) {
			for (int x : singleShip.getPosition().keySet()) {
				if (s.getPosition().containsValue(singleShip.getPosition().get(x))) {
					throw new BadPlacementException("That ship overlaps another");
				}
			}
		}
	}

	// requires a String[] with x and y position (A4), returns xy coords (0,4)
	private ArrayList<Integer> convertLocation(String[] pos) throws BadPlacementException{
		ArrayList<Integer> xy = new ArrayList<>();
		
		if (pos[0].length() == 3) {
			xy.add(9);
		} else {
			xy.add(pos[0].charAt(1) - '1');
		}
		xy.add(pos[0].charAt(0) - 'A');
		
		for (int x : xy) {
			if (x > 9 || x < 0){
				throw new BadPlacementException("The format entered was incorrect, enter in format (A1)");
			}
		}
		return xy;
	}

	public void setShip(Ship ship) {
		aliveShips.add(ship);
	}

	public List<Ship> getShips() {
		return aliveShips;
	}

	public String[][] getBoard() {
		return board;
	}

	public boolean checkBoard(int[] location) {
		int row = location[0];
		int column = location[1];
		if (board[row][column].equals("X") || board[row][column].equals("O")) {
			return true;
		} else {
			return false;
		}
	}

	public String checkHit(int[] location) {
		int row = location[0];
		int column = location[1];
		for (Ship ship : aliveShips) {
			HashMap<Integer, ArrayList<Integer>> positions = ship.getPosition();
			for (ArrayList<Integer> shipPositions : positions.values()) {
				if (shipPositions.get(0) == row && shipPositions.get(1) == column) {
					board[row][column] = "X";
					if(checkDeadShip(ship)){
						return ship.getName();
					} else{
						return "X";
					}
				}
			}
		}
		board[row][column] = "O";
		return "O";
	}

	private boolean checkDeadShip(Ship ship) {
		int count = 0;
		Set<Integer> keys = ship.getPosition().keySet();
		for (Integer key : keys) {
			ArrayList<Integer> coord = ship.getPosition().get(key);
			if (board[coord.get(0)][coord.get(1)].equals("X")) {
				count++;
			}
		}
		if (count == ship.getPosition().size()) {
			aliveShips.remove(ship);
			return true;
		} else {
			return false;
		}
	}

	public void initBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = "_";
			}
		}
	}

	public boolean checkWin() {
		return aliveShips.isEmpty();
	}
        
        public void setLocation(ArrayList<Integer> list){
            board[list.get(0)][list.get(1)] = "X";
        }

}