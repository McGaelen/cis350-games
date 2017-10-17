package cis350.games;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;

/**
 * Contains all the game logic for Connect Four. It also manages
 * an instance of a ConnectFourBoard. The current player's turn and
 * the number of rows and columns in the board are stored.
 *
 * Multiple operations can be performed on the board, such as checking
 * if there is a win condition, figuring out who won and how, placing a
 * chip in the board, resetting the board, and loading and saving the game.
 */
public final class ConnectFourEngine implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The number of chips in a row needed to win the game.
     */
    private final Integer connectsNeededForWin = 4;
    /**
     * The number of rows in the ConnectFourBoard.
     */
    private Integer rows;
    /**
     * The number of columns in the ConnectFourBoard.
     */
    private Integer cols;
    /**
     * The ConnectFourBoard managed by this object.
     */
    private ConnectFourBoard board;
    /**
     * The current player's turn.
     */
    private Integer turn;
    /**
     * The number corresponding to the winning player.
     */
    private Integer winner;
    /**
     * A description of how the winner won.
     */
    private String winCase;

    /**
     * Creates an instance of the Connect Four engine. Creates an
     * instance of ConnectFourBoard automatically.
     * @param boardRows number of rows in the ConnectFourBoard.
     * @param boardCols number of columns in the ConnectFourBoard.
     * @param startingTurn the player that will take the first turn.
     */
    public ConnectFourEngine(
            final Integer boardRows,
            final Integer boardCols,
            final Integer startingTurn) {
        this.rows = boardRows;
        this.cols = boardCols;
        this.board = new ConnectFourBoard(this.rows, this.cols);
        this.turn = startingTurn;
        this.winner = 0;
        this.winCase = "";
    }

    /**
     * Gets the number of rows in the ConnectFourBoard.
     * @return the number of rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the ConnectFourBoard.
     * @return the number of columns
     */
    public Integer getCols() {
        return cols;
    }

    /**
     *
     * @return
     */
    public Integer getCellOwner(final Integer row, final Integer col) {
        return board.getRow(row).get(col);
    }

    /**
     * Gets the player that will take the next turn.
     * @return the number of the player that is next
     */
    public Integer getTurn() {
        return turn;
    }

    /**
     * Gets the winner of the game, if there is one. <code>checkWin()</code>
     * should be called before this, otherwise this value may be stale.
     * @return the number of the player that won, or 0 if there's no winner.
     */
    public Integer getWinner() {
        return winner;
    }

    /**
     * Gets the method by which the winning player won the game.
     * @return the description of the win case. Will be empty if
     * there is no winner.
     */
    public String getWinCase() {
        return winCase;
    }

    /**
     * Checks the ConnectFourBoard for a winner.  Goes through 4 cases:
     * <ul>
     *     <li>Horizontal</li>
     *     <li>Vertical</li>
     *     <li>Up and to the Right</li>
     *     <li>Up and to the Left</li>
     * </ul>
     *
     * The first case found is declared the winning case.  A description of
     * the win case is stored in <code>winCase</code>, and the player that won
     * is stored in <code>winner</code>.
     * @return true if a winner was found; false if no winner was found.
     */
    public boolean checkWin() {
        Integer cell;
        Integer offset;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                offset = 0;
                cell = board.getRow(r).get(c);

                if (cell == 0) {
                    continue;
                }

                /* Horizontal Case: Iterates to the right of the current
                 * cell as long as the current cell is the same as the
                 * previous, up to this.connectsNeededForWin times. */
                for (int i = c; i < (c + this.connectsNeededForWin); i++) {
                    if (i == cols || !board.getRow(r).get(i).equals(cell)) {
                        break;
                    }

                    if (i == ((c + this.connectsNeededForWin) - 1)) {
                        this.winner = cell;
                        this.winCase = "Horizontal Win";
                        return true;
                    }
                }

                /* Vertical Case: Same as the horizontal case, but
                 * iterates downward from the current cell. */
                for (int i = r; i < (r + this.connectsNeededForWin); i++) {
                    if (i == rows || !board.getRow(i).get(c).equals(cell)) {
                        break;
                    }

                    if (i == ((r + this.connectsNeededForWin) - 1)) {
                        this.winner = cell;
                        this.winCase = "Vertical Win";
                        return true;
                    }
                }

                /* Up and Right Case: Iterates to the right, but keeps
                 * track of an offset that also raises the index up one
                 * row, to check diagonally. */
                for (int i = c; i < (c + this.connectsNeededForWin); i++) {
                    if (i == cols
                        || (r - offset) < 0
                        || !board.getRow(r - offset).get(i).equals(cell)) {
                        break;
                    }

                    if (i == ((c + this.connectsNeededForWin) - 1)) {
                        this.winner = cell;
                        this.winCase = "Diagonal (Up and to the Right) Win";
                        return true;
                    }
                    offset++;
                }

                /* Up and Left Case: Similar to up and right, this
                 * keeps an offset for the row, but iterates to the
                 * left instead. */
                offset = 0;
                for (int i = c; i > (c - this.connectsNeededForWin); i--) {
                    if (i < 0
                        || (r - offset) < 0
                        || !board.getRow(r - offset).get(i).equals(cell)) {
                        break;
                    }

                    if (i == ((c - this.connectsNeededForWin) + 1)) {
                        this.winner = cell;
                        this.winCase = "Diagonal (Up and to the Left) Win";
                        return true;
                    }
                    offset++;
                }
            }
        }

        return false;
    }

    /**
     * Places a chip assigned to whoever's turn it is currently.
     * @param col the column in which to drop the player's chip.
     * @throws Exception If the column doesn't exist in the board.
     */
    public Integer placeChip(final Integer col) throws Exception {
        return board.placeChipForPlayer(col, this.turn);
    }

    /**
     * Advances the current turn to the next player, and wraps
     * around to player 1 when the last player is reached.
     */
    public void advanceTurn() {
        this.turn++;
        if (this.turn > 2) {
            this.turn = 1;
        }
    }

    /**
     * Resets the engine to start the game over.
     */
    public void reset() {
        this.board = new ConnectFourBoard(this.rows, this.cols);
        this.turn = 1;
        this.winner = 0;
        this.winCase = "";
    }

    /**
     * Statically loads a serialized ConnectFourEngine from a file.
     * @param filename the path to the serialized file.
     * @return A new instance of a ConnectFourEngine with the data
     * obtained from the file.
     * @throws IOException When the path specified in
     * <code>filename</code> could not be found.
     * @throws ClassNotFoundException When the class loaded from the file
     * isn't a ConnectFourEngine.
     */
    public static ConnectFourEngine load(final File filename)
            throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(filename)
        );
        ConnectFourEngine game = (ConnectFourEngine) input.readObject();
        input.close();
        return game;
    }

    /**
     * Saves the calling instance to a file specified in <code>filename</code>.
     * @param filename the path to save the file to.
     * @throws IOException When the output file could not be opened.
     */
    public void save(final File filename) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(filename)
        );
        output.writeObject(this);
        output.close();
    }

    public String toString() {
        return this.board.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && this.getClass() == obj.getClass()) {
            final ConnectFourEngine that = (ConnectFourEngine) obj;

            if (this.rows.equals(that.rows)
                && this.cols.equals(that.cols)
                && this.board.equals(that.board)
                && this.turn.equals(that.turn)
                && this.winner.equals(that.winner)
                && this.winCase.equals(that.winCase)) {
                return true;
            }
        }
        return false;
    }
}
