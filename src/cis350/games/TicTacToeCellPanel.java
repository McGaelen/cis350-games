package cis350.games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/***********************************************************************
 * Class to display a cell in the Tic Tac Toe board.
 * Contains a label for the player moves.
 * Stores the row and column of the cell.
 * @author Edric Lin
 * @version 10/18/17
 **********************************************************************/

public class TicTacToeCellPanel extends JPanel {

    /** player mark label X or O.*/
    private JLabel mark;

    /** row of cell.*/
    private int row;

    /** column of cell.*/
    private int col;

    /*******************************************************************
     * Constructor for Tic Tac Toe cell. Sets the background and font
     * of the cell.
     * @param p the mark to set the cell label to
     * @param r the row of the cell
     * @param c the column of the cell
     ******************************************************************/
    public TicTacToeCellPanel(final String p, final int r, final int c) {
        // set the layout to GridBag
        setLayout(new GridBagLayout());
        // create border and background
        setBorder(BorderFactory.createEtchedBorder(
                EtchedBorder.RAISED));
        Dimension dim = new Dimension(150, 150);
        setPreferredSize(dim);
        setBackground(Color.lightGray);

        // store cell row and column
        row = r;
        col = c;

        // create and add player label
        mark = new JLabel(p);
        mark.setFont(new Font("Helvetica Neue", Font.BOLD, 75));
        add(mark);
    }

    /*******************************************************************
     * Return the cell's mark label.
     * @return mark
     ******************************************************************/
    public JLabel getMark() {
        return mark;
    }

    /*******************************************************************
     * Return the cell's row.
     * @return row
     ******************************************************************/
    public int getRow() {
        return row;
    }

    /*******************************************************************
     * Return the cell's column.
     * @return col
     ******************************************************************/
    public int getCol() {
        return col;
    }
}
