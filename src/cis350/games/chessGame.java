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
  * @author Austin Maley
  *
  */
public class chessGame {

    /**
     * chessPlayer representing the white player.
     */
    private static chessPlayer whitePlayer;

    /**
     * chessPlayer representing the black player.
     */
    private static chessPlayer blackPlayer;

    /**
     * Color of the current player's turn.
     */
    private cis350.games.chessBoard.Color gameTurn;

    /**
     * Representation of the game board.
     */
    private chessStandardBoard gameBoard;

    /**
     * Is true if the game is over.
     */
    private boolean gameOver;
    /**
    * size of Squares.
    */
    private int squareSize;
    /**
    * Window for program.
    */
    private JFrame window;
    /**
    * Game Panel.
    */
    private JPanel gamePanel;
    /**
    * Side Panel.
    */
    private JPanel sidePanel;
    /**
    * White Player Name Label.
    */
    private JLabel whiteLabel;
    /**
    * Black Player Name Label.
    */
    private JLabel blackLabel;
    /**
    * White Score Label.
    */
    private JLabel whiteScore;
    /**
    * Black Score Label.
    */
    private JLabel blackScore;
    /**
    * Forfeit Button.
    */
    private JButton forfeitButton;
    /**
    * Undo Button.
    */
    private JButton undoButton;
    /**
    * Restart button.
    */
    private JButton restartButton;
    /**
    * The moving piece.
    */
    private chessPiece movingPiece;
    /**
    * List of Moves.
    */
    private Stack<chessMoveCommand> commandStack;


