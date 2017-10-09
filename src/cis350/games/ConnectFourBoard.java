/*
    CIS 350 Connect 4 - Game Board
    Gaelen McIntee
    10/4/2017

    This class holds a 2d array of Integers that represent the game board.
    It also manages placing chips, checking if it's full and printing it out.
*/


package cis350.games;

import java.util.ArrayList;

public class ConnectFourBoard {

    private ArrayList<ArrayList<Integer>> board;
    private Integer rows;
    private Integer cols;


    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }

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

    public void placeChip(int col, int player) throws Exception {
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
        row -= 1;
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
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

//    public static void main(String[] args) {
//        ConnectFourBoard board = new ConnectFourBoard(2, 1);
//        System.out.println(board);
//        try {
//            board.placeChip(1,1);
//            board.placeChip(1,1);
//        } catch (Exception e) {
//            //no op;
//        }
//        System.out.println(board);
//        System.out.println("Is the board full?  " + board.checkFull());
//    }

}
