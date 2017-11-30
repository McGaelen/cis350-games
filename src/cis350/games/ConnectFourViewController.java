package cis350.games;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * This class controls the UI for Connect Four. An instance of this class
 * is automatically created by JavaFX.
 */
public class ConnectFourViewController {

    /**
     * A reference to the game logic.
     */
    private ConnectFourEngine game;
    /**
     * Holds a reference to every colored circle in the game board.
     */
    private ArrayList<ArrayList<Pane>> paneGrid;
    /**
     * Holds a reference to every button in the bottom row of the game board.
     */
    private ArrayList<Button> buttonRow;
    /**
     * The current height of the window.
     */
    private double windowWidth;
    /**
     * The current width of the window.
     */
    private double windowHeight;

    /**
     * Reference to the root pane of the UI.
     */
    @FXML private AnchorPane connectFourRoot;
    /**
     * Reference to the GridPane in the UI that holds all the colored circles.
     */
    @FXML private GridPane boardGrid;
    /**
     * Reference to the toolbar in the UI.
     */
    @FXML private ToolBar toolBar;

    /**
     * Specifies the amount of padding around certain UI elements.
     */
    private final double paddingAmt = 14;
    /**
     * The size of each cell in the game board.
     */
    private final double boardCellSize = 50;
    /**
     * The number of rows to put in the game engine.
     */
    private final Integer numRows = 6;
    /**
     * The number of columns to put in the game engine.
     */
    private final Integer numCols = 6;
    /**
     * Height of the buttons in the button row.
     */
    private final double placeChipButtonHeight = 35;

    /**
     * Creates a new instance of this controller.
     * Called automatically by JavaFX.
     */
    public ConnectFourViewController() {
        this.game = new ConnectFourEngine(numRows, numCols, 1);
        this.paneGrid = new ArrayList<>();
        this.buttonRow = new ArrayList<>();
    }

