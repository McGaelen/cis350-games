package game.suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/***********************************************************************
 * Class to test TicTacToe. Runs through the constructor and methods
 * to make sure they are functioning properly. Makes sure that errors
 * are correctly being thrown.
 * @author Edric Lin
 * @version 10/18/17
 **********************************************************************/

public class TicTacToeTest {

	/**
	 * test the game constructor.
	 */
    @Test
    public void testConstructor() {
        TicTacToe game = new TicTacToe();
        String[][] board = game.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(board[i][j], " ");
            }
        }
    }

    /**
     * test isValidMove with occupied space.
     */
    @Test
    public void testIsValidMove() {
        TicTacToe game = new TicTacToe();

        String[][] board = {
                {"X", "X", "O" },
                {"O", "O", "X" },
                {"X", "X", "O" },
        };

        game.setBoard(board);

        assertFalse(game.isValidMove(0, 0));
    }

    /**
     * test isValidMove with out of bounds moves.
     */
    @Test
    public void testIsValidMove1() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.isValidMove(-100, 0));
        assertFalse(game.isValidMove(100, 0));
    }

    /**
     * test isValidMove with all valid moves.
     */
    @Test
    public void testIsValidMove2() {
        TicTacToe game = new TicTacToe();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(game.isValidMove(i, j));
            }
        }
    }

    /**
     * test move with occupied space.
     */
    @Test
    (expected = IllegalArgumentException.class)
    public void testMove() {
        TicTacToe game = new TicTacToe();

        String[][] board = {
                {"X", "X", "O" },
                {"O", "O", "X" },
                {"X", "X", "O" },
        };

        game.setBoard(board);
        game.move(0, 0, "X");
    }

    /**
     *  test move with out of bounds move.
     */
    @Test
    (expected = IllegalArgumentException.class)
    public void testMove2() {
        TicTacToe game = new TicTacToe();
        game.move(0, -100, "X");
    }

    /**
     * test move with out of bounds move.
     */
    @Test
    (expected = IllegalArgumentException.class)
    public void testMove3() {
        TicTacToe game = new TicTacToe();
        game.move(0, 100, "X");
    }

    /**
     * test move with out of bounds moves.
     */
    @Test
    public void testMove4() {
        TicTacToe game = new TicTacToe();
        String[][] board = game.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.move(i, j, "X");
                assertEquals(board[i][j], "X");
            }
        }
    }

    /**
     * test isWinner with no win state.
     */
    @Test
    public void testIsWinner() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.isWinner());

        String[] players = {
                "X", "O", "X", "X", "X", "O", "O", "X", "O"};
        int move = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.move(i, j, players[move]);
                assertFalse(game.isWinner());
                move++;
            }
        }
    }

    /**
     * test isWinner with all win states using "X".
     */
    @Test
    public void testIsWinner2() {

        int[][][] winLines = {

                // horizontal win
                {{0, 0}, {0, 1}, {0, 2} },
                {{1, 0}, {1, 1}, {1, 2} },
                {{2, 0}, {2, 1}, {2, 2} },

                // vertical win
                {{0, 0}, {1, 0}, {2, 0} },
                {{0, 1}, {1, 1}, {2, 1} },
                {{0, 2}, {1, 2}, {2, 2} },

                // diagonal win
                {{0, 0}, {1, 1}, {2, 2} },
                {{2, 0}, {1, 1}, {0, 2} },
        };

        // go through each win line (8 total)
        for (int i = 0; i < 8; i++) {

            // start new game after each win line
            TicTacToe game = new TicTacToe();

            // play each mark (3 marks for a win)
            for (int j = 0; j < 3; j++) {
                game.move(
                        winLines[i][j][0],
                        winLines[i][j][1], "X");
            }

            // check win
            assertTrue(game.isWinner());
        }
    }

    /**
     * test isWinner with all win states using "O".
     */
    @Test
    public void testIsWinner3() {

        int[][][] winLines = {

                // horizontal win
                {{0, 0}, {0, 1}, {0, 2} },
                {{1, 0}, {1, 1}, {1, 2} },
                {{2, 0}, {2, 1}, {2, 2} },

                // vertical win
                {{0, 0}, {1, 0}, {2, 0} },
                {{0, 1}, {1, 1}, {2, 1} },
                {{0, 2}, {1, 2}, {2, 2} },

                // diagonal win
                {{0, 0}, {1, 1}, {2, 2} },
                {{2, 0}, {1, 1}, {0, 2} },
        };

        // go through each win line (8 total)
        for (int i = 0; i < 8; i++) {

            // start new game after each win line
            TicTacToe game = new TicTacToe();

            // play each mark (3 marks for a win)
            for (int j = 0; j < 3; j++) {
                game.move(winLines[i][j][0],
                        winLines[i][j][1], "O");
            }

            // check win
            assertTrue(game.isWinner());
        }
    }

    /**
     * test isTie with no tie.
     */
    @Test
    public void testIsTie() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.isTie());

        game.move(0, 0, "X");
        assertFalse(game.isTie());
    }

    /**
     * test isTie with tie (full board).
     */
    @Test
    public void testIsTie2() {
        TicTacToe game = new TicTacToe();

        String[][] board = {
                {"X", "X", "O" },
                {"O", "O", "X" },
                {"X", "X", "O" },
        };

        game.setBoard(board);
        assertTrue(game.isTie());
    }
}
