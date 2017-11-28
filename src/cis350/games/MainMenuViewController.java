package cis350.games;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;

public class MainMenuViewController {

    /* To get access to buttons, labels, etc,
     * in your code, declare it with the same name as
     * it's corresponding fx:id set in the fxml file */
    /* Example: the name 'textfield1' matches
     * the fx:id attribute in the main-menu.fxml file */
    @FXML
    private TextField textfield1;
    @FXML
    private Label label1;
    @FXML
    private Button launchConnectFour;

    /* clicking the button will trigger this method, which sets the text
    * of label1 to the text currently entered in textfield1 */
    @FXML
    private void setText() {
        label1.setText(textfield1.getText());
    }

    @FXML
    private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
    @FXML
    private void connectFourStart() {
        //launch connectFourStart
    }
    @FXML
    private void checkersStart() {
        //launch checkers
    }
    @FXML
    private void chessStart() {
        //launch chess
    }
    @FXML
    private void ticTacToeStart() {
        //launch tic-tac-toe
    }
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

    @FXML
    private void ticTacToeButtonClick() {
//    if (Main.ticTacToeLaunchScene == null) {
//            try {
//                Main.ticTacToeLaunchScene
//    = new Scene(FXMLLoader.load(getClass().getResource(
        //"ticTacToeLaunch.fxml")));
//                Main.stage.setScene(Main.ticTacToeLaunchScene);
//            } catch (IOException e) {
//                System.out.println("Couldn't load ticTacToeLaunch.fxml");
//            }
//        } else {
//            Main.stage.setScene(Main.ticTacToeLaunchScene);
//        }
    TicTacToeGUI gui = new TicTacToeGUI();
    gui.setTitle("Tic Tac Toe Test");
    gui.pack();
    gui.setResizable(false);
    gui.setVisible(true);
    }

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

    @FXML
    private void exit() {
        System.exit(0);
    }
}
