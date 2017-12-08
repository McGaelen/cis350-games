package game.suite;

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
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(4, 0, ChessBoard.Color.white, board);
        assertTrue(newKing.canMove(5, 0));
    }

    /**
     * Test valid vertical move.
     */
    public void testValidVerticalKingMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(4, 0, ChessBoard.Color.white, board);
        assertTrue(newKing.canMove(4, 1));
    }

    /**
     * Test valid Diagonal move.
     */
    public void testValidDiagonalKingMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(4, 1, ChessBoard.Color.white, board);
        assertTrue(newKing.canMove(3, 2));
    }

    /**
     * Test invalid King move.
     */
    public void testInvalidKingMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, ChessBoard.Color.black, board);
        assertFalse(newKing.canMove(3, 5));
    }

    /**
     * Test ally piece putting king in check.
     */
    public void testInvalidAllyPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, ChessBoard.Color.black, board);
        ChessPawn allyPawn = new ChessPawn(2, 6, ChessBoard.Color.black, board);
        assertFalse(newKing.canMove(2, 6));
    }

    /**
     * Test king capturing enemy piece.
     */
    public void testValidEnemyPieceMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, ChessBoard.Color.black, board);
        ChessPawn enemyPawn = new ChessPawn(2, 6, ChessBoard.Color.white, board);
        assertTrue(newKing.canMove(2, 6));
    }

    /**
     * Test King putting itself in check.
     */
    public void testInvalidMoveToCheckLocation() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, ChessBoard.Color.black, board);
        newKing = board.getBlackKingTracker();
        ChessPawn enemyPawn = new ChessPawn(5, 5, ChessBoard.Color.white, board);
        assertFalse(newKing.canMove(4, 6));
    }

    /**
     * Test if King displays checked status.
     */
    public void testKingInCheck() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, ChessBoard.Color.black, board);
        ChessPawn enemyPawn = new ChessPawn(4, 6, ChessBoard.Color.white, board);
        assertTrue(newKing.isKingInCheck(newKing));
    }

}
