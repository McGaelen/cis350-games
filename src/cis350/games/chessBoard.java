package cis350.games;

/***********************************************************************
 * Superclass Board defines a standard board.
 **********************************************************************/
public abstract class chessBoard {


    /**
     * number of squares on the x-axis.
     */
    private int numXSquares;
    /**
     * number of squares on the y-axis.
     */
    private int numYSquares;
    /**
     * number of squares in total on the board.
     */
    private int totalSquares;
    /**
     * square list.
     */
    private chessSquare[][] squaresList;

    /**
     * Abstract method to check the boundaries of our chess board.
     * @param xLocation x-coordinate
     * @param yLocation y-coordinate
     * @return true if the location is inside the board
     */
    abstract boolean inBoardBounds(int xLocation, int yLocation);

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
    public chessSquare[][] getSquaresList() {
        return squaresList;
    }

    /**
     * Sets the square list.
     * @param squaresList List of squares.
     */
    public void setSquaresList(final chessSquare[][] squaresList) {
        this.squaresList = squaresList;
    }

    /**
     * Enum for Color values of black or white pieces and squares.
     */
    public enum Color {
        /**
         * Different colors.
         */
        white, black;

        /**
         * Returns the opposite color.
         * @return black, white
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
