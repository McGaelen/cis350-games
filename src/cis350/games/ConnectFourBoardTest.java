package cis350.games;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.Assert.*;

public class ConnectFourBoardTest {

    private ConnectFourBoard board;

    @Before
    public void setUp() {
        board = new ConnectFourBoard(10,10);
    }

    @Test
    public void testGetBoard() {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        assertEquals(10, b.getBoard().size());
        assertEquals(10, b.getRow(1).size());
    }

    @Test
    public void testGetRows() {
        ConnectFourBoard b = new ConnectFourBoard(90, 2);
        assertEquals( (Integer)90, b.getRows());
    }

    @Test
    public void testGetCols() {
        ConnectFourBoard b = new ConnectFourBoard(2, 90);
        assertEquals( (Integer)90, b.getCols());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnBelowOne() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        b.placeChipForPlayer(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnHigherThanMax() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        b.placeChipForPlayer(11, 1);
    }

    @Test(expected = Exception.class)
    public void testPlaceChipColumnFull() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2,1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(1, 1);
    }

    @Test
    public void testPlaceChip() {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        try {
            b.placeChipForPlayer(1, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals( (Integer)1, b.getRow(9).get(0));
    }

    @Test
    public void testCheckFullTrue() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
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

    @Test
    public void testCheckFullFalse() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        try {
            b.placeChipForPlayer(1, 1);
            b.placeChipForPlayer(2, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(false, b.checkFull());
    }

    @Test
    public void testGetRow() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(1);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(2, 1);
        assertEquals(expected, b.getRow(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRowBelowOne() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        b.getRow(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetRowTooHigh() {
    		board.getRow(100);
    }

    @Test
    public void testToString() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        b.placeChipForPlayer(1, 1);
        b.placeChipForPlayer(2, 2 );
        String expected = "\n-  -  \n1  2  \n";
        assertEquals(expected, b.toString());
    }
    
    @Test
    public void testToStringDefaultStatement() {
    		board.getBoard().get(1).add(55);
    		assertFalse(board.toString().contains("55"));
    }

    @Test
    public void testEqualsSameObj() {
        assertEquals(true, board.equals(board));
    }

    @Test
    public void testEqualsNull() {
        assertEquals(false, board.equals(null));
    }

    @Test
    public void testEqualsUnlikeObjects() {
        assertEquals(false, board.equals((Integer)1));
    }

    @Test
    public void testEqualsTrue() {
        ConnectFourBoard other = new ConnectFourBoard(10, 10);
        assertEquals(true, board.equals(other));
    }
    
    @Test
    public void testEqualsUnlikeRows() {
    		ConnectFourBoard other = new ConnectFourBoard(9,10);
    		assertFalse(board.equals(other));
    }
    
    @Test
    public void testEqualsUnlikeCols() {
    		ConnectFourBoard other = new ConnectFourBoard(10,9);
    		assertFalse(board.equals(other));
    }
    
    @Test
    public void testEqualsUnlikeBoards() {
    		ConnectFourBoard other = new ConnectFourBoard(10,10);
    		other.getBoard().get(1).add(1);
    		assertFalse(board.equals(other));
    }

    @Test
    public void testHashCode() {
        ConnectFourBoard other = new ConnectFourBoard(10,10);
        assertTrue(board.equals(other) && other.equals(board));
        assertTrue(board.hashCode() == other.hashCode());
    }
}
