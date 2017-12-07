package cis350.games;

import cis350.games.ChessBoard.Color;

/***********************************************************************
 * Subclass of a Piece specific to a Knight.
 * This handles all movements the knight is capable of making.
 **********************************************************************/
public class ChessKnight extends ChessPiece {

    /**
     * Constructor for the Knight.
     * @param initX x-coordinate
     * @param initY y-coordinate
     * @param color color of the Knight
     * @param board the current board
     */
    public ChessKnight(
            final int initX, final int initY,
            final Color color, final ChessStandardBoard board) {
        super(initX, initY, color, board);
        this.setNameOfPiece("knight");
    }

    /**
     * Checks if the move is valid.
     * @param newX is the new x-coordinate
     * @param newY is the new y-coordinate
     * @return true if the move is valid
     */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - getXLocation();
        int yDisplacement = newY - getYLocation();
        // No need to check for obstacles since knight can hop over pieces
        if (isValidKnightMove(xDisplacement, yDisplacement)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the move is a knight move.
     * @param xDisplacement is the change in x
     * @param yDisplacement is the change in y
     * @return true if it is a valid knight move
     */
    public static boolean isValidKnightMove(
            final int xDisplacement, final int yDisplacement) {
        if (Math.abs(xDisplacement) == 2 && Math.abs(yDisplacement) == 1) {
            return true;
        } else if (Math.abs(xDisplacement)
                == 1 && Math.abs(yDisplacement) == 2) {
            return true;
        } else {
            return false;
        }
    }

}

