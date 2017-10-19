package cis350.games;

import cis350.games.checkersPiece;
import cis350.games.checkersStandardBoard;
import cis350.games.checkersBoard.Color;


public class checkersKing extends checkersPiece {


	public checkersKing(int initX, int initY, Color color, checkersStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "king";
	}
	

	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		checkersSquare squareToCheck = currentBoard.squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
		checkersSquare squareToCheck2 = currentBoard.squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
		if(isValidKingMove(xDisplacement, yDisplacement)) {
			if(Math.abs(yDisplacement) == 2) {
				if(squareToCheck2.isOccupied) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(Math.abs(yDisplacement) == 1) {
				if(squareToCheck.isOccupied) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		else {
			return false;
		}	
		return false;
	}


	private boolean isValidKingMove(int xDisplacement, int yDisplacement) {
			if(Math.abs(xDisplacement) == 2 && Math.abs(yDisplacement) == 2)
				return true;
			else if(Math.abs(xDisplacement) == 1 && Math.abs(yDisplacement) == 1)
				return true;
			else
				return false;
	}

}

