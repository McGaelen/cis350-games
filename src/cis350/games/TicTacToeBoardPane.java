package cis350.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToeBoardPane extends GridPane implements EventHandler {

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

	/** observer for achievements. */
    private TicTacToeObserver observer;
	
    
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
	public void handle(Event event) {

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
	
	public int getNumTurnsX() {
		return numTurnsX;
	}

	public void setNumTurnsX(int numTurnsX) {
		this.numTurnsX = numTurnsX;
	}

	public int getNumTurnsO() {
		return numTurnsO;
	}

	public void setNumTurnsO(int numTurnsO) {
		this.numTurnsO = numTurnsO;
	}
	
	public boolean isWinStatus() {
		return winStatus;
	}

	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}

	public boolean isTieStatus() {
		return tieStatus;
	}

	public void setTieStatus(boolean tieStatus) {
		this.tieStatus = tieStatus;
	}
}