    /**
     * Method to initialize gameBoard, populate it with pieces.
     * @param cGameType Possible feature
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void gameInit(final boolean cGameType) {
        gameBoard = new chessStandardBoard(8, 8);
        gameBoard.populateBoardWithPieces(cGameType);
        gameTurn = cis350.games.chessBoard.Color.white;
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
        if (whiteName == ""
                || whiteName == null) {
            whiteName = "Player 1";
        }
        String blackName = JOptionPane.showInputDialog(
                "Please input Black player name");
        if (blackName == "" || blackName == null) {
            blackName = "Player 2";
        }
        whitePlayer = new chessPlayer(
                whiteName, cis350.games.chessBoard.Color.white);
        blackPlayer = new chessPlayer(
                blackName, cis350.games.chessBoard.Color.black);
    }

    /**
     * Helper method to get the type of game. Possible Release 2 feature
     * @return false for now
     */
    private static boolean getGameType() {
        return false;
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
        window = new JFrame("Chess");
        gamePanel = initializeGamePanel(gameBoard);
        Container contentPanel = window.getContentPane();
        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        sidePanel = initializeSidePanel();
        contentPanel.add(gamePanel);
        contentPanel.add(sidePanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.validate();
        window.pack();

    }

    /**
     * Initializes the Game Panel.
     * @param cGameBoard The current board
     * @return gameDisplay
     */
    private JPanel initializeGamePanel(final chessStandardBoard cGameBoard) {
        chessGameDisplay gameDisplay =
                new chessGameDisplay(cGameBoard, squareSize);
        gameDisplay.setPreferredSize(new Dimension(640, 640));
        gameDisplay.setLayout(new BorderLayout());
        return gameDisplay;
    }



    /**
    * Helper method to initialize a side JPanel for the game.
    * @return sideDisplay
    */
    private JPanel initializeSidePanel() {
        JPanel sideDisplay = new JPanel();
        undoButton = new JButton("Undo Move");
        restartButton = new JButton("Restart Game");
        forfeitButton = new JButton("Forfeit Game");
        setupButtonListeners();
        whiteLabel = new JLabel(
                "WHITE PLAYER : ".concat(whitePlayer.getPlayerName()) + " ");
        whiteLabel.setForeground(Color.BLUE);
        blackLabel = new JLabel(
                "BLACK PLAYER : ".concat(blackPlayer.getPlayerName()) + " ");
        whiteScore = new JLabel(
                whitePlayer.getPlayerName() + " Score : " + whitePlayer
                .getPlayerScore());
        blackScore = new JLabel(
                blackPlayer.getPlayerName() + " Score : " + blackPlayer
                .getPlayerScore());
        sideDisplay.setLayout(new BoxLayout(sideDisplay, BoxLayout.PAGE_AXIS));
        sideDisplay.add(whiteLabel);
        sideDisplay.add(blackLabel);
        sideDisplay.add(undoButton);
        sideDisplay.add(forfeitButton);
        sideDisplay.add(restartButton);
        sideDisplay.add(whiteScore);
        sideDisplay.add(blackScore);
        return sideDisplay;
    }

    /**
     * Helper method to setup the button listeners.
     * Undo, Restart and Forfeit buttons.
     */
    private void setupButtonListeners() {
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                undoMove();
            }
        });
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
     * Actions for the mouse when it's clicked and released.
     */
    public void mouseActions() {
        gamePanel.addMouseListener(new MouseAdapter() {

            /**
            * Actions when the mouse is clicked
            * @param me the mouse
            */
            public void mousePressed(final MouseEvent me) {
                int xOrigin = me.getX();
                int yOrigin = me.getY();
                xOrigin = xOrigin / squareSize;
                yOrigin = yOrigin / squareSize;
                yOrigin = 7 - yOrigin;
                movingPiece = gameBoard.getSquaresList()
                        [xOrigin][yOrigin].getOccupyingPiece();
            }

            /**
            * Actions when the mouse is released
            * @param me the mouse
            */
            public void mouseReleased(final MouseEvent me) {
                int xDestination = me.getX();
                int yDestination = me.getY();
                xDestination = xDestination / squareSize;
                yDestination = yDestination / squareSize;
                yDestination = 7 - yDestination;
                if (movingPiece.getColor() == gameTurn
                        && movingPiece.canMove(xDestination, yDestination)) {
                    chessPiece enemyPiece = null;
                    if (gameBoard.getSquaresList()
                            [xDestination][yDestination].getIsOccupied()) {
                        enemyPiece = gameBoard.getSquaresList()
                                [xDestination][yDestination]
                                        .getOccupyingPiece();
                    }
                    chessMoveCommand newCommand =
                            new chessMoveCommand(movingPiece, enemyPiece,
                                    xDestination, yDestination);
                    commandStack.add(newCommand);
                    newCommand.execute();
                    if (movingPiece.getColor().equals(
                            cis350.games.chessBoard.Color.white)) {
                        gameTurn = gameTurn.opposite();
                        blackLabel.setForeground(Color.BLUE);
                        whiteLabel.setForeground(Color.BLACK);
                        checkKingStatus(gameBoard.getBlackKingTracker());
                     } else {
                         gameTurn = gameTurn.opposite();
                         whiteLabel.setForeground(Color.BLUE);
                         blackLabel.setForeground(Color.BLACK);
                         checkKingStatus(gameBoard.getWhiteKingTracker());
                     }

                } else {
                    messageBox("This is an Illegal Move!",
                            "Illegal move message");
                }
            }
        });
    }

    /**
    * Checks if the King is in Check of CheckMate.
    * @param kingToCheck is the king to check
    */
    protected void checkKingStatus(final chessKing kingToCheck) {
        chessPlayer currentPlayer;
        chessPlayer otherPlayer;
        if (kingToCheck.getColor()
                == cis350.games.chessBoard.Color.white) {
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        } else {
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        if (kingToCheck.isKingInCheck(kingToCheck)) {
            if (kingToCheck.isKingCheckmate(kingToCheck)) {
                messageBox(currentPlayer.getPlayerName()
                        + " ,Your King is in Checkmate\nYou Lost\n"
                        + "Please Click Restart to Play again", "GAME OVER!!");
                gameOver = true;
                otherPlayer.setPlayerScore(otherPlayer.getPlayerScore() + 1);
                whiteScore.setText(whitePlayer.getPlayerName()
                        + " Score : " + whitePlayer.getPlayerScore());
                blackScore.setText(blackPlayer.getPlayerName()
                        + " Score : " + blackPlayer.getPlayerScore());
                return;
            }
            messageBox(currentPlayer.getPlayerName()
                    + " ,Your King is in Check", "King in Check!!");
        }
    }
    /**
    * Undoes the previous move.
    */
    private void undoMove() {
        if (!commandStack.isEmpty()) {
            chessMoveCommand move = commandStack.pop();
            move.undo();
            if (gameTurn == cis350.games.chessBoard.Color.white) {
                blackLabel.setForeground(Color.BLUE);
                whiteLabel.setForeground(Color.BLACK);
            } else {
                whiteLabel.setForeground(Color.BLUE);
                blackLabel.setForeground(Color.BLACK);
            }
            gameTurn = gameTurn.opposite();
        }
    }

    /**
    * Restarts the Chess game.
    */
    private void restartGame() {
        String player;
        if (gameTurn.equals(cis350.games.chessBoard.Color.white)) {
            player = blackPlayer.getPlayerName();
        } else {
            player = whitePlayer.getPlayerName();
        }
        int response = JOptionPane.showConfirmDialog(
                null, player + " , would you like to restart?",
                "Restart", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            gameOver = true;
            window.setVisible(false);
            startNewGame();
        }
    }

    /**
    * Forfeits the Chess game.
    */
    private void forfeitGame() {
        chessPlayer currentPlayer;
        chessPlayer otherPlayer;
        if (gameTurn == cis350.games.chessBoard.Color.white) {
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        } else {
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        int response = JOptionPane.showConfirmDialog(
                null, currentPlayer.getPlayerName() + " ,"
                        + "Are you sure you want to forfeit",
                        "Forfeit", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            gameOver = true;
            otherPlayer.setPlayerScore(otherPlayer.getPlayerScore() + 1);
            whiteScore.setText(whitePlayer.getPlayerName()
                    + " Score : " + whitePlayer.getPlayerScore());
            blackScore.setText(blackPlayer.getPlayerName()
                    + " Score : " + blackPlayer.getPlayerScore());
            messageBox(currentPlayer.getPlayerName()
                    + " ,You Lost\nPlease Click Restart to Play again",
                    "GAME OVER!!");
        }
    }


    /**
    * Runs the Chess Game. Used for testing
    * @param args command line arguments
    */
    public static void main(final String[] args) {
        getGamePlayers();
        startNewGame();
    }


    /**
    * Starts a new game.
    */
    public static void startNewGame() {
        chessGame newGame = new chessGame();
        newGame.gameInit(getGameType());
        newGame.setupDisplay();
        newGame.gameStart();
        newGame.mouseActions();

    }

    /**
    * Displays error messages.
    * @param message the message to be displayed
    * @param title the title of the message box
    */
    public static void messageBox(final String message, final String title) {
        JOptionPane.showMessageDialog(
                null, message, title, JOptionPane.WARNING_MESSAGE);
    }

}

