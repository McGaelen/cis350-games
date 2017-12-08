package game.suite;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/***********************************************************************
 * Class to display a cell in the Tic Tac Toe board.
 * Contains a label for the player moves.
 * Stores the row and column of the cell.
 * 
 * @author Edric Lin
 * @version 12/5/17
 **********************************************************************/
public class TicTacToeCellPane extends BorderPane {
	
	/** player mark x or o 8. */
	private Label mark;
	
	/** row of the cell. */
	private int row;
	
	/** column of the cell. */
	private int col;
	
    /*******************************************************************
     * Constructor for board cell. Contains a label for the player's 
     * mark. Stores the row and column of the cell in the board.
     * 
     * @param p the player's mark
     * @param r the row of the cell
     * @param c the column of the cell
     ******************************************************************/
	public TicTacToeCellPane(final String p, final int r, final int c) {
		
		super();
		
		// set pane size and border
		setPrefSize(150, 150);
		setBackground(new Background(new BackgroundFill(Color.
				web("#E6E6E6"), CornerRadii.EMPTY, 
				Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, 
	            BorderWidths.DEFAULT)));
		
		// store cell row and column
		row = r;
		col = c;
		
		// create and add player label
		mark = new Label(p);
		mark.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 75));
		
		setCenter(mark);
	}
	
    /*******************************************************************
     * Return the cell's mark label.
     * 
     * @return mark
     ******************************************************************/
    public Label getMark() {
        return mark;
    }

    /*******************************************************************
     * Return the cell's row.
     * 
     * @return row
     ******************************************************************/
    public int getRow() {
        return row;
    }

    /*******************************************************************
     * Return the cell's column.
     * 
     * @return col
     ******************************************************************/
    public int getCol() {
        return col;
    }
}
