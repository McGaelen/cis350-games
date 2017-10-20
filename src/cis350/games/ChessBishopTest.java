package cis350.games;

import cis350.games.chessBishop;
import cis350.games.chessBoard.Color;
import cis350.games.chessPawn;
import cis350.games.chessStandardBoard;
import junit.framework.TestCase;

 /**
 * Tests for the Bishop Piece.
 */
public class ChessBishopTest extends TestCase {
 /**
 * Test valid Bishop move.
 */
  public void testValidBishopMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessBishop newBishop = new chessBishop(5, 3, Color.black, board);
    assertTrue(newBishop.canMove(1, 7));
  }

 /**
 * Test invalid bishop move.
 */
    public void testInvalidBishopMove(){
        chessStandardBoard board = new chessStandardBoard(8,8);
        chessBishop newBishop = new chessBishop(5, 3, Color.white, board);
        assertFalse(newBishop.canMove(4, 5));
    }

    /**
     * Test valid blocking piece move. Piece not in path.
     */
    public void testValidBlockingPieceMove(){
        chessStandardBoard board = new chessStandardBoard(8,8);
        chessBishop testBishop = new chessBishop(1, 0, Color.white, board);
        chessPawn blockingPawn = new chessPawn(1, 1, Color.white, board);
        assertTrue(testBishop.canMove(0, 1));
    }

    /**
     * Test invalid blocking piece move. Bishop getting stopped.
     */
    public void testInvalidBlockingPieceMove(){
        chessStandardBoard board = new chessStandardBoard(8,8);
        chessBishop testBishop = new chessBishop(1, 0, Color.white, board);
        chessPawn blockingPawn = new chessPawn(2, 1, Color.white, board);
        assertFalse(testBishop.canMove(3, 2));
    }
}
