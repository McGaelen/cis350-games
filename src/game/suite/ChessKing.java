package game.suite;

/***********************************************************************
 * Subclass of a Piece specific to a King. This handles all
 * movements the king is capable of making.
 **********************************************************************/
public class ChessKing extends ChessPiece {

    /**
     * Constructor for the King.
     * @param initX x-coordinate
     * @param initY y-coordinate
     * @param color If the color of the king
     * @param board is the current board
     */
    public ChessKing(final int initX, final int initY,
                     final ChessBoard.Color color, final ChessStandardBoard board) {
        super(initX, initY, color, board);
        this.setNameOfPiece("king");
    }

    /**
     * Checks if the move is valid.
     * @param newX the new x-coordinate
     * @param newY the new y-coordinate
     * @return true if the move is valid
     */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - getXLocation();
        int yDisplacement = newY - getYLocation();
        // No need to check for obstacles since it's a single step move.
        if (isValidKingMove(xDisplacement, yDisplacement)) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the move is a valid King move.
     * @param xDisplacement change in x
     * @param yDisplacement change in y
     * @return true if the move is a valid king move
     */
    private boolean isValidKingMove(
            final int xDisplacement, final int yDisplacement) {
        if (Math.abs(xDisplacement)
                == 1 && Math.abs(yDisplacement) == 1) { // Diagonal
            return true;
        } else if (Math.abs(xDisplacement)
                == 1 && Math.abs(yDisplacement) == 0) { // Horizontal
            return true;
        } else if (Math.abs(xDisplacement)
                == 0 && Math.abs(yDisplacement) == 1) { // Vertical
            return true;
        }
        return false;
    }

}

