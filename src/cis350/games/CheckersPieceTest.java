package cis350.games;

import cis350.games.checkersBoard.Color;
import junit.framework.TestCase;

/**
 * General tests for all Pieces.
 */
public class CheckersPieceTest extends TestCase {
    /**
     * Testing the capture function and checking final location of pieces.
     */
    public void testEnemyCapture() {
        checkersStandardBoard board = new checkersStandardBoard(8, 8);
        checkersPawn whitePawn
        = new checkersPawn(1, 1, Color.white, board);
        checkersPawn blackPawn
        = new checkersPawn(2, 2, Color.black, board);
        assertTrue(whitePawn.canMove(3, 3));
        whitePawn.executeCaptureOrMove(3, 3);
        assertTrue(whitePawn.xLocation
                == 3 && whitePawn.yLocation == 3);
        assertTrue((board.squaresList[2][2].isOccupied));
    }
    /**
     * Testing out of bounds invalid move.
     */
    public void testInvalidPositiveBoundsMove() {
        checkersStandardBoard board = new checkersStandardBoard(8, 8);
        checkersPawn whitePawn
        = new checkersPawn(7, 7, Color.white, board);
        assertFalse(whitePawn.canMove(8, 8));
    }
    /**
     * Invalid negative out of bounds move.
     */
    public void testInvalidNegativeBoundsMove() {
        checkersStandardBoard board = new checkersStandardBoard(8, 8);
        checkersPawn whitePawn
        = new checkersPawn(0, 1, Color.white, board);
        assertFalse(whitePawn.canMove(0, -1));
    }
}
