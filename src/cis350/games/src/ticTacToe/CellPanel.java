package ticTacToe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class CellPanel extends JPanel{

	/** player mark X or O */
	private JLabel mark;

	/** row of cell */
	private int row;

	/** column of cell */
	private int col;

	public CellPanel(String p, int r, int c) {

		// set the layout to GridBag
		setLayout(new GridBagLayout());
		
		// create border and background
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		Dimension dim = new Dimension(150,150);
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

	public JLabel getMark() {
		return mark;
	}

	public void setMark(JLabel mark) {
		this.mark = mark;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	
}
