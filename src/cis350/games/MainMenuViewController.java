package cis350.games;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

/******************************************************************************
 * Controller class for the main menu
 * @author Mathew Charath
 * @version 12/1/2017
 ******************************************************************************/
public class MainMenuViewController {
	
	/************************************************************
	 * resets the scene to the main menu
	 ***********************************************************/
    @FXML
    private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
    
    /************************************************************
     * Launches the connectFour game when the 
     * connect four button is clicked
     ***********************************************************/
    @FXML
    private void connectFourBtnClick() {
        if (Main.connectFourScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("connect-four.fxml"));
                Main.connectFourScene = new Scene(loader.load());
                Main.connectFourViewController = loader.getController();
                Main.connectFourViewController.addConnectFourAchievementsObserver(Main.achievementsViewController);
                Main.stage.setScene(Main.connectFourScene);
            } catch (IOException e) {
                System.out.println(e.getMessage() + "Couldn't load connect-four.fxml");
            }
        } else {
            Main.stage.setScene(Main.connectFourScene);
        }
    }

    /************************************************************
     * Launches the checkers game when the 
     * checkers button is clicked
     ***********************************************************/
    @FXML
    private void checkersButtonClick() {
        checkersGame.getGamePlayers();
        checkersGame.startNewGame();
        /**
        if (Main.checkersLaunchScene == null) {
            try {
                Main.checkersLaunchScene =
                new Scene(FXMLLoader.load(getClass().getResource(
                "CheckerLaunch.fxml")));
                Main.stage.setScene(Main.checkersLaunchScene);
            } catch (IOException e) {
                System.out.println("Couldn't load CheckerLaunch.fxml");
            }
        } else {
            Main.stage.setScene(Main.checkersLaunchScene);
        }
         **/
    }

    /************************************************************
     * Launches the chess game when the
     * chess button is clicked
     ***********************************************************/
    @FXML
    private void chessButtonClick() {
        chessGame.getGamePlayers();
        chessGame.startNewGame();
        /**
        if (Main.chessLaunchScene == null) {
            try {
                Main.chessLaunchScene =
                new Scene(FXMLLoader.load(
                getClass().getResource("chessLaunch.fxml")));
                Main.stage.setScene(Main.chessLaunchScene);
            } catch (IOException e) {
                System.out.println("Couldn't load chessLaunch.fxml");
            }
        }
        else {
            Main.stage.setScene(Main.chessLaunchScene);
        }
        **/
    }

    /************************************************************
     * Launches the tic-tac-toe game when the
     * tic-tac-toe button is clicked
     ***********************************************************/
    @FXML
    private void ticTacToeButtonClick() {
  
    // create and add observer
    TicTacToeObserver observer = new TicTacToeObserver();
    observer.addTicTacToeObserver(Main.achievementsViewController);
    
    // add GUI to the scene and the scene to the stage
    Scene scene = new Scene(observer.getGui(), 440, 460);
    Main.stage.setScene(scene);
    Main.stage.sizeToScene();
    Main.stage.setResizable(false);
    Main.stage.setTitle("Tic Tac Toe");
    }

    /************************************************************
     * Launches the achievements screen when the 
     * achievements button is clicked 
     ***********************************************************/
    @FXML
    private void achievementsButtonClick() {
        if (Main.achievementsLaunchScene == null) {
            try {
                Main.achievementsLaunchScene
                = new Scene(FXMLLoader.load(
                        getClass().getResource("achievementsLaunch.fxml")));
                Main.stage.setScene(Main.achievementsLaunchScene);
            } catch (IOException e) {
                System.out.println("Couldn't load achievementsLaunch.fxml");
            }
        } else {
            Main.stage.setScene(Main.achievementsLaunchScene);
        }
    }

    /************************************************************
     *Exits the application 
     ***********************************************************/
    @FXML
    private void exit() {
        System.exit(0);
    }
}
