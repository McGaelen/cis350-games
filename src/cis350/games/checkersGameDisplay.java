package cis350.games;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The game display class which is a subclass of JPanel.
 */
public class checkersGameDisplay extends JPanel {

    private static final long serialVersionUID = 1L;
    /**
     * Holds an instance of a checkers board.
     */
    private checkersStandardBoard board;
    /**
     * The size that each square in the board should be.
     */
    private int squareSize;

    /**
     * Constructor to initialize a game display.
     * @param gameBoard a reference to a checkers game board.
     * @param squareSizeParam the size of each square in the board.
     */
    public checkersGameDisplay(
            final checkersStandardBoard gameBoard,
            final int squareSizeParam) {
        board = gameBoard;
        this.squareSize = squareSizeParam;
    }

    /**
     * Draws each square of the game board.
     * @param graphic the square to be drown.
     */
    @Override
    public void paintComponent(final Graphics graphic) {
        for (int i = 0; i < board.getNumXSquares(); i++) {
            for (int j = 0; j < board.getNumYSquares(); j++) {
                checkersSquare squareToDraw = board.getSquaresList()[i][j];
                if (squareToDraw.getColor().equals(checkersBoard.Color.black)) {
                    graphic.setColor(new Color(58, 95, 205));
                    graphic.fillRect(
                            (squareSize * i),
                            (7 - j) * squareSize, squareSize, squareSize
                    );
                    if (squareToDraw.getIsOccupied()) {
                        squareToDraw.getOccupyingPiece()
                                .drawPiece(graphic, squareSize, i, j);
                    }
                } else {
                    graphic.setColor(new Color(230, 230, 250));
                    graphic.fillRect(
                            (squareSize * i),
                            (7 - j) * squareSize, squareSize, squareSize
                    );
                    if (squareToDraw.getIsOccupied()) {
                        squareToDraw.getOccupyingPiece()
                                .drawPiece(graphic, squareSize, i, j);
                    }
                }
            }
        }
    }
}
