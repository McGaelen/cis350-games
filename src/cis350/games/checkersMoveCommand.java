package cis350.games;


/**
 * Represents a command issued in checkers. A stack of checkersMoveCommands
 * will be kept in a stack to keep a history of all moves made.
 */
public class checkersMoveCommand {
    /**
     * The piece that was moved by the player.
     */
    checkersPiece movingPiece;
    /**
     * The piece jumped by the player.
     */
    checkersPiece enemyPiece;
    /**
     * The resulting x coordinate of the player's piece.
     */
    int xDestination;
    /**
     * The resulting y coordinate of the player's piece.
     */
    int yDestination;
    /**
     * The original x coordinate of the player's piece.
     */
    int xOrigin;
    /**
     * The original y coordinate of the player's piece.
     */
    int yOrigin;

    /**
     * Creates a new record of a command in checkers.
     * @param movingPieceParam the piece that moved.
     * @param enemyPieceParam the piece that was jumped.
     * @param xDestinationParam The resulting x coordinate
     *                          of the player's piece.
     * @param yDestinationParam The resulting y coordinate
     *                          of the player's piece.
     */
    public checkersMoveCommand(
            final checkersPiece movingPieceParam,
            final checkersPiece enemyPieceParam,
            final int xDestinationParam,
            final int yDestinationParam) {
        this.movingPiece = movingPieceParam;
        this.enemyPiece = enemyPieceParam;
        this.xOrigin = movingPiece.xLocation;
        this.yOrigin = movingPiece.yLocation;
        this.xDestination = xDestinationParam;
        this.yDestination = yDestinationParam;
    }

    /**
     * Reverses the move stored by this checkers command.
     */
    public void undo() {
        this.movingPiece.executeCaptureOrMove(xOrigin, yOrigin);
        if (this.enemyPiece != null) {
            this.enemyPiece.executeCaptureOrMove(xDestination, yDestination);
        }
    }

    /**
     * Executes this checkers move.
     */
    public void execute() {
        movingPiece.executeCaptureOrMove(xDestination, yDestination);
    }

}

