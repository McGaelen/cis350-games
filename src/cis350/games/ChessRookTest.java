package cis350.games;

import cis350.games.chessBoard.Color;
import cis350.games.chessPawn;
import cis350.games.chessRook;
import cis350.games.chessStandardBoard;
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
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(0, 0, Color.white, board);
    assertTrue(newRook.canMove(0, 6));
  }

  /**
* Test valid horizontal rook move.
*/
  public void testValidHorizontalRookMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(0, 0, Color.white, board);
    assertTrue(newRook.canMove(7, 0));
  }

  /**
* Test invalid rook move.
*/
  public void testInvalidRookMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(1, 5, Color.black, board);
    assertFalse(newRook.canMove(7, 0));
  }

  /**
* Test invalid ally at destination piece move.
*/
  public void testInvalidAllyPieceMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(0, 0, Color.black, board);
    chessRook allyRook = new chessRook(4, 0, Color.black, board);
    assertFalse(newRook.canMove(4, 0));
  }

  /**
 * Test valid enemy capture move.
 */
  public void testValidEnemyPieceMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(0, 0, Color.black, board);
    chessRook enemyRook = new chessRook(4, 0, Color.white, board);
    assertTrue(newRook.canMove(4, 0));
  }

  /**
 * Test obstacle in path move.
 */
  public void testBlockingPieceMove() {
    chessStandardBoard board = new chessStandardBoard(8,8);
    chessRook newRook = new chessRook(2, 1, Color.white, board);
    chessPawn blockingPawn = new chessPawn(4, 1, Color.white, board);
    assertFalse(newRook.canMove(6, 1));
  }

}
