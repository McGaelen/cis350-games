package cis350.games;

import cis350.games.chessBoard.Color;

/**
 * This class represents a square on the chess board.
 */
public class chessSquare {

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
    public chessPiece occupyingPiece;

    /**
     * Constructor to initialize chess board Squares.
     * @param cIsOccupied is the square occupied?
     * @param cColor color of the piece
     */
    public chessSquare(final boolean cIsOccupied, final Color cColor) {
        this.isOccupied = cIsOccupied;
        this.color = cColor;
        this.occupyingPiece = null;
    }
}
