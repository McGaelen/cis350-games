package cis350.games;

import cis350.games.checkersBoard.Color;

/**
 * This class represents a square on the checkers board.
 */
public class checkersSquare {

    /**
     * Square is occupied or not.
     */
    public boolean isOccupied;
    /**
     * Assign 0 as white color and 1 as black color.
     */
    public Color color;
    /**
     * Square objects keep track of which piece occupies that square.
     */
    public checkersPiece occupyingPiece;

    /**
     * Constructor to initialize checkers board Squares.
     * @param cIsOccupied Is the square occupied
     * @param cColor The color of the piece
     */
    public checkersSquare(final boolean cIsOccupied, final Color cColor) {
        this.isOccupied = cIsOccupied;
        this.color = cColor;
        this.occupyingPiece = null;
    }

    /**
     * Removes a piece from the Square.
     * @param square the square containing the piece to be removed
     */
    public void remove(final checkersSquare square) {
        square.isOccupied = false;
        square.occupyingPiece = null;
    }
}
