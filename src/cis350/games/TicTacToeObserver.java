package cis350.games;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class TicTacToeObserver extends Observable implements EventHandler{

	private TicTacToeFXGUI gui;

	public TicTacToeObserver() {
		super();
		gui = new TicTacToeFXGUI();
		gui.getBoard().setOnMouseClicked(this);
	}

	public void addTicTacToeObserver(Observer o) {
		addObserver(o);
	}
	
	@Override
	public void handle(Event event) {
		
		TicTacToeBoardPane board = gui.getBoard();
		
		if (event.getSource() == board) {
			System.out.println("Board Clicked");
			System.out.println("Num turns X: " + board.getNumTurnsX());
			System.out.println("Num turns O: " + board.getNumTurnsO());
			
			if (board.isTieStatus()) {
				setChanged();
				notifyObservers(Achievement.TTT_TIE);
				System.out.println("Tie Achievement");
				board.setNumTurnsX(0);
				board.setNumTurnsO(0);
				board.setTieStatus(false);
			}
			
			if (board.isWinStatus()) {
				if (board.getNumTurnsX() <= 3 && 
						board.getNumTurnsO() <= 3) {
					setChanged();
					notifyObservers(Achievement.TTT_WIN_THREE);
					System.out.println("Win 3 Move Achievement");
				}
				board.setNumTurnsX(0);
				board.setNumTurnsO(0);
				board.setWinStatus(false);
			}
		}
	}

	public TicTacToeFXGUI getGui() {
		return gui;
	}

	public void setGui(TicTacToeFXGUI gui) {
		this.gui = gui;
	}
}
