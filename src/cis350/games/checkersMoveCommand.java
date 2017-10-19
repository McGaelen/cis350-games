package cis350.games;

import cis350.games.checkersPiece;

/**
 *
 */
public class checkersMoveCommand {
	

	checkersPiece movingPiece;
	checkersPiece enemyPiece;
	int xDestination;
	int yDestination;
	int xOrigin;
	int yOrigin;

    /**
     *
     * @param movingPiece
     * @param enemyPiece
     * @param xDestination
     * @param yDestination
     */
	public checkersMoveCommand(checkersPiece movingPiece, checkersPiece enemyPiece, int xDestination, int yDestination){
		this.movingPiece = movingPiece;
		this.enemyPiece = enemyPiece;
		this.xOrigin = movingPiece.xLocation;
		this.yOrigin = movingPiece.yLocation;
		this.xDestination = xDestination;
		this.yDestination = yDestination;
	}

    /**
     *
     */
	public void undo(){
		this.movingPiece.executeCaptureOrMove(xOrigin, yOrigin);
		if(this.enemyPiece != null)
			this.enemyPiece.executeCaptureOrMove(xDestination, yDestination);
	}

    /**
     *
     */
	public void execute(){
		movingPiece.executeCaptureOrMove(xDestination, yDestination);
	}

}

