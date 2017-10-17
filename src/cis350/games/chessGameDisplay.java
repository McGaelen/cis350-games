package cis350.games;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import cis350.games.chessBoard;
//import cis350.games.chessPiece;
import cis350.games.chessSquare;
import cis350.games.chessStandardBoard;

/**
 * The game display class which is a subclass of JPanel.
 *
 */
public class chessGameDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	chessStandardBoard board;
	int squareSize;
	
	// Constructor to initialize a game display

	public chessGameDisplay(chessStandardBoard gameBoard, int squareSize){
		board = gameBoard;
		this.squareSize = squareSize;	
	}
	

	@Override
	public void paintComponent(Graphics graphic){
		for(int i = 0; i < board.numXSquares; i++){
			for(int j = 0; j < board.numYSquares; j++){
				chessSquare squareToDraw = board.squaresList[i][j];
				if(squareToDraw.color.equals(chessBoard.Color.black)){
					graphic.setColor(new Color(58,95,205));
					graphic.fillRect((squareSize*i), (7-j)*squareSize, squareSize, squareSize);
					if(squareToDraw.isOccupied)
						squareToDraw.occupyingPiece.drawPiece(graphic, squareSize, i, j);
				}
				else{
					graphic.setColor(new Color(230, 230, 250));
					graphic.fillRect((squareSize*i), (7-j)*squareSize, squareSize, squareSize);
					if(squareToDraw.isOccupied)
						squareToDraw.occupyingPiece.drawPiece(graphic, squareSize, i, j);
				}
			}
		}
	}
	
}

