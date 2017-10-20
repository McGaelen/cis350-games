package cis350.games;

import java.util.Stack;
import javax.swing.JPanel;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

public class ChessViewController {

    static chessPlayer whitePlayer;
    static chessPlayer blackPlayer;
    cis350.games.chessBoard.Color gameTurn;
    chessStandardBoard gameBoard;
    boolean gameOver;
    static boolean gameType;
    int squareSize;
    JPanel gamePanel;
    JPanel sidePanel;
    chessPiece movingPiece;
    Stack<chessMoveCommand> commandStack;

    @FXML private AnchorPane chessRoot;
    @FXML private GridPane boardGrid;
    @FXML private ToolBar toolBar;
    @FXML private Label feedbackLabel;


    public ChessViewController() {
        gameBoard = new chessStandardBoard(8, 8);
        gameBoard.populateBoardWithPieces(gameType);
        gameTurn = cis350.games.chessBoard.Color.white;
        gameOver = false;
        squareSize = 80;
        commandStack = new Stack();
    }

    @FXML
    public void initialize() {
        GridPane root = new GridPane();
        final int size = 8;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                StackPane square = new StackPane();
                String color;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: " + color + ";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(
                    new ColumnConstraints(5, Control.USE_COMPUTED_SIZE,
                            Double.POSITIVE_INFINITY,
                            Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(
                    new RowConstraints(5, Control.USE_COMPUTED_SIZE,
                            Double.POSITIVE_INFINITY,
                            Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    /**
     * Method to start off a game thread and start running a game loop.
     */

    public void gameStart() {
        Thread gameThread = new Thread() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        gameThread.start();

    }


    /**
     * Helper method to run the main game loop.
     * If the game is over the game loop breaks
     * and the gamePanel stops getting repainted (updated).
     */

    private void gameLoop() {
        while (true) {
            if (gameOver) {
                break;
            }
            gamePanel.repaint();
        }
    }


    /**
     * Method to setup initial display of the Board.
     * Sets up the gamePanel and sidePanel in the game's main frame.
     */

    public void setupDisplay() {
        GridPane root = new GridPane();
        final int size = 8;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                StackPane square = new StackPane();
                String color;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: " + color + ";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(
                    new ColumnConstraints(5, Control.USE_COMPUTED_SIZE,
                            Double.POSITIVE_INFINITY, Priority.ALWAYS,
                            HPos.CENTER, true));
            root.getRowConstraints().add(
                    new RowConstraints(5, Control.USE_COMPUTED_SIZE,
                    Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true));
        }

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
    public void chessStart() {

    }

}
