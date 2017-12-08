package game.suite;


/**
 * Represents a command issued in Checkers. A stack of CheckersMoveCommands
 * will be kept in a stack to keep a history of all moves made.
 */
public class CheckersMoveCommand {
    /**
     * The piece that was moved by the player.
     */
    private CheckersPiece movingPiece;
    /**
     * The piece jumped by the player.
     */
    private CheckersPiece enemyPiece;
    /**
     * The resulting x coordinate of the player's piece.
     */
    private int xDestination;
    /**
     * The resulting y coordinate of the player's piece.
     */
    private int yDestination;
    /**
     * The original x coordinate of the player's piece.
     */
    private int xOrigin;
    /**
     * The original y coordinate of the player's piece.
     */
    private int yOrigin;

    /**
     * Creates a new record of a command in Checkers.
     * @param movingPieceParam the piece that moved.
     * @param enemyPieceParam the piece that was jumped.
     * @param xDestinationParam The resulting x coordinate
     *                          of the player's piece.
     * @param yDestinationParam The resulting y coordinate
     *                          of the player's piece.
     */
    public CheckersMoveCommand(
            final CheckersPiece movingPieceParam,
            final CheckersPiece enemyPieceParam,
            final int xDestinationParam,
            final int yDestinationParam) {
        this.movingPiece = movingPieceParam;
        this.enemyPiece = enemyPieceParam;
        this.xOrigin = movingPiece.getXLocation();
        this.yOrigin = movingPiece.getYLocation();
        this.xDestination = xDestinationParam;
        this.yDestination = yDestinationParam;
    }

    /**
     * Reverses the move stored by this Checkers command.
     */
    public void undo() {
        this.movingPiece.executeCaptureOrMove(xOrigin, yOrigin);
        if (this.enemyPiece != null) {
            this.enemyPiece.executeCaptureOrMove(xDestination, yDestination);
        }
    }

    /**
     * Executes this Checkers move.
     */
    public void execute() {
        movingPiece.executeCaptureOrMove(xDestination, yDestination);
    }

}

