package cis350.games;

import cis350.games.chessPiece;

/**
 * chessMoveCommand class to store the moves by Players
 */
public class chessMoveCommand {

    /**
     * The moving piece
     */
    chessPiece movingPiece;
    /**
     * the enemy piece
     */
    chessPiece enemyPiece;
    /**
     * the destination x-coordinate
     */
    int xDestination;
    /**
     * the destination y-coordinate
     */
    int yDestination;
    /**
     * the original x-coordinate
     */
    int xOrigin;
    /**
     * the original y-coordinate
     */
    int yOrigin;

    /**
     * Constructor for the move.
     * @param movingPiece is the moving piece
     * @param enemyPiece is the enemy piece
     * @param xDestination is the x-coordinate
     * @param yDestination is the y-coordinate
     */
    public chessMoveCommand(chessPiece movingPiece, chessPiece enemyPiece, int xDestination, int yDestination){
        this.movingPiece = movingPiece;
        this.enemyPiece = enemyPiece;
        this.xOrigin = movingPiece.xLocation;
        this.yOrigin = movingPiece.yLocation;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
    }

    /**
     * Undoes the last move
     */
    public void undo(){
        this.movingPiece.executeCaptureOrMove(xOrigin, yOrigin);
        if(this.enemyPiece != null)
            this.enemyPiece.executeCaptureOrMove(xDestination, yDestination);
    }

    /**
     * executes or performs the move
     */
    public void execute(){
        movingPiece.executeCaptureOrMove(xDestination, yDestination);
    }

}

