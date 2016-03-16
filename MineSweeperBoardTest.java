package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 *  The test class for MineSweeperBoard.
 *  Tests all the methods in MineSweeperBoard.
 *
 *  @author Cameron Byers
 *  @version Sep 11, 2014
 */
public class MineSweeperBoardTest extends student.TestCase
{
    /**
     * The Mine Sweeper board.
     */
    MineSweeperBoard board;

    /**
     * This is the constructor.
     */
    public void setUp()
    {
        board = new MineSweeperBoard(4, 4, 1);
    }

    // ----------------------------------------------------------
    /**
     * Sees if the board is what is should be after a method.
     * @param theBoard is the playing board.
     * @param expected is what to expect the board to look like.
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected[0].length(), expected.length, 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard);
    }

    // ----------------------------------------------------------
    /**
     * Tests the height method.
     */
    public void testHeight()
    {
        assertEquals(4, board.height());
    }

    // ----------------------------------------------------------
    /**
     * Tests the width method.
     */
    public void testWidth()
    {
        assertEquals(4, board.width());
    }

    // ----------------------------------------------------------
    /**
     * Tests the setCell method.
     */
    public void testSetCell()
    {
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        assertBoard(board, "OOOO",
                           "OOOO",
                           "OMOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Tests the getCell method.
     */
    public void testGetCell()
    {
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(MineSweeperCell.MINE, board.getCell(1, 2));
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-1, 2));
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(4, 2));
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(2, -1));
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(2, 4));
    }

    // ----------------------------------------------------------
    /**
     * Tests the flagCell method.
     */
    public void testFlagCell()
    {
        board.loadBoardState("OOOO",
                             "OFOO",
                             "O+OO",
                             "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "OOOO",
                           "OOOO",
                           "O+OO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "OOOO",
                             "OOOO",
                             "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "OOOO",
                           "OFOO",
                           "OOOO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "O+OO",
                             "OOOO",
                             "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "OOOO",
                           "OMOO",
                           "OOOO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "OMOO",
                             "OOOO",
                             "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "OOOO",
                           "O+OO",
                           "OOOO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "O OO",
                             "OOOO",
                             "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "OOOO",
                           "O OO",
                           "OOOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Tests the isGameLost method.
     */
    public void testIsGameLost()
    {
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O*OO",
                             "OOOO");
        assertEquals(true, board.isGameLost());
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(false, board.isGameLost());
    }

    // ----------------------------------------------------------
    /**
     * Tests isGameWon method.
     */
    public void testIsGameWon()
    {
        board.loadBoardState("    ",
                             "111 ",
                             "1M1 ",
                             "111 ");
        assertEquals(true, board.isGameWon());
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(false, board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Tests the numberOfAdjacentMines method.
     */
    public void testNumberOfAdjacentMines()
    {
        board.loadBoardState("OOOO",
                             "O+OO",
                             "OOOO",
                             "OOOO");
        assertEquals(0, board.numberOfAdjacentMines(-1, -1));
        board.loadBoardState("OOOO",
                             "O+OO",
                             "OOOO",
                             "OOOO");
        assertEquals(0, board.numberOfAdjacentMines(0, 3));
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        assertEquals(1, board.numberOfAdjacentMines(2, 3));
        board.loadBoardState("OOOO",
                             "++OO",
                             "OOOO",
                             "OOOO");
        assertEquals(2, board.numberOfAdjacentMines(0, 0));
        board.loadBoardState("O+OO",
                             "++OO",
                             "OOOO",
                             "OOOO");
        assertEquals(3, board.numberOfAdjacentMines(0, 0));
        board.loadBoardState("OOOO",
                             "O+++",
                             "O+OO",
                             "OOOO");
        assertEquals(4, board.numberOfAdjacentMines(2, 2));
        board.loadBoardState("OOOO",
                             "O+++",
                             "O+O+",
                             "OOOO");
        assertEquals(5, board.numberOfAdjacentMines(2, 2));
        board.loadBoardState("O+++",
                             "O+O+",
                             "OOO+",
                             "OOOO");
        assertEquals(6, board.numberOfAdjacentMines(2, 1));
        board.loadBoardState("OOOO",
                             "O+++",
                             "O+OO",
                             "O+++");
        assertEquals(7, board.numberOfAdjacentMines(2, 2));
        board.loadBoardState("OOOO",
                             "+++O",
                             "+O+O",
                             "+++O");
        assertEquals(8, board.numberOfAdjacentMines(1, 2));
    }

    // ----------------------------------------------------------
    /**
     * Tests the revealBoard method.
     */
    public void testRevealBoard()
    {
        board.loadBoardState("OOOO",
                             "OOOO",
                             "OOOO",
                             "OOO+");
        board.revealBoard();
        assertBoard(board, "    ",
                           "    ",
                           "  11",
                           "  1*");
    }

    // ----------------------------------------------------------
    /**
     * Tests the uncoverCell method.
     */
    public void testUncoverCell()
    {
        board.loadBoardState("OOO+",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        board.uncoverCell(2, 1);
        assertBoard(board, "OOO+",
                           "OO2O",
                           "O+OO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O+OO",
                             "OOOO");
        board.uncoverCell(1, 2);
        assertBoard(board, "OOOO",
                           "OOOO",
                           "O*OO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "OOOO",
                             "OFOO",
                             "OOOO");
        board.uncoverCell(1, 2);
        assertBoard(board, "OOOO",
                           "OOOO",
                           "OFOO",
                           "OOOO");
        board.loadBoardState("OOOO",
                             "OOOO",
                             "OMOO",
                             "OOOO");
        board.uncoverCell(1, 2);
        assertBoard(board, "OOOO",
                           "OOOO",
                           "OMOO",
                           "OOOO");
    }
}
