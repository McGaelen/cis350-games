package game.suite;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

/***********************************************************************
 * Class to display the Tic Tac Toe game board. Contains a 2D array of
 * CellPane which displays the Tic Tac Toe game.
 * 
 * @author Edric Lin
 * @version 12/5/17
 **********************************************************************/
public class TicTacToeBoardPane extends GridPane 
implements EventHandler<Event> {

    /** game engine for 1024 game. */
    private TicTacToe game;

    /** 2d array to display game board. */
    private TicTacToeCellPane[][] cells;

    /** the current player's turn. */
    private String currentPlayer;
    
    /** number of X turns for achievements. */
    private int numTurnsX;
    
    /** number of O turns for achievements. */
    private int numTurnsO;
    
    /** tie status for achievements. */
    private boolean tieStatus;
    
    /** win status for achievements. */
    private boolean winStatus;
	
    
    /*******************************************************************
     * Constructor for Tic Tac Toe board. Starts a new game and 
     * initializes an empty board. Current player is set at x.
     ******************************************************************/
	public TicTacToeBoardPane() {

		super();
		
        // create new tic tac toe game
        game = new TicTacToe();
		
        // set current player to X
        currentPlayer = "X";
        
        // create new cells based on board size
        cells = new TicTacToeCellPane[3][3];
		
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new TicTacToeCellPane("", r, c);
                //cells[r][c].addMouseListener(this);
                cells[r][c].setOnMouseClicked(this);
                add(cells[r][c], c, r);
            }
        }
	}

    /*******************************************************************
     * Responds to a click on a cell, which is the player attempting to
     * make a move. Makes the move if it is valid, and checks to see
     * if win or tie conditions have been met. Updates the GUI board
     * after every move.
     * 
     * @param event the event that fired
     ******************************************************************/
	@Override
	public void handle(final Event event) {

        // get the row and column of the cell that was clicked
        int row = ((TicTacToeCellPane) event.getSource()).getRow();
        int col = ((TicTacToeCellPane) event.getSource()).getCol();

        try {
            // move and update board
            game.move(row, col, currentPlayer);
            updateBoard();
            
            if (currentPlayer == "X") {
            	numTurnsX++;
            } else {
                numTurnsO++;
            }

            // check if win
            if (game.isWinner()) {
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Game Message");
            	alert.setHeaderText(null);
            	alert.setContentText(currentPlayer + " wins!");
            	alert.showAndWait();
                game.startGame();
                currentPlayer = "X";
                updateBoard();
                
                winStatus = true;
            } else if (game.isTie()) {
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Game Message");
            	alert.setHeaderText(null);
            	alert.setContentText("It's a tie!");
            	alert.showAndWait();
                game.startGame();
                currentPlayer = "X";
                updateBoard();
                
                tieStatus = true;
            } else {
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }
            }
        } catch (IllegalArgumentException e) {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Game Message");
        	alert.setHeaderText(null);
        	alert.setContentText("This space has already been taken.");
        	alert.showAndWait();
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
     * 
     * @return game
     ******************************************************************/
    public TicTacToe getGame() {
        return game;
    }

    /*******************************************************************
     * Set the current TicTacToe object.
     * 
     * @param cGame the TicTacToe object to set the current TicTacToe
     * object to
     ******************************************************************/
    public void setGame(final TicTacToe cGame) {
        this.game = cGame;
    }

    /*******************************************************************
     * Return the current player.
     * 
     * @return currentPlayer.
     ******************************************************************/
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /*******************************************************************
     * Set the current player.
     * 
     * @param cCurrentPlayer the player to set the current player to
     ******************************************************************/
    public void setCurrentPlayer(final String cCurrentPlayer) {
        this.currentPlayer = cCurrentPlayer;
    }
	
    /*******************************************************************
     * Return number of turns X has taken.
     * 
     * @return the number of turns X has taken.
     ******************************************************************/
	public int getNumTurnsX() {
		return numTurnsX;
	}

    /*******************************************************************
     * Set the number of turns X has taken.
     * 
     * @param numTurnsX the number of turns X has taken.
     ******************************************************************/
	public void setNumTurnsX(final int numTurnsX) {
		this.numTurnsX = numTurnsX;
	}

    /*******************************************************************
     * Return number of turns O has taken.
     * 
     * @return the number of turns O has taken.
     ******************************************************************/
	public int getNumTurnsO() {
		return numTurnsO;
	}

    /*******************************************************************
     * Set the number of turns O has taken.
     * 
     * @param numTurnsO the number of turns O has taken.
     ******************************************************************/
	public void setNumTurnsO(final int numTurnsO) {
		this.numTurnsO = numTurnsO;
	}
	
    /*******************************************************************
     * Return the win status of the game.
     * 
     * @return the win status of the game.
     ******************************************************************/
	public boolean isWinStatus() {
		return winStatus;
	}

    /*******************************************************************
     * Set the win status of the game.
     * 
     * @param winStatus the win status of the game
     ******************************************************************/
	public void setWinStatus(final boolean winStatus) {
		this.winStatus = winStatus;
	}

    /*******************************************************************
     * Return the tie status of the game.
     * 
     * @return the tie status of the game.
     ******************************************************************/
	public boolean isTieStatus() {
		return tieStatus;
	}

    /*******************************************************************
     * Set the tie status of the game.
     * 
     * @param tieStatus the tie status of the game
     ******************************************************************/
	public void setTieStatus(final boolean tieStatus) {
		this.tieStatus = tieStatus;
	}
}
