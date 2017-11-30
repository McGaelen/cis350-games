package cis350.games;


/**
 * Subclass of a board.
 * Standard version of a checkers Board. Has methods for creating a standard
 * checkers board and populating it with regular checkers pieces.
 * Can be used to create a standard game of checkers.
 */
public class checkersStandardBoard extends checkersBoard {

    /**
     * Tracker for the white king for check.
     * checkmate and game ending conditions.
     */
    private checkersKing whiteKingTracker;
    /**
     * Tracker for the black king for check.
     * checkmate and game ending conditions.
     */
    private checkersKing blackKingTracker;
    
    /**
     * Returns white king tracker.
     * @return whiteKingTracker
     */
     public checkersKing getWhiteKingTracker() {
         return this.whiteKingTracker;
     }
     
     /**
     * Returns black king tracker.
     * @return blackKingTracker
     */
     public checkersKing getBlackKingTracker() {
         return this.blackKingTracker;
     }

    /**
     * Method to initialize the checkers board.
     * @param xSquares the number of squares on the x-axis
     * @param ySquares the number of squares on the y-axis
     */
    public checkersStandardBoard(final int xSquares, final int ySquares) {

        this.setNumXSquares(xSquares);
        this.setNumYSquares(ySquares);
        this.setTotalSquares(this.getNumXSquares() * this.getNumYSquares());
        this.setSquaresList(new checkersSquare
                [this.getNumXSquares()][this.getNumYSquares()]);
        populateBoardWithSquares();
        this.whiteKingTracker = null;
        this.blackKingTracker = null;
    }

    /**
     * Method to populate our board with Squares.
     * General pattern of white and black squares on the board.
     */
    public void populateBoardWithSquares() {
        for (int i = 0; i < this.getNumXSquares(); i++) {
            for (int j = 0; j < this.getNumYSquares(); j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        getSquaresList()[i][j] =
                                new checkersSquare(false, Color.black);
                    } else {
                        getSquaresList()[i][j] =
                                new checkersSquare(false, Color.white);
                    }
                } else {
                    if (j % 2 == 0) {
                        getSquaresList()[i][j] =
                                 new checkersSquare(false, Color.white);
                    } else {
                        getSquaresList()[i][j] =
                                new checkersSquare(false, Color.black);
                    }
                }
            }
        }
    }

    /**
     * Method to populate our checkers board with standard pieces.
     */
    public void populateBoardWithPieces() {
        setupPawns();
    }

    /**
     * Setup 8 black and 8 white pawns in their initial positions.
     */
    public void setupPawns() {
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
            checkersPawn newWhitePawn =
                    new checkersPawn(i, 0, Color.white, this);
            checkersPawn newWhitePawn2 =
                    new checkersPawn(i, 2, Color.white, this);
            checkersPawn newBlackPawn =
                    new checkersPawn(i, 6, Color.black, this);
            this.getSquaresList()[i][0].setIsOccupied(true);
            this.getSquaresList()[i][2].setIsOccupied(true);
            this.getSquaresList()[i][6].setIsOccupied(true);
            this.getSquaresList()[i][0].setOccupyingPiece(newWhitePawn);
            this.getSquaresList()[i][2].setOccupyingPiece(newWhitePawn2);
            this.getSquaresList()[i][6].setOccupyingPiece(newBlackPawn);
            } else {
                checkersPawn newWhitePawn =
                        new checkersPawn(i, 1, Color.white, this);
                checkersPawn newBlackPawn =
                        new checkersPawn(i, 7, Color.black, this);
                checkersPawn newBlackPawn2 =
                        new checkersPawn(i, 5, Color.black, this);
                this.getSquaresList()[i][1].setIsOccupied(true);
                this.getSquaresList()[i][7].setIsOccupied(true);
                this.getSquaresList()[i][5].setIsOccupied(true);
                this.getSquaresList()[i][1].setOccupyingPiece(newWhitePawn);
                this.getSquaresList()[i][7].setOccupyingPiece(newBlackPawn);
                this.getSquaresList()[i][5].setOccupyingPiece(newBlackPawn2);
            }
        }
    }

    /**
     * Helper method to check if locations passed in are mapped.
     * @see cis350.games.BoardinBoardBounds(int, int)
     * @param newX new x coordinate
     * @param newY new y coordinate
     * @return boolean true if move is in board bounds
     */
    public boolean inBoardBounds(final int newX, final int newY) {
        if (newX < getNumXSquares() && newY
                < getNumYSquares() && newX > -1 && newY > -1) {
            return true;
        } else {
            return false;
        }
    }

}
