package cis350.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ConnectFourViewController {

    private ConnectFourEngine game;
    private ArrayList<Pane> imageGrid; // will be used to hold the images in the grid

    @FXML
    private GridPane boardGrid;

    public ConnectFourViewController() {
        game = new ConnectFourEngine(5, 10, 1);
    }

    @FXML
    public void initialize() {
        ColumnConstraints colConstraint = new ColumnConstraints();
        RowConstraints rowConstraint = new RowConstraints();
        colConstraint.setHgrow(Priority.ALWAYS);
        rowConstraint.setVgrow(Priority.ALWAYS);

        for (int row = 0; row <= game.getRows(); row++) {
            boardGrid.getRowConstraints().add(rowConstraint);
            boardGrid.getColumnConstraints().add(colConstraint);

            for (int col = 0; col < game.getCols(); col++) {

                if (row < game.getRows()) {
                    System.out.print("");
//                    Pane p = new Pane(new ImageView(new Image("resources/black-checker-piece-clip-art.png")));
//                    p.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//                    boardGrid.add(p, col, row);
                } else {
                    Button b = new Button();
                    b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    b.setText("Place Chip");
                    b.setId("placeChip-" + col);
                    b.setOnAction(this::placeChip);
                    boardGrid.add(b, col, row);
                }
            }
        }
    }

    public void placeChip(ActionEvent event) {
        Button source = (Button)event.getSource();
        String id = source.getId();
        int col = new Integer( id.substring(id.indexOf('-') + 1) );
        System.out.println(col);

//        game.placeChip(col);

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
        Main.stage.setScene(Main.mainScene);
    }

    @FXML
    public void connectFourStart() {

    }
}
