package cis350.games;

import javafx.fxml.FXML;

public class ConnectFourViewController {

    @FXML
    private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
}
