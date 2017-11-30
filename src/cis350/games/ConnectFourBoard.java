package cis350.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class holds a 2d array of Integers that represent the game board.
 * It also manages placing chips, checking if it's full and printing it out.
 */
public final class ConnectFourBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * A representation of the connect four game board.
     */
    private ArrayList<ArrayList<Integer>> board;
    /**
     * The number of rows in the game board.
     */
    private Integer rows;
    /**
     * The number of columns in the game board.
     */
    private Integer cols;

    /**
     * Creates a new game board with the specified number of
     * rows and columns.
     * @param boardRows the number of rows in the game board.
     * @param boardCols the number of columns in the game board.
     */
    public ConnectFourBoard(final int boardRows, final int boardCols) {
        this.rows = boardRows;
        this.cols = boardCols;
        this.board = new ArrayList<ArrayList<Integer>>(this.rows);

        for (int j = 0; j < this.rows; j++) {
            this.board.add(j, new ArrayList<Integer>(this.cols));
            for (int i = 0; i < this.cols; i++) {
                this.board.get(j).add(0);
            }
        }
    }

    /**
     * Gets the ArrayList representation of the game board.
     * @return The ArrayList.
     */
    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }

    /**
     * Gets the number of rows in the game board.
     * @return the number of rows.
     */
    public Integer getRows() {
        return rows;
    }

    /** Gets the number of columns in the game board.
     * @return the number of columns.
     */
    public Integer getCols() {
        return cols;
    }

    /**
     * Places a chip in the specified column for the specified player.
     *
     * The chip will "fall down" to the lowest spot without a chip in it.
     * After placing the chip, the number of the row that the chip fell into
     * is returned.
     * @param column the column to place the chip in.
     * @param player the player to assign to the placed chip.
     * @return the number of the row that the chip fell into.
     * @throws Exception If an invalid column number is given.
     */
    public Integer placeChipForPlayer(
            final int column, final int player) throws Exception {
        int col = column - 1;

        if (col < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid column number.");
        } else if (this.board.get(0).get(col) != 0) {
            throw new Exception("Column is full.");
        }
        for (int i = 0; i < this.board.size(); i++) {
            if (i == (this.rows - 1)
                || this.board.get(i + 1).get(col) != 0) {
                this.board.get(i).set(col, player);
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the board is full by checking the top spot in
     * each column.
     * @return false if the board isn't full, true if it is full.
     */
    public boolean checkFull() {
        for (Integer val : this.board.get(0)) {
            if (val == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the row at the specified index.
     * @param row the index of the row to return.
     * @return an ArrayList of the row queried.
     * @throws IllegalArgumentException if the row number does not exist.
     */
    public ArrayList<Integer> getRow(
            final int row) throws IllegalArgumentException {
        if (row < 0 || row > this.rows - 1) {
            throw new IllegalArgumentException("Invalid row number");
        }
        return this.board.get(row);
    }

    /**
     * Provides a string representation of the game board.
     * @return the string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("\n");

        for (ArrayList<Integer> r : this.board) {
            for (Integer c : r) {
                switch (c) {
                    case 0:
                        s.append("-  ");
                        break;
                    case 1:
                        s.append(c).append("  ");
                        break;
                    case 2:
                        s.append(c).append("  ");
                        break;
                    default:
                        break;
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Compares if this and another ConnectFourBoard are equal.
     * @param obj the object to compare this instance to.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && this.getClass() == obj.getClass()) {
            final ConnectFourBoard that = (ConnectFourBoard) obj;

            if (this.rows.equals(that.rows)
                && this.cols.equals(that.cols)
                && this.board.equals(that.board)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a hash code for this object.
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.board,
                this.rows,
                this.cols
        );
    }
}
