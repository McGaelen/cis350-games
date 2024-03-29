package game.suite;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The game display class which is a subclass of JPanel.
 *
 */
public class ChessGameDisplay extends JPanel {
    /**
     * No idea why we need this.
     */
    private static final long serialVersionUID = 1L;
    /**
     * the current board.
     */
    private ChessStandardBoard board;
    /**
     * the square size.
     */
    private int squareSize;

    /**
     * Constructor for the Game Display.
     * @param gameBoard the current game board
     * @param cSquareSize the size of the squares
     */
    public ChessGameDisplay(
            final ChessStandardBoard gameBoard, final int cSquareSize) {
        board = gameBoard;
        this.squareSize = cSquareSize;
    }

    /**
     * Creates the board.
     * @param graphic the graphic to be painted
     */
    @Override
    public void paintComponent(final Graphics graphic) {
        for (int i = 0; i < board.getNumXSquares(); i++) {
            for (int j = 0; j < board.getNumYSquares(); j++) {
                ChessSquare squareToDraw = board.getSquaresList()[i][j];
                if (squareToDraw.getColor().equals(ChessBoard.Color.black)) {
                    graphic.setColor(new Color(58, 95, 205));
                    graphic.fillRect((squareSize * i),
                            (7 - j) * squareSize, squareSize, squareSize);
                    if (squareToDraw.getIsOccupied()) {
                        squareToDraw.getOccupyingPiece().drawPiece(
                                graphic, squareSize, i, j);
                    }
                } else {
                    graphic.setColor(new Color(230, 230, 250));
                    graphic.fillRect((squareSize * i),
                            (7 - j) * squareSize, squareSize, squareSize);
                    if (squareToDraw.getIsOccupied()) {
                        squareToDraw.getOccupyingPiece().drawPiece(
                                graphic, squareSize, i, j);
                    }
                }
            }
        }
    }

}

