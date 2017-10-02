package cis350.games;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class MainMenuViewController {

    /* To get access to buttons, labels, etc, in your code, declare it with the same name as
     * it's corresponding fx:id set in the fxml file */
    /* Example: the name 'textfield1' matches the fx:id attribute in the main-menu.fxml file */
    @FXML
    private TextField textfield1;
    @FXML
    private Label label1;

    /* clicking the button will trigger this method, which sets the text
    * of label1 to the text currently entered in textfield1 */
    @FXML
    private void setText() {
        label1.setText(textfield1.getText());
    }

    @FXML
    private void goToView() {
        if (Main.connectFourScene == null) {
            try {
                Main.connectFourScene = new Scene(FXMLLoader.load(getClass().getResource("connect-four.fxml")));
                Main.stage.setScene(Main.connectFourScene);
            } catch (IOException e) {
                System.out.println("couldn't load connect-four.fxml");
            }
        } else {
            Main.stage.setScene(Main.connectFourScene);
        }
    }
}
