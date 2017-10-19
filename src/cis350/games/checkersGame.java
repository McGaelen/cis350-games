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


import cis350.games.checkersKing;
import cis350.games.checkersPiece;
import cis350.games.checkersStandardBoard;
//import cis350.games.checkersBoard;
import cis350.games.checkersGameDisplay;


// Game class to setup a complete checkers Game. Will move to Controllers once implemented properly.
  
public class checkersGame {
	
	static checkersPlayer whitePlayer;
	static checkersPlayer blackPlayer;
	cis350.games.checkersBoard.Color gameTurn;
	checkersStandardBoard gameBoard;
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
	JButton restartButton;
	checkersPiece movingPiece;
	Stack<checkersMoveCommand> commandStack;
	

	// Method to initialize gameBoard, populate it with pieces according to gameType 

	public void gameInit() {
		gameBoard = new checkersStandardBoard(8,8);
		gameBoard.populateBoardWithPieces();
		gameTurn = cis350.games.checkersBoard.Color.white;
		gameOver = false;
		squareSize = 80;
		commandStack = new Stack();
	}

	// Helper method to instantiate players of the current game.
	
	private static void getGamePlayers() {
		String whiteName = JOptionPane.showInputDialog("Please input White player name");
		if(whiteName == "" || whiteName == null)
			whiteName = "Player 1";
		String blackName = JOptionPane.showInputDialog("Please input Black player name");
		if(blackName == "" || blackName == null)
			blackName = "Player 2";
		whitePlayer = new checkersPlayer(whiteName, cis350.games.checkersBoard.Color.white);
		blackPlayer = new checkersPlayer(blackName, cis350.games.checkersBoard.Color.black);
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
		window = new JFrame("Checkers");
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
	  
	private JPanel initializeGamePanel(checkersStandardBoard gameBoard) {
        checkersGameDisplay gameDisplay = new checkersGameDisplay(gameBoard, squareSize);
        gameDisplay.setPreferredSize(new Dimension(640,640));
        gameDisplay.setLayout(new BorderLayout());
        return gameDisplay;
    }
	
	
	// Helper method to initialize a side JPanel for the game. 

	private JPanel initializeSidePanel(){
		JPanel sideDisplay = new JPanel();
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
		sideDisplay.add(forfeitButton);
		sideDisplay.add(restartButton);
		sideDisplay.add(whiteScore);
		sideDisplay.add(blackScore);
		return sideDisplay;
	}
	
	
	// Helper method to setup the button listeners for Restart and Forfeit buttons.
	 
	private void setupButtonListeners() {
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
			int xPrev;
			int yPrev;
			public void mousePressed(MouseEvent me){
				int xOrigin = me.getX();
				int yOrigin = me.getY();
				xOrigin = xOrigin/squareSize;
				yOrigin = yOrigin/squareSize;
				yOrigin = 7 - yOrigin;
				xPrev = xOrigin;
				yPrev = yOrigin;
				movingPiece = gameBoard.squaresList[xOrigin][yOrigin].occupyingPiece;
			}
			
			public void mouseReleased(MouseEvent me) {
			    int xDestination = me.getX();
				int yDestination = me.getY();
				xDestination = xDestination/squareSize;
				yDestination = yDestination/squareSize;
				yDestination = 7 - yDestination;
				if(movingPiece.color == gameTurn && movingPiece.canMove(xDestination, yDestination)){
					checkersPiece enemyPiece = null;
					//White Pawn Piece Moves
					if(yDestination > yPrev){
						if(xDestination > xPrev){
							gameBoard.squaresList[xDestination-1][yDestination-1].occupyingPiece = null;
							gameBoard.squaresList[xDestination-1][yDestination-1].isOccupied = false;
						}
						if(xDestination < xPrev){
							gameBoard.squaresList[xDestination+1][yDestination-1].occupyingPiece = null;
							gameBoard.squaresList[xDestination+1][yDestination-1].isOccupied = false;
						}
					}
					//Black Pawn Piece Moves
					if(yDestination < yPrev){
						if(xDestination > xPrev){
							gameBoard.squaresList[xDestination-1][yDestination+1].occupyingPiece = null;
							gameBoard.squaresList[xDestination-1][yDestination+1].isOccupied = false;
						}
						if(xDestination < xPrev){
							gameBoard.squaresList[xDestination+1][yDestination+1].occupyingPiece = null;
							gameBoard.squaresList[xDestination+1][yDestination+1].isOccupied = false;
						}
					}
					checkersMoveCommand newCommand = new checkersMoveCommand(movingPiece, enemyPiece, xDestination, yDestination);
					commandStack.add(newCommand);
					newCommand.execute();
					
					if(yDestination == 7) {
						movingPiece.whitePromote(xDestination, yDestination);
					}
					if(yDestination == 0) {
						movingPiece.blackPromote(xDestination, yDestination);
					}
					
					if(movingPiece.color.equals(cis350.games.checkersBoard.Color.white)){
						gameTurn = gameTurn.opposite();
						blackLabel.setForeground(Color.BLUE);
						whiteLabel.setForeground(Color.BLACK);		
						checkWhiteWin();
					 }
					 else{
						 gameTurn = gameTurn.opposite();
						 whiteLabel.setForeground(Color.BLUE);
						 blackLabel.setForeground(Color.BLACK);
						 checkBlackWin();
					 }
					 
				}
				else{
					messageBox("This is an Illegal Move!", "Illegal move message");
				}	
			}

			
		});
	}
	
	private void checkWhiteWin() {
		checkersPiece cPiece = null;
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(gameBoard.squaresList[i][j].isOccupied) {
					cPiece = gameBoard.squaresList[i][j].occupyingPiece;
					if((cPiece.color == cis350.games.checkersBoard.Color.black)) {
						count = count + 1;
					}
				}
			}
		}
		if(count == 0) {
			messageBox(blackPlayer.playerName + " ,You have no more pieces\nYou Lost\nPlease Click Restart to Play again", "GAME OVER!!");
			gameOver = true;
			whitePlayer.playerScore++;
			whiteScore.setText(whitePlayer.playerName + " Score : " + whitePlayer.playerScore);
			blackScore.setText(blackPlayer.playerName + " Score : " + blackPlayer.playerScore);
			return;
		}		
	}
	
	private void checkBlackWin() {
		checkersPiece cPiece = null;
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(gameBoard.squaresList[i][j].isOccupied) {
					cPiece = gameBoard.squaresList[i][j].occupyingPiece;
					if((cPiece.color == cis350.games.checkersBoard.Color.white)) {
						count = count + 1;
					}
				}
			}
		}
		if(count == 0) {
			messageBox(whitePlayer.playerName + " ,You have no more pieces\nYou Lost\nPlease Click Restart to Play again", "GAME OVER!!");
			gameOver = true;
			blackPlayer.playerScore++;
			whiteScore.setText(whitePlayer.playerName + " Score : " + whitePlayer.playerScore);
			blackScore.setText(blackPlayer.playerName + " Score : " + blackPlayer.playerScore);
			return;
		}		
	}

	private void restartGame(){
		String player;
		if(gameTurn.equals(cis350.games.checkersBoard.Color.white))
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
		checkersPlayer currentPlayer;
		checkersPlayer otherPlayer;
		if(gameTurn == cis350.games.checkersBoard.Color.white){
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
		checkersGame newGame = new checkersGame();
		newGame.gameInit();
		newGame.setupDisplay();
		newGame.gameStart();
		newGame.mouseActions();
		
	}

	public static void messageBox(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
	
}


