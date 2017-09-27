package cis350.games;

import javafx.fxml.FXML;

import javafx.scene.control.*;

public class Controller {

    /* To get access to buttons, labels, etc, in your code, declare it with the same name as
     * it's corresponding fx:id set in the fxml file */
    /* Example: the name 'textfield1' matches the fx:id attribute in the sample.fxml file */
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
}
