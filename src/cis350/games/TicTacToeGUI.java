package cis350.games;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TicTacToeGUI extends JFrame implements ActionListener{

	private TicTacToeBoardPanel board;

	/** menu bar for the game */
	private JMenuBar menu;

	/** menu bar drop down options*/
	private JMenu fileMenu;

	/** menu item to start new game */
	private JMenuItem newGameItem;

	/** menu item to quit game */
	private JMenuItem quitItem;

	public static void main(String[] args) {
		TicTacToeGUI gui = new TicTacToeGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Tic Tac Toe Test");
		gui.pack();
		gui.setResizable(false);
		gui.setVisible(true);
	}

	public TicTacToeGUI() {

		setLayout(new GridLayout());

		board = new TicTacToeBoardPanel();
		add(board);

		// create file menu drop down
		fileMenu = new JMenu("File");

		// create file menu options
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");

		// add menu options to file menu
		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);

		// add file menu drop down to menu bar
		menu = new JMenuBar();
		setJMenuBar(menu);
		menu.add(fileMenu);

		// add listeners for the two menu items
		newGameItem.addActionListener(this);
		quitItem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if new game menu item clicked, restart game and update board
		if (e.getSource() == newGameItem) {
			int value = JOptionPane.showConfirmDialog(this, ("Are you "
					+ "sure you want to start a new game?"), "Message", 
					JOptionPane.YES_NO_OPTION);
			if (value == JOptionPane.YES_OPTION) {
				board.getGame().startGame();
				board.setCurrentPlayer("X");
				board.updateBoard();
			}
		}

		// if quit game menu item clicked, close game
		if (e.getSource() == quitItem) {
			int value = JOptionPane.showConfirmDialog(this, ("Are you "
					+ "sure you want to quit?"), "Message", 
					JOptionPane.YES_NO_OPTION);
			if (value == JOptionPane.YES_OPTION) {
				System.exit(1);
			}
		}
	}
}
