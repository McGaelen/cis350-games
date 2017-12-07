package cis350.games;

/**
 * chessMoveCommand class to store the moves by Players.
 */
public class ChessMoveCommand {

    /**
     * The moving piece.
     */
    private ChessPiece movingPiece;
    /**
     * the enemy piece.
     */
    private ChessPiece enemyPiece;
    /**
     * the destination x-coordinate.
     */
    private int xDestination;
    /**
     * the destination y-coordinate.
     */
    private int yDestination;
    /**
     * the original x-coordinate.
     */
    private int xOrigin;
    /**
     * the original y-coordinate.
     */
    private int yOrigin;

    /**
     * Constructor for the move.
     * @param mPiece is the moving piece
     * @param ePiece is the enemy piece
     * @param xDest is the x-coordinate
     * @param yDest is the y-coordinate
     */
    public ChessMoveCommand(final ChessPiece mPiece,
            final ChessPiece ePiece, final int xDest, final int yDest) {
        this.movingPiece = mPiece;
        this.enemyPiece = ePiece;
        this.xOrigin = mPiece.getXLocation();
        this.yOrigin = mPiece.getYLocation();
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

