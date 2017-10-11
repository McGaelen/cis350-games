package cis350.games;

import org.junit.Before;
import org.junit.Test;
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
        game.advanceTurn();
        game.checkWin();
        game.reset();
        ConnectFourBoard expectedboard = new ConnectFourBoard(10, 9);
        assertEquals( expectedboard.toString(), game.toString());
        assertEquals( (Integer)1, game.getTurn());
        assertEquals( (Integer)0, game.getWinner());
        assertEquals("", game.getWinCase());
    }

}
