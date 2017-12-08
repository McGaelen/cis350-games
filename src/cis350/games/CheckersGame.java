package cis350.games;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Observable;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Game class to setup a complete Checkers Game.
 */
public class CheckersGame extends Observable {

    /**
     * CheckersPlayer representing the white player.
     */
    private static CheckersPlayer whitePlayer;
    /**
     * CheckersPlayer representing the white player.
     */
    private static CheckersPlayer blackPlayer;
    /**
     * Win streak for achievements.
     */
    private int streak;
    /**
     * Player currently with win streak.
     */
    private CheckersPlayer playerWithStreak;
    /**
     * Color of the current player's turn.
     */
    private CheckersBoard.Color gameTurn;
    /**
     * Representation of the game board.
     */
    private CheckersStandardBoard gameBoard;
    /**
     * Is true if the game is over.
     */
    private boolean gameOver;
    /**
     * Holds the size of a square in the game board.
     */
    private int squareSize;
    /**
     * Reference to the game's window.
     */
    private JFrame window;
    /**
     * Displays the game board.
     */
    private JPanel gamePanel;
    /**
     * Displays the right side controls.
     */
    private JPanel sidePanel;
    /**
     * Displays the white player's name.
     */
    private JLabel whiteLabel;
    /**
     * Displays the black player's name.
     */
    private JLabel blackLabel;
    /**
     * Displays the white player's score.
     */
    private JLabel whiteScore;
    /**
     * Displays the black player's score.
     */
    private JLabel blackScore;
    /**
     * Displays a forfeit button to end the game.
     */
    private JButton forfeitButton;
    /**
     * Displays a restart button to restart a game.
     */
    private JButton restartButton;
    /**
     * Reference to the currently moving piece.
     */
    private CheckersPiece movingPiece;
    /**
     * History of commands that were executed.
     */
    private Stack<CheckersMoveCommand> commandStack;

    /**
     * Initializes the gameBoard, and populates it with pieces
     * according to the gameType.
     */
    public void gameInit() {
        gameBoard = new CheckersStandardBoard(8, 8);
        gameBoard.populateBoardWithPieces();
        gameTurn = cis350.games.CheckersBoard.Color.white;
        gameOver = false;
        squareSize = 80;
        commandStack = new Stack<CheckersMoveCommand>();
    }

