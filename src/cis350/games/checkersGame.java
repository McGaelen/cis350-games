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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Game class to setup a complete checkers Game.
 */
public class checkersGame {

    /**
     * checkersPlayer representing the white player.
     */
    static checkersPlayer whitePlayer;
    /**
     * checkersPlayer representing the white player.
     */
    static checkersPlayer blackPlayer;
    /**
     * Color of the current player's turn.
     */
    checkersBoard.Color gameTurn;
    /**
     * Representation of the game board.
     */
    checkersStandardBoard gameBoard;
    /**
     * Is true if the game is over.
     */
    boolean gameOver;
    /**
     * Holds the size of a square in the game board.
     */
    int squareSize;
    /**
     * Reference to the game's window.
     */
    JFrame window;
    /**
     * Displays the game board.
     */
    JPanel gamePanel;
    /**
     * Displays the right side controls.
     */
    JPanel sidePanel;
    /**
     * Displays the white player's name.
     */
    JLabel whiteLabel;
    /**
     * Displays the black player's name.
     */
    JLabel blackLabel;
    /**
     * Displays the white player's score.
     */
    JLabel whiteScore;
    /**
     * Displays the black player's score.
     */
    JLabel blackScore;
    /**
     * Displays a forfeit button to end the game.
     */
    JButton forfeitButton;
    /**
     * Displays a restart button to restart a game.
     */
    JButton restartButton;
    /**
     * Reference to the currently moving piece.
     */
    checkersPiece movingPiece;
    /**
     * History of commands that were executed.
     */
    Stack<checkersMoveCommand> commandStack;

    /**
     * Initializes the gameBoard, and populates it with pieces
     * according to the gameType.
     */
    public void gameInit() {
        gameBoard = new checkersStandardBoard(8, 8);
        gameBoard.populateBoardWithPieces();
        gameTurn = cis350.games.checkersBoard.Color.white;
        gameOver = false;
        squareSize = 80;
        commandStack = new Stack();
    }

    /**
     * Helper method to instantiate players of the current game.
     */
    static void getGamePlayers() {
        String whiteName =
                JOptionPane.showInputDialog("Please input White player name");
        if (whiteName == "" || whiteName == null) {
            whiteName = "Player 1";
        }
        String blackName =
                JOptionPane.showInputDialog("Please input Black player name");
        if (blackName == "" || blackName == null) {
            blackName = "Player 2";
        }
        whitePlayer = new checkersPlayer(
                whiteName, cis350.games.checkersBoard.Color.white
        );
        blackPlayer = new checkersPlayer(
                blackName, cis350.games.checkersBoard.Color.black
        );
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
     * Helper method to run the main game loop. If the game
     * is over the game loop breaks and the gamePanel stops
     * getting repainted (updated).
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
     * Sets up the main game window.
     */
    public void setupDisplay() {
        window = new JFrame("Checkers");
        gamePanel = initializeGamePanel(gameBoard);
        Container contentPanel = window.getContentPane();
        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.LINE_AXIS)
        );
        sidePanel = initializeSidePanel();
        contentPanel.add(gamePanel);
        contentPanel.add(sidePanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.validate();
        window.pack();
    }

    /**
     * Initializes a JPanel for the game.
     * @param board the game board to link to the Panel.
     * @return a fully set up gameDisplay.
     */
    private JPanel initializeGamePanel(final checkersStandardBoard board) {
        checkersGameDisplay gameDisplay =
                new checkersGameDisplay(board, squareSize);
        gameDisplay.setPreferredSize(new Dimension(640, 640));
        gameDisplay.setLayout(new BorderLayout());
        return gameDisplay;
    }

