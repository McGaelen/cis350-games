package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessStandardBoard;
import cis350.games.chessBoard.Color;


public class chessKing extends chessPiece {


	public chessKing(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "king";
	}
	

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

