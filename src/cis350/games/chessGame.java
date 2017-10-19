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


import cis350.games.chessKing;
import cis350.games.chessPiece;
import cis350.games.chessStandardBoard;
//import cis350.games.chessBoard;
import cis350.games.chessGameDisplay;


// Game class to setup a complete Chess Game. Will move to Controllers once implemented properly.
  
public class chessGame {
	
	static chessPlayer whitePlayer;
	static chessPlayer blackPlayer;
	cis350.games.chessBoard.Color gameTurn;
	chessStandardBoard gameBoard;
	boolean gameOver;
	static boolean gameType;
	int squareSize;
	JFrame window;
	JPanel gamePanel;
	JPanel sidePanel;
	JLabel whiteLabel;
	JLabel blackLabel;
	JLabel whiteScore;
	JLabel blackScore;
	JButton forfeitButton;
	JButton undoButton;
	JButton restartButton;
	chessPiece movingPiece;
	Stack<chessMoveCommand> commandStack;
	

	// Method to initialize gameBoard, populate it with pieces according to gameType 

	public void gameInit(boolean gameType) {
		gameBoard = new chessStandardBoard(8,8);
		gameBoard.populateBoardWithPieces(gameType);
		gameTurn = cis350.games.chessBoard.Color.white;
		gameOver = false;
		squareSize = 80;
		commandStack = new Stack();
	}

	// Helper method to instantiate players of the current game.
	
	static void getGamePlayers() {
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
		//int response = JOptionPane.showConfirmDialog(null, "Do you want to play a Special Game?", "Game Type", JOptionPane.YES_NO_OPTION);
		//if(response == JOptionPane.YES_OPTION)
		//	gameType = true;
		//else
			gameType = false;
		return gameType;
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
		window = new JFrame("Chess");
        gamePanel = initializeGamePanel(gameBoard); 
        Container contentPanel = window.getContentPane();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        sidePanel = initializeSidePanel();
        contentPanel.add(gamePanel);
        contentPanel.add(sidePanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.validate();
        window.pack();

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
		undoButton = new JButton("Undo Move");
		restartButton = new JButton("Restart Game");
		forfeitButton = new JButton("Forfeit Game");
		setupButtonListeners();
		whiteLabel = new JLabel("WHITE PLAYER : ".concat(whitePlayer.playerName) + " ");
		whiteLabel.setForeground(Color.BLUE);
		blackLabel = new JLabel("BLACK PLAYER : ".concat(blackPlayer.playerName) + " ");
		whiteScore = new JLabel(whitePlayer.playerName + " Score : " + whitePlayer.playerScore);
		blackScore = new JLabel(blackPlayer.playerName + " Score : " + blackPlayer.playerScore);
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
	
	
	// Helper method to setup the button listeners for Undo, Restart and Forfeit buttons.
	 
	private void setupButtonListeners() {
		undoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				undoMove();
			}
		});
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				restartGame();
			}
		});
		forfeitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				forfeitGame();
			}
		});
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
						blackLabel.setForeground(Color.BLUE);
						whiteLabel.setForeground(Color.BLACK);
						checkKingStatus(gameBoard.blackKingTracker);
					 }
					 else{
						 gameTurn = gameTurn.opposite();
						 whiteLabel.setForeground(Color.BLUE);
						 blackLabel.setForeground(Color.BLACK);
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
				whiteScore.setText(whitePlayer.playerName + " Score : " + whitePlayer.playerScore);
				blackScore.setText(blackPlayer.playerName + " Score : " + blackPlayer.playerScore);
				return;
			}
			messageBox(currentPlayer.playerName + " ,Your King is in Check", "King in Check!!");
		}
	}
	
	private void undoMove(){
		if(!commandStack.isEmpty()){
			chessMoveCommand move = commandStack.pop();
			move.undo();
			if(gameTurn == cis350.games.chessBoard.Color.white){
				blackLabel.setForeground(Color.BLUE);
				whiteLabel.setForeground(Color.BLACK);
			}
			else{
				whiteLabel.setForeground(Color.BLUE);
			 	blackLabel.setForeground(Color.BLACK);
			}
			gameTurn = gameTurn.opposite();
		}
	}

	private void restartGame(){
		String player;
		if(gameTurn.equals(cis350.games.chessBoard.Color.white))
			player = blackPlayer.playerName;
		else
			player = whitePlayer.playerName;
		int response = JOptionPane.showConfirmDialog(null, player + " , would you like to restart?", "Restart", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			gameOver = true;
			window.setVisible(false);
			startNewGame();
		}
	}
	

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
			whiteScore.setText(whitePlayer.playerName + " Score : " + whitePlayer.playerScore);
			blackScore.setText(blackPlayer.playerName + " Score : " + blackPlayer.playerScore);
			messageBox(currentPlayer.playerName + " ,You Lost\nPlease Click Restart to Play again", "GAME OVER!!");
		}
	}


	public static void main(String args[]){
		getGamePlayers();
		startNewGame();
	}


	public static void startNewGame() {
		chessGame newGame = new chessGame();
		newGame.gameInit(getGameType());
		newGame.setupDisplay();
		newGame.gameStart();
		newGame.mouseActions();
		
	}

	public static void messageBox(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
	
}

