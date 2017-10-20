package cis350.games;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cis350.games.chessKing;
import cis350.games.chessPiece;
import cis350.games.chessStandardBoard;
//import cis350.games.chessBoard;
import cis350.games.chessGameDisplay;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;

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
        gameBoard = new chessStandardBoard(8,8);
        gameBoard.populateBoardWithPieces(gameType);
        gameTurn = cis350.games.chessBoard.Color.white;
        gameOver = false;
        squareSize = 80;
        commandStack = new Stack();
    }

    // Method to initialize gameBoard, populate it with pieces according to gameType

    @FXML
    public void initialize() {
        GridPane root = new GridPane();
        final int size = 8 ;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    // Helper method to instantiate players of the current game.

    private static void getGamePlayers() {
        String whiteName = JOptionPane.showInputDialog("Please input White player name");
        if(whiteName == "" || whiteName == null)
            whiteName = "Player 1";
        String blackName = JOptionPane.showInputDialog("Please input Black player name");
        if(blackName == "" || blackName == null)
            blackName = "Player 2";
        whitePlayer = new chessPlayer(whiteName, cis350.games.chessBoard.Color.white);
        blackPlayer = new chessPlayer(blackName, cis350.games.chessBoard.Color.black);
    }

    //Helper method to get the type of game.

    private static boolean getGameType() {
        return false;
    }


    //  Method to start off a game thread and start running a game loop.

    public void gameStart(){
        Thread gameThread = new Thread(){
            @Override
            public void run(){
                gameLoop();
            }
        };
        gameThread.start();

    }


    //  Helper method to run the main game loop. If the game is over the game loop breaks
    //  and the gamePanel stops getting repainted (updated).

    private void gameLoop(){
        while(true){
            if(gameOver)
                break;
            gamePanel.repaint();
        }
    }


    // Method to setup initial display of the Board. Sets up the gamePanel and sidePanel in the
    // game's main frame.

    public void setupDisplay(){
        GridPane root = new GridPane();
        final int size = 8 ;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        

    }


    //  Helper method to initialize a JPanel for the game.

    private JPanel initializeGamePanel(chessStandardBoard gameBoard) {
        chessGameDisplay gameDisplay = new chessGameDisplay(gameBoard, squareSize);
        gameDisplay.setPreferredSize(new Dimension(640,640));
        gameDisplay.setLayout(new BorderLayout());
        return gameDisplay;
    }


    // Helper method to initialize a side JPanel for the game.

    private JPanel initializeSidePanel(){
        JPanel sideDisplay = new JPanel();
        sideDisplay.setLayout(new BoxLayout(sideDisplay, BoxLayout.PAGE_AXIS));
        return sideDisplay;
    }


    public void mouseActions(){
        gamePanel.addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent me){
                int xOrigin = me.getX();
                int yOrigin = me.getY();
                xOrigin = xOrigin/squareSize;
                yOrigin = yOrigin/squareSize;
                yOrigin = 7 - yOrigin;
                movingPiece = gameBoard.squaresList[xOrigin][yOrigin].occupyingPiece;
            }

            public void mouseReleased(MouseEvent me) {
                int xDestination = me.getX();
                int yDestination = me.getY();
                xDestination = xDestination/squareSize;
                yDestination = yDestination/squareSize;
                yDestination = 7 - yDestination;
                if(movingPiece.color == gameTurn && movingPiece.canMove(xDestination, yDestination)){
                    chessPiece enemyPiece = null;
                    if(gameBoard.squaresList[xDestination][yDestination].isOccupied)
                        enemyPiece = gameBoard.squaresList[xDestination][yDestination].occupyingPiece;
                    chessMoveCommand newCommand = new chessMoveCommand(movingPiece, enemyPiece, xDestination, yDestination);
                    commandStack.add(newCommand);
                    newCommand.execute();
                    if(movingPiece.color.equals(cis350.games.chessBoard.Color.white)){
                        gameTurn = gameTurn.opposite();
                        checkKingStatus(gameBoard.blackKingTracker);
                     }
                     else{
                         gameTurn = gameTurn.opposite();
                         checkKingStatus(gameBoard.whiteKingTracker);
                     }

                }
                else{
                    messageBox("This is an Illegal Move!", "Illegal move message");
                }
            }
        });
    }


    protected void checkKingStatus(chessKing kingToCheck) {
        chessPlayer currentPlayer;
        chessPlayer otherPlayer;
        if(kingToCheck.color == cis350.games.chessBoard.Color.white){
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        }
        else{
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        if(kingToCheck.isKingInCheck(kingToCheck)) {
            if(kingToCheck.isKingCheckmate(kingToCheck)) {
                messageBox(currentPlayer.playerName + " ,Your King is in Checkmate\nYou Lost\nPlease Click Restart to Play again", "GAME OVER!!");
                gameOver = true;
                otherPlayer.playerScore++;
                return;
            }
            messageBox(currentPlayer.playerName + " ,Your King is in Check", "King in Check!!");
        }
    }


    @FXML
    private void undoMove(){
        if(!commandStack.isEmpty()){
            chessMoveCommand move = commandStack.pop();
            move.undo();
            if(gameTurn == cis350.games.chessBoard.Color.white){

            }
            else{

            }
            gameTurn = gameTurn.opposite();
        }
    }


    @FXML
    private void forfeitGame() {
        chessPlayer currentPlayer;
        chessPlayer otherPlayer;
        if(gameTurn == cis350.games.chessBoard.Color.white){
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        }
        else{
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        int response = JOptionPane.showConfirmDialog(null, currentPlayer.playerName + " , Are you sure you want to forfeit", "Forfeit", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            gameOver = true;
            otherPlayer.playerScore++;
            messageBox(currentPlayer.playerName + " ,You Lost\nPlease Click Restart to Play again", "GAME OVER!!");
        }
    }





    public static void startNewGame() {
        ChessViewController newGame = new ChessViewController();
        newGame.initialize();
        newGame.setupDisplay();
        newGame.gameStart();
        newGame.mouseActions();

    }

    public static void messageBox(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    @FXML
    public void newGame() {
        getGamePlayers();
        String player;
        if(gameTurn.equals(cis350.games.chessBoard.Color.white))
            player = blackPlayer.playerName;
        else
            player = whitePlayer.playerName;
        int response = JOptionPane.showConfirmDialog(null, player + " , would you like to restart?", "Restart", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            gameOver = true;
            //window.setVisible(false);
            startNewGame();
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
