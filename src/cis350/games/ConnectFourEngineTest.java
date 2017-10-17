package cis350.games;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.*;

public class ConnectFourEngineTest {

    ConnectFourEngine game;

    @Before
    public void setUp() {
        game = new ConnectFourEngine(10, 9, 1);
    }

    @Test
    public void testGetRows() {
        assertEquals( (Integer)10, game.getRows());
    }

    @Test
    public void testGetCols() {
        assertEquals( (Integer)9, game.getCols());
    }

    @Test
    public void testGetCellOwner() throws Exception {
        game.placeChip(1);
        assertEquals((Integer)1, game.getCellOwner(9, 0));
    }

    @Test
    public void testGetTurn() {
        assertEquals( (Integer)1, game.getTurn());
    }

    @Test
    public void testPlaceChip() throws Exception {
        ConnectFourEngine turntest = new ConnectFourEngine(5, 5, 1);
        turntest.placeChip(1);
        ConnectFourBoard expected = new ConnectFourBoard(5, 5);
        expected.placeChipForPlayer(1, 1);
        assertEquals(expected.toString(), turntest.toString());
    }

    @Test(expected = Exception.class)
    public void testPlaceInvalidChip() throws Exception {
        game.placeChip(0);
    }

    @Test
    public void testAdvanceTurnOneToTwo() {
        game.advanceTurn();
        assertEquals( (Integer)2, game.getTurn());
    }

    @Test
    public void testAdvanceTurnTwoToOne() {
        game.advanceTurn();
        game.advanceTurn();
        assertEquals( (Integer)1, game.getTurn());
    }

    @Test
    public void testReset() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        game.reset();
        ConnectFourBoard expectedboard = new ConnectFourBoard(10, 9);
        assertEquals( expectedboard.toString(), game.toString());
        assertEquals( (Integer)1, game.getTurn());
        assertEquals( (Integer)0, game.getWinner());
        assertEquals("", game.getWinCase());
    }

    @Test
    public void checkHorizontalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        assertEquals(true, game.checkWin());
        assertEquals((Integer)1, game.getWinner());
        assertEquals("Horizontal Win", game.getWinCase());
    }

    @Test
    public void checkNearHorizontalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        assertEquals(false, game.checkWin());
        assertEquals((Integer)0, game.getWinner());
        assertEquals("", game.getWinCase());
    }

    @Test
    public void checkVerticalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(1);
        assertEquals(true, game.checkWin());
        assertEquals((Integer)1, game.getWinner());
        assertEquals("Vertical Win", game.getWinCase());
    }

    @Test
    public void checkDiagonalUpRight() throws Exception {
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(3);
        game.placeChip(4);
        game.placeChip(4);
        game.placeChip(4);
        game.advanceTurn();
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        System.out.println(game);
        assertEquals(true, game.checkWin());
        assertEquals((Integer)2, game.getWinner());
        assertEquals("Diagonal (Up and to the Right) Win", game.getWinCase());
    }
//
//    @Test
//    public void testSave() throws IOException {
//        File f = new File("out");
//        game.save(f);
//        assertEquals(true, f.exists());
//    }
//
//    @Test
//    public void testLoad() {
//
//    }
}
