package cis350.games;

import cis350.games.chessPiece;
import cis350.games.chessSquare;
import cis350.games.chessStandardBoard;
import cis350.games.chessGame;
import cis350.games.chessBoard.Color;


// Subclass of a Piece specific to a Bishop. This handles all movements the bishop is capable
// of making.
 
public class chessBishop extends chessPiece {
	
	
	// Bishop constructor initializes name of piece to Bishop. Every other parameter is
	// initialized by superclass.
	 
	public chessBishop(int initX, int initY, Color color, chessStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "bishop";
	}
	
	
	// Bishop specific implementation of abstract method
	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		if(isValidBishopMove(xDisplacement, yDisplacement)){
			// Total number of steps the piece has to take
			int steps = Math.abs(xDisplacement);
			int xDirection = xDisplacement/steps;
			int yDirection = yDisplacement/steps;
			// Check for obstacles in path of Bishop.
			for(int i = 1; i < steps; i++){
				chessSquare squareToCheck = currentBoard.squaresList[xLocation + i*xDirection][yLocation + i*yDirection];
				if(squareToCheck.isOccupied)
					return false;
			}
			return true;
		}
		return false;
	}
	
	//Helper method for Bishop specific move check (Diagonals)
	
	public static boolean isValidBishopMove(int xDisplacement, int yDisplacement) {
		if((Math.abs(xDisplacement) == Math.abs(yDisplacement)) && xDisplacement != 0)
			return true;
		return false;
	}

}

