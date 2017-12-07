package cis350.games;

import cis350.games.CheckersBoard.Color;
import junit.framework.TestCase;

/**
 * General tests for all Pieces.
 */
public class CheckersPieceTest extends TestCase {
    /**
     * Testing the capture function and checking final location of pieces.
     */
    public void testEnemyCapture() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersPawn whitePawn
        = new CheckersPawn(1, 1, Color.white, board);
        CheckersPawn blackPawn
        = new CheckersPawn(2, 2, Color.black, board);
        assertTrue(whitePawn.canMove(3, 3));
        whitePawn.executeCaptureOrMove(3, 3);
        assertTrue(whitePawn.getXLocation()
                == 3 && whitePawn.getYLocation() == 3);
        assertTrue((board.getSquaresList()[2][2].getIsOccupied()));
    }
    /**
     * Testing out of bounds invalid move.
     */
    public void testInvalidPositiveBoundsMove() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersPawn whitePawn
        = new CheckersPawn(7, 7, Color.white, board);
        assertFalse(whitePawn.canMove(8, 8));
    }
    /**
     * Invalid negative out of bounds move.
     */
    public void testInvalidNegativeBoundsMove() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersPawn whitePawn
        = new CheckersPawn(0, 1, Color.white, board);
        assertFalse(whitePawn.canMove(0, -1));
    }
}
