package cis350.games;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller {

    /* To get access to buttons, labels, etc, in your code, declare it with the same name as
     * it's corresponding fx:id set in the fxml file */
    /* Example: the name 'textfield1' matches the fx:id attribute in the sample.fxml file */
    @FXML
    private FlowPane windowCenter;

    /* clicking the button will trigger this method, which sets the text
    * of label1 to the text currently entered in textfield1 */
    @FXML
    private void launchConnectFour() {
        try {
            Node connectFourNode = FXMLLoader.load(ConnectFourViewController.class.getResource("connect-four.fxml"));
            windowCenter.getChildren().add(connectFourNode);
        } catch (IOException e) {
            System.out.println("Couldn't load Connect Four FXML." + e.getMessage());
        }

    }
}
