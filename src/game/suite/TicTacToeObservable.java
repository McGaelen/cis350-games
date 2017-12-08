package game.suite;

import java.util.Observable; 
import java.util.Observer;

import javafx.event.Event;
import javafx.event.EventHandler;

/***********************************************************************
 * Class to turn the TicTacToeFXGUI into an observable class. Creates
 * a new TicTacToeFXGUI and checks for achievements after every click
 * on the baord.
 * 
 * @author Edric Lin
 * @version 12/5/17
 **********************************************************************/
public class TicTacToeObservable extends Observable 
implements EventHandler<Event> {

	/** the gui for the tic tac toe game. */
	private TicTacToeFXGUI gui;

    /*******************************************************************
     * Constructor for Tic Tac Toe gui observer. Crates a new gui and
     * places an event listener on the game board pane.
     ******************************************************************/
	public TicTacToeObservable() {
		super();
		gui = new TicTacToeFXGUI();
		gui.getBoard().setOnMouseClicked(this);
	}

    /*******************************************************************
     * Method to add an observer to the TicTacToeObservable object.
     * 
     * @param o the observer to add.
     ******************************************************************/
	public void addTicTacToeObserver(final Observer o) {
		addObserver(o);
	}
	
    /*******************************************************************
     * Method to check for achievements after each click on the board.
     * Notifies observers 
     * 
     * @param event the event that fired
     ******************************************************************/
	@Override
	public void handle(final Event event) {
		
		TicTacToeBoardPane board = gui.getBoard();
		
		if (event.getSource() == board) {
			System.out.println("Board Clicked");
			System.out.println("Num turns X: " 
			+ board.getNumTurnsX());
			System.out.println("Num turns O: " 
			+ board.getNumTurnsO());
			
			if (board.isTieStatus()) {
				setChanged();
				notifyObservers(Achievement.TTT_TIE);
				System.out.println("Tie Achievement");
				board.setNumTurnsX(0);
				board.setNumTurnsO(0);
				board.setTieStatus(false);
			}
			
			if (board.isWinStatus()) {
				if (board.getNumTurnsX() <= 3 
						&& board.getNumTurnsO() <= 3) {
					setChanged();
					notifyObservers(Achievement.
							TTT_WIN_THREE);
					System.out.println("Win 3 "
							+ "Move Achievement");
				}
				
				
				if ((board.getNumTurnsX() 
						+ board.getNumTurnsO()) == 9) {
					setChanged();
					notifyObservers(Achievement.
							TTT_WIN_FULL_BOARD);
					System.out.println("Win Full Board "
							+ "Achievement");
				}
				board.setNumTurnsX(0);
				board.setNumTurnsO(0);
				board.setWinStatus(false);
			}
		}
	}

    /*******************************************************************
     * Return the gui in the TicTacToeObservable.
     * 
     * @return the gui in the TicTacToeObservable.
     ******************************************************************/
	public TicTacToeFXGUI getGui() {
		return gui;
	}

    /*******************************************************************
     * Set the gui in the TicTacToeObservable.
     * 
     * @param gui the gui from the TicTacToeObservable.
     ******************************************************************/
	public void setGui(final TicTacToeFXGUI gui) {
		this.gui = gui;
	}
}
