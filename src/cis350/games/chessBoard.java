package cis350.games;

import cis350.games.chessSquare;
import cis350.games.chessBoard.Color;

/***********************************************************************
 * Superclass Board defines a standard board.
 **********************************************************************/
public abstract class chessBoard {

	
    /**
     * number of squares on the x-axis
     */
	public int numXSquares;
	/**
     * number of squares on the y-axis
     */
	public int numYSquares;
	/**
     * number of squares in total on the board
     */
	public int totalSquares;
	/**
     * square list
     */
	public chessSquare squaresList[][];

    /**
     * Abstract method to check the boundaries of our chess board.
     * @param xLocation x-coordinate
     * @param yLocation y-coordinate
     * @return true if the location is inside the board
     */
	abstract boolean inBoardBounds(int xLocation, int yLocation);
	
    /**
     * Enum for Color values of black or white pieces and squares
     */
	public enum Color{
		white, black;
		
		public Color opposite(){
			if(this == white)
				return black;
			else
				return white;
		}
	}
}
