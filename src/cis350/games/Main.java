package cis350.games;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for Game Suite.
 * @author Austin Maley
 *
 */
public final class Main extends Application {

    /* Static references to each scene in the app.
     * These are available globally */
	
	/** main stage to display everything. */
    private static Stage stage; // Don't overwrite this reference
    
    /** main screen to launch games. */
    private static Scene mainScene;
    
    /**
     * connect four scene.
     */
    private static Scene connectFourScene;
    /**
     * checkers scene.
     */
    private static Scene checkersLaunchScene;
    /**
     * chess scene.
     */
    private static Scene chessLaunchScene;
    /**
     * Tic Tac Toe scene.
     */
    private static Scene ticTacToeLaunchScene;
    /**
     * Achievements Scene.
     */
    private static Scene achievementsLaunchScene;
    /**
     * ConnectFour View Controller.
     */
    private static ConnectFourViewController connectFourViewController;
    /**
     * Achievements view controller.
     */
    private static AchievementsViewController achievementsViewController;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        mainScene = new Scene(FXMLLoader.load(getClass()
                .getResource("MainMenu.fxml")));
        primaryStage.setTitle("Game Suite 2.0");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("achievementsLaunch.fxml"));
        achievementsLaunchScene = new Scene(loader.load());
        achievementsViewController = loader.getController();
    }

    /**
     * Main method.
     * @param args argument.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
