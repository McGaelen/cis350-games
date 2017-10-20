package cis350.games;

/**
 * Represents a game board for a Checkers Game.
 */
public abstract class checkersBoard {

    /**
     * The number of squares in the game board in the X direction.
     */
    int numXSquares;
    /**
     * The number of squares in the game board in the Y direction.
     */
    int numYSquares;
    /**
     * The total number of squares in the game board.
     */
    int totalSquares;
    /**
     * A double array of checkersSquares representing the entire game board.
     */
    public checkersSquare[][] squaresList;

    /**
     * Abstract method to check if a given coordinate is within the
     * bounds of the checkersBoard.
     * @param xLocation the x coordinate to check bounds with.
     * @param yLocation the y coordinate to check bounds with.
     * @return true if the coordinate is within the checkersBoard,
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
