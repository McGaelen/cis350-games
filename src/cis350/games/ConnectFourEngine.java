/*
    CIS 350 Connect 4 - Game Engine
    Gaelen McIntee
    10/4/2017

    This class contains all the game logic for Connect Four in Ruby.
    It also manages an instance of a ConnectFourBoard.
*/

package cis350.games;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ConnectFourEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer rows;
    private Integer cols;
    private ConnectFourBoard board;
    private Integer turn;
    private Integer connectsNeededForWin;
    private Integer winner;
    private String winCase;

    public ConnectFourEngine(
            Integer rows, Integer cols, Integer startingTurn) {
        this.rows = rows;
        this.cols = cols;
        this.board = new ConnectFourBoard(this.rows, this.cols);
        this.connectsNeededForWin = 4;
        this.turn = startingTurn;
        this.winner = 0;
        this.winCase = "";
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }

    public ConnectFourBoard getBoard() {
        return board;
    }

    public Integer getTurn() {
        return turn;
    }

    public Integer getConnectsNeededForWin() {
        return connectsNeededForWin;
    }

    public Integer getWinner() {
        return winner;
    }

    public String getWinCase() {
        return winCase;
    }

    public boolean checkWin() {
        Integer cell;
        Integer offset;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                offset = 0;
                cell = board.getRow(r).get(c);

                if (cell == 0) { continue; }

			    /* Horizontal Case: Iterates to the right of the current
			     * cell as long as the current cell is the same as the
			     * previous, up to this.connectsNeededForWin times. */
                for ( int i = c; i < (c+this.connectsNeededForWin); i++)
                {
                    if (i == cols || !board.getRow(r).get(i).equals(cell))
                        break;

                    if (i == ((c+this.connectsNeededForWin)-1)) {
					    this.winner = cell;
					    this.winCase = "Horizontal Win";
                        return true;
                    }
                }

			    /* Vertical Case: Same as the horizontal case, but
			     * iterates downward from the current cell. */
                for ( int i = r; i < (r+this.connectsNeededForWin); i++)
                {
                    if (i == rows || !board.getRow(i).get(c).equals(cell))
                        break;

                    if (i == ((r+this.connectsNeededForWin)-1)) {
					    this.winner = cell;
					    this.winCase = "Vertical Win";
                        return true;
                    }
                }

			    /* Up and Right Case: Iterates to the right, but keeps
			     * track of an offset that also raises the index up one
			     * row, to check diagonally. */
                for ( int i = c; i < (c+this.connectsNeededForWin); i++)
                {
                    if (i == cols || (r-offset) < 0 || !board.getRow(r-offset).get(i).equals(cell))
                        break;

                    if (i == ((c+this.connectsNeededForWin)-1)) {
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
                for ( int i = c; i > (c-this.connectsNeededForWin); i--)
                {
                    if (i < 0 || (r-offset) < 0 || !board.getRow(r-offset).get(i).equals(cell))
                        break;

                    if (i == ((c-this.connectsNeededForWin)+1)) {
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

    public void placeChip(Integer col) throws Exception {
        board.placeChip(col, this.turn);
    }

    public void advanceTurn() {
        this.turn++;
        if (this.turn > 2) { this.turn = 1; }
    }

    public void reset() {
        this.board = new ConnectFourBoard(this.rows, this.cols);
        this.turn = 1;
        this.winner = 0;
        this.winCase = "";
    }

    public static ConnectFourEngine load(String filename)
            throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename));
        ConnectFourEngine game = (ConnectFourEngine)input.readObject();
        input.close();
        return game;
    }

    public void save(String filename) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
        output.writeObject(this);
        output.close();
    }

//    public static void main (String[] args) throws Exception {
//        ConnectFourEngine game = new ConnectFourEngine(10, 10, 1);
//        // Up and to the right case
//        game.placeChip(1);
//        game.advanceTurn();
//        game.placeChip(2);
//        game.placeChip(3);
//        game.placeChip(4);
//        game.advanceTurn();
//        game.placeChip(2);
//        game.advanceTurn();
//        game.placeChip(3);
//        game.placeChip(4);
//        game.advanceTurn();
//        game.placeChip(3);
//        game.advanceTurn();
//        game.placeChip(4);
//        game.advanceTurn();
//        game.placeChip(4);
//
//        System.out.println(game.getBoard());
//        System.out.println("is there a win?  " + game.checkWin());
//        System.out.println("who won?  player " + game.getWinner());
//        System.out.println(game.getWinCase());
//
//        game.reset();
//        System.out.println(game.getBoard() + "game reset!");
//        System.out.println("is there a win?  " + game.checkWin());
//        System.out.println("who won?  player " + game.getWinner());
//        System.out.println(game.getWinCase());
//
//        // –––––––––– Loading and Saving ––––––––––––––
//        ConnectFourEngine game2 = new ConnectFourEngine(5, 5, 1);
//        game2.placeChip(2);
//        game2.placeChip(1);
//        game2.placeChip(3);
//        game2.advanceTurn();
//        game2.save("out.c4");
//        System.out.println("game2 saved!");
//        System.out.println(game.getBoard());
//        System.out.println("rows  " + game.getRows());
//        System.out.println("cols  " + game.getCols());
//        System.out.println("turn  " + game.getTurn());
//
//        game = ConnectFourEngine.load("out.c4");
//        System.out.println(game.getBoard());
//        System.out.println("rows  " + game.getRows());
//        System.out.println("cols  " + game.getCols());
//        System.out.println("turn  " + game.getTurn());
//        System.out.println("new game loaded!");
//    }
}
