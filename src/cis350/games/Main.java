package cis350.games;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Main extends Application {

    /* Static references to each scene in the app. These are available globally */
    static Stage stage; // Don't overwrite this reference
    static Scene mainScene;
    static Scene connectFourScene;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        mainScene = new Scene(
                FXMLLoader.load(getClass().getResource("MainMenu.fxml"))
        );
        primaryStage.setTitle("CIS 350 Games");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        stage = primaryStage;
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
