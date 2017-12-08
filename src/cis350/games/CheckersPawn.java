package cis350.games;

import cis350.games.CheckersBoard.Color;

/**
 * Class that represents a normal Checkers piece that is not a king.
 */
public class CheckersPawn extends CheckersPiece {

    /**
     * Creates a new normal Checkers piece.
     * @param initX the x location of the piece.
     * @param initY the y location of the piece.
     * @param color the color of the piece.
     * @param board the board that the piece belongs to.
     */
    public CheckersPawn(
            final int initX,
            final int initY,
            final Color color,
            final CheckersStandardBoard board) {
        super(initX, initY, color, board);
        this.setNameOfPiece("pawn");
    }

    /**
     * Detects if the requested move is a valid move.
     * @param newX The requested x coordinate of the Checkers piece.
     * @param newY The requested y coordinate of the Checkers piece.
     * @return true if the move is valid, false if not.
     */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - getXLocation();
        int yDisplacement = newY - getYLocation();
        CheckersSquare squareToCheck =
            getCurrentBoard()
            .getSquaresList()[getXLocation() + xDisplacement]
                    [getYLocation() + yDisplacement];
        CheckersSquare squareToCheck2 =
            getCurrentBoard()
            .getSquaresList()[getXLocation() + xDisplacement]
                    [getYLocation() + yDisplacement];
        if (isValidPawnMove(xDisplacement, yDisplacement)) {

            if (Math.abs(yDisplacement) == 2) {
                if (squareToCheck2.getIsOccupied()) {
                    return false;
                }
                return true;
            } else if (Math.abs(yDisplacement) == 1) {
                if (squareToCheck.getIsOccupied()) {
                    return false;
                }
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Checks if the requested move is valid specifically for a pawn.
     * @param xDisplacement the requested x coordinate
     * @param yDisplacement the requested y coordinate
     * @return true if it's a valid move, false if not.
     */
    private boolean isValidPawnMove(
            final int xDisplacement,
            final int yDisplacement) {
        if (getColor().equals(Color.white)) {
            if (Math.abs(xDisplacement) == 2 && yDisplacement == 2) {
                return true;
            } else if (Math.abs(xDisplacement) == 1 && yDisplacement == 1) {
                return true;
            }
            return false;
        } else {
            if (Math.abs(xDisplacement) == 2 && yDisplacement == -2) {
                return true;
            } else if (Math.abs(xDisplacement) == 1 && yDisplacement == -1) {
                return true;
            }
            return false;
        }
    }
}

