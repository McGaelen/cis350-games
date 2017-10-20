package cis350.games;

import cis350.games.checkersBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the Pawn.
 */
public class CheckersPawnTest extends TestCase {
    /**
     * Test valid white pawn move.
     */
    public void testValidWhitePawnMove() {
        checkersStandardBoard board = new checkersStandardBoard(8, 8);
        checkersPawn newPawn
        = new checkersPawn(1, 1, Color.white, board);
        assertTrue(newPawn.canMove(2, 2));
        assertTrue(newPawn.canMove(0, 2));
    }
    /**
     * Test valid black pawn move.
     */
    public void testValidBlackPawnMove() {
        checkersStandardBoard board
        = new checkersStandardBoard(8, 8);
        checkersPawn newPawn
        = new checkersPawn(4, 4, Color.black, board);
        assertTrue(newPawn.canMove(3, 3));
        assertTrue(newPawn.canMove(5, 3));
    }
    /**
     * Test invalid pawn move.
     */
    public void testInvalidPawnMove() {
        checkersStandardBoard board
        = new checkersStandardBoard(8, 8);
        checkersPawn newPawn
        = new checkersPawn(1, 4, Color.black, board);
        assertFalse(newPawn.canMove(1, 6));
    }
    /**
     * Test valid enemy piece move/capture.
     */
    public void testValidEnemyPieceMove() {
        checkersStandardBoard board
        = new checkersStandardBoard(8, 8);
        checkersPawn newPawn
        = new checkersPawn(2, 2, Color.white, board);
        checkersPawn enemyPawn
        = new checkersPawn(3, 3, Color.black, board);
        assertTrue(newPawn.canMove(4, 4));
    }
    /**
     * Test invalid enemy piece move/capture.
     */
    public void testInvalidEnemyPieceMove() {
        checkersStandardBoard board = new checkersStandardBoard(8, 8);
        checkersPawn newPawn
        = new checkersPawn(2, 2, Color.white, board);
        checkersPawn enemyPawn
        = new checkersPawn(3, 3, Color.black, board);
        checkersPawn enemyPawn2
        = new checkersPawn(4, 4, Color.black, board);
        assertFalse(newPawn.canMove(4, 4));
    }
}
