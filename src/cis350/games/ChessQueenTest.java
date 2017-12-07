package cis350.games;

import cis350.games.ChessBoard.Color;
import junit.framework.TestCase;

  /**
 * Test cases for the Queen.
 *
 */
public class ChessQueenTest extends TestCase {

    /**
     * Test valid vertical queen move.
     */
    public void testValidVerticalQueenMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessQueen newQueen = new ChessQueen(3, 0, Color.black, board);
        assertTrue(newQueen.canMove(7, 0));
    }

    /**
     * Test valid queen horizontal move.
     */
    public void testValidHorizontalQueenMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessQueen newQueen = new ChessQueen(3, 0, Color.black, board);
        assertTrue(newQueen.canMove(5, 0));
    }

    /**
     * Test valid queen diagonal move.
     */
    public void testValidDiagonalQueenMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessQueen newQueen = new ChessQueen(3, 0, Color.black, board);
        assertTrue(newQueen.canMove(6, 3));
    }

    /**
     * Test blocking piece queen move.
     */
    public void testBlockingPieceQueenMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessQueen newQueen = new ChessQueen(3, 0, Color.black, board);
        ChessRook newRook = new ChessRook(3, 1, Color.white, board);
        assertFalse(newQueen.canMove(3, 3));
    }

    /**
     * TYest invalid queen move.
     */
    public void testInvalidQueenMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessQueen newQueen = new ChessQueen(1, 2, Color.black, board);
        assertFalse(newQueen.canMove(2, 4));
    }

}
