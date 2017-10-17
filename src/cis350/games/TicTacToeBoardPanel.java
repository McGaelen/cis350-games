package cis350.games;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeBoardPanel extends JPanel implements MouseListener{

	/** game engine for 1024 game */
	private TicTacToe game;

	/** 2d array to display game board */
	private TicTacToeCellPanel[][] cells;

	private String currentPlayer;

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
				cells[r][c] = new TicTacToeCellPanel("",r,c);
				cells[r][c].addMouseListener(this);
				add(cells[r][c]);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		int row = ((TicTacToeCellPanel) arg0.getComponent()).getRow();
		int col = ((TicTacToeCellPanel) arg0.getComponent()).getCol();

		try {
			// move and update board
			game.move(row, col, currentPlayer);
			updateBoard();

			// check if win
			if(game.isWinner()) {
				JOptionPane.showMessageDialog(this, 
						currentPlayer + " wins!");
				game.startGame();
				currentPlayer = "X";
				updateBoard();
			}

			//check if tie
			else if(game.isTie()) {
				JOptionPane.showMessageDialog(this, 
						"It's a tie!");
				game.startGame();
				currentPlayer = "X";
				updateBoard();
			}

			// else switch player turn 
			else {
				if (currentPlayer.equals("X")) {
					currentPlayer = "O";
				}
				else {
					currentPlayer = "X";
				}
			}
		} 

		// invalid move display error
		catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, 
					"This space has already been taken.");
		}
	}

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

	public TicTacToe getGame() {
		return game;
	}

	public void setGame(TicTacToe game) {
		this.game = game;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
