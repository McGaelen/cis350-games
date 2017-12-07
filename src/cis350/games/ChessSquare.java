package cis350.games;

import cis350.games.ChessBoard.Color;

/**
 * This class represents a square on the chess board.
 */
public class ChessSquare {

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
    private ChessPiece occupyingPiece;

    /**
     * Constructor to initialize chess board Squares.
     * @param cIsOccupied is the square occupied?
     * @param cColor color of the piece
     */
    public ChessSquare(final boolean cIsOccupied, final Color cColor) {
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
    public ChessPiece getOccupyingPiece() {
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
    public void setOccupyingPiece(final ChessPiece nPiece) {
        this.occupyingPiece = nPiece;
    }
}
