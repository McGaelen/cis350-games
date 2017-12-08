package game.suite;

import game.suite.CheckersBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the King Piece.
 *
 */
public class CheckersKingTest extends TestCase {
    /**
    * Test valid vertical move.
    */
    public void testValidKingMove() {
        CheckersStandardBoard board
        = new CheckersStandardBoard(8, 8);
        CheckersKing newKing
        = new CheckersKing(4, 4, Color.white, board);
        assertTrue(newKing.canMove(6, 6));
        assertTrue(newKing.canMove(2, 2));
        assertTrue(newKing.canMove(6, 2));
        assertTrue(newKing.canMove(2, 6));
        assertTrue(newKing.canMove(3, 3));
        assertTrue(newKing.canMove(5, 5));
        assertTrue(newKing.canMove(3, 5));
        assertTrue(newKing.canMove(5, 3));
    }
    /**
     * Test invalid King move.
     */
    public void testInvalidKingMove() {
        CheckersStandardBoard board = new CheckersStandardBoard(8, 8);
        CheckersKing newKing
        = new CheckersKing(3, 7, Color.black, board);
        assertFalse(newKing.canMove(3, 5));
    }

}