    /**
     * Helper method to instantiate players of the current game.
     */
    static void getGamePlayers() {
        String whiteName =
                JOptionPane.showInputDialog("Please input White player name");
        if (whiteName.equals("")) {
            whiteName = "Player 1";
        }
        String blackName =
                JOptionPane.showInputDialog("Please input Black player name");
        if (blackName.equals("")) {
            blackName = "Player 2";
        }
        whitePlayer = new CheckersPlayer(
                whiteName, cis350.games.CheckersBoard.Color.white
        );
        blackPlayer = new CheckersPlayer(
                blackName, cis350.games.CheckersBoard.Color.black
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
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.validate();
        window.pack();
        window.setLocationRelativeTo(null);
    }

    /**
     * Initializes a JPanel for the game.
     * @param board the game board to link to the Panel.
     * @return a fully set up gameDisplay.
     */
    private JPanel initializeGamePanel(final CheckersStandardBoard board) {
        CheckersGameDisplay gameDisplay =
                new CheckersGameDisplay(board, squareSize);
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
                "WHITE PLAYER : ".concat(whitePlayer.getPlayerName()) + " "
        );
        whiteLabel.setForeground(Color.BLUE);
        blackLabel = new JLabel(
                "BLACK PLAYER : ".concat(blackPlayer.getPlayerName()) + " "
        );
        whiteScore = new JLabel(
                whitePlayer.getPlayerName() + " Score : " + whitePlayer
                .getPlayerScore()
        );
        blackScore = new JLabel(
                blackPlayer.getPlayerName() + " Score : " + blackPlayer
                .getPlayerScore()
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
            private int xPrev;
            private int yPrev;

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
                        gameBoard.getSquaresList()
                        [xOrigin][yOrigin].getOccupyingPiece();
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
                if (movingPiece.getColor() == gameTurn
                        && movingPiece.canMove(xDestination, yDestination)) {
                    CheckersPiece enemyPiece = null;
                    //White Pawn Piece Moves
                    if (yDestination > yPrev) {
                        if (xDestination > xPrev) {
                            gameBoard
                                .getSquaresList()
                                [xDestination - 1][yDestination - 1]
                                .setOccupyingPiece(null);
                            gameBoard
                                .getSquaresList()
                                [xDestination - 1][yDestination - 1]
                                .setIsOccupied(false);
                        }
                        if (xDestination < xPrev) {
                            gameBoard
                                .getSquaresList()
                                [xDestination + 1][yDestination - 1]
                                .setOccupyingPiece(null);
                            gameBoard
                                .getSquaresList()
                                [xDestination + 1][yDestination - 1]
                                .setIsOccupied(false);
                        }
                    }
                    //Black Pawn Piece Moves
                    if (yDestination < yPrev) {
                        if (xDestination > xPrev) {
                            gameBoard
                                .getSquaresList()
                                [xDestination - 1][yDestination + 1]
                                .setOccupyingPiece(null);
                            gameBoard
                                .getSquaresList()
                                [xDestination - 1][yDestination + 1]
                                .setIsOccupied(false);
                        }
                        if (xDestination < xPrev) {
                            gameBoard
                                .getSquaresList()
                                [xDestination + 1][yDestination + 1]
                                .setOccupyingPiece(null);
                            gameBoard
                                .getSquaresList()
                                [xDestination + 1][yDestination + 1]
                                .setIsOccupied(false);
                        }
                    }
                    CheckersMoveCommand newCommand = new CheckersMoveCommand(
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

                    if (movingPiece.getColor()
                            .equals(CheckersBoard.Color.white)) {
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
        CheckersPiece cPiece = null;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.getSquaresList()[i][j].getIsOccupied()) {
                    cPiece = gameBoard.getSquaresList()
                            [i][j].getOccupyingPiece();
                    if ((cPiece.getColor() == CheckersBoard.Color.black)) {
                        count = count + 1;
                    }
                }
            }
        }
        if (count == 0) {
            messageBox(blackPlayer.getPlayerName()
                    + " ,You have no more pieces\nYou Lost\nPlease "
                    + "Click Restart to Play again", "GAME OVER!!"
            );
            gameOver = true;
            whitePlayer.setPlayerScore(whitePlayer.getPlayerScore() + 1);
            whiteScore.setText(whitePlayer.getPlayerName()
                    + " Score : " + whitePlayer.getPlayerScore()
            );
            blackScore.setText(blackPlayer.getPlayerName()
                    + " Score : " + blackPlayer.getPlayerScore()
            );
            if (checkForFirstWin(whitePlayer.getPlayerScore())) {
                messageBox(whitePlayer.getPlayerName()
                        + ", You earned an achievement for your first win!!!!",
                        "****FIRST WIN ACHIEVEMENT****"
                );
            }
            if (getPlayerWithStreak() == whitePlayer) {
                setStreak(getStreak() + 1);
            } else {
                setPlayerWithStreak(whitePlayer);
                setStreak(1);
            }
            checkStreakAchievements(getStreak());
        }
    }

    /**
     * Check for win streak achievements.
     * @param streakCount the win streak
     */
    private void checkStreakAchievements(final int streakCount) {
        if (streakCount == 3) {
            this.setChanged();
            this.notifyObservers(Achievement.CHECKERS_WIN_STREAK_3);
            messageBox(getPlayerWithStreak().getPlayerName()
                    + ", You earned an achievement for your 3 "
                    + "game win streak!!!!",
                    "****3 GAME WIN STREAK ACHIEVEMENT****"
            );
        } else if (streakCount == 5) {
            this.setChanged();
            this.notifyObservers(Achievement.CHECKERS_WIN_STREAK_5);
            messageBox(getPlayerWithStreak().getPlayerName()
                    + ", You earned an achievement for your 5 "
                    + "game win streak!!!!",
                    "****5 GAME WIN STREAK ACHIEVEMENT****"
            );
        } else {
            return;
        }
    }

    /**
     * Check for first win achievement.
     * @param score the player win score
     * @return true if achievement met
     */
    private boolean checkForFirstWin(final int score) {
        if (score == 1) {
            this.setChanged();
            this.notifyObservers(Achievement.CHECKERS_FIRST_WIN);
            System.out.println("GOT IT");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the black game has won the game.
     */
    private void checkBlackWin() {
        CheckersPiece cPiece = null;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.getSquaresList()
                        [i][j].getIsOccupied()) {
                    cPiece = gameBoard
                            .getSquaresList()[i][j].getOccupyingPiece();
                    if ((cPiece.getColor() == CheckersBoard.Color.white)) {
                        count = count + 1;
                    }
                }
            }
        }
        if (count == 0) {
            messageBox(whitePlayer.getPlayerName()
                    + " ,You have no more pieces\nYou Lost\nPlease "
                    + "Click Restart to Play again", "GAME OVER!!"
            );
            gameOver = true;
            blackPlayer.setPlayerScore(blackPlayer.getPlayerScore() + 1);
            whiteScore.setText(whitePlayer.getPlayerName()
                    + " Score : " + whitePlayer.getPlayerScore()
            );
            blackScore.setText(blackPlayer.getPlayerName()
                    + " Score : " + blackPlayer.getPlayerScore()
            );
            if (checkForFirstWin(blackPlayer.getPlayerScore())) {
                messageBox(blackPlayer.getPlayerName()
                        + ", You earned an achievement for your first win!!!!",
                        "****FIRST WIN ACHIEVEMENT****"
                );
            }
            if (getPlayerWithStreak() == blackPlayer) {
                setStreak(getStreak() + 1);
            } else {
                setPlayerWithStreak(blackPlayer);
                setStreak(1);
            }
            checkStreakAchievements(getStreak());
        }
    }

