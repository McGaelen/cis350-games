package cis350.games;

import cis350.games.chessBishop;
import cis350.games.chessBoard;
import cis350.games.chessKing;
import cis350.games.chessKnight;
import cis350.games.chessPawn;
import cis350.games.chessQueen;
import cis350.games.chessRook;
import cis350.games.chessSquare;
import cis350.games.chessBoard.Color;

/**
 * Subclass of a board. Standard version of a chess Board. Has methods for creating a standard
 * chess board and populating it with regular chess pieces.
 * Can be used to create a standard game of cis350.games.
 */
public class chessStandardBoard extends chessBoard {
	
	/**
	 * Trackers for the white and black kings for check, checkmate and game ending conditions.
	 */
	public chessKing whiteKingTracker;
	public chessKing blackKingTracker;
	
	/**
	 * Method to initialize the chess board.
	 * @param xSquares
	 * @param ySquares
	 */
	public chessStandardBoard(int xSquares, int ySquares) {

		this.numXSquares = xSquares;
		this.numYSquares = ySquares;
		this.totalSquares = this.numXSquares * this.numYSquares;
		this.squaresList = new chessSquare[this.numXSquares][this.numYSquares];
		populateBoardWithSquares();
		this.whiteKingTracker = null;
		this.blackKingTracker = null;
	}

	/**
	 * Method to populate our board with Squares.
	 * General pattern of white and black squares on the board.
	 */
	public void populateBoardWithSquares() {
		for (int i = 0; i < this.numXSquares; i++) {
			for (int j = 0; j < this.numYSquares; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						squaresList[i][j] = new chessSquare(false, Color.black);
					else
						squaresList[i][j] = new chessSquare(false, Color.white);
				} 
				else {
					if (j % 2 == 0)
						squaresList[i][j] = new chessSquare(false, Color.white);
					else
						squaresList[i][j] = new chessSquare(false, Color.black);
				}
			}
		}
	}
	
	/**
	 * Method to populate our chess board with standard pieces.
	 */
	public void populateBoardWithPieces(boolean special) {
		setupKnights();
		setupBishops();
		setupPawns();
		setupRooks();
		setupQueens();
		setupKings();
	}

	/**
	 * Setup 8 black and 8 white pawns in their initial positions.
	 */
	public void setupPawns(){
		for(int i = 0; i < 8; i++){
			chessPawn newWhitePawn = new chessPawn(i, 1, Color.white, this);
			chessPawn newBlackPawn = new chessPawn(i, 6, Color.black, this);
			this.squaresList[i][1].isOccupied = true;
			this.squaresList[i][6].isOccupied = true;
			this.squaresList[i][1].occupyingPiece = newWhitePawn;
			this.squaresList[i][6].occupyingPiece = newBlackPawn;
			
		}
	}
	
	/**
	 * Setup 2 black rooks and 2 white rooks in their initial positions.
	 */
	public void setupRooks(){
		chessRook whiteRookOne = new chessRook(0, 0, Color.white, this);
		chessRook whiteRookTwo = new chessRook(7, 0, Color.white, this);
		chessRook blackRookOne = new chessRook(0, 7, Color.black, this);
		chessRook blackRookTwo = new chessRook(7, 7, Color.black, this);
		this.squaresList[0][0].isOccupied = true;
		this.squaresList[7][0].isOccupied = true;
		this.squaresList[0][0].occupyingPiece = whiteRookOne;
		this.squaresList[7][0].occupyingPiece = whiteRookTwo;
		this.squaresList[0][7].isOccupied = true;
		this.squaresList[7][7].isOccupied = true;
		this.squaresList[0][7].occupyingPiece = blackRookOne;
		this.squaresList[7][7].occupyingPiece = blackRookTwo;
		
	}
	
	/**
	 * Setup 2 black Bishops and 2 white Bishops in their initial positions.
	 */
	public void setupBishops(){
		chessBishop whiteBishopOne = new chessBishop(2, 0, Color.white, this);
		chessBishop whiteBishopTwo = new chessBishop(5, 0, Color.white, this);
		chessBishop blackBishopOne = new chessBishop(2, 7, Color.black, this);
		chessBishop blackBishopTwo = new chessBishop(5, 7, Color.black, this);
		this.squaresList[2][0].isOccupied = true;
		this.squaresList[5][0].isOccupied = true;
		this.squaresList[2][0].occupyingPiece = whiteBishopOne;
		this.squaresList[5][0].occupyingPiece = whiteBishopTwo;
		this.squaresList[2][7].isOccupied = true;
		this.squaresList[5][7].isOccupied = true;
		this.squaresList[2][7].occupyingPiece = blackBishopOne;
		this.squaresList[5][7].occupyingPiece = blackBishopTwo;
	}
	
	/**
	 * Setup 2 black Knights and 2 white Knights in their initial positions.
	 */
	public void setupKnights(){
		chessKnight whiteKnightOne = new chessKnight(1, 0, Color.white, this);
		chessKnight whiteKnightTwo = new chessKnight(6, 0, Color.white, this);
		chessKnight blackKnightOne = new chessKnight(1, 7, Color.black, this);
		chessKnight blackKnightTwo = new chessKnight(6, 7, Color.black, this);
		this.squaresList[1][0].isOccupied = true;
		this.squaresList[6][0].isOccupied = true;
		this.squaresList[1][0].occupyingPiece = whiteKnightOne;
		this.squaresList[6][0].occupyingPiece = whiteKnightTwo;
		this.squaresList[1][7].isOccupied = true;
		this.squaresList[6][7].isOccupied = true;
		this.squaresList[1][7].occupyingPiece = blackKnightOne;
		this.squaresList[6][7].occupyingPiece = blackKnightTwo;
	}
	
	/**
	 * Setup 2 queens white and black in their initial positions.
	 */	
	public void setupQueens(){
		chessQueen whiteQueen = new chessQueen(3, 0, Color.white, this);
		chessQueen blackQueen = new chessQueen(3, 7, Color.black, this);
		this.squaresList[3][0].isOccupied = true;
		this.squaresList[3][7].isOccupied = true;
		this.squaresList[3][0].occupyingPiece = whiteQueen;
		this.squaresList[3][7].occupyingPiece = blackQueen;
	}
	
	/**
	 * Setup 2 queens white and black in their initial positions.
	 */
	public void setupKings(){
		chessKing whiteKing = new chessKing(4, 0, Color.white, this);
		chessKing blackKing = new chessKing(4, 7, Color.black, this);
		this.squaresList[4][0].isOccupied = true;
		this.squaresList[4][7].isOccupied = true;
		this.squaresList[4][0].occupyingPiece = whiteKing;
		this.squaresList[4][7].occupyingPiece = blackKing;
		whiteKingTracker = whiteKing;
		blackKingTracker = blackKing;
	}
	
	/**
	 * Helper method to check if locations passed in are mapped on our generated board.
	 * @see cis350.games.Board#inBoardBounds(int, int)
	 * @param newX
	 * @param newY
	 * @return boolean true if move is in board bounds
	 */
	public boolean inBoardBounds(int newX, int newY){
		if(newX < numXSquares && newY < numYSquares && newX > -1 && newY > -1){
			return true;
		}
		else
			return false;
	}

}
