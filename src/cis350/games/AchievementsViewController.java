package cis350.games;

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
 * achievementsLaunch to display them in the GUI
 * @author Mathew Charath
 * @version 12/1/2017
 **********************************************************************/
public class AchievementsViewController implements Observer {

	/** ArrayList that holds the triggered achievements */
    private ArrayList<Achievement> achievementsList;
    
    /** List view which holds the achievements to be displayed */
    @FXML
    ListView<Achievement> list = new ListView<Achievement>();
    
    /** JavaFX label that would contain a description of an achievement*/
    @FXML
    Label description = new Label();
    
    /** JavaFX label that would contain the title of the achievement */
    @FXML
    Label title = new Label();
    
    /** JavaFX label that would contain the name of the game */
    @FXML
    Label game = new Label();

    /*******************************************************************************
     * Constructor for the AchievementsViewController class
     *******************************************************************************/
    public AchievementsViewController() {
    	//ArrayList that would contain the triggered achievements
        this.achievementsList = new ArrayList<>();
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

           @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
            }
        });
    }

    /******************************************************************************
     * Displays the name,game and description of a selected achievement 
     * @param MouseEvent arg0 Instance of a mouse click event
     ******************************************************************************/
    @FXML 
    public void handleMouseClick(MouseEvent arg0) {
    	switch(list.getSelectionModel().getSelectedItem()) {
    	case C4_WIN_UNDER_MOVES_COUNT:
    		title.setText("WIN IN UNDER 5 MOVES");
    		game.setText("Game: CONNECT FOUR");
    		description.setText("Win a game of Connect Four in under 5 moves");
    		//System.out.println("Win a game of Connect Four in under 5 moves");
    		break;
    	case TTT_TIE:
    		title.setText("IT'S A TIE!");
    		game. setText("Game: TIC-TAC-TOE");
    		description.setText("Tie in a game of Tic Tac Toe");
    		//System.out.println("Tie in a game of Tic Tac Toe");
    		break;
		case C4_FULL_BOARD:
			title.setText("FULL BOARD");
			game.setText("Game: CONNECT FOUR");
			description.setText("Use the entire board in Connect Four");
			//System.out.println("Use the entire board in Connect Four");
			break;
		case TTT_WIN_THREE:
			title.setText("TRIPLE WIN");
			game.setText("Game: TIC-TAC-TOE");
			description.setText("Win 3 games of tic tac toe??");
			//System.out.println("Win 3 games of tic tac toe??");
			break;
		default:
			break;
    	}
    }
    
    /*****************************************************************************
     * Update method of the observer class pattern that notifies when an
     * achievement has been triggered
     * @param o observable object
     * @param arg an Object instance
     *****************************************************************************/
    public void update(Observable o, Object arg) {
        //System.out.println("update() called");
        Achievement a;
        if (arg instanceof Achievement) {
            a = (Achievement)arg;

            if (!this.achievementsList.contains(a)) {
                this.achievementsList.add(a);
            }
        }
        //System.out.println(this.achievementsList);
        ObservableList<Achievement> achievements = FXCollections.observableArrayList(achievementsList);
        list.setItems(achievements);
    }
    
    
    /*******************************************************************
     * Returns the GUI back to the main menu.
     ******************************************************************/
    @FXML private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
    
    /****************************************************************************
     * Exits the application
     ****************************************************************************/
    @FXML private void exit() {
    	System.exit(0);
    }
}
