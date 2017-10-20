package cis350.games;

import cis350.games.checkersBoard.Color;

/**
 * Class that represents a normal checkers piece that is not a king.
 */
public class checkersPawn extends checkersPiece {

    /**
     * Creates a new normal checkers piece.
     * @param initX the x location of the piece.
     * @param initY the y location of the piece.
     * @param color the color of the piece.
     * @param board the board that the piece belongs to.
     */
    public checkersPawn(
            final int initX,
            final int initY,
            final Color color,
            final checkersStandardBoard board) {
        super(initX, initY, color, board);
        this.nameOfPiece = "pawn";
    }

    /**
     * Detects if the requested move is a valid move.
     * @param newX The requested x coordinate of the checkers piece.
     * @param newY The requested y coordinate of the checkers piece.
     * @return true if the move is valid, false if not.
     */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - xLocation;
        int yDisplacement = newY - yLocation;
        checkersSquare squareToCheck =
            currentBoard
            .squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
        checkersSquare squareToCheck2 =
            currentBoard
            .squaresList[xLocation + xDisplacement][yLocation + yDisplacement];
        if (isValidPawnMove(xDisplacement, yDisplacement)) {

            if (Math.abs(yDisplacement) == 2) {
                if (squareToCheck2.isOccupied) {
                    return false;
                } else {
                    return true;
                }
            } else if (Math.abs(yDisplacement) == 1) {
                if (squareToCheck.isOccupied) {
                    return false;
                } else {
                    return true;
                }
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
        if (color.equals(Color.white)) {
            if (Math.abs(xDisplacement) == 2 && yDisplacement == 2) {
                return true;
            } else if (Math.abs(xDisplacement) == 1 && yDisplacement == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Math.abs(xDisplacement) == 2 && yDisplacement == -2) {
                return true;
            } else if (Math.abs(xDisplacement) == 1 && yDisplacement == -1) {
                return true;
            } else {
                return false;
            }
        }
    }
}

