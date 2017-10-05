package cis350.games;

import org.junit.*;
import static junit.framework.Assert.*;

public class ConnectFourBoardTest {

    @Test
    public void testGetBoard() {
        ConnectFourBoard b = new ConnectFourBoard(10, 10);
        assertEquals(b.getBoard().size(), 10);
        assertEquals(b.getRow(0).size(), 10);
    }
}
