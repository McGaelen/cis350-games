package cis350.games;

import cis350.games.CheckersBoard.Color;

/**
 * Represents a King piece in Checkers.
 */
public class CheckersKing extends CheckersPiece {

    /**
     * Creates a new CheckersKing at the specified location.
     * @param initX the x location of the king.
     * @param initY the y location of the king.
     * @param color the king's color.
     * @param board reference to the game board the king is on.
     */
    public CheckersKing(
            final int initX,
            final int initY,
            final Color color,
            final CheckersStandardBoard board) {
        super(initX, initY, color, board);
        this.setNameOfPiece("king");
    }

    /**
     * Detects if the move requested by the player is valid.
     * @param newX the possible new x position of the king.
     * @param newY the possible new y position of the king.
     * @return true if it's a valid move, false if not.
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
        if (isValidKingMove(xDisplacement, yDisplacement)) {
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
        }
        return false;
    }
}
