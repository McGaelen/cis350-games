package cis350.games;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cis350.games.checkersBoard.Color;

/**
 *
 */
public abstract class checkersPiece {
	

	// Piece specific name will be stored here
	String nameOfPiece;
	// Black or White piece
	public Color color;
	// Reference to the board this piece is on to indirectly access squaresList
	checkersStandardBoard currentBoard;
	// xLocation of piece on board.
	public int xLocation;
	// yLocation of piece on board.
	public int yLocation;

    /**
     *
     * @param newX
     * @param newY
     * @return
     */
	abstract boolean isValidSpecialMove(int newX, int newY);

    /**
     *
     * @param initX
     * @param initY
     * @param color
     * @param board
     */
	public checkersPiece(int initX, int initY, Color color, checkersStandardBoard board) {
		this.color = color;
		board.squaresList[initX][initY].isOccupied = true;
		board.squaresList[initX][initY].occupyingPiece = this;
		this.currentBoard = board;
		this.xLocation = initX;
		this.yLocation = initY;
	}

    /**
     *
     * @param newX
     * @param newY
     * @return
     */
	public boolean canMove(int newX, int newY){
		if(!currentBoard.inBoardBounds(newX, newY))
			return false;
		if(!isValidSpecialMove(newX, newY))
			return false;
		if(!isEnemyPieceAtDestination(newX, newY))
			return false;
		if(isKingInDanger(newX, newY))
			return false;
		return true;
	}

    /**
     *
     * @param newX
     * @param newY
     * @return
     */
	private boolean isEnemyPieceAtDestination(int newX, int newY){
		checkersSquare squareToCheck = currentBoard.squaresList[newX][newY];
		if(squareToCheck.isOccupied){
			return isEnemyPiece(this.color, squareToCheck.occupyingPiece);
		}
		return true;
	}

    /**
     *
     * @param newX
     * @param newY
     */
	public void executeCaptureOrMove(int newX, int newY){
		movePiece(this, newX, newY);
	}

    /**
     *
     * @param kingToCheck
     * @return
     */
	public boolean isKingInCheck(checkersKing kingToCheck) {
		int kingXLocation = kingToCheck.xLocation;
		int kingYLocation = kingToCheck.yLocation;
		Color colorToCheck = kingToCheck.color;
		// Iterates through the squares on the board and checks if enemy pieces can attack king.
		for(int i = 0; i < currentBoard.numXSquares; i++){
			for(int j = 0; j < currentBoard.numYSquares; j++){
				checkersSquare squareToCheck = currentBoard.squaresList[i][j];
				if(squareToCheck.isOccupied){
					if(isEnemyPiece(colorToCheck, squareToCheck.occupyingPiece)){
						checkersPiece enemyPiece = squareToCheck.occupyingPiece;
						if(enemyPiece.isValidSpecialMove(kingXLocation, kingYLocation))
							return true;
					}
				}
			}
		}
		return false;
		
	}

    /**
     *
     * @param newPieceX
     * @param newPieceY
     * @return
     */
	private boolean isKingInDanger(int newPieceX, int newPieceY) {
		int oldPieceX = this.xLocation;
		int oldPieceY = this.yLocation;
		checkersKing kingToCheck;
		checkersSquare squareToCheck = currentBoard.squaresList[newPieceX][newPieceY];
		if(squareToCheck.isOccupied){
			checkersPiece pieceToCheck = squareToCheck.occupyingPiece;
			if(isEnemyPieceAtDestination(newPieceX, newPieceY)){
				checkersPiece enemyPiece = pieceToCheck;
				if(this.color.equals(Color.white)){
					if(currentBoard.whiteKingTracker == null)
						return false;
					kingToCheck = currentBoard.whiteKingTracker;
				}
				else{
					if(currentBoard.blackKingTracker == null)
						return false;
					kingToCheck = currentBoard.blackKingTracker;
				}
				movePiece(this, newPieceX, newPieceY);
				if(isKingInCheck(kingToCheck)){
					movePiece(this, oldPieceX, oldPieceY);
					movePiece(enemyPiece, newPieceX, newPieceY);
					return true;
				}
				movePiece(this, oldPieceX, oldPieceY);
				movePiece(enemyPiece, newPieceX, newPieceY);
			}
		}
		else{
			if(this.color.equals(Color.white)){
				if(currentBoard.whiteKingTracker == null)
					return false;
				kingToCheck = currentBoard.whiteKingTracker;
			}
			else{
				if(currentBoard.blackKingTracker == null)
					return false;
				kingToCheck = currentBoard.blackKingTracker;
			}
			movePiece(this, newPieceX, newPieceY);
			if(isKingInCheck(kingToCheck)){
				movePiece(this, oldPieceX, oldPieceY);
				return true;
			}
			movePiece(this, oldPieceX, oldPieceY);
		}
		return false;
	}

