package cis350.games;

import cis350.games.chessBoard.Color;
import cis350.games.chessQueen;
import cis350.games.chessRook;
import cis350.games.chessStandardBoard;
import junit.framework.TestCase;

  /**
 * Test cases for the Queen
 
 *
 */
public class ChessQueenTest extends TestCase {
	
	/**
	 * Test valid vertical queen move.
	 */
	public void testValidVerticalQueenMove(){
		chessStandardBoard board = new chessStandardBoard(8,8);
		chessQueen newQueen = new chessQueen(3, 0, Color.black, board);
		assertTrue(newQueen.canMove(7, 0));
	}
	
	/**
	 * Test valid queen horizontal move
	 */
	public void testValidHorizontalQueenMove(){
		chessStandardBoard board = new chessStandardBoard(8,8);
		chessQueen newQueen = new chessQueen(3, 0, Color.black, board);
		assertTrue(newQueen.canMove(5, 0));
	}
	
	/**
	 * Test valid queen diagonal move.
	 */
	public void testValidDiagonalQueenMove(){
		chessStandardBoard board = new chessStandardBoard(8,8);
		chessQueen newQueen = new chessQueen(3, 0, Color.black, board);
		assertTrue(newQueen.canMove(6, 3));
	}
	
	/**
	 * Test blocking piece queen move.
	 */
	public void testBlockingPieceQueenMove(){
		chessStandardBoard board = new chessStandardBoard(8,8);
		chessQueen newQueen = new chessQueen(3, 0, Color.black, board);
		chessRook newRook = new chessRook(3, 1, Color.white, board);
		assertFalse(newQueen.canMove(3, 3));
	}
	
	/**
	 * TYest invalid queen move.
	 */
	public void testInvalidQueenMove(){
		chessStandardBoard board = new chessStandardBoard(8,8);
		chessQueen newQueen = new chessQueen(1, 2, Color.black, board);
		assertFalse(newQueen.canMove(2, 4));
	}

}
