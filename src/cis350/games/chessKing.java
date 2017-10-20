package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessStandardBoard;
import cis350.games.chessBoard.Color;

/***********************************************************************
 * Subclass of a Piece specific to a King. This handles all 
 * movements the king is capable of making.
 **********************************************************************/
public class chessKing extends chessPiece {

    /**
     * Constructor for the King
     * @param initX x-coordinate
     * @param initY y-coordinate
     * @param color If the color of the king
     * @param board is the current board
     */
	public chessKing(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "king";
	}

    /**
     * Checks if the move is valid
     * @param newX the new x-coordinate
     * @param newY the new y-coordinate
     * @return true if the move is valid
     */
	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		// No need to check for obstacles since it's a single step move.
		if(isValidKingMove(xDisplacement, yDisplacement))
			return true;
		else
			return false;
	}

    /**
     * Checks if the move is a valid King move
     * @param xDisplacement change in x
     * @param yDisplacement change in y
     * @return true if the move is a valid king move
     */
	private boolean isValidKingMove(int xDisplacement, int yDisplacement) {
		// Diagonal
		if(Math.abs(xDisplacement) == 1 && Math.abs(yDisplacement) == 1)
			return true;
		// Horizontal
		else if(Math.abs(xDisplacement) == 1 && Math.abs(yDisplacement) == 0)
			return true;
		// Vertical
		else if(Math.abs(xDisplacement) == 0 && Math.abs(yDisplacement) == 1)
			return true;
		else
			return false;
	}

}

