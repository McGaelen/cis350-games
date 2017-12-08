package cis350.games;

import cis350.games.ChessBoard.Color;

/***********************************************************************
 * Subclass of a Piece specific to a Pawn.
 * This handles all movements the pawn is capable of making.
 **********************************************************************/
public class ChessPawn extends ChessPiece {

    /**
    * Constructor for the Pawn.
    * @param initX x-coordinate
    * @param initY y-coordinate
    * @param color color of the piece
    * @param board the current board
    */
    public ChessPawn(
            final int initX, final int initY,
            final Color color, final ChessStandardBoard board) {
        super(initX, initY, color, board);
        this.setNameOfPiece("pawn");
    }

    /**
    * Checks if the Pawn move is valid.
    * @param newX new x-coordinate
    * @param newY new y-coordinate
    * @return true if the move is valid
    */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - getXLocation();
        int yDisplacement = newY - getYLocation();
        if (isValidPawnMove(xDisplacement, yDisplacement)) {
            ChessSquare squareToCheck
            = getCurrentBoard().getSquaresList()
            [getXLocation() + xDisplacement][getYLocation() + yDisplacement];
            // If the pawn moves forward the square should not be occupied
            if (xDisplacement == 0) {
                if (squareToCheck.getIsOccupied()) {
                    return false;
                }
                return true;
            } else { // If the pawn moves to capture it should be occupied
                if (squareToCheck.getIsOccupied()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
        * Checks if the Pawn move is valid.
        * @param xDisplacement the change in x
        * @param yDisplacement the change in y
        * @return true if the move is valid
        */
    private boolean isValidPawnMove(
            final int xDisplacement, final int yDisplacement) {
        // Two steps allowed in first move
        if ((this.getYLocation() == 6
                && this.getColor().equals(Color.black))
                || (this.getYLocation() == 1
                && this.getColor().equals(Color.white))) {
            return handlePawnFirstMove(xDisplacement, yDisplacement);
        } else { // Single steps allowed in future moves.
            return handleRegularPawnMove(xDisplacement, yDisplacement);
        }
    }

    /**
        * Handles Pawn moving only 1 space.
        * @param xDisplacement the change in x
        * @param yDisplacement the change in y
        * @return true if the move is valid
        */
    private boolean handleRegularPawnMove(
            final int xDisplacement, final int yDisplacement) {
        if (getColor().equals(Color.white)) {
            // White capture or move upwards.
            if (yDisplacement == 1
                    && (xDisplacement == 0
                    || Math.abs(xDisplacement) == 1)) {
                return true;
            }
            return false;
        } else { // Black capture or move downwards.
            if (yDisplacement == -1
                    && (xDisplacement == 0 || Math.abs(xDisplacement) == 1)) {
                return true;
            }
            return false;
        }
    }

    /**
        * Handles Pawn moving only 2 spaces on the first move.
        * @param xDisplacement the change in x
        * @param yDisplacement the change in y
        * @return true if the move is valid
        */
    private boolean handlePawnFirstMove(
            final int xDisplacement, final int yDisplacement) {
        // White pawns can only move upwards.
        if (getColor().equals(Color.white)) {
            // Two step without capture.
            if ((yDisplacement == 1 || yDisplacement == 2)
                    && (xDisplacement == 0)) {
                return true;
            } else if (yDisplacement == 1 && Math.abs(xDisplacement) == 1) {
                return true;
            }
            return false;
        } else {
            if ((yDisplacement == -1 || yDisplacement == -2)
                    && (xDisplacement == 0)) {
                return true;
            } else if (yDisplacement == -1 && Math.abs(xDisplacement) == 1) {
                return true;
            }
            return false;
        }
    }
}

