package cis350.games;

import cis350.games.ChessBoard.Color;
import junit.framework.TestCase;

/**
 * General tests for all Pieces.
 *
 */
public class ChessPieceTest extends TestCase {

    /**
     * Testing the capture function and checking final location of pieces.
     */
    public void testEnemyCapture() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn whitePawn = new ChessPawn(1, 1, Color.white, board);
        ChessPawn blackPawn = new ChessPawn(2, 2, Color.black, board);
        assertTrue(whitePawn.canMove(2, 2));
        whitePawn.executeCaptureOrMove(2, 2);
        assertTrue(whitePawn.getXLocation() == 2 
                && whitePawn.getYLocation() == 2);
    }

    /**
     * Testing out of bounds invalid move.
     */
    public void testInvalidPositiveBoundsMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn whitePawn = new ChessPawn(7, 7, Color.white, board);
        assertFalse(whitePawn.canMove(8, 8));
    }

    /**
     * Invalid negative out of bounds move.
     */
    public void testInvalidNegativeBoundsMove() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessPawn whitePawn = new ChessPawn(0, 1, Color.white, board);
        assertFalse(whitePawn.canMove(0, -1));
    }

    /**
     * Testing the king in danger state on the board for given king.
     */
    public void testKingInDanger() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        ChessKing newKing = new ChessKing(3, 7, Color.black, board);
        newKing = board.getBlackKingTracker();
        ChessPawn allyPawn = new ChessPawn(5, 5, Color.black, board);
        ChessBishop enemyBishop = new ChessBishop(7, 3, Color.white, board);
        assertFalse(allyPawn.canMove(5, 4));
    }

    /**
     * Test to check he fastest checkmate state in the game.
     */
    public void testCheckmate() {
        ChessStandardBoard board = new ChessStandardBoard(8, 8);
        board.populateBoardWithPieces(false);
        ChessPiece wpawn1 = board.getSquaresList()[5][1].getOccupyingPiece();
        wpawn1.executeCaptureOrMove(5, 2);
        assertTrue(wpawn1.getXLocation() == 5 && wpawn1.getYLocation() == 2);
        ChessPiece bpawn1 = board.getSquaresList()[4][6].getOccupyingPiece();
        bpawn1.executeCaptureOrMove(4, 4);
        assertTrue(bpawn1.getXLocation() == 4 && bpawn1.getYLocation() == 4);
        ChessPiece wpawn2 = board.getSquaresList()[6][1].getOccupyingPiece();
        wpawn2.executeCaptureOrMove(6, 3);
        assertTrue(wpawn2.getXLocation() == 6 && wpawn2.getYLocation() == 3);
        ChessPiece bqueen = board.getSquaresList()[3][7].getOccupyingPiece();
        bqueen.executeCaptureOrMove(7, 3);
        assertTrue(bqueen.getXLocation() == 7 && bqueen.getYLocation() == 3);
        ChessKing wking = (ChessKing) board
                .getSquaresList()[4][0].getOccupyingPiece();
        assertTrue(wking.isKingInCheck(wking));
        assertTrue(wking.isKingCheckmate(wking));
    }
}
