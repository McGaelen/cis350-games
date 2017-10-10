package cis350.games;

import org.junit.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class ConnectFourBoardTest {

    @Test
    public void testGetBoard() {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        assertEquals(10, b.getBoard().size());
        assertEquals(10, b.getRow(1).size());
    }

    @Test
    public void testGetRows() {
        ConnectFourBoard b = new ConnectFourBoard(90, 2);
        assertEquals(new Integer(90), b.getRows());
    }

    @Test
    public void testGetCols() {
        ConnectFourBoard b = new ConnectFourBoard(2, 90);
        assertEquals(new Integer(90), b.getCols());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnBelowOne() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        b.placeChip(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceChipColumnHigherThanMax() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        b.placeChip(11, 1);
    }

    @Test(expected = Exception.class)
    public void testPlaceChipColumnFull() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2,1);
        b.placeChip(1, 1);
        b.placeChip(1, 1);
        b.placeChip(1, 1);
    }

    @Test
    public void testPlaceChip() {
        ConnectFourBoard b = new ConnectFourBoard(10,10);
        try {
            b.placeChip(1, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(new Integer(1), b.getRow(9).get(0));
    }

    @Test
    public void testCheckFullTrue() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        try {
            b.placeChip(1, 1);
            b.placeChip(1, 1);
            b.placeChip(2, 1);
            b.placeChip(2, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(true, b.checkFull());
    }

    @Test
    public void testCheckFullFalse() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        try {
            b.placeChip(1, 1);
            b.placeChip(2, 1);
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
        b.placeChip(1, 1);
        b.placeChip(2, 1);
        assertEquals(expected, b.getRow(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRowBelowOne() {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        b.getRow(-1);
    }

    @Test
    public void testToString() throws Exception {
        ConnectFourBoard b = new ConnectFourBoard(2,2);
        b.placeChip(1, 1);
        b.placeChip(2, 2 );
        String expected = "\n-  -  \n1  2  \n";
        assertEquals(expected, b.toString());
    }
}
