package cis350.games;

/**
 * chessMoveCommand class to store the moves by Players.
 */
public class chessMoveCommand {

    /**
     * The moving piece.
     */
    chessPiece movingPiece;
    /**
     * the enemy piece.
     */
    chessPiece enemyPiece;
    /**
     * the destination x-coordinate.
     */
    int xDestination;
    /**
     * the destination y-coordinate.
     */
    int yDestination;
    /**
     * the original x-coordinate.
     */
    int xOrigin;
    /**
     * the original y-coordinate.
     */
    int yOrigin;

    /**
     * Constructor for the move.
     * @param mPiece is the moving piece
     * @param ePiece is the enemy piece
     * @param xDest is the x-coordinate
     * @param yDest is the y-coordinate
     */
    public chessMoveCommand(final chessPiece mPiece,
            final chessPiece ePiece, final int xDest, final int yDest) {
        this.movingPiece = mPiece;
        this.enemyPiece = ePiece;
        this.xOrigin = mPiece.xLocation;
        this.yOrigin = mPiece.yLocation;
        this.xDestination = xDest;
        this.yDestination = yDest;
    }

    /**
     * Undoes the last move.
     */
    public void undo() {
        this.movingPiece.executeCaptureOrMove(xOrigin, yOrigin);
        if (this.enemyPiece != null) {
            this.enemyPiece.executeCaptureOrMove(xDestination, yDestination);
        }
    }

    /**
     * executes or performs the move.
     */
    public void execute() {
        movingPiece.executeCaptureOrMove(xDestination, yDestination);
    }

}

