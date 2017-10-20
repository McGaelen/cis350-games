package cis350.games;

import cis350.games.chessBoard.Color;

/**
 * Subclass of a Piece specific to a Rook.
 * This handles all movements the rook is capable.
 * of making.
 */
public class chessRook extends chessPiece {

    /**
     * Rook constructor initializes name of piece to Rook.
     * Every other parameter is initialized by superclass.
     * @param initX x-coordinate
     * @param initY y-coordinate
     * @param color color of the Rook
     * @param board the current board
     */
    public chessRook(
            final int initX, final int initY,
            final Color color, final chessStandardBoard board) {
        super(initX, initY, color, board);
        this.nameOfPiece = "rook";
    }

    /**
     * Rook specific implementation of abstract method.
     * @see chessGame.Piece#isValidSpecialMove(int, int)
     * @param newX the new x location
     * @param newY the new y location
     * @return boolean true if valid special move
     */
    @Override
    boolean isValidSpecialMove(final int newX, final int newY) {
        int xDisplacement = newX - xLocation;
        int yDisplacement = newY - yLocation;
        if (isValidRookMove(xDisplacement, yDisplacement)) {
            // Total number of steps the piece has to take.
            //Either x = 0 or y = 0.
            int steps = Math.max(Math.abs(xDisplacement),
                    Math.abs(yDisplacement));
            int xDirection = xDisplacement / steps;
            int yDirection = yDisplacement / steps;
            // Check for obstacles in path of Rook.
            for (int i = 1; i < steps; i++) {
                chessSquare squareToCheck = currentBoard.squaresList
                        [xLocation + i * xDirection]
                                [yLocation + i * yDirection];
                if (squareToCheck.isOccupied) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    /**
     * Helper method for Rook specific move check (Vertical + Horizontal).
     * @param xDisplacement change in x from the move
     * @param yDisplacement change in y from the move
     * @return boolean true if valid Rook move
     */
    public static boolean isValidRookMove(
           final int xDisplacement, final int yDisplacement) {
        // Vertical
        if (xDisplacement != 0 && yDisplacement == 0) {
            return true;
        } else if (xDisplacement == 0 && yDisplacement != 0) { // Horizontal
            return true;
        } else {
            return false;
        }
    }

}

