package game.suite;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

/***********************************************************************
 * Class to create the Tic Tac Toe GUI. Adds the game board and a
 * file menu to start a new game and quit.
 * 
 * @author Edric Lin
 * @version 12/5/17
 **********************************************************************/
public class TicTacToeFXGUI extends VBox implements EventHandler<ActionEvent> {
	
	/** menu bar. */
	private MenuBar menu;
	
	/** file menu drop down. */
	private Menu fileMenu;
	
	/** new game option in file menu. */
	private MenuItem newGame;
	
	/** quit option in file menu. */
	private MenuItem quit;
	
	/** tic tac toe game board. */
	private TicTacToeBoardPane board;
	
    /*******************************************************************
     * Constructor for Tic Tac Toe gui. Contains game board and menu
     * bar. 
     ******************************************************************/
	public TicTacToeFXGUI() {
		// set stage title
		//primaryStage.setTitle("Tic Tac Toe");
		
		//VBox layout = new VBox();
		
		// create file menu options
		newGame = new MenuItem("New Game");
		quit = new MenuItem("Quit");
		
		// add event listeners for menu options
		newGame.setOnAction(this);
		quit.setOnAction(this);
		
		// create file menu and add file menu options
		fileMenu = new Menu("File");
		fileMenu.getItems().add(newGame);
		fileMenu.getItems().add(quit);
		
		// create menu bar and add file menu
		menu = new MenuBar();
		menu.getMenus().add(fileMenu);
		
		// create new game board
		board = new TicTacToeBoardPane();
		
		// add menu bar and game to layout
		getChildren().add(menu);
		getChildren().add(board);
		
		
		// create scene and add to window
//		Scene scene = new Scene(layout, 450, 450);
//		primaryStage.setScene(scene);
//		primaryStage.sizeToScene();
//		primaryStage.setResizable(false);
//		primaryStage.show();
	}

    /*******************************************************************
     * Responds to file menu actions.
     * 
     * @param event the event that was fired
     ******************************************************************/
	@Override
	public void handle(final ActionEvent event) {
		// if new game menu item clicked, restart game and update board
        if (event.getSource() == newGame) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Game Message");
            alert.setHeaderText("Are you sure you want to start a "
            		+ "new game?");
            alert.setContentText("Click OK to continue.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
            	board.getGame().startGame();
                board.setCurrentPlayer("X");
                board.updateBoard();
            }
        }

        // if quit game menu item clicked, close game
        if (event.getSource() == quit) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Game Message");
            alert.setHeaderText("Are you sure you want to quit the "
            		+ "game?");
            alert.setContentText("Click OK to continue.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
            	Main.getStage().setScene(Main.getMainScene());
            }
        }
    }

    /*******************************************************************
     * Returns the game board pane.
     * 
     * @return the game board pane
     ******************************************************************/
	public TicTacToeBoardPane getBoard() {
		return board;
	}

    /*******************************************************************
     * Sets the game board pane.
     * 
     * @param board the game board pane to set
     ******************************************************************/
	public void setBoard(final TicTacToeBoardPane board) {
		this.board = board;
	}
	
	
}
