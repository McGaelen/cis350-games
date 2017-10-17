package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessSquare;
import cis350.games.chessStandardBoard;
import cis350.games.chessGame;
import cis350.games.chessBoard.Color;

/**
 * Subclass of a Piece specific to a Rook. This handles all movements the rook is capable
 * of making.
 
 */
public class chessRook extends chessPiece {

	/**
	 * Rook constructor initializes name of piece to Rook. Every other parameter is
	 * initialized by superclass.
	 * @param initX
	 * @param initY
	 * @param color
	 * @param board
	 */
	public chessRook(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "rook";
	}

	/**
	 * Rook specific implementation of abstract method.
	 * @see chessGame.Piece#isValidSpecialMove(int, int)
	 * @param newX
	 * @param newY
	 * @return boolean true if valid special move
	 */
	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		if(isValidRookMove(xDisplacement, yDisplacement)){
			// Total number of steps the piece has to take. Either x = 0 or y = 0.
			int steps = Math.max(Math.abs(xDisplacement), Math.abs(yDisplacement));
			int xDirection = xDisplacement/steps;
			int yDirection = yDisplacement/steps;
			// Check for obstacles in path of Rook.
			for(int i = 1; i < steps; i++){
				chessSquare squareToCheck = currentBoard.squaresList[xLocation + i*xDirection][yLocation + i*yDirection];
				if(squareToCheck.isOccupied)
					return false;
			}
			return true;
		}
		return false;
			
	}
	
	/**
	 * Helper method for Rook specific move check (Vertical + Horizontal)
	 * @param xDisplacement
	 * @param yDisplacement
	 * @return boolean true if valid Rook move
	 */
	public static boolean isValidRookMove(int xDisplacement, int yDisplacement) {
		// Vertical
		if(xDisplacement != 0 && yDisplacement == 0)
			return true;
		// Horizontal
		else if(xDisplacement == 0 && yDisplacement != 0)
			return true;
		else
			return false;
	}

}

