package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessSquare;
import cis350.games.chessStandardBoard;
import cis350.games.chessBoard.Color;

/***********************************************************************
 * Subclass of a Piece specific to a Pawn. This handles all 
 * movements the pawn is capable of making.
 **********************************************************************/
public class chessPawn extends chessPiece {

	/**
    * Constructor for the Pawn
    * @param initX x-coordinate
    * @param initY y-coordinate
    * @param color color of the piece
    * @param board the current board
    */
	public chessPawn(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "pawn";
	}

	/**
    * Checks if the Pawn move is valid
    * @param newX new x-coordinate
    * @param newY new y-coordinate
    * @return true if the move is valid
    */
	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		if(isValidPawnMove(xDisplacement, yDisplacement)){
			chessSquare squareToCheck = currentBoard.squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
			// If the pawn moves forward the square should not be occupied
			if(xDisplacement == 0){
				if(squareToCheck.isOccupied)
					return false;
				return true;
			}
			// If the pawn moves to capture the square should be occupied
			else{
				if(squareToCheck.isOccupied)
					return true;
				return false;
			}
		}
		return false;
	}

	/**
	    * Checks if the Pawn move is valid
	    * @param xDisplacement the change in x
	    * @param yDisplacement the change in y
	    * @return true if the move is valid
	    */
	private boolean isValidPawnMove(int xDisplacement, int yDisplacement) {
		// Two steps allowed in first move
		if((this.yLocation == 6 && this.color.equals(Color.black)) || (this.yLocation == 1 && this.color.equals(Color.white))){
			return handlePawnFirstMove(xDisplacement, yDisplacement);
		}
		// Single steps allowed in future moves.
		else{
			return handleRegularPawnMove(xDisplacement, yDisplacement);
		}
	}

	/**
	    * Handles Pawn moving only 1 space
	    * @param xDisplacement the change in x
	    * @param yDisplacement the change in y
	    * @return true if the move is valid
	    */
	private boolean handleRegularPawnMove(int xDisplacement, int yDisplacement) {
		if(color.equals(Color.white)){
			// White capture or move upwards.
			if(yDisplacement == 1 && (xDisplacement == 0 || Math.abs(xDisplacement) == 1))
				return true;
			else
				return false;
		}
		else{
			// Black capture or move downwards.
			if(yDisplacement == -1 && (xDisplacement == 0 || Math.abs(xDisplacement) == 1))
				return true;
			else
				return false;
		}
	}

	/**
	    * Handles Pawn moving only 2 spaces on the first move
	    * @param xDisplacement the change in x
	    * @param yDisplacement the change in y
	    * @return true if the move is valid
	    */
	private boolean handlePawnFirstMove(int xDisplacement, int yDisplacement) {
		// White pawns can only move upwards.
		if(color.equals(Color.white)){
			// Two step without capture.
			if((yDisplacement == 1 || yDisplacement == 2) && (xDisplacement == 0))
				return true;
			// One step plus capture.
			else if(yDisplacement == 1 && Math.abs(xDisplacement) == 1)
				return true;
			else			
				return false;
		}
		// Black pawns can only move downwards.
		else{
			if((yDisplacement == -1 || yDisplacement == -2) && (xDisplacement == 0))
				return true;
			else if(yDisplacement == -1 && Math.abs(xDisplacement) == 1)
				return true;
			else
				return false;
		}
	}
}

