package cis350.games;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds a 2d array of Integers that represent the game board.
 * It also manages placing chips, checking if it's full and printing it out.
 */
public class ConnectFourBoard implements Serializable {

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
     * @param rows the number of rows in the game board.
     * @param cols the number of columns in the game board.
     */
    public ConnectFourBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new ArrayList<ArrayList<Integer>>(this.rows);

        for (int j = 0; j < this.rows; j++) {
            this.board.add(j, new ArrayList<Integer>(this.cols));
            for (int i = 0; i < this.cols; i++) {
                this.board.get(j).add(0);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }


    public void placeChipForPlayer(int col, int player) throws Exception {
        col -= 1;

        if (col < 0 || col >= this.cols) {
            throw new IllegalArgumentException("Invalid column number.");
        } else if (this.board.get(0).get(col) != 0) {
            throw new Exception("Column is full.");
        } else {
            for (int i = 0; i < this.board.size(); i++) {
                if (i == (this.rows-1) || this.board.get(i+1).get(col) != 0) {
                    this.board.get(i).set(col, player);
                    break;
                }
            }
        }
    }

    public boolean checkFull() {
        for (Integer val : this.board.get(0)) {
            if (val == 0) { return false; }
        }
        return true;
    }

    public ArrayList<Integer> getRow(int row) throws IllegalArgumentException {
        if (row < 0 || row > this.rows-1) {
            throw new IllegalArgumentException("Invalid row number");
        }
        return this.board.get(row);
    }

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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && this.getClass() == obj.getClass()) {
            final ConnectFourBoard that = (ConnectFourBoard) obj;
            return this.board.equals(that.board);
        }
        return false;
    }

//        public static void main(String[] args) {
//        ConnectFourBoard board = new ConnectFourBoard(2, 1);
//        ConnectFourBoard board2 = new ConnectFourBoard(2, 1);
//        System.out.println(board.equals(board2));
//        System.out.println(board);
//        try {
//            board.placeChipForPlayer(1,1);
//            board.placeChipForPlayer(1,1);
//        } catch (Exception e) {
//            //no op;
//        }
//        System.out.println(board);
//        System.out.println("Is the board full?  " + board.checkFull());
//    }
}
