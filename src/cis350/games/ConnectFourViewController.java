package cis350.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ConnectFourViewController {

    private ConnectFourEngine game;
    private ArrayList<ArrayList<Pane>> paneGrid; // will be used to hold the circles in the grid
    private String feedback;
    private double windowWidth;
    private double windowHeight;

    @FXML private AnchorPane connectFourRoot;
    @FXML private GridPane boardGrid;
    @FXML private ToolBar toolBar;
    @FXML private Label feedbackLabel;

    public ConnectFourViewController() {
        this.game = new ConnectFourEngine(6, 10, 1);
        this.paneGrid = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        this.boardGrid.getChildren().clear();
        this.paneGrid.clear();

        for (int row = 0; row <= this.game.getRows(); row++) {
            this.paneGrid.add(new ArrayList<>());

            for (int col = 0; col < this.game.getCols(); col++) {
                if (row < this.game.getRows()) {
                    Circle c = new Circle(25, Paint.valueOf("#BAC1C4"));
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
                }
            }
        }
        this.resizeElements();
    }

    private void resizeElements() {
        this.windowWidth = 14 + (this.boardGrid.getHgap() + 50) * this.game.getCols();
        this.feedbackLabel.setLayoutY(toolBar.getHeight() + 14 + (this.boardGrid.getVgap() + 50) * (this.game.getRows() + 1) + 26);

        this.windowHeight = this.feedbackLabel.getLayoutY();

        this.toolBar.setPrefWidth(this.windowWidth);
        this.connectFourRoot.setPrefWidth(this.windowWidth);
        this.connectFourRoot.setPrefHeight(this.windowHeight);

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

        this.game.checkWin();
        System.out.println(this.game.getWinner());
        System.out.println(this.game.getWinCase());
    }

    @FXML
    public void newGame() {
        this.game.reset();
        this.initialize();
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
