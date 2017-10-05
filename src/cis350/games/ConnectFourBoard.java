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

    public void setBoard(ArrayList<ArrayList<Integer>> board) {
        this.board = board;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public ConnectFourBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new ArrayList<ArrayList<Integer>>(this.rows);

        for (int j = 0; j < this.cols; j++) {
            this.board.set(j, new ArrayList<Integer>(this.cols));
            for (Integer i : this.board.get(j)) {
                i = 0;
            }
        }
    }

    public void placeChip(int col, int player) throws Exception {
        col -= 1;

        if (col < 0 || col >= this.cols) {
            throw new Exception("Invalid column number.");
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

    public ArrayList<Integer> getRow(int row) {
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


}
