package ticTacToe;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainTestGUI extends JFrame{

	private CellPanel cell;
	private BoardPanel board;

	public static void main(String[] args) {
		MainTestGUI gui = new MainTestGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Tic Tac Toe Test");
		gui.pack();
		gui.setResizable(false);
		gui.setVisible(true);
	}
	
	public MainTestGUI() {
		
		setLayout(new GridLayout());
		
		cell = new CellPanel("X",0,0);
		
		board = new BoardPanel();
		
		add(board);
	}
}