    /**
     *
     * @param pieceToMove
     * @param newPieceX
     * @param newPieceY
     */
	private void movePiece(checkersPiece pieceToMove, int newPieceX, int newPieceY){
		checkersSquare currentSquare = currentBoard.squaresList[pieceToMove.xLocation][pieceToMove.yLocation];
		checkersSquare targetSquare = currentBoard.squaresList[newPieceX][newPieceY];
		currentSquare.isOccupied = false;
		currentSquare.occupyingPiece = null;
		targetSquare.isOccupied = true;
		targetSquare.occupyingPiece = pieceToMove;
		pieceToMove.xLocation = newPieceX;
		pieceToMove.yLocation = newPieceY;
	}

    /**
     *
     * @param colorToCheck
     * @param occupyingPiece
     * @return
     */
	private boolean isEnemyPiece(Color colorToCheck, checkersPiece occupyingPiece) {
		if(colorToCheck.equals(occupyingPiece.color))
			return false;
		else
			return true;
	}

    /**
     *
     * @param graphic
     * @param squareSize
     * @param x
     * @param y
     */
	public void drawPiece(Graphics graphic, int squareSize, int x, int y){
		if(this.color.equals(Color.black)){
			String name = this.nameOfPiece.concat(".png");
			String imagePath = "assets/black_";
			String imageName = imagePath.concat(name);
			drawPieceHelper(graphic, squareSize, imageName, x, y);
		}
		else{
			String name = this.nameOfPiece.concat(".png");
			String imagePath = "assets/white_";
			String imageName = imagePath.concat(name);
			drawPieceHelper(graphic, squareSize, imageName, x, y);
		}
	}

    /**
     *
     * @param graphic
     * @param squareSize
     * @param imageName
     * @param x
     * @param y
     */
	private void drawPieceHelper(Graphics graphic, int squareSize, String imageName, int x, int y) {
		File imageFile = new File(imageName);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int imageHeight = image.getHeight();
		int imageWidth = image.getWidth();
		int heightPadding = (squareSize - imageHeight)/2;
		int widthPadding = (squareSize - imageWidth)/2;
		graphic.drawImage(image, (squareSize*x) + widthPadding, ((7-y)*squareSize) + heightPadding, imageWidth, imageHeight, null);
	}

    /**
     *
     * @param kingToCheck
     * @return
     */
	public boolean isKingCheckmate(checkersKing kingToCheck){
		if(!isKingInCheck(kingToCheck))
			return false;
		Color colorToCheck = kingToCheck.color;
		for(int i = 0; i < currentBoard.numXSquares; i++){
			for(int j = 0; j < currentBoard.numYSquares; j++){
				checkersSquare squareToCheck = currentBoard.squaresList[i][j];
				if(squareToCheck.isOccupied){
					if(!isEnemyPiece(colorToCheck, squareToCheck.occupyingPiece)){
						checkersPiece allyPiece = squareToCheck.occupyingPiece;
						 if(!checkmateHelper(allyPiece, kingToCheck))
							 return false;
					}
				}	
			}
		}
		return true;
	}

    /**
     *
     * @param allyPiece
     * @param kingToCheck
     * @return
     */
	private boolean checkmateHelper(checkersPiece allyPiece, checkersKing kingToCheck) {
		int oldPieceX = allyPiece.xLocation;
		int oldPieceY = allyPiece.yLocation;
		for(int i = 0; i < currentBoard.numXSquares; i++){
			for(int j = 0; j < currentBoard.numYSquares; j++){
				checkersSquare squareToCheck = currentBoard.squaresList[i][j];
				if(isEnemyPieceAtDestination(i,j)){
					if(allyPiece.isValidSpecialMove(i, j)){
						if(squareToCheck.isOccupied){
							checkersPiece enemyPiece = squareToCheck.occupyingPiece;
							movePiece(allyPiece, i, j);
							if(!isKingInCheck(kingToCheck)){
								movePiece(allyPiece, oldPieceX, oldPieceY);
								movePiece(enemyPiece, i, j);
								return false;
							}
							movePiece(allyPiece, oldPieceX, oldPieceY);
							movePiece(enemyPiece, i, j);
						}
						else{
							movePiece(allyPiece, i, j);
							if(!isKingInCheck(kingToCheck)){
								movePiece(allyPiece, oldPieceX, oldPieceY);
								return false;
							}
							movePiece(allyPiece, oldPieceX, oldPieceY);
						}
					}
				}
			}
		}
		return true;
	}

    /**
     *
     * @param xDest
     * @param yDest
     */
	public void whitePromote(int xDest, int yDest) {
		checkersKing newWhiteKing = new checkersKing(xDest, yDest, Color.white, currentBoard);
		currentBoard.squaresList[xDest][yDest].isOccupied = true;
		currentBoard.squaresList[xDest][yDest].occupyingPiece = newWhiteKing;
		
	}

    /**
     *
     * @param xDest
     * @param yDest
     */
	public void blackPromote(int xDest, int yDest) {
		checkersKing newBlackKing = new checkersKing(xDest, yDest, Color.black, currentBoard);
		currentBoard.squaresList[xDest][yDest].isOccupied = true;
		currentBoard.squaresList[xDest][yDest].occupyingPiece = newBlackKing;
		
	}
	
}



