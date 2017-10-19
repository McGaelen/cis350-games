package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessStandardBoard;
import cis350.games.chessBoard.Color;

/**
 *
 */
public class chessKnight extends chessPiece {

    /**
     *
     * @param initX
     * @param initY
     * @param color
     * @param board
     */
	public chessKnight(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "knight";
	}

    /**
     *
     * @param newX
     * @param newY
     * @return
     */
	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		// No need to check for obstacles since knight can hop over pieces
		if(isValidKnightMove(xDisplacement, yDisplacement))
			return true;
		else
			return false;
	}

    /**
     *
     * @param xDisplacement
     * @param yDisplacement
     * @return
     */
	public static boolean isValidKnightMove(int xDisplacement, int yDisplacement) {
		if(Math.abs(xDisplacement) == 2 && Math.abs(yDisplacement) == 1)
			return true;
		else if(Math.abs(xDisplacement) == 1 && Math.abs(yDisplacement) == 2)
			return true;
		else
			return false;
	}

}

