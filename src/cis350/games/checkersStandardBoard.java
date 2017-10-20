package cis350.games;

import cis350.games.checkersBoard;
import cis350.games.checkersKing;
import cis350.games.checkersPawn;
import cis350.games.checkersSquare;
import cis350.games.checkersBoard.Color;

/**
 * Subclass of a board. Standard version of a checkers Board. Has methods for creating a standard
 * checkers board and populating it with regular checkers pieces.
 * Can be used to create a standard game of cis350.games.
 */
public class checkersStandardBoard extends checkersBoard {
	
	/**
	 * Trackers for the white and black kings for check, checkmate and game ending conditions.
	 */
	public checkersKing whiteKingTracker;
	public checkersKing blackKingTracker;
	
	/**
	 * Method to initialize the checkers board.
	 * @param xSquares
	 * @param ySquares
	 */
	public checkersStandardBoard(int xSquares, int ySquares) {

		this.numXSquares = xSquares;
		this.numYSquares = ySquares;
		this.totalSquares = this.numXSquares * this.numYSquares;
		this.squaresList = new checkersSquare[this.numXSquares][this.numYSquares];
		populateBoardWithSquares();
		this.whiteKingTracker = null;
		this.blackKingTracker = null;
	}

	/**
	 * Method to populate our board with Squares.
	 * General pattern of white and black squares on the board.
	 */
	public void populateBoardWithSquares() {
		for (int i = 0; i < this.numXSquares; i++) {
			for (int j = 0; j < this.numYSquares; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						squaresList[i][j] = new checkersSquare(false, Color.black);
					else
						squaresList[i][j] = new checkersSquare(false, Color.white);
				} 
				else {
					if (j % 2 == 0)
						squaresList[i][j] = new checkersSquare(false, Color.white);
					else
						squaresList[i][j] = new checkersSquare(false, Color.black);
				}
			}
		}
	}
	
	/**
	 * Method to populate our checkers board with standard pieces.
	 */
	public void populateBoardWithPieces() {
		setupPawns();
	}

	/**
	 * Setup 8 black and 8 white pawns in their initial positions.
	 */
	public void setupPawns(){
		for(int i = 0; i < 8; i++){
			if(i%2 == 0) {
			checkersPawn newWhitePawn = new checkersPawn(i, 0, Color.white, this);
			checkersPawn newWhitePawn2 = new checkersPawn(i, 2, Color.white, this);
			checkersPawn newBlackPawn = new checkersPawn(i, 6, Color.black, this);
			this.squaresList[i][0].isOccupied = true;
			this.squaresList[i][2].isOccupied = true;
			this.squaresList[i][6].isOccupied = true;
			this.squaresList[i][0].occupyingPiece = newWhitePawn;
			this.squaresList[i][2].occupyingPiece = newWhitePawn2;
			this.squaresList[i][6].occupyingPiece = newBlackPawn;
			}
			else {
				checkersPawn newWhitePawn = new checkersPawn(i, 1, Color.white, this);
				checkersPawn newBlackPawn = new checkersPawn(i, 7, Color.black, this);
				checkersPawn newBlackPawn2 = new checkersPawn(i, 5, Color.black, this);
				this.squaresList[i][1].isOccupied = true;
				this.squaresList[i][7].isOccupied = true;
				this.squaresList[i][5].isOccupied = true;
				this.squaresList[i][1].occupyingPiece = newWhitePawn;
				this.squaresList[i][7].occupyingPiece = newBlackPawn;
				this.squaresList[i][5].occupyingPiece = newBlackPawn2;
			}
		}
	}
	
	/**
	 * Helper method to check if locations passed in are mapped on our generated board.
	 * @see cis350.games.Board#inBoardBounds(int, int)
	 * @param newX
	 * @param newY
	 * @return boolean true if move is in board bounds
	 */
	public boolean inBoardBounds(int newX, int newY){
		if(newX < numXSquares && newY < numYSquares && newX > -1 && newY > -1){
			return true;
		}
		else
			return false;
	}

}