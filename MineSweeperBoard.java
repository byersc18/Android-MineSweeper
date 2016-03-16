package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 *  This is the board of the Mine Sweeper game.
 *  Controls the actions of the board.
 *
 *  @author Cameron Byers
 *  @version Sep 10, 2014
 */
public class MineSweeperBoard extends MineSweeperBoardBase
{
    private int columns;
    private int rows;
    private int numBombs;
    private MineSweeperCell[][] board;

    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoard object.
     * Initializes board with covered cells and places mines.
     * @param x number of columns
     * @param y number of rows
     * @param bombs is number of bombs
     */
    public MineSweeperBoard(int x, int y, int bombs)
    {
        setColumns(x);
        setRows(y);
        setNumBombs(bombs);
        board = new MineSweeperCell[x][y];

        for (int i = 0; i < x; i++)
        {
            for (int h = 0; h < y; h++)
            {
                board[i][h] = MineSweeperCell.COVERED_CELL;
            }
        }

        int j = getNumBombs();
        while (j > 0)
        {
            int m = (int)(Math.random() * x);
            int n = (int)(Math.random() * y);
            if (board[m][n] == MineSweeperCell.COVERED_CELL)
            {
                board[m][n] = MineSweeperCell.MINE;
                j--;
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * @return the columns
     */
    public int getColumns()
    {
        return columns;
    }

    // ----------------------------------------------------------
    /**
     * @param columns the columns to set
     */
    public void setColumns(int columns)
    {
        this.columns = columns;
    }

    // ----------------------------------------------------------
    /**
     * @return the rows
     */
    public int getRows()
    {
        return rows;
    }

    // ----------------------------------------------------------
    /**
     * @param rows the rows to set
     */
    public void setRows(int rows)
    {
        this.rows = rows;
    }

    // ----------------------------------------------------------
    /**
     * @return the numBombs
     */
    public int getNumBombs()
    {
        return numBombs;
    }

    // ----------------------------------------------------------
    /**
     * @param numBombs the numBombs to set
     */
    public void setNumBombs(int numBombs)
    {
        this.numBombs = numBombs;
    }

    /**
     * sets a flag on a selected cell
     * @param x is the x coordinate of cell
     * @param y is the y coordinate of cell
     */
    @Override
    public void flagCell(int x, int y)
    {
        if (board[x][y] == MineSweeperCell.FLAG)
        {
            board[x][y] = MineSweeperCell.COVERED_CELL;
        }
        else if (board[x][y] == MineSweeperCell.COVERED_CELL)
        {
            board[x][y] = MineSweeperCell.FLAG;
        }
        else if (board[x][y] == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.FLAGGED_MINE;
        }
        else if (board[x][y] == MineSweeperCell.FLAGGED_MINE)
        {
            board[x][y] = MineSweeperCell.MINE;
        }
    }

    /**
     * gets the value at selected cell
     * @param x is the x coordinate of cell
     * @param y is the y coordinate of cell
     * @return the selected MineSweeperCell
     */
    @Override
    public MineSweeperCell getCell(int x, int y)
    {
        if ((x < 0) || (x >= getColumns()) || (y < 0) ||
            (y >= getRows()))
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else
        {
            return board[x][y];
        }
    }

    /**
     * returns how many rows are in the grid
     * @return the integer of the height of the board
     */
    @Override
    public int height()
    {
        return this.getRows();
    }

    /**
     * checks to see if the game is lost by seeing if there is a uncovered mine
     * @return true if game is lost and false if game is not lost
     */
    @Override
    public boolean isGameLost()
    {
        for (int i = 0; i < getColumns(); i++)
        {
            for (int k = 0; k < getRows(); k++)
            {
                if (board[i][k] == MineSweeperCell.UNCOVERED_MINE)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks to see if the game is won by checking if the number of bombs is
     * is equal to the number of covered cells
     * @return true if game is won and false if it is not
     */
    @Override
    public boolean isGameWon()
    {
        int numCells = (getColumns() * getRows());
        int numUncoveredCells = 0;

        for (int i = 0; i < getColumns(); i++)
        {
            for (int k = 0; k < getRows(); k++)
            {
                if (board[i][k] == MineSweeperCell.ADJACENT_TO_0 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_1 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_2 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_3 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_4 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_5 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_6 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_7 ||
                    board[i][k] == MineSweeperCell.ADJACENT_TO_8)
                {
                    numUncoveredCells++;
                }
            }
        }
        return numUncoveredCells == numCells - getNumBombs();
    }

    /**
     * finds the number of mines around a selected cell by first seeing if that
     * cell is on the board and then checking all the cells around it and
     * adding up the number of mines around it
     * @param x is the x coordinate of cell
     * @param y is the y coordinate of cell
     * @return the integer of the number of mines around a cell
     */
    @Override
    public int numberOfAdjacentMines(int x, int y)
    {
        int numAdjMines = 0;

        //the size of the grid
        if (x >= 0 && x < getColumns() && y >= 0 && y < getRows())
        {
            //top left cell
            if ((x - 1) >= 0 && (y - 1) >= 0)
            {
                if (board[x - 1][y - 1] == MineSweeperCell.MINE
                    || board[x - 1][y - 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //top middle cell
            if ((y - 1) >= 0)
            {
                if (board[x][y - 1] == MineSweeperCell.MINE
                    || board[x][y - 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //top right cell
            if ((x + 1) < getColumns() && (y - 1) >= 0)
            {
                if (board[x + 1][y - 1] == MineSweeperCell.MINE
                    || board[x + 1][y - 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //left cell
            if ((x - 1) >= 0)
            {
                if (board[x - 1][y] == MineSweeperCell.MINE
                    || board[x - 1][y] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //right cell
            if ((x + 1) < getColumns())
            {
                if (board[x + 1][y] == MineSweeperCell.MINE
                    || board[x + 1][y] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //bottom left cell
            if ((x - 1) >= 0 && (y + 1) < getRows())
            {
                if (board[x - 1][y + 1] == MineSweeperCell.MINE
                    || board[x - 1][y + 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //bottom middle cell
            if ((y + 1) < getRows())
            {
                if (board[x][y + 1] == MineSweeperCell.MINE
                    || board[x][y + 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }

            //bottom right cell
            if ((x + 1) < getColumns() && (y + 1) < getRows())
            {
                if (board[x + 1][y + 1] == MineSweeperCell.MINE
                    || board[x + 1][y + 1] == MineSweeperCell.FLAGGED_MINE)
                {
                    numAdjMines++;
                }
            }
        }
        return numAdjMines;
    }

    /**
     * uncovers all the cells on the board
     */
    @Override
    public void revealBoard()
    {
        for (int i = 0; i < getColumns(); i++)
        {
            for (int k = 0; k < getRows(); k++)
            {
                uncoverCell(i, k);
            }
        }
    }

    /**
     * sets a value for the selected cell
     * @param x is the x coordinate of cell
     * @param y is the y coordinate of cell
     * @param value is the value that is assigned to the selected cell
     */
    @Override
    protected void setCell(int x, int y, MineSweeperCell value)
    {
        board[x][y] = value;
    }

    /**
     * uncovers selected cell and reveals its value, but does not uncover the
     * cell if it has a flag on it
     * @param x is the x coordinate of cell
     * @param y is the y coordinate of cell
     */
    @Override
    public void uncoverCell(int x, int y)
    {
        if (board[x][y] != MineSweeperCell.FLAG &&
            board[x][y] != MineSweeperCell.FLAGGED_MINE)
        {
            if (board[x][y] == MineSweeperCell.MINE)
            {
                board[x][y] = MineSweeperCell.UNCOVERED_MINE;
            }
            else
            {
                int adjTo = numberOfAdjacentMines(x, y);
                board[x][y] = MineSweeperCell.adjacentTo(adjTo);
            }
        }

    }

    /**
     * returns the number of columns in the board
     * @return the integer of the width of the board
     */
    @Override
    public int width()
    {
        return this.getColumns();
    }
}
