package game.suite;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/***********************************************************************
 * controller class for the achievementsLaunch.fxml file
 * gives information such as the achievements, name, game and descriptions to 
 * achievementsLaunch to display them in the GUI.
 * @author Mathew Charath
 * @version 12/1/2017
 **********************************************************************/
public class AchievementsViewController implements Observer {

	/** ArrayList that holds the triggered achievements. */
    private ArrayList<Achievement> achievementsList;
    
    /** List view which holds the achievements to be displayed. */
    @FXML
    private ListView<Achievement> list = new ListView<Achievement>();
    
    /** JavaFX label that would contain a description of an achievement.*/
    @FXML
    private Label description = new Label();
    
    /** JavaFX label that would contain the title of the achievement. */
    @FXML
    private Label title = new Label();
    
    /** JavaFX label that would contain the name of the game. */
    @FXML
    private Label game = new Label();

    /******************************************************************
     * Constructor for the AchievementsViewController class.
     ********************************************************************/
    public AchievementsViewController() {
    	//ArrayList that would contain the triggered achievements
        this.achievementsList = new ArrayList<>();
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

           @Override
            public void handle(final MouseEvent event) {
                System.out.println("clicked on " 
            + list.getSelectionModel().getSelectedItem());
            }
        });
    }

    /*******************************************************************
     * Displays the name,game and description of a selected achievement. 
     * @param arg0 Instance of a mouse click event.
     ******************************************************************/
    @FXML 
    public void handleMouseClick(final MouseEvent arg0) {
    	switch (list.getSelectionModel().getSelectedItem()) {
    	case C4_DIAGONAL_WIN:
    		title.setText("DIAGONAL WIN");
    		game.setText("Game: Connect Four");
    		description.setText("Win a game of Connect Four "
    				+ "with a diagonal win");
    		break;
    	case C4_WIN_UNDER_MOVES_COUNT:
    		title.setText("WIN IN UNDER 5 MOVES");
    		game.setText("Game: CONNECT FOUR");
    		description.setText("Win a game of Connect Four in "
    				+ "under 5 moves");
    		break;
    	case TTT_TIE:
    		title.setText("IT'S A TIE!");
    		game.setText("Game: TIC-TAC-TOE");
    		description.setText("Tie in a game of Tic Tac Toe");
    		//System.out.println("Tie in a game of Tic Tac Toe");
    		break;
		case C4_FULL_BOARD:
			title.setText("FULL BOARD");
			game.setText("Game: CONNECT FOUR");
			description.setText("Use the entire board "
					+ "in Connect Four");
			break;
		case TTT_WIN_THREE:
			title.setText("TRIPLE WIN");
			game.setText("Game: TIC-TAC-TOE");
			description.setText("Win 3 games of tic tac toe??");
			//System.out.println("Win 3 games of tic tac toe??");
			break;
		case TTT_WIN_FULL_BOARD:
			title.setText("FULL BOARD WIN");
			game.setText("Game: TIC-TAC-TOE");
			description.setText("Win a in which all "
					+ "spaces are occupied");
			break;
		case CHESS_FIRST_WIN:
			title.setText("FIRST WIN");
			game.setText("Game: CHESS");
			description.setText("Win a game of chess");
			break;
		case CHESS_COMPLETE_RANDOM_GAME:
			title.setText("COMPLETE A GAME OF CHESS");
			game.setText("Game: CHESS");
			description.setText("Complete a game of chess");
			break;
		case CHESS_COMPLETE_LEGALS_GAME:
			title.setText("COMPLETE A GAME OF LEGALS");
			game.setText("Game: CHESS");
			description.setText("Complete a game of Legals");
			break;
		case CHESS_COMPLETE_PEASANTS_GAME:
			title.setText("COMPLETE A GAME OF PEASANTS");
			game.setText("Game: CHESS");
			description.setText("Complete a game of Peasants");
			break;
		case CHECKERS_FIRST_WIN:
			title.setText("FIRST WIN");
			game.setText("Game: CHECKERS");
			description.setText("Win a game of checkers");
			break;
		case CHECKERS_WIN_STREAK_3:
			title.setText("3 GAME WIN STREAK");
			game.setText("Game: CHECKERS");
			description.setText("Win 3 games of checkers in a row");
			break;
		case CHECKERS_WIN_STREAK_5:
			title.setText("5 GAME WIN STREAK");
			game.setText("Game: CHECKERS");
			description.setText("Win 5 games of checkers in a row");
			break;
		default:
			break;
    	}
    }
    
    /*******************************************************************
     * Update method of the observer class pattern that notifies when an
     * achievement has been triggered.
     * @param o observable object
     * @param arg an Object instance
     ******************************************************************/
    public void update(final Observable o, final Object arg) {
        //System.out.println("update() called");
        Achievement a;
        if (arg instanceof Achievement) {
            a = (Achievement) arg;

            if (!this.achievementsList.contains(a)) {
                this.achievementsList.add(a);
            }
        }
        //System.out.println(this.achievementsList);
        ObservableList<Achievement> achievements = FXCollections.
        		observableArrayList(achievementsList);
        list.setItems(achievements);
    }
    
    
    /*******************************************************************
     * Returns the GUI back to the main menu.
     ******************************************************************/
    @FXML private void goBack() {
        Main.getStage().setScene(Main.getMainScene());
    }
    
    /*******************************************************************
     * Exits the application.
     ******************************************************************/
    @FXML private void exit() {
    	System.exit(0);
    }
}