package cis350.games;

import cis350.games.ChessBoard.Color;

/**
 * Subclass of a Piece specific to a Queen.
 * This handles all movements the queen is capable of making.
 */
public class ChessQueen extends ChessPiece {

/**
* Queen constructor initializes name of piece to Queen.
* Every other parameter is initialized by superclass.
* @param initX x-coordinate
* @param initY y-coordinate
* @param color color of the queen
* @param board the current board
*/
  public ChessQueen(
      final int initX, final int initY,
      final Color color, final ChessStandardBoard board) {
    super(initX, initY, color, board);
    this.setNameOfPiece("queen");
  }

/**
 * Queen specific implementation of abstract method.
 * @see chessGame.Piece#isValidSpecialMove(int, int)
 * @param newX new x location
 * @param newY new y location
 * @return boolean true if valid special move
 */
 @Override
  boolean isValidSpecialMove(final int newX, final int newY) {
    int xDisplacement = newX - getXLocation();
    int yDisplacement = newY - getYLocation();
    if (isValidQueenMove(xDisplacement, yDisplacement)) {
      int steps = Math.max(Math.abs(xDisplacement),
          Math.abs(yDisplacement));
      int xDirection = xDisplacement / steps;
      int yDirection = yDisplacement / steps;
      // Check for obstacles in path of Queen.
      for (int i = 1; i < steps; i++) {
        ChessSquare squareToCheck =
            getCurrentBoard().getSquaresList()
            [getXLocation() + i * xDirection]
            [getYLocation() + i * yDirection];
            if (squareToCheck.getIsOccupied()) {
          return false;
            }
      }
      return true;
    }
    return false;
  }

/**
 * Helper method for Queen specific moves.
 * @param xDisplacement change in x from move
 * @param yDisplacement change in y from move
 * @return boolean true if valid queen move
 */
  private boolean isValidQueenMove(final int xDisplacement,
      final int yDisplacement) {
    // Diagonal movement.
    if ((Math.abs(xDisplacement) == Math.abs(yDisplacement))
        && xDisplacement != 0) {
      return true;
    } else {
      if (xDisplacement != 0 && yDisplacement == 0) {
        // Horizontal movement
        return true;
      } else if (xDisplacement == 0 && yDisplacement != 0) {
        return true;
      } else {
        return false;
      }
    }
  }

}

