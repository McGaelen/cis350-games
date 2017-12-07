package cis350.games;

import cis350.games.ChessBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the Pawn.
 */
public class ChessPawnTest extends TestCase {

    /**
     * Test valid white pawn move.
     */
    public void testValidWhitePawnMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(0, 1, Color.white, board);
        assertTrue(newPawn.canMove(0, 2));
    }

    /**
     * Test valid black pawn move.
     */
    public void testValidBlackPawnMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(0, 1, Color.black, board);
        assertTrue(newPawn.canMove(0, 0));
    }

    /**
     * Test invalid pawn move.
     */
    public void testInvalidPawnMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(1, 4, Color.black, board);
        assertFalse(newPawn.canMove(1, 6));
    }

    /**
     * Test valid enemy piece move/capture.
     */
    public void testValidEnemyPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(2, 2, Color.white, board);
        ChessPawn enemyPawn = new ChessPawn(3, 3, Color.black, board);
        assertTrue(newPawn.canMove(3, 3));
    }

    /**
     * Test valid First white pawn move.
     */
    public void testValidFirstPawnMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(0, 1, Color.white, board);
        assertTrue(newPawn.canMove(0, 3));
    }

    /**
     * Test valid First black pawn move.
     */
    public void testValidFirstBlackPawnMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(3, 6, Color.black, board);
        assertTrue(newPawn.canMove(3, 4));
    }

    /**
     * Test valid black pawn first move.
     */
    public void testValidFirstPawnEnemyMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn newPawn = new ChessPawn(0, 1, Color.white, board);
        ChessPawn enemyPawn = new ChessPawn(1, 2, Color.black, board);
        assertTrue(newPawn.canMove(1, 2));
    }

}
