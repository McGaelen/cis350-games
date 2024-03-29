package game.suite;

import game.suite.CheckersBoard.Color;

/**
 * This class represents a square on the Checkers board.
 */
public class CheckersSquare {

    /**
     * Square is occupied or not.
     */
    private boolean isOccupied;
    /**
     * Assign 0 as white color and 1 as black color.
     */
    private Color color;
    /**
     * Square objects keep track of which piece occupies that square.
     */
    private CheckersPiece occupyingPiece;

    /**
     * Constructor to initialize Checkers board Squares.
     * @param cIsOccupied Is the square occupied
     * @param cColor The color of the piece
     */
    public CheckersSquare(final boolean cIsOccupied, final Color cColor) {
        this.isOccupied = cIsOccupied;
        this.color = cColor;
        this.occupyingPiece = null;
    }
    
    /**
     * Returns isOccupied variable.
     * @return isOccupied
     */
    public boolean getIsOccupied() {
        return this.isOccupied;
    }
    
    /**
     * Returns color variable.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Returns occupyingPiece variable.
     * @return occupyingPiece
     */
    public CheckersPiece getOccupyingPiece() {
        return this.occupyingPiece;
    }
    
    /**
     * Sets the isOccupied variable.
     * @param bool State we are setting the boolean.
     */
    public void setIsOccupied(final boolean bool) {
        this.isOccupied = bool;
    }
    
    /**
     * Sets color variable.
     * @param col is the color we are setting
     */
    public void setColor(final Color col) {
        this.color = col;
    }
    
    /**
     * sets occupyingPiece variable.
     * @param nPiece is the piece we are setting
     */
    public void setOccupyingPiece(final CheckersPiece nPiece) {
        this.occupyingPiece = nPiece;
    }

    /**
     * Removes a piece from the Square.
     * @param square the square containing the piece to be removed
     */
    public void remove(final CheckersSquare square) {
        square.isOccupied = false;
        square.occupyingPiece = null;
    }
}