    /**
     * Restarts the game.
     */
    private void restartGame() {
        String player;
        if (gameTurn.equals(cis350.games.CheckersBoard.Color.white)) {
            player = blackPlayer.getPlayerName();
        } else {
            player = whitePlayer.getPlayerName();
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
            startNewGame(getStreak(), getPlayerWithStreak());
        }
    }

    /**
     * Forfeits the game for the player that clicked the button.
     */
    private void forfeitGame() {
        CheckersPlayer currentPlayer;
        CheckersPlayer otherPlayer;
        if (gameTurn == cis350.games.CheckersBoard.Color.white) {
            currentPlayer = whitePlayer;
            otherPlayer = blackPlayer;
        } else {
            currentPlayer = blackPlayer;
            otherPlayer = whitePlayer;
        }
        int response = JOptionPane.showConfirmDialog(
            null,
            currentPlayer.
            getPlayerName() + " , Are you sure you want to forfeit",
            "Forfeit",
            JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            gameOver = true;
            otherPlayer.setPlayerScore(otherPlayer.getPlayerScore() + 1);
            whiteScore.setText(whitePlayer.getPlayerName()
                    + " Score : " + whitePlayer.getPlayerScore()
            );
            blackScore.setText(blackPlayer.getPlayerName()
                    + " Score : " + blackPlayer.getPlayerScore()
            );
            messageBox(currentPlayer.getPlayerName()
                    + " ,You Lost\nPlease Click Restart to Play again",
                    "GAME OVER!!"
            );
            if (checkForFirstWin(otherPlayer.getPlayerScore())) {
                messageBox(otherPlayer.getPlayerName()
                        + ", You earned an achievement for your first win!!!!",
                        "****FIRST WIN ACHIEVEMENT****"
                );
            }
            if (getPlayerWithStreak() == otherPlayer) {
                setStreak(getStreak() + 1);
            } else {
                setPlayerWithStreak(otherPlayer);
                setStreak(1);
            }
            checkStreakAchievements(getStreak());
            
        }
    }

    /**
     * Used for main method testing.
     * @param args command line args.
     */
    public static void main(final String[] args) {
        getGamePlayers();
        startNewGame(0, whitePlayer);
    }

    /**
     * Calls all helper methods to start the game.
     * @param streak the player streak
     * @param playerWithStreak the player with the streak
     */
    public static void startNewGame(final int streak, 
    		final CheckersPlayer playerWithStreak) {
        CheckersGame newGame = new CheckersGame();
        newGame.addObserver(Main.getAchievementsViewController());
        newGame.gameInit();
        newGame.setupDisplay();
        newGame.setStreak(streak);
        newGame.setPlayerWithStreak(playerWithStreak);
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

    /**
     * Get the player with a win streak.
     * @return the player with a win streak
     */
    public CheckersPlayer getPlayerWithStreak() {
        return playerWithStreak;
    }

    /**
     * Set the player with the win streak.
     * @param playerWithStreak the player with the win streak
     */
    public void setPlayerWithStreak(final CheckersPlayer playerWithStreak) {
        this.playerWithStreak = playerWithStreak;
    }

    /**
     * Get the current win streak.
     * @return the current win streak.
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Set the current win streak.
     * @param streak the current win streak
     */
    public void setStreak(final int streak) {
        this.streak = streak;
    }

}


