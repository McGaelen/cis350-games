package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessBoard.Color;

/**
 * This class represents a square on the chess board.
  
 */
public class chessSquare {

	/**
	 * Common variables belonging to a chess board square
	 */
	// Square is occupied or not
	public boolean isOccupied;
	// Assign 0 as white color and 1 as black color
	public Color color;
	// Square objects keep track of which piece occupies that square.
	public chessPiece occupyingPiece;

	/**
	 * Constructor to initialize chess board Squares
	 * @param isOccupied
	 * @param color
	 */
	public chessSquare(boolean isOccupied, Color color) {
		this.isOccupied = isOccupied;
		this.color = color;
		this.occupyingPiece = null;
	}
}
