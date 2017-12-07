package cis350.games;

import cis350.games.ChessBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the Knight.
 *
 */
public class ChessKnightTest extends TestCase {

    /**
     * Test valid vertical Knight move.
     */
    public void testValidVerticalKnightMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKnight newKnight = new ChessKnight(1, 0, Color.white, board);
        assertTrue(newKnight.canMove(2, 2));
    }

    /**
     * Test valid horizontal Knight move.
     */
    public void testValidHorizontalKnightMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKnight newKnight = new ChessKnight(1, 0, Color.white, board);
        assertTrue(newKnight.canMove(3, 1));
    }

    /**
     * Test invalid Knight move.
     */
    public void testInvalidKnightMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKnight newKnight = new ChessKnight(6, 2, Color.white, board);
        assertFalse(newKnight.canMove(5, 1));
    }

    /**
     * Test valid Hopping over blocking piece move.
     */
    public void testBlockingPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKnight newKnight = new ChessKnight(1, 0, Color.white, board);
        ChessPawn blockingPawn = new ChessPawn(1, 1, Color.white, board);
        assertTrue(newKnight.canMove(2, 2));
    }

    /**
     * Test invalid ally at destination.
     */
    public void testInvalidAllyBlockingPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKnight newKnight = new ChessKnight(1, 0, Color.white, board);
        ChessPawn blockingPawn = new ChessPawn(2, 2, Color.white, board);
        assertFalse(newKnight.canMove(2, 2));
    }

}