    /**
     * Initializes a side JPanel for the game.
     * @return returns a fully set up side panel.
     */
    private JPanel initializeSidePanel() {
        JPanel sideDisplay = new JPanel();
        restartButton = new JButton("Restart Game");
        forfeitButton = new JButton("Forfeit Game");
        setupButtonListeners();
        whiteLabel = new JLabel(
                "WHITE PLAYER : ".concat(whitePlayer.playerName) + " "
        );
        whiteLabel.setForeground(Color.BLUE);
        blackLabel = new JLabel(
                "BLACK PLAYER : ".concat(blackPlayer.playerName) + " "
        );
        whiteScore = new JLabel(
                whitePlayer.playerName + " Score : " + whitePlayer.playerScore
        );
        blackScore = new JLabel(
                blackPlayer.playerName + " Score : " + blackPlayer.playerScore
        );
        sideDisplay.setLayout(new BoxLayout(sideDisplay, BoxLayout.PAGE_AXIS));
        sideDisplay.add(whiteLabel);
        sideDisplay.add(blackLabel);
        sideDisplay.add(forfeitButton);
        sideDisplay.add(restartButton);
        sideDisplay.add(whiteScore);
        sideDisplay.add(blackScore);
        return sideDisplay;
    }

    /**
     * Sets up the button listeners for Restart and Forfeit buttons.
     */
    private void setupButtonListeners() {
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                restartGame();
            }
        });
        forfeitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                forfeitGame();
            }
        });
    }

    /**
     * Sets up all the mouse action listeners.
     */
    public void mouseActions() {
        gamePanel.addMouseListener(new MouseAdapter() {
            int xPrev;
            int yPrev;

            /**
             * Gets the currently moving piece based on the coordinates
             * of where the mouse was clicked.
             * @param me the mouse event that was triggered.
             */
            public void mousePressed(final MouseEvent me) {
                int xOrigin = me.getX();
                int yOrigin = me.getY();
                xOrigin = xOrigin / squareSize;
                yOrigin = yOrigin / squareSize;
                yOrigin = 7 - yOrigin;
                xPrev = xOrigin;
                yPrev = yOrigin;
                movingPiece =
                        gameBoard.squaresList[xOrigin][yOrigin].occupyingPiece;
            }

            /**
             * Places a piece based on the coordinates of where
             * the mouse was released.
             * @param me the mouse event that was triggered.
             */
            public void mouseReleased(final MouseEvent me) {
                int xDestination = me.getX();
                int yDestination = me.getY();
                xDestination = xDestination / squareSize;
                yDestination = yDestination / squareSize;
                yDestination = 7 - yDestination;
                if (movingPiece.color == gameTurn
                        && movingPiece.canMove(xDestination, yDestination)) {
                    checkersPiece enemyPiece = null;
                    //White Pawn Piece Moves
                    if (yDestination > yPrev) {
                        if (xDestination > xPrev) {
                            gameBoard
                                .squaresList[xDestination - 1][yDestination - 1]
                                .occupyingPiece = null;
                            gameBoard
                                .squaresList[xDestination - 1][yDestination - 1]
                                .isOccupied = false;
                        }
                        if (xDestination < xPrev) {
                            gameBoard
                                .squaresList[xDestination + 1][yDestination - 1]
                                .occupyingPiece = null;
                            gameBoard
                                .squaresList[xDestination + 1][yDestination - 1]
                                .isOccupied = false;
                        }
                    }
                    //Black Pawn Piece Moves
                    if (yDestination < yPrev) {
                        if (xDestination > xPrev) {
                            gameBoard
                                .squaresList[xDestination - 1][yDestination + 1]
                                .occupyingPiece = null;
                            gameBoard
                                .squaresList[xDestination - 1][yDestination + 1]
                                .isOccupied = false;
                        }
                        if (xDestination < xPrev) {
                            gameBoard
                                .squaresList[xDestination + 1][yDestination + 1]
                                .occupyingPiece = null;
                            gameBoard
                                .squaresList[xDestination + 1][yDestination + 1]
                                .isOccupied = false;
                        }
                    }
                    checkersMoveCommand newCommand = new checkersMoveCommand(
                            movingPiece, enemyPiece, xDestination, yDestination
                    );
                    commandStack.add(newCommand);
                    newCommand.execute();

                    if (yDestination == 7) {
                        movingPiece.whitePromote(xDestination, yDestination);
                    }
                    if  (yDestination == 0) {
                        movingPiece.blackPromote(xDestination, yDestination);
                    }

                    if (movingPiece.color.equals(checkersBoard.Color.white)) {
                        gameTurn = gameTurn.opposite();
                        blackLabel.setForeground(Color.BLUE);
                        whiteLabel.setForeground(Color.BLACK);
                        checkWhiteWin();
                    } else {
                        gameTurn = gameTurn.opposite();
                        whiteLabel.setForeground(Color.BLUE);
                        blackLabel.setForeground(Color.BLACK);
                        checkBlackWin();
                    }

                } else {
                    messageBox(
                            "This is an Illegal Move!", "Illegal move message"
                    );
                }
            }
        });
    }

    /**
     * Checks if the white player has won the game.
     */
    private void checkWhiteWin() {
        checkersPiece cPiece = null;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.squaresList[i][j].isOccupied) {
                    cPiece = gameBoard.squaresList[i][j].occupyingPiece;
                    if ((cPiece.color == checkersBoard.Color.black)) {
                        count = count + 1;
                    }
                }
            }
        }
        if (count == 0) {
            messageBox(blackPlayer.playerName
                    + " ,You have no more pieces\nYou Lost\nPlease "
                    + "Click Restart to Play again", "GAME OVER!!"
            );
            gameOver = true;
            whitePlayer.playerScore++;
            whiteScore.setText(whitePlayer.playerName
                    + " Score : " + whitePlayer.playerScore
            );
            blackScore.setText(blackPlayer.playerName
                    + " Score : " + blackPlayer.playerScore
            );
        }
    }

    /**
     * Checks if the black game has won the game.
     */
    private void checkBlackWin() {
        checkersPiece cPiece = null;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.squaresList[i][j].isOccupied) {
                    cPiece = gameBoard.squaresList[i][j].occupyingPiece;
                    if ((cPiece.color == checkersBoard.Color.white)) {
                        count = count + 1;
                    }
                }
            }
        }
        if (count == 0) {
            messageBox(whitePlayer.playerName
                    + " ,You have no more pieces\nYou Lost\nPlease "
                    + "Click Restart to Play again", "GAME OVER!!"
            );
            gameOver = true;
            blackPlayer.playerScore++;
            whiteScore.setText(whitePlayer.playerName
                    + " Score : " + whitePlayer.playerScore
            );
            blackScore.setText(blackPlayer.playerName
                    + " Score : " + blackPlayer.playerScore
            );
        }
    }

    /**
     * Restarts the game.
     */
    private void restartGame() {
        String player;
        if (gameTurn.equals(cis350.games.checkersBoard.Color.white)) {
            player = blackPlayer.playerName;
        } else {
            player = whitePlayer.playerName;
        }
        int response = JOptionPane.showConfirmDialog(
                null,
                player + " , would you like to restart?",
                "Restart",
                JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            gameOver = true;
            window.setVisible(false);
            startNewGame();
        }
    }

    /**
     * Forfeits the game for the player that clicked the button.
     */
    private void forfeitGame() {
        checkersPlayer currentPlayer;
        checkersPlayer otherPlayer;
        if (gameTurn == cis350.games.checkersBoard.Color.white) {
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        } else {
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        int response = JOptionPane.showConfirmDialog(
            null,
            currentPlayer.playerName + " , Are you sure you want to forfeit",
            "Forfeit",
            JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            gameOver = true;
            otherPlayer.playerScore++;
            whiteScore.setText(whitePlayer.playerName
                    + " Score : " + whitePlayer.playerScore
            );
            blackScore.setText(blackPlayer.playerName
                    + " Score : " + blackPlayer.playerScore
            );
            messageBox(currentPlayer.playerName
                    + " ,You Lost\nPlease Click Restart to Play again",
                    "GAME OVER!!"
            );
        }
    }

    /**
     * Used for main method testing.
     * @param args command line args.
     */
    public static void main(final String[] args) {
        getGamePlayers();
        startNewGame();
    }

    /**
     * Calls all helper methods to start the game.
     */
    public static void startNewGame() {
        checkersGame newGame = new checkersGame();
        newGame.gameInit();
        newGame.setupDisplay();
        newGame.gameStart();
        newGame.mouseActions();

    }

    /**
     * Shows a dialog box with the given message and title.
     * @param message the message to show in the dialog
     * @param title the string to put in the dialog's title bar.
     */
    public static void messageBox(final String message, final String title) {
        JOptionPane.showMessageDialog(
                null, message, title, JOptionPane.WARNING_MESSAGE
        );
    }

}


