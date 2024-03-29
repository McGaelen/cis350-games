package game.suite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

/******************************************************************************
 * Controller class for the main menu.
 * @author Mathew Charath
 * @version 12/1/2017
 ******************************************************************************/
public class MainMenuViewController {
	
    /************************************************************
     * resets the scene to the main menu.
	 ***********************************************************/
    @FXML
    private void goBack() {
        Main.getStage().setScene(Main.getMainScene());
    }
    
    /************************************************************
     * Launches the connectFour game when the .
     * connect four button is clicked
     ***********************************************************/
    @FXML
    private void connectFourBtnClick() {
        if (Main.getConnectFourScene() == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("connect-four.fxml"));
                Main.setConnectFourScene(new Scene(loader.load()));
                Main.setConnectFourViewController(loader.getController());
                Main.getConnectFourViewController()
                .addConnectFourAchievementsObserver(Main
                        .getAchievementsViewController());
                Main.getStage().setScene(Main.getConnectFourScene());
            } catch (IOException e) {
                System.out.println(e.getMessage() 
                        + "Couldn't load connect-four.fxml");
            }
        } else {
            Main.getStage().setScene(Main.getConnectFourScene());
        }
    }

    /************************************************************
     * Launches the checkers game when the. 
     * checkers button is clicked
     ***********************************************************/
    @FXML
    private void checkersButtonClick() {
        CheckersGame.getGamePlayers();
        CheckersGame.startNewGame(0, null);
        
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
     * Launches the chess game when the.
     * chess button is clicked
     ***********************************************************/
    @FXML
    private void chessButtonClick() {
        ChessGame.getGamePlayers();
        ChessGame.startNewGame();
    }

    /************************************************************
     * Launches the tic-tac-toe game when the.
     * tic-tac-toe button is clicked
     ***********************************************************/
    @FXML
    private void ticTacToeButtonClick() {
  
    // create and add observer
    TicTacToeObservable observer = new TicTacToeObservable();
    observer.addTicTacToeObserver(Main.getAchievementsViewController());
    
    // add GUI to the scene and the scene to the stage
    Scene scene = new Scene(observer.getGui(), 440, 460);
    Main.getStage().setScene(scene);
    Main.getStage().sizeToScene();
    Main.getStage().setResizable(false);
    Main.getStage().setTitle("Tic Tac Toe");
    }

    /************************************************************
     * Launches the achievements screen when the .
     * achievements button is clicked 
     ***********************************************************/
    @FXML
    private void achievementsButtonClick() {
        if (Main.getAchievementsLaunchScene() == null) {
            try {
                Main.setAchievementsLaunchScene(new Scene(FXMLLoader.load(
                        getClass().getResource("achievementsLaunch.fxml"))));
                Main.getStage().setScene(Main.getAchievementsLaunchScene());
            } catch (IOException e) {
                System.out.println("Couldn't load achievementsLaunch.fxml");
            }
        } else {
            Main.getStage().setScene(Main.getAchievementsLaunchScene());
        }
    }

    /************************************************************
     *Exits the application .
     ***********************************************************/
    @FXML
    private void exit() {
        System.exit(0);
    }
}
