package ticTacToe;

public class TicTacToe {

	private String[][] board;


	public TicTacToe() {
		startGame();
	}

	public void startGame() {
		
		// create 3x3 board
		board = new String[3][3];
		
		// fill board with place holders
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = " ";
			}
		}
	}

	public boolean isValidMove(int row, int column) {

		// move is not within board
		if (row > 2 || column > 2 || row < 0 || column < 0) {
			return false;
		}

		// location already contains a move
		if (!board[row][column].equals(" ")) {
			return false;
		}

		return true;
	}

	public void move(int row, int column, String player) {

		// throw error if invalid move
		if (!isValidMove(row, column)) {
			throw new IllegalArgumentException();
			//System.out.println("Please enter a valid move.");
		}

		// place move if valid move
		board[row][column] = player;
	}

	public boolean isWinner() {

		// eight winning lines
		// each win line contains three coordinates

		int[][][] winLines = {

				// horizontal win
				{ {0, 0}, {0, 1}, {0, 2} },
				{ {1, 0}, {1, 1}, {1, 2} },
				{ {2, 0}, {2, 1}, {2, 2} },

				// vertical win
				{ {0, 0}, {1, 0}, {2, 0} },
				{ {0, 1}, {1, 1}, {2, 1} },
				{ {0, 2}, {1, 2}, {2, 2} },

				// diagonal win
				{ {0, 0}, {1, 1}, {2, 2} },
				{ {2, 0}, {1, 1}, {0, 2} },
		};

		// go through win line combinations
		for (int[][] line : winLines) {

			// need 3 matches to win
			int xMatch = 0;
			int oMatch = 0;

			// check coords with board
			for (int[] coord : line) {

				// if location has x, increment x
				if (board[coord[0]][coord[1]].equals("X")) {
					xMatch++;
				}

				// if location has o, increment o
				if (board[coord[0]][coord[1]].equals("O")) {
					oMatch++;
				}
			}

			// win condition met if three matches
			if (xMatch == 3 || oMatch == 3) {
				return true;
			}
		}
		
		// win condition not met
		return false;
	}
	
	public boolean isTie() {

		// iterate through board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				
				// tie condition not met if open spaces
				if (board[row][col].equals(" ")) {
					return false;
				}
			}
		}
		
		// tie condition met
		return true;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}
	
	
}
