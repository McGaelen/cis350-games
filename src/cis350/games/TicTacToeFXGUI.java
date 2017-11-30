package cis350.games;

import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeFXGUI extends VBox implements EventHandler{
	
	private MenuBar menu;
	
	private Menu fileMenu;
	
	private MenuItem newGame;
	
	private MenuItem quit;
	
	private TicTacToeBoardPane board;
	
//	public static void main(String[] args) {
//		launch(args);
//	}

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

	@Override
	public void handle(Event event) {
		// if new game menu item clicked, restart game and update board
        if (event.getSource() == newGame) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Game Message");
            alert.setHeaderText("Are you sure you want to start a "
            		+ "new game?");
            alert.setContentText("Click OK to continue.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
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
            if (result.get() == ButtonType.OK){
            	Main.stage.setScene(Main.mainScene);
            }
        }
    }
	
}
