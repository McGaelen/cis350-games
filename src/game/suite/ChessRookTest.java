package game.suite;

import game.suite.ChessBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the Rook.

 *
 */
public class ChessRookTest extends TestCase {

  /**
* Test valid vertical Rook move.
*/
  public void testValidVerticalRookMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(0, 0, Color.white, board);
    assertTrue(newRook.canMove(0, 6));
  }

  /**
* Test valid horizontal rook move.
*/
  public void testValidHorizontalRookMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(0, 0, Color.white, board);
    assertTrue(newRook.canMove(7, 0));
  }

  /**
* Test invalid rook move.
*/
  public void testInvalidRookMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(1, 5, Color.black, board);
    assertFalse(newRook.canMove(7, 0));
  }

  /**
* Test invalid ally at destination piece move.
*/
  public void testInvalidAllyPieceMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(0, 0, Color.black, board);
    ChessRook allyRook = new ChessRook(4, 0, Color.black, board);
    assertFalse(newRook.canMove(4, 0));
  }

  /**
 * Test valid enemy capture move.
 */
  public void testValidEnemyPieceMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(0, 0, Color.black, board);
    ChessRook enemyRook = new ChessRook(4, 0, Color.white, board);
    assertTrue(newRook.canMove(4, 0));
  }

  /**
 * Test obstacle in path move.
 */
  public void testBlockingPieceMove() {
    ChessStandardBoard board = new ChessStandardBoard(8, 8);
    ChessRook newRook = new ChessRook(2, 1, Color.white, board);
    ChessPawn blockingPawn = new ChessPawn(4, 1, Color.white, board);
    assertFalse(newRook.canMove(6, 1));
  }

}
