package cis350.games;

import org.junit.Before; 
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Class to test connect four engine.
 * @author Gaelen
 */
public class ConnectFourEngineTest {

	/** game to test. */
    private ConnectFourEngine game;

    /**
     * set up game for test.
     */
    @Before
    public void setUp() {
        game = new ConnectFourEngine(10, 9, 1);
    }

    /**
     * test getRows.
     */
    @Test
    public void testGetRows() {
        assertEquals((Integer) 10, game.getRows());
    }

    /**
     * test getCols.
     */
    @Test
    public void testGetCols() {
        assertEquals((Integer) 9, game.getCols());
    }

    /**
     * test getCellOwner.
     * @throws Exception 
     */
    @Test
    public void testGetCellOwner() throws Exception {
        game.placeChip(1);
        assertEquals((Integer) 1, game.getCellOwner(9, 0));
    }

    /**
     * 
     */
    @Test
    public void testGetTurn() {
        assertEquals((Integer) 1, game.getTurn());
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
        assertEquals((Integer) 2, game.getTurn());
    }

    @Test
    public void testAdvanceTurnTwoToOne() {
        game.advanceTurn();
        game.advanceTurn();
        assertEquals((Integer) 1, game.getTurn());
    }

    @Test
    public void testReset() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        game.reset();
        ConnectFourBoard expectedboard = new ConnectFourBoard(10, 9);
        assertEquals(expectedboard.toString(), game.toString());
        assertEquals((Integer) 1, game.getTurn());
        assertEquals((Integer) 0, game.getWinner());
        assertEquals("", game.getWinCase());
    }

    @Test
    public void testCheckHorizontalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        assertEquals(true, game.checkWin());
        assertEquals((Integer) 1, game.getWinner());
        assertEquals("Horizontal Win", game.getWinCase());
    }

    @Test
    public void testCheckNearHorizontalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        assertEquals(false, game.checkWin());
        assertEquals((Integer) 0, game.getWinner());
        assertEquals("", game.getWinCase());
    }

    @Test
    public void testCheckVerticalWin() throws Exception {
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(1);
        assertEquals(true, game.checkWin());
        assertEquals((Integer) 1, game.getWinner());
        assertEquals("Vertical Win", game.getWinCase());
    }

    @Test
    public void testCheckDiagonalUpRightWin() throws Exception {
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
        assertEquals(true, game.checkWin());
        assertEquals((Integer) 2, game.getWinner());
        assertEquals("Diagonal (Up and to the Right) Win", game.getWinCase());
    }

    @Test
    public void testCheckWinHitsColumnLimit() throws Exception {
            game.placeChip(9);
            game.placeChip(8);
            game.placeChip(7);
            assertEquals(false, game.checkWin());
    }

    @Test
    public void testCheckDiagonalUpLeftWin() throws Exception {
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(2);
        game.placeChip(3);
        game.advanceTurn();
        game.placeChip(1);
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        System.out.println(game);
        assertEquals(true, game.checkWin());
        assertEquals((Integer) 2, game.getWinner());
        assertEquals("Diagonal (Up and to the Left) Win", game.getWinCase());
    }

    @Test
    public void testCheckDiagonalUpLeftTopOfBoardLimit() throws Exception {
        ConnectFourEngine g = new ConnectFourEngine(3, 4, 1);
        g.placeChip(1);
        g.placeChip(1);
        g.placeChip(1);
        g.placeChip(2);
        g.placeChip(2);
        g.placeChip(3);
        g.advanceTurn();
        g.placeChip(2);
        g.placeChip(3);
        g.placeChip(4);
        System.out.println(g);
        assertEquals(false, g.checkWin());
    }

    @Test
    public void testEquals() throws Exception {
        ConnectFourEngine rowsDiff = new ConnectFourEngine(32, 9, 1);
        ConnectFourEngine colsDiff = new ConnectFourEngine(10, 32, 1);
        ConnectFourEngine equal = new ConnectFourEngine(10, 9, 1);
        assertTrue(game.equals(equal));
        assertTrue(game.equals(game));
        assertFalse(game.equals(null));
        assertFalse(game.equals(new Integer(10)));
        assertFalse(game.equals(rowsDiff));
        assertFalse(game.equals(colsDiff));
        game.placeChip(1);
        assertFalse(game.equals(equal));
        game.advanceTurn();
        assertFalse(game.equals(equal));
        game.advanceTurn();
        game.placeChip(2);
        game.placeChip(3);
        game.placeChip(4);
        game.checkWin();
        assertFalse(game.equals(equal));
    }

    @Test
    public void testHashCode() {
        ConnectFourEngine other = new ConnectFourEngine(10, 9, 1);
        assertTrue(game.equals(other) && other.equals(game));
        assertTrue(game.hashCode() == other.hashCode());
    }

    @Test
    public void testSave() throws IOException {
        File f = new File("testSaveOut");
        game.save(f);
        assertEquals(true, f.exists());
        Files.deleteIfExists(Paths.get("testSaveOut"));
    }

    @Test
    public void testLoad() throws Exception {
        File f = new File("testLoadOut");
        game.save(f);
        ConnectFourEngine load = ConnectFourEngine.load(f);
        assertTrue(game.equals(load));
        Files.deleteIfExists(Paths.get("testLoadOut"));
    }
    
    @Test
    public void testCheckFull() {
    		assertFalse(game.checkFull());
    }
    
    @Test
    public void testCheckFullTrue() throws Exception {
    		game = new ConnectFourEngine(2, 2, 1);
    		game.placeChip(1);
    		game.placeChip(1);
    		game.placeChip(2);
    		game.placeChip(2);
    		assertTrue(game.checkFull());
    }
    
//    @Test
//    public void testCheckTurnAchievementNotWon() {
//    		
//    }
    
//    @Test
//    public void testCheckTurnAchievement() throws Exception {
//    		game.placeChip(1);
//    		game.advanceTurn();
//    		game.placeChip(1);
//    		game.advanceTurn();
//		game.placeChip(2);
//		game.advanceTurn();
//		game.placeChip(2);
//		game.advanceTurn();
//		game.placeChip(3);
//		game.advanceTurn();
//		game.placeChip(3);
//		game.advanceTurn();
//		game.placeChip(4);
//		game.advanceTurn();
//		game.placeChip(4);
//		game.advanceTurn();
//		
//    }
}
