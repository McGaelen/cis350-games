package game.suite;


/**
 * Represents a game board for a Checkers Game.
 */
public abstract class CheckersBoard {

    /**
     * The number of squares in the game board in the X direction.
     */
    private int numXSquares;
    /**
     * The number of squares in the game board in the Y direction.
     */
    private int numYSquares;
    /**
     * The total number of squares in the game board.
     */
    private int totalSquares;
    /**
     * A double array of CheckersSquares representing the entire game board.
     */
    private CheckersSquare[][] squaresList;
    
    /**
     * Get total squares.
     * @return totalSquares
     */
    public int getTotalSquares() {
        return totalSquares;
    }

    /**
     * Set total squares.
     * @param totalSquares The total amount of squares.
     */
    public void setTotalSquares(final int totalSquares) {
        this.totalSquares = totalSquares;
    }

    /**
     * Get number of vertical squares.
     * @return numYSquares
     */
    public int getNumYSquares() {
        return numYSquares;
    }

    /**
     * Set the number of vertical squares.
     * @param numYSquares Total amount of vertical squares. 
     */
    public void setNumYSquares(final int numYSquares) {
        this.numYSquares = numYSquares;
    }

    /**
     * Get the number of horizontal squares.
     * @return numXSquares
     */
    public int getNumXSquares() {
        return numXSquares;
    }

    /**
     * Sets the number of horizontal squares.
     * @param numXSquares Number of horizontal squares.
     */
    public void setNumXSquares(final int numXSquares) {
        this.numXSquares = numXSquares;
    }

    /**
     * Returns the square list.
     * @return squaresList
     */
    public CheckersSquare[][] getSquaresList() {
        return squaresList;
    }

    /**
     * Sets the square list.
     * @param squaresList List of squares.
     */
    public void setSquaresList(final CheckersSquare[][] squaresList) {
        this.squaresList = squaresList;
    }

    /**
     * Abstract method to check if a given coordinate is within the
     * bounds of the CheckersBoard.
     * @param xLocation the x coordinate to check bounds with.
     * @param yLocation the y coordinate to check bounds with.
     * @return true if the coordinate is within the CheckersBoard,
     * false if not.
     */
    abstract boolean inBoardBounds(int xLocation, int yLocation);

    /**
     * Represents Color values of black or white pieces and squares.
     */
    public enum Color {
        /**
         * The color white.
         */
        white,
        /**
         * The color black.
         */
        black;

        /**
         * Gives the opposite color of the calling Color enum.
         * @return white if the color value is black; black if
         * the color value is white.
         */
        public Color opposite() {
            if (this == white) {
                return black;
            } else {
                return white;
            }
        }
    }
}
