package cis350.games;

import cis350.games.chessBoard.Color;
import junit.framework.TestCase;

/**
 * Test cases for the King.
 *
 */
public class ChessKingTest extends TestCase {

    /**
     * Test valid horizontal move.
     */
    public void testValidHorizontalKingMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(4, 0, Color.white, board);
        assertTrue(newKing.canMove(5, 0));
    }

    /**
     * Test valid vertical move.
     */
    public void testValidVerticalKingMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(4, 0, Color.white, board);
        assertTrue(newKing.canMove(4, 1));
    }

    /**
     * Test valid Diagonal move.
     */
    public void testValidDiagonalKingMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(4, 1, Color.white, board);
        assertTrue(newKing.canMove(3, 2));
    }

    /**
     * Test invalid King move.
     */
    public void testInvalidKingMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        assertFalse(newKing.canMove(3, 5));
    }

    /**
     * Test ally pice putting king in check.
     */
    public void testInvalidAllyPieceMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        chessPawn allyPawn = new chessPawn(2, 6, Color.black, board);
        assertFalse(newKing.canMove(2, 6));
    }

    /**
     * Test king capturing enemy piece.
     */
    public void testValidEnemyPieceMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        chessPawn enemyPawn = new chessPawn(2, 6, Color.white, board);
        assertTrue(newKing.canMove(2, 6));
    }

    /**
     * Test King putting itself in check.
     */
    public void testInvalidMoveToCheckLocation() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        board.blackKingTracker = newKing;
        chessPawn enemyPawn = new chessPawn(5, 5, Color.white, board);
        assertFalse(newKing.canMove(4, 6));
    }

    /**
     * Test if King displays checked status.
     */
    public void testKingInCheck() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        chessPawn enemyPawn = new chessPawn(4, 6, Color.white, board);
        assertTrue(newKing.isKingInCheck(newKing));
    }

}
