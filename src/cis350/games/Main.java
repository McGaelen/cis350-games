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
        setStage(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("achievementsLaunch.fxml"));
        setAchievementsLaunchScene(new Scene(loader.load()));
        setAchievementsViewController(loader.getController());
    }

    /**
     * Main method.
     * @param args argument.
     */
    public static void main(final String[] args) {
        launch(args);
    }
    /**
     * Gets stage.
     * @return stage
     */
    public static Stage getStage() {
        return stage;
    }
    /**
     * Sets stage.
     * @param stage stage.
     */
    public static void setStage(final Stage stage) {
        Main.stage = stage;
    }
    /**
     * Gets C4 scene.
     * @return connectFourScene
     */
    public static Scene getConnectFourScene() {
        return connectFourScene;
    }
    /**
     * Set connect Four scene.
     * @param connectFourScene connect 4 scene.
     */
    public static void setConnectFourScene(final Scene connectFourScene) {
        Main.connectFourScene = connectFourScene;
    }
    /**
     * get Checkers scene.
     * @return checkers Scene
     */
    public static Scene getCheckersLaunchScene() {
        return checkersLaunchScene;
    }
    /**
     * Sets checkers scene.
     * @param checkersLaunchScene checkers scene
     */
    public static void setCheckersLaunchScene(final Scene checkersLaunchScene) {
        Main.checkersLaunchScene = checkersLaunchScene;
    }

    /**
     * Gets chess scene.
     * @return chessLaunchScene
     */
    public static Scene getChessLaunchScene() {
        return chessLaunchScene;
    }
    /**
     * Set chess scene.
     * @param chessLaunchScene chess scene.
     */
    public static void setChessLaunchScene(final Scene chessLaunchScene) {
        Main.chessLaunchScene = chessLaunchScene;
    }
    /**
     * get tic tac toe scene.
     * @return tic tact toe scene
     */
    public static Scene getTicTacToeLaunchScene() {
        return ticTacToeLaunchScene;
    }
    /**
     * Sets tic tac toe scene.
     * @param ticTacToeLaunchScene tic tac toe scene
     */
    public static void setTicTacToeLaunchScene(
            final Scene ticTacToeLaunchScene) {
        Main.ticTacToeLaunchScene = ticTacToeLaunchScene;
    }
    /**
     * Gets achievements scene.
     * @return achievementsScene
     */
    public static Scene getAchievementsLaunchScene() {
        return achievementsLaunchScene;
    }
    /**
     * Sets Achievements scene.
     * @param achievementsLaunchScene achievementsLaunchScene
     */
    public static void setAchievementsLaunchScene(
            final Scene achievementsLaunchScene) {
        Main.achievementsLaunchScene = achievementsLaunchScene;
    }
    /**
     * Gets connect four view controller.
     * @return connect 4 controller
     */
    public static ConnectFourViewController getConnectFourViewController() {
        return connectFourViewController;
    }
    /**
     * Sets connect 4 view controller.
     * @param connectFourViewController connectFourViewController
     */
    public static void setConnectFourViewController(
            final ConnectFourViewController connectFourViewController) {
        Main.connectFourViewController = connectFourViewController;
    }
    /**
     * Gets Achievements view controller.
     * @return achievements view controller
     */
    public static AchievementsViewController getAchievementsViewController() {
        return achievementsViewController;
    }
    /**
     * Set achievements view controller.
     * @param achievementsViewController achievementsViewController
     */
    public static void setAchievementsViewController(
            final AchievementsViewController achievementsViewController) {
        Main.achievementsViewController = achievementsViewController;
    }
}
