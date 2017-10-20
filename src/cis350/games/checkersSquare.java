package cis350.games;

import cis350.games.checkersPiece;
import cis350.games.checkersBoard.Color;

/**
 * This class represents a square on the checkers board.
  
 */
public class checkersSquare {

    /**
     * Square is occupied or not
     */
    public boolean isOccupied;
    /**
     * Assign 0 as white color and 1 as black color
     */
    public Color color;
    /**
     * Square objects keep track of which piece occupies that square.
     */
    public checkersPiece occupyingPiece;

    /**
     * Constructor to initialize checkers board Squares
     * @param isOccupied
     * @param color
     */
    public checkersSquare(boolean isOccupied, Color color) {
        this.isOccupied = isOccupied;
        this.color = color;
        this.occupyingPiece = null;
    }

    /**
     * Removes a piece from the Square
     * @param square the square containing the piece to be removed
     */
    public void remove(checkersSquare square) {
        square.isOccupied = false;
        square.occupyingPiece = null;
    }
}
