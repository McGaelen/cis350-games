package cis350.games;

import cis350.games.CheckersBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the Pawn.
 */
public class CheckersPawnTest extends TestCase {
    /**
     * Test valid white pawn move.
     */
    public void testValidWhitePawnMove() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersPawn newPawn
        = new CheckersPawn(1, 1, Color.white, board);
        assertTrue(newPawn.canMove(2, 2));
        assertTrue(newPawn.canMove(0, 2));
    }
    /**
     * Test valid black pawn move.
     */
    public void testValidBlackPawnMove() {
        CheckersStandardBoard board
        = new CheckersStandardBoard(8, 8);
        CheckersPawn newPawn
        = new CheckersPawn(4, 4, Color.black, board);
        assertTrue(newPawn.canMove(3, 3));
        assertTrue(newPawn.canMove(5, 3));
    }
    /**
     * Test invalid pawn move.
     */
    public void testInvalidPawnMove() {
        CheckersStandardBoard board
        = new CheckersStandardBoard(8, 8);
        CheckersPawn newPawn
        = new CheckersPawn(1, 4, Color.black, board);
        assertFalse(newPawn.canMove(1, 6));
    }
    /**
     * Test valid enemy piece move/capture.
     */
    public void testValidEnemyPieceMove() {
        CheckersStandardBoard board
        = new CheckersStandardBoard(8, 8);
        CheckersPawn newPawn
        = new CheckersPawn(2, 2, Color.white, board);
        CheckersPawn enemyPawn
        = new CheckersPawn(3, 3, Color.black, board);
        assertTrue(newPawn.canMove(4, 4));
    }
    /**
     * Test invalid enemy piece move/capture.
     */
    public void testInvalidEnemyPieceMove() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersPawn newPawn
        = new CheckersPawn(2, 2, Color.white, board);
        CheckersPawn enemyPawn
        = new CheckersPawn(3, 3, Color.black, board);
        CheckersPawn enemyPawn2
        = new CheckersPawn(4, 4, Color.black, board);
        assertFalse(newPawn.canMove(4, 4));
    }
}
