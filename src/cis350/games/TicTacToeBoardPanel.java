package cis350.games;


import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/***********************************************************************
 * Class to display the Tic Tac Toe game board. Contains a 2D array of
 * CellPanels which displays the Tic Tac Toe game.
 * @author Edric Lin
 * @version 10/18/17
 **********************************************************************/
public class TicTacToeBoardPanel extends JPanel implements MouseListener {

    /** game engine for 1024 game. */
    private TicTacToe game;

    /** 2d array to display game board. */
    private TicTacToeCellPanel[][] cells;

    /** the current player's turn. */
    private String currentPlayer;

    /*******************************************************************
     * Constructor the Tic Tac Toe board. The player "X" moves first,
     * and a 3x3 board is created.
     ******************************************************************/
    public TicTacToeBoardPanel() {

        // create new tic tac toe game
        game = new TicTacToe();

        // set current player to X
        currentPlayer = "X";

        // create new cells based on board size
        cells = new TicTacToeCellPanel[3][3];

        // set grid layout for cells
        setLayout(new GridLayout(3, 3));

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new TicTacToeCellPanel("", r, c);
                cells[r][c].addMouseListener(this);
                add(cells[r][c]);
            }
        }
    }

    /*******************************************************************
     * Responds to a click on a cell, which is the player attempting to
     * make a move. Makes the move if it is valid, and checks to see
     * if win or tie conditions have been met. Updates the GUI board
     * after every move.
     * @param arg0 the mouse event that fired
     ******************************************************************/
    @Override
    public void mousePressed(final MouseEvent arg0) {

        // get the row and column of the cell that was clicked
        int row = ((TicTacToeCellPanel) arg0.getComponent()).getRow();
        int col = ((TicTacToeCellPanel) arg0.getComponent()).getCol();

        try {
            // move and update board
            game.move(row, col, currentPlayer);
            updateBoard();

            // check if win
            if (game.isWinner()) {
                JOptionPane.showMessageDialog(this,
                        currentPlayer + " wins!");
                game.startGame();
                currentPlayer = "X";
                updateBoard();
            } else if (game.isTie()) {
                JOptionPane.showMessageDialog(this,
                        "It's a tie!");
                game.startGame();
                currentPlayer = "X";
                updateBoard();
            } else {
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    "This space has already been taken.");
        }
    }

    /*******************************************************************
     * Updates the GUI board to mirror that of the game engine.
     ******************************************************************/
    public void updateBoard() {

        // get actual game board
        String[][] board = game.getBoard();

        // set initial cell values to that of actual board
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c].getMark().setText(board[r][c]);
            }
        }
    }

    /*******************************************************************
     * Return the current TicTacToe object.
     * @return game
     ******************************************************************/
    public TicTacToe getGame() {
        return game;
    }

    /*******************************************************************
     * Set the current TicTacToe object.
     * @param cGame the TicTacToe object to set the current TicTacToe
     * object to
     ******************************************************************/
    public void setGame(final TicTacToe cGame) {
        this.game = cGame;
    }

    /*******************************************************************
     * Return the current player.
     * @return currentPlayer.
     ******************************************************************/
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /*******************************************************************
     * Set the current player.
     * @param cCurrentPlayer the player to set the current player to
     ******************************************************************/
    public void setCurrentPlayer(final String cCurrentPlayer) {
        this.currentPlayer = cCurrentPlayer;
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) { }

    @Override
    public void mouseEntered(final MouseEvent arg0) { }

    @Override
    public void mouseExited(final MouseEvent arg0) { }

    @Override
    public void mouseReleased(final MouseEvent arg0) { }
}