    /**
     * Completes setup of the UI after the FXML file has finished loading
     * and an instance of this controller has been created.
     *
     * Adds all the colored circles to the boardGrid and the buttons to
     * place chips in the board.
     *
     * Called automatically by JavaFX.
     */
    @FXML public void initialize() {
        this.boardGrid.getChildren().clear();
        this.paneGrid.clear();
        this.buttonRow.clear();

        for (int row = 0; row <= this.game.getRows(); row++) {
            this.paneGrid.add(new ArrayList<>());

            for (int col = 0; col < this.game.getCols(); col++) {
                if (row < this.game.getRows()) {
                    Circle c;
                    if (this.game.getCellOwner(row, col) == 1) {
                        c = new Circle(boardCellSize / 2, Paint.valueOf("#DE0003"));
                    } else if (this.game.getCellOwner(row, col) == 2) {
                        c = new Circle(boardCellSize / 2, Paint.valueOf("#3034F0"));
                    } else {
                        c = new Circle(boardCellSize / 2, Paint.valueOf("#BAC1C4"));
                    }
                    c.setCenterX(boardCellSize / 2);
                    c.setCenterY(boardCellSize / 2);
                    Pane p = new Pane(c);
                    p.setPrefSize(boardCellSize, boardCellSize);
                    p.setMaxSize(boardCellSize, boardCellSize);
                    this.boardGrid.add(p, col, row);
                    this.paneGrid.get(row).add(p);
                } else {
                    Button b = new Button();
                    b.setMaxSize(boardCellSize, boardCellSize);
                    b.setPrefSize(boardCellSize, placeChipButtonHeight);
                    b.setText("^");
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

    /**
     * Resizes all the elements in the UI to fit the game board.
     * Calculates the screen's width and height and sets windowWidth and
     * windowHeight to those values.
     *
     * Called automatically by initialize().
     */
    private void resizeElements() {
        this.windowWidth = paddingAmt
                + (this.boardGrid.getHgap() + boardCellSize)
                * this.game.getCols();
        this.windowHeight = this.toolBar.getHeight()
                + paddingAmt + (this.boardGrid.getVgap() + boardCellSize)
                * (this.game.getRows() + 1) + paddingAmt;

        this.toolBar.setPrefWidth(this.windowWidth);
        this.toolBar.resize(this.windowWidth, this.toolBar.getHeight());
        this.connectFourRoot.setPrefWidth(this.windowWidth);
        this.connectFourRoot.setPrefHeight(
                this.windowHeight + this.toolBar.getHeight() + paddingAmt
        );
        this.connectFourRoot.resize(
                this.connectFourRoot.getPrefWidth(),
                this.connectFourRoot.getPrefHeight()
        );
    }

    /**
     * Places a chip in a column corresponding to the location of the button
     * that was pushed.
     *
     * The number in the id of the button corresponds to which column should
     * get a chip placed in it.
     *
     * A check is performed for a winner every time this function is called,
     * and a dialog box will appear displaying the winner, if there is one.
     *
     * @param event The button that was pushed to place a chip.
     */
    private void placeChip(final ActionEvent event) {
        Button source = (Button) event.getSource();
        String id = source.getId();
        int col = Integer.parseInt(id.substring(id.indexOf('-') + 1));
        System.out.println("Player "
        + this.game.getTurn() + " chose column " + col);

        try {
            int row = this.game.placeChip(col + 1);
            Circle c;
            if (this.game.getTurn() == 1) {
                c = new Circle(boardCellSize / 2, Paint.valueOf("#DE0003"));
            } else {
                c = new Circle(boardCellSize / 2, Paint.valueOf("#3034F0"));
            }
            c.setCenterY(boardCellSize / 2);
            c.setCenterX(boardCellSize / 2);
            this.paneGrid.get(row).get(col).getChildren().setAll(c);
            this.game.advanceTurn();
        } catch (Exception e) {
            System.out.println("Couldn't place chip in column " + col);
        }

        if (this.game.checkWin()) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Winner");
            a.setContentText(
                    "Player " + this.game.getWinner()
                    + " won with a " + this.game.getWinCase()
                    + "!\nWould you like to play again?\n\n"
            );
            ButtonType no =  new ButtonType("No", ButtonBar.ButtonData.NO);
            a.getDialogPane().getButtonTypes().add(no);

            a.showAndWait().ifPresent((result) -> {
                if (result == ButtonType.OK) {
                    this.newGame();
                } else {
                    this.buttonsEnabled(false);
                }
            });
        }

        if (this.game.checkFull()) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Full Board");
            a.setContentText("The board is completely full!\nWould you like to play again?\n\n");
            ButtonType no =  new ButtonType("No", ButtonBar.ButtonData.NO);
            a.getDialogPane().getButtonTypes().add(no);

            a.showAndWait().ifPresent((result) -> {
                if (result == ButtonType.OK) {
                    this.newGame();
                } else {
                    this.buttonsEnabled(false);
                }
            });
        }
    }

    public void addConnectFourAchievementsObserver(Observer o) {
        this.game.addObserver(o);
    }

    /**
     * Enables or disables all buttons in the button row.
     * @param enabled true to enable all the buttons, false to disable them.
     */
    private void buttonsEnabled(final boolean enabled) {
        for (Button b : this.buttonRow) {
            b.setDisable(!enabled);
        }
    }

    /**
     * Starts a new game by resetting the engine and
     * manually calling initialize().
     */
    @FXML private void newGame() {
        this.game.reset();
        this.initialize();
    }

    /**
     * Manages loading a game from a file.
     *
     * A file chooser dialog will appear, and if the file is valid,
     * the game will be set to the deserialized ConnectFourEngine object.
     */
    @FXML private void loadGame() {
        System.out.println("Load game attempted at " + new Date());
        FileChooser dialog = new FileChooser();
        File gameFile;

        dialog.setTitle("Open Connect Four Game");
        gameFile = dialog.showOpenDialog(Main.stage);

        if (gameFile == null) {
            return;
        }
        try {
            this.game = ConnectFourEngine.load(gameFile);
            this.game.addObserver(Main.achievementsViewController);
        } catch (ClassNotFoundException c) {
            new Alert(AlertType.ERROR,
                    "The file was not a valid Connect Four file."
            ).showAndWait();
        } catch (IOException e) {
            new Alert(AlertType.ERROR,
                    "Error loading Connect Four game file."
            ).showAndWait();
        }

        this.initialize();
        this.resizeElements();
    }

    /**
     * Manages saving a game to a file.
     *
     * A file chooser will be shown for the user to choose a location
     * to save their game, and the current game engine object will be
     * serialized to a file at the given location.
     */
    @FXML private void saveGame() {
        System.out.println("Save game attempted at " + new Date());
        FileChooser dialog = new FileChooser();
        File gameFile;

        dialog.setTitle("Save Connect Four Game");
        gameFile = dialog.showSaveDialog(Main.stage);

        if (gameFile == null) {
            return;
        }
        try {
            this.game.save(gameFile);
        } catch (IOException e) {
            new Alert(AlertType.ERROR,
                    "Error saving Connect Four game file."
            ).showAndWait();
        }
    }

    /**
     * Sets the main menu as the currently displaying scene,
     * in order to go "back".
     */
    @FXML private void goBack() {
        Main.stage.setScene(Main.mainScene);
    }
}
