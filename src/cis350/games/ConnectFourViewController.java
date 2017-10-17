package cis350.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectFourViewController {

    private ConnectFourEngine game;
    private ArrayList<ArrayList<Pane>> paneGrid; // will be used to hold the circles in the grid
    private ArrayList<Button> buttonRow;
//    private String feedback;  // for release 2
    private double windowWidth;
    private double windowHeight;

    @FXML private AnchorPane connectFourRoot;
    @FXML private GridPane boardGrid;
    @FXML private ToolBar toolBar;
//    @FXML private Label feedbackLabel; // for release 2

    private final double PADDING_AMT = 14;
    private final double BOARD_CELL_SIZE = 50;


    public ConnectFourViewController() {
        this.game = new ConnectFourEngine(6, 6, 1);
        this.paneGrid = new ArrayList<>();
        this.buttonRow = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        this.boardGrid.getChildren().clear();
        this.paneGrid.clear();
        this.buttonRow.clear();

        for (int row = 0; row <= this.game.getRows(); row++) {
            this.paneGrid.add(new ArrayList<>());

            for (int col = 0; col < this.game.getCols(); col++) {
                if (row < this.game.getRows()) {
                    Circle c;
                    if (this.game.getCellOwner(row, col) == 1) {
                        c = new Circle(25, Paint.valueOf("#DE0003"));
                    } else if (this.game.getCellOwner(row, col) == 2) {
                        c = new Circle(25, Paint.valueOf("#3034F0"));
                    } else {
                        c = new Circle(25, Paint.valueOf("#BAC1C4"));
                    }
                    c.setCenterX(25);
                    c.setCenterY(25);
                    Pane p = new Pane(c);
                    p.setPrefSize(50, 50);
                    p.setMaxSize(50, 50);
                    this.boardGrid.add(p, col, row);
                    this.paneGrid.get(row).add(p);
                } else {
                    Button b = new Button();
                    b.setMaxSize(50, 50);
                    b.setPrefSize(50, 35);
                    b.setText("↑");
                    b.setFont(new Font(12));
                    b.setId("placeChip-" + col);
                    b.setOnAction(this::placeChip);
                    this.boardGrid.add(b, col, row);
                    this.buttonRow.add(b);
                }
            }
        }
        this.resizeElements();
    }

    private void resizeElements() {
        this.windowWidth = PADDING_AMT + (this.boardGrid.getHgap() + BOARD_CELL_SIZE) * this.game.getCols();
        this.windowHeight = this.toolBar.getHeight() + PADDING_AMT + (this.boardGrid.getVgap() + BOARD_CELL_SIZE) * (this.game.getRows() + 1) + PADDING_AMT;
//        this.feedbackLabel.setLayoutY(this.windowHeight - (this.toolBar.getHeight() + PADDING_AMT + BOARD_CELL_SIZE));

        this.toolBar.setPrefWidth(this.windowWidth);
        this.toolBar.resize(this.windowWidth, this.toolBar.getHeight());
        this.connectFourRoot.setPrefWidth(this.windowWidth);
        this.connectFourRoot.setPrefHeight(this.windowHeight + this.toolBar.getHeight() + PADDING_AMT);
        this.connectFourRoot.resize(this.connectFourRoot.getPrefWidth(), this.connectFourRoot.getPrefHeight());
    }

    public void placeChip(ActionEvent event) {
        Button source = (Button)event.getSource();
        String id = source.getId();
        int col = new Integer( id.substring(id.indexOf('-') + 1) );

        try {
            int row = this.game.placeChip(col + 1);
            Circle c;
            if (this.game.getTurn() == 1) {
                c = new Circle(25, Paint.valueOf("#DE0003"));
            } else {
                c = new Circle(25, Paint.valueOf("#3034F0"));
            }
            c.setCenterY(25);
            c.setCenterX(25);
            this.paneGrid.get(row).get(col).getChildren().setAll(c);
            this.game.advanceTurn();
        } catch (Exception e) {
            System.out.println("Couldn't place chip in column " + col);
        }

        if (this.game.checkWin()) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Winner");
            a.setContentText("Player " + this.game.getWinner() + " won with a " + this.game.getWinCase() + "!\nWould you like to play again?\n\n");
            ButtonType no =  new ButtonType("No", ButtonBar.ButtonData.NO);
            a.getDialogPane().getButtonTypes().add(no);

            a.showAndWait().ifPresent( (result) -> {
                if (result == ButtonType.OK) {
                    this.newGame();
                } else {
                    this.buttonsEnabled(false);
                }
            });
        }
    }

    public void buttonsEnabled(final boolean enabled) {
        for (Button b : this.buttonRow) {
            b.setDisable(!enabled);
        }
    }

    @FXML
    public void newGame() {
        this.game.reset();
        this.initialize();
    }

    @FXML
    public void loadGame() {
        FileChooser dialog = new FileChooser();
        File gameFile;

        dialog.setTitle("Open Connect Four Game");
        gameFile = dialog.showOpenDialog(Main.stage);

        if (gameFile == null) {
            return;
        }
        try {
            this.game = ConnectFourEngine.load(gameFile);
        } catch (ClassNotFoundException c) {
            new Alert(AlertType.ERROR, "The file was not a valid Connect Four file.").showAndWait();
        } catch (IOException e) {
            new Alert(AlertType.ERROR, "Error loading Connect Four game file.").showAndWait();
        }

        this.initialize();
        this.resizeElements();
    }

    @FXML
    public void saveGame() {
        FileChooser dialog = new FileChooser();
        File gameFile;

        dialog.setTitle("Save Connect Four Game");
        gameFile = dialog.showSaveDialog(Main.stage);

        try {
            this.game.save(gameFile);
        } catch (IOException e) {
            new Alert(AlertType.ERROR, "Error saving Connect Four game file.").showAndWait();
        }
    }

    @FXML
    public void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
}
