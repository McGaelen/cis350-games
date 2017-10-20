package cis350.games;

import cis350.games.chessBoard.Color;
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
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKnight newKnight = new chessKnight(1, 0, Color.white, board);
        assertTrue(newKnight.canMove(2, 2));
    }

    /**
     * Test valid horizontal Knight move.
     */
    public void testValidHorizontalKnightMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKnight newKnight = new chessKnight(1, 0, Color.white, board);
        assertTrue(newKnight.canMove(3, 1));
    }

    /**
     * Test invalid Knight move.
     */
    public void testInvalidKnightMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKnight newKnight = new chessKnight(6, 2, Color.white, board);
        assertFalse(newKnight.canMove(5, 1));
    }

    /**
     * Test valid Hopping over blocking piece move.
     */
    public void testBlockingPieceMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKnight newKnight = new chessKnight(1, 0, Color.white, board);
        chessPawn blockingPawn = new chessPawn(1, 1, Color.white, board);
        assertTrue(newKnight.canMove(2, 2));
    }

    /**
     * Test invalid ally at destination.
     */
    public void testInvalidAllyBlockingPieceMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKnight newKnight = new chessKnight(1, 0, Color.white, board);
        chessPawn blockingPawn = new chessPawn(2, 2, Color.white, board);
        assertFalse(newKnight.canMove(2, 2));
    }

}
