package cis350.games;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Main extends Application {

    /* Static references to each scene in the app.
     * These are available globally */
    static Stage stage; // Don't overwrite this reference
    static Scene mainScene;
    static Scene connectFourScene;
    static Scene checkersLaunchScene;
    static Scene chessLaunchScene;
    static Scene ticTacToeLaunchScene;
    static Scene achievementsLaunchScene;

    static ConnectFourViewController connectFourViewController;
    static AchievementsViewController achievementsViewController;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        mainScene = new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
        primaryStage.setTitle("Game Suite 2.0");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("achievementsLaunch.fxml"));
        achievementsLaunchScene = new Scene(loader.load());
        achievementsViewController = loader.getController();
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
