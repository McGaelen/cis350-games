package cis350.games;

import cis350.games.checkersBoard.Color;

/**
 * Represents a King piece in checkers.
 */
public class checkersKing extends checkersPiece {

    /**
     * Creates a new checkersKing at the specified location.
     * @param initX the x location of the king.
     * @param initY the y location of the king.
     * @param color the king's color.
     * @param board reference to the game board the king is on.
     */
    public checkersKing(
            final int initX,
            final int initY,
            final Color color,
            final checkersStandardBoard board) {
        super(initX, initY, color, board);
        this.nameOfPiece = "king";
    }

    /**
     * Detects if the move requested by the player is valid.
     * @param newX the possible new x position of the king.
     * @param newY the possible new y position of the king.
     * @return true if it's a valid move, false if not.
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
        if (isValidKingMove(xDisplacement, yDisplacement)) {
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
     * Detects if the move requested by the player is valid for a king.
     * @param xDisplacement the x location of the king.
     * @param yDisplacement the y location of the king.
     * @return true if the move is valid, false if not.
     */
    private boolean isValidKingMove(
            final int xDisplacement,
            final int yDisplacement) {
        if (Math.abs(xDisplacement) == 2 && Math.abs(yDisplacement) == 2) {
            return true;
        } else if (Math.abs(xDisplacement) == 1
                && Math.abs(yDisplacement) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
