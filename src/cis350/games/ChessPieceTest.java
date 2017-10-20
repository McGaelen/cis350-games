package cis350.games;

import cis350.games.chessBoard.Color;
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
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessPawn whitePawn = new chessPawn(1, 1, Color.white, board);
        chessPawn blackPawn = new chessPawn(2, 2, Color.black, board);
        assertTrue(whitePawn.canMove(2, 2));
        whitePawn.executeCaptureOrMove(2, 2);
        assertTrue(whitePawn.xLocation == 2 && whitePawn.yLocation == 2);
    }

    /**
     * Testing out of bounds invalid move.
     */
    public void testInvalidPositiveBoundsMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessPawn whitePawn = new chessPawn(7, 7, Color.white, board);
        assertFalse(whitePawn.canMove(8, 8));
    }

    /**
     * Invalid negative out of bounds move.
     */
    public void testInvalidNegativeBoundsMove() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessPawn whitePawn = new chessPawn(0, 1, Color.white, board);
        assertFalse(whitePawn.canMove(0, -1));
    }

    /**
     * Testing the king in danger state on the baord for given king.
     */
    public void testKingInDanger() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        chessKing newKing = new chessKing(3, 7, Color.black, board);
        board.blackKingTracker = newKing;
        chessPawn allyPawn = new chessPawn(5, 5, Color.black, board);
        chessBishop enemyBishop = new chessBishop(7, 3, Color.white, board);
        assertFalse(allyPawn.canMove(5, 4));
    }

    /**
     * Test to check he fastest checkmate state in the game.
     */
    public void testCheckmate() {
        chessStandardBoard board = new chessStandardBoard(8, 8);
        board.populateBoardWithPieces(false);
        chessPiece wpawn1 = board.squaresList[5][1].occupyingPiece;
        wpawn1.executeCaptureOrMove(5, 2);
        assertTrue(wpawn1.xLocation == 5 && wpawn1.yLocation == 2);
        chessPiece bpawn1 = board.squaresList[4][6].occupyingPiece;
        bpawn1.executeCaptureOrMove(4, 4);
        assertTrue(bpawn1.xLocation == 4 && bpawn1.yLocation == 4);
        chessPiece wpawn2 = board.squaresList[6][1].occupyingPiece;
        wpawn2.executeCaptureOrMove(6, 3);
        assertTrue(wpawn2.xLocation == 6 && wpawn2.yLocation == 3);
        chessPiece bqueen = board.squaresList[3][7].occupyingPiece;
        bqueen.executeCaptureOrMove(7, 3);
        assertTrue(bqueen.xLocation == 7 && bqueen.yLocation == 3);
        chessKing wking = (chessKing) board.squaresList[4][0].occupyingPiece;
        assertTrue(wking.isKingInCheck(wking));
        assertTrue(wking.isKingCheckmate(wking));
    }
}
