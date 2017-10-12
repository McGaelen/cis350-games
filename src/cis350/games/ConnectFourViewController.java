package cis350.games;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConnectFourViewController {

    private Stage mainMenuRef;
    private ConnectFourEngine game;

    @FXML
    private AnchorPane connectFourRoot;

    @FXML
    private GridPane boardGrid;

    public ConnectFourViewController() {
        game = new ConnectFourEngine(6, 7, 1);
    }

    @FXML
    public void initialize() {
        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getCols(); j++) {
                Button b = new Button();
                b.setText(i + "-" + j);
                b.setId("gridButton[" + i + "][" + j + "]");
                boardGrid.add(b, j, i);
            }
        }

    }

    @FXML
    public void newGame() {
        // confirmation dialogue
    }

    @FXML
    public void loadGame() {
        // open up dialogue
    }

    @FXML
    public void saveGame() {
        // open up dialogue
    }

    @FXML
    public void goBack() {
        // set stage back to mainscene
    }
}
