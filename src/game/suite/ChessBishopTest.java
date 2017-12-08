package game.suite;

import game.suite.ChessBoard.Color;
import junit.framework.TestCase;

 /**
 * Tests for the Bishop Piece.
 */
public class ChessBishopTest extends TestCase {
 /**
 * Test valid Bishop move.
 */
  public void testValidBishopMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessBishop newBishop = new ChessBishop(5, 3, Color.black, board);
    assertTrue(newBishop.canMove(1, 7));
  }

 /**
 * Test invalid bishop move.
 */
    public void testInvalidBishopMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessBishop newBishop = new ChessBishop(5, 3, Color.white, board);
        assertFalse(newBishop.canMove(4, 5));
    }

    /**
     * Test valid blocking piece move. Piece not in path.
     */
    public void testValidBlockingPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessBishop testBishop = new ChessBishop(1, 0, Color.white, board);
        ChessPawn blockingPawn = new ChessPawn(2, 1, Color.white, board);
        assertTrue(testBishop.canMove(0, 1));
    }

    /**
     * Test invalid blocking piece move. Bishop getting stopped.
     */
    public void testInvalidBlockingPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessBishop testBishop = new ChessBishop(1, 0, Color.white, board);
        ChessPawn blockingPawn = new ChessPawn(2, 1, Color.white, board);
        assertFalse(testBishop.canMove(3, 2));
    }
}
