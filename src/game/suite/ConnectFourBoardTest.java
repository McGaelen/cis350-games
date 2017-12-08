package game.suite;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Class for testing the connect 4 board.
 * @author Austin Maley
 *
 */
public class ConnectFourBoardTest {
    /**
     * connect four board.
     */
    private ConnectFourBoard board;

    /**
     * Sets up the board.
     */
    @Before
    public void setUp() {
        board = new ConnectFourBoard(10, 10);
    }

    /**
     * Tests the getBoard method.
     */
    @Test
    public void testGetBoard() {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        assertEquals(10, b.getBoard().size());
        assertEquals(10, b.getRow(1).size());
    }
    /**
     * Tests the getRows method.
     */
    @Test
    public void testGetRows() {
        ConnectFourBoard b = new ConnectFourBoard(90, 2);
        assertEquals((Integer) 90, b.getRows());
    }
    /**
     * Tests the GetCols method.
     */
    @Test
    public void testGetCols() {
        ConnectFourBoard b = new ConnectFourBoard(2, 90);
        assertEquals((Integer) 90, b.getCols());
    }
    /**
     * Tests the PlaceChipColumnBelowOne method.
     * @throws Exception exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnBelowOne() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        b.placeChipForPlayer(0, 1);
    }
    /**
     * Tests the PlaceChipColumnHigherThanMax method.
     * @throws Exception exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnHigherThanMax() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        b.placeChipForPlayer(11, 1);
    }
    /**
     * Tests the PlaceChipColumnFull method.
     * @throws Exception exception
     */
    @Test(expected = Exception.class)
    public void testPlaceChipColumnFull() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2, 1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(1, 1);
    }
    /**
     * Tests the PlaceChip method.
     */
    @Test
    public void testPlaceChip() {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        try {
            b.placeChipForPlayer(1, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals((Integer) 1, b.getRow(9).get(0));
    }
    /**
     * Checks if board is full.
     */
    @Test
    public void testCheckFullTrue() {
        ConnectFourBoard b = new ConnectFourBoard(2, 2);
        try {
            b.placeChipForPlayer(1, 1);
            b.placeChipForPlayer(1, 1);
            b.placeChipForPlayer(2, 1);
            b.placeChipForPlayer(2, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(true, b.checkFull());
    }
    /**
     * Checks to make sure board is not full.
     */
    @Test
    public void testCheckFullFalse() {
        ConnectFourBoard b = new ConnectFourBoard(2, 2);
        try {
            b.placeChipForPlayer(1, 1);
            b.placeChipForPlayer(2, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(false, b.checkFull());
    }
    /**
     * Tests the getRow method.
     * @throws Exception exception
     */
    @Test
    public void testGetRow() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2, 2);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(2, 1);
        assertEquals(expected, b.getRow(1));
    }
    /**
     * Tests the getRowBelowOne method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetRowBelowOne() {
        ConnectFourBoard b = new ConnectFourBoard(2, 2);
        b.getRow(-1);
    }
    /**
     * Makes sure row maximum can't be exceeded.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetRowTooHigh() {
            board.getRow(100);
    }
    /**
     * Tests the toString method.
     * @throws Exception exception
     */
    @Test
    public void testToString() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2, 2);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(2, 2);
        String expected = "\n-  -  \n1  2  \n";
        assertEquals(expected, b.toString());
    }
    /**
     * Tests the toString default.
     */
    @Test
    public void testToStringDefaultStatement() {
        board.getBoard().get(1).add(55);
        assertFalse(board.toString().contains("55"));
    }
    /**
     * Tests objects equal the same object.
     */
    @Test
    public void testEqualsSameObj() {
        assertEquals(true, board.equals(board));
    }
    /**
     * Tests equals null.
     */
    @Test
    public void testEqualsNull() {
        assertEquals(false, board.equals(null));
    }
    /**
     * Tests objects don't equal different objects.
     */
    @Test
    public void testEqualsUnlikeObjects() {
        assertEquals(false, board.equals((Integer) 1));
    }
    /**
     * Tests the equals method.
     */
    @Test
    public void testEqualsTrue() {
        ConnectFourBoard other = new ConnectFourBoard(10, 10);
        assertEquals(true, board.equals(other));
    }
    /**
     * Test to make sure different rows don't equal each other.
     */
    @Test
    public void testEqualsUnlikeRows() {
        ConnectFourBoard other = new ConnectFourBoard(9, 10);
        assertFalse(board.equals(other));
    }
    /**
     * Test to make sure different columns don't equal each other.
     */
    @Test
    public void testEqualsUnlikeCols() {
        ConnectFourBoard other = new ConnectFourBoard(10, 9);
        assertFalse(board.equals(other));
    }
    /**
     * Test to make sure different boards don't equal each other..
     */
    @Test
    public void testEqualsUnlikeBoards() {
        ConnectFourBoard other = new ConnectFourBoard(10, 10);
        other.getBoard().get(1).add(1);
        assertFalse(board.equals(other));
    }
    /**
     * Test the hash codes.
     */
    @Test
    public void testHashCode() {
        ConnectFourBoard other = new ConnectFourBoard(10, 10);
        assertTrue(board.equals(other) && other.equals(board));
        assertTrue(board.hashCode() == other.hashCode());
    }
}
