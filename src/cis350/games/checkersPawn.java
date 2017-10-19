package cis350.games;

import cis350.games.checkersSquare;
import cis350.games.checkersPiece;
import cis350.games.checkersSquare;
import cis350.games.checkersStandardBoard;
import cis350.games.checkersBoard.Color;


public class checkersPawn extends checkersPiece {


	public checkersPawn(int initX, int initY, Color color, checkersStandardBoard board) {
		super(initX, initY, color, board);
		this.nameOfPiece = "pawn";
	}


	@Override
	boolean isValidSpecialMove(int newX, int newY) {
		int xDisplacement = newX - xLocation;
		int yDisplacement = newY - yLocation;
		checkersSquare squareToCheck = currentBoard.squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
		checkersSquare squareToCheck2 = currentBoard.squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
		if(isValidPawnMove(xDisplacement, yDisplacement)) {
			
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


	private boolean isValidPawnMove(int xDisplacement, int yDisplacement) {		
		if(color.equals(Color.white)){
			if(Math.abs(xDisplacement) == 2 && yDisplacement == 2)
				return true;
			else if(Math.abs(xDisplacement) == 1 && yDisplacement == 1)
				return true;
			else
				return false;
		}
		else{
			if(Math.abs(xDisplacement) == 2 && yDisplacement == -2)
				return true;
			else if(Math.abs(xDisplacement) == 1 && yDisplacement == -1)
				return true;
			else
				return false;
		}
	}
}

