package cis350.games;

/***********************************************************************
 * Class to create Tic Tac Toe game engine. TicTacToe contains a
 * 2D array of Strings for players "X" and "O" to move. Three moves in
 * a row horizontally, vertically, or diagonally results in a win.
 * @author Edric Lin
 * @version 10/18/17
 **********************************************************************/

public class TicTacToe {

    /** game board for tic tac toe. */
    private String[][] board;


    /*******************************************************************
     * Constructor for Tic Tac Toe game. Starts a new Tic Tac Toe game
     * by calling the startGame() function.
     ******************************************************************/
    public TicTacToe() {
        startGame();
    }

    /*******************************************************************
     * Starts a new game by creating a new 3x3 empty board. Each board
     * cell starts off as " ", meaning that it is empty.
     ******************************************************************/
    public void startGame() {

        // create 3x3 board
        board = new String[3][3];

        // fill board with place holders
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = " ";
            }
        }
    }

    /*******************************************************************
     * Checks that a certain move is valid. Moves are invalid if they
     * are outside the 3x3 board, or if the location already contains
     * a move.
     * @param row the row of the move location
     * @param column the column of the move location
     * @return true if valid move and false if invalid move
     ******************************************************************/
    public boolean isValidMove(final int row, final int column) {

        // move is not within board
        if (row > 2 || column > 2 || row < 0 || column < 0) {
            return false;
        }

        // location already contains a move
        if (!board[row][column].equals(" ")) {
            return false;
        }

        return true;
    }

    /*******************************************************************
     * Places the specified player at a specified move location.
     * Checks that the move is valid before making the move.
     * @param row the row of the move location
     * @param column the column of the move location
     * @param player the player marker ("X" or "O")
     * @throws {@link IllegalArgumentException} if move is invalid
     ******************************************************************/
    public void move(final int row, final int column, final String player) {

        // throw error if invalid move
        if (!isValidMove(row, column)) {
            throw new IllegalArgumentException();
            //System.out.println("Please enter a valid move.");
        }

        // place move if valid move
        board[row][column] = player;
    }

    /*******************************************************************
     * Checks if any win conditions have been met. Player wins if
     * there are three consecutive player markers horizontally,
     * vertically, or diagonally.
     * @return true if win condition has been met and false if win
     * condition has not been met
     ******************************************************************/
    public boolean isWinner() {

        // eight winning lines
        // each win line contains three coordinates
        int[][][] winLines = {

                // horizontal win
                {{0, 0}, {0, 1}, {0, 2} },
                {{1, 0}, {1, 1}, {1, 2} },
                {{2, 0}, {2, 1}, {2, 2} },

                // vertical win
                {{0, 0}, {1, 0}, {2, 0} },
                {{0, 1}, {1, 1}, {2, 1} },
                {{0, 2}, {1, 2}, {2, 2} },

                // diagonal win
                {{0, 0}, {1, 1}, {2, 2} },
                {{2, 0}, {1, 1}, {0, 2} },
        };

        // go through win line combinations
        for (int[][] line : winLines) {

            // need 3 matches to win
            int xMatch = 0;
            int oMatch = 0;

            // check coords with board
            for (int[] coord : line) {

                // if location has x, increment x
                if (board[coord[0]][coord[1]].equals("X")) {
                    xMatch++;
                }

                // if location has o, increment o
                if (board[coord[0]][coord[1]].equals("O")) {
                    oMatch++;
                }
            }

            // win condition met if three matches
            if (xMatch == 3 || oMatch == 3) {
                return true;
            }
        }

        // win condition not met
        return false;
    }

    /*******************************************************************
     * Checks if any win conditions have been met. Player wins if
     * there are three consecutive player markers horizontally,
     * vertically, or diagonally.
     * @return true if win condition has been met and false if win
     * condition has not been met
     ******************************************************************/
    public boolean isTie() {

        // iterate through board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                // tie condition not met if open spaces
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        // tie condition met
        return true;
    }

    /*******************************************************************
     * Return the current Tic Tac Toe board.
     * @return board
     ******************************************************************/
    public String[][] getBoard() {
        return board;
    }

    /*******************************************************************
     * Set the current Tic Tac Toe board. Used for testing purposes.
     * @param cBoard the board to set the Tic Tac Toe board to
     ******************************************************************/
    public void setBoard(final String[][] cBoard) {
        this.board = cBoard;
    }
}
