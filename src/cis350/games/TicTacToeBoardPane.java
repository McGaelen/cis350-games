package cis350.games;

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

public class TicTacToeBoardPane extends Application implements EventHandler{

    /** game engine for 1024 game. */
    private TicTacToe game;

    /** 2d array to display game board. */
    private TicTacToeCellPane[][] cells;

    /** the current player's turn. */
    private String currentPlayer;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// set stage title
		primaryStage.setTitle("Tic-Tac-Toe");

        // create new tic tac toe game
        game = new TicTacToe();
		
        // set current player to X
        currentPlayer = "X";
        
        // create new cells based on board size
        cells = new TicTacToeCellPane[3][3];
        
		GridPane layout = new GridPane();
		
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c] = new TicTacToeCellPane("", r, c);
                //cells[r][c].addMouseListener(this);
                cells[r][c].setOnMouseClicked(this);
                layout.add(cells[r][c], c, r);
            }
        }
		
        // create window and add scene to window
		Scene scene = new Scene(layout, 450, 450);

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void handle(Event event) {

        // get the row and column of the cell that was clicked
        int row = ((TicTacToeCellPane) event.getSource()).getRow();
        int col = ((TicTacToeCellPane) event.getSource()).getCol();

        try {
            // move and update board
            game.move(row, col, currentPlayer);
            updateBoard();

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
            } else if (game.isTie()) {
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Game Message");
            	alert.setHeaderText(null);
            	alert.setContentText("It's a tie!");
            	alert.showAndWait();
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
}
