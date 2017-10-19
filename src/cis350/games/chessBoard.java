package cis350.games;

import cis350.games.chessSquare;
import cis350.games.chessBoard.Color;

/**
 *
 */
public abstract class chessBoard {

	
	//  Common variables in Chess game board. Defined to create new types of Boards.

	public int numXSquares;
	public int numYSquares;
	public int totalSquares;
	public chessSquare squaresList[][];

	
	// Abstract method to check the boundaries of our chess board.

    /**
     *
     * @param xLocation
     * @param yLocation
     * @return
     */
	abstract boolean inBoardBounds(int xLocation, int yLocation);
	
	
	//  Enum for Color values of black or white pieces and squares

    /**
     *
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
