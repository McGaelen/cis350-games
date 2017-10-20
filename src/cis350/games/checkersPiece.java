package cis350.games;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cis350.games.checkersBoard.Color;

/**
 * Defines what a checkers piece should be.
 */
public abstract class checkersPiece {


    /**
     * Piece specific name will be stored here.
     */
    String nameOfPiece;
    /**
     * Black or White piece.
     */
    public Color color;
    /**
     * Reference to the board this piece is on to indirectly access squaresList.
     */
    checkersStandardBoard currentBoard;
    /**
     * xLocation of piece on board.
     */
    public int xLocation;
    /**
     * yLocation of piece on board.
     */
    public int yLocation;

    /**
     * Determines if the move is valid or not.
     * @param newX the requested x coordinate.
     * @param newY the requested y coordinate.
     * @return true if it's a valid move, false if not.
     */
    abstract boolean isValidSpecialMove(int newX, int newY);

    /**
     * Creates a new checkers piece at the location given.
     * @param initX the x location of the piece.
     * @param initY the y location of the piece.
     * @param colorParam the color of the piece.
     * @param board the board that the piece belongs to.
     */
    public checkersPiece(
            final int initX,
            final int initY,
            final Color colorParam,
            final checkersStandardBoard board) {
        this.color = colorParam;
        board.squaresList[initX][initY].isOccupied = true;
        board.squaresList[initX][initY].occupyingPiece = this;
        this.currentBoard = board;
        this.xLocation = initX;
        this.yLocation = initY;
    }

    /**
     * Determines if this piece is eligible to move.
     * @param newX requested x coordinate
     * @param newY requested y coordinate
     * @return true if it can move, false if not.
     */
    public boolean canMove(final int newX, final int newY) {
        if (!currentBoard.inBoardBounds(newX, newY)) {
            return false;
        }
        if (!isValidSpecialMove(newX, newY)) {
            return false;
        }
        if (!isEnemyPieceAtDestination(newX, newY)) {
            return false;
        }
        if (isKingInDanger(newX, newY)) {
            return false;
        }
        return true;
    }

    /**
     * Detects if an enemy piece is at the requested spot.
     * @param newX requested x coordinate.
     * @param newY requested y coordinate.
     * @return true if an enemy is at the specified coordinates.
     */
    private boolean isEnemyPieceAtDestination(final int newX, final int newY) {
        checkersSquare squareToCheck = currentBoard.squaresList[newX][newY];
        if (squareToCheck.isOccupied) {
            return isEnemyPiece(this.color, squareToCheck.occupyingPiece);
        }
        return true;
    }

    /**
     * Wrapper function for calling movePiece().
     * @param newX the new x coordinate to move to.
     * @param newY the new y coordinate to move to.
     */
    public void executeCaptureOrMove(final int newX, final int newY) {
        movePiece(this, newX, newY);
    }

    /**
     * Detects if the king is currently in check.
     * @param kingToCheck the king that is possibly in chec.
     * @return true if the king is in check, false if not.
     */
    public boolean isKingInCheck(final checkersKing kingToCheck) {
        int kingXLocation = kingToCheck.xLocation;
        int kingYLocation = kingToCheck.yLocation;
        Color colorToCheck = kingToCheck.color;
        // Iterates through the squares on the board
        // and checks if enemy pieces can attack king.
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                checkersSquare squareToCheck = currentBoard.squaresList[i][j];
                if (squareToCheck.isOccupied) {
                    if (isEnemyPiece(colorToCheck,
                            squareToCheck.occupyingPiece)) {
                        checkersPiece enemyPiece = squareToCheck.occupyingPiece;
                        if (enemyPiece.isValidSpecialMove(kingXLocation,
                                kingYLocation)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Detects if the king piece is close to being in check.
     * @param newPieceX the new piece's x coordinate
     * @param newPieceY the new piece's y coordinate
     * @return ture if the king is in danger, false if not.
     */
    private boolean isKingInDanger(final int newPieceX, final int newPieceY) {
        int oldPieceX = this.xLocation;
        int oldPieceY = this.yLocation;
        checkersKing kingToCheck;
        checkersSquare squareToCheck =
                currentBoard.squaresList[newPieceX][newPieceY];
        if (squareToCheck.isOccupied) {
            checkersPiece pieceToCheck = squareToCheck.occupyingPiece;
            if (isEnemyPieceAtDestination(newPieceX, newPieceY)) {
                checkersPiece enemyPiece = pieceToCheck;
                if (this.color.equals(Color.white)) {
                    if (currentBoard.whiteKingTracker == null) {
                        return false;
                    }
                    kingToCheck = currentBoard.whiteKingTracker;
                } else {
                    if (currentBoard.blackKingTracker == null) {
                        return false;
                    }
                    kingToCheck = currentBoard.blackKingTracker;
                }
                movePiece(this, newPieceX, newPieceY);
                if (isKingInCheck(kingToCheck)) {
                    movePiece(this, oldPieceX, oldPieceY);
                    movePiece(enemyPiece, newPieceX, newPieceY);
                    return true;
                }
                movePiece(this, oldPieceX, oldPieceY);
                movePiece(enemyPiece, newPieceX, newPieceY);
            }
        } else {
            if (this.color.equals(Color.white)) {
                if (currentBoard.whiteKingTracker == null) {
                    return false;
                }
                kingToCheck = currentBoard.whiteKingTracker;
            } else {
                if (currentBoard.blackKingTracker == null) {
                    return false;
                }
                kingToCheck = currentBoard.blackKingTracker;
            }
            movePiece(this, newPieceX, newPieceY);
            if (isKingInCheck(kingToCheck)) {
                movePiece(this, oldPieceX, oldPieceY);
                return true;
            }
            movePiece(this, oldPieceX, oldPieceY);
        }
        return false;
    }

    /**
     * Moves this piece to another location of the board.
     * @param pieceToMove reference to the piece to be moved.
     * @param newPieceX the new piece's requested x coordinate.
     * @param newPieceY the new piece's requested y coordinate.
     */
    private void movePiece(
            final checkersPiece pieceToMove,
            final int newPieceX,
            final int newPieceY) {
        checkersSquare currentSquare =
            currentBoard
            .squaresList[pieceToMove.xLocation][pieceToMove.yLocation];
        checkersSquare targetSquare =
            currentBoard.squaresList[newPieceX][newPieceY];
        currentSquare.isOccupied = false;
        currentSquare.occupyingPiece = null;
        targetSquare.isOccupied = true;
        targetSquare.occupyingPiece = pieceToMove;
        pieceToMove.xLocation = newPieceX;
        pieceToMove.yLocation = newPieceY;
    }

    /**
     * Detects if another piece is an enemy.
     * @param colorToCheck the color to check against.
     * @param occupyingPiece the other piece to check against.
     * @return true if the other piece is an enemy, false if not.
     */
    private boolean isEnemyPiece(
            final Color colorToCheck,
            final checkersPiece occupyingPiece) {
        if (colorToCheck.equals(occupyingPiece.color)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Draws a piece on the game board.
     * @param graphic the graphic to draw.
     * @param squareSize how big the piece should be drawn.
     * @param x the x coordinate to draw the piece at.
     * @param y the y coordinate to draw the piece at.
     */
    public void drawPiece(
            final Graphics graphic,
            final int squareSize,
            final int x,
            final int y) {
        if (this.color.equals(Color.black)) {
            String name = this.nameOfPiece.concat(".png");
            String imagePath = "assets/black_";
            String imageName = imagePath.concat(name);
            drawPieceHelper(graphic, squareSize, imageName, x, y);
        } else {
            String name = this.nameOfPiece.concat(".png");
            String imagePath = "assets/white_";
            String imageName = imagePath.concat(name);
            drawPieceHelper(graphic, squareSize, imageName, x, y);
        }
    }

    /**
     * Helper method that draws a piece on the board.
     * @param graphic the graphic to draw.
     * @param squareSize the size that the piece should be drawn.
     * @param imageName the name of the image file to load to display a piece.
     * @param x the x coordinate to draw the piece at.
     * @param y the y coordinate to draw the piece at.
     */
    private void drawPieceHelper(
            final Graphics graphic,
            final int squareSize,
            final String imageName,
            final int x,
            final int y) {
        File imageFile = new File(imageName);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();
        int heightPadding = (squareSize - imageHeight) / 2;
        int widthPadding = (squareSize - imageWidth) / 2;
        graphic.drawImage(
                image,
                (squareSize * x) + widthPadding,
                ((7 - y) * squareSize) + heightPadding,
                imageWidth,
                imageHeight,
                null
        );
    }

    /**
     * Checks if the king is in checkmate.
     * @param kingToCheck the king piece to check.
     * @return true if the king is in check, false if not.
     */
    public boolean isKingCheckmate(final checkersKing kingToCheck) {
        if (!isKingInCheck(kingToCheck)) {
            return false;
        }
        Color colorToCheck = kingToCheck.color;
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                checkersSquare squareToCheck = currentBoard.squaresList[i][j];
                if (squareToCheck.isOccupied) {
                    if (!isEnemyPiece(colorToCheck,
                            squareToCheck.occupyingPiece)) {
                        checkersPiece allyPiece = squareToCheck.occupyingPiece;
                         if (!checkmateHelper(allyPiece, kingToCheck)) {
                             return false;
                         }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Contains helper logic to perform a checkmate check.
     * @param allyPiece another piece allied to the player.
     * @param kingToCheck the king piece to check.
     * @return true if the king is in checkmate, false if not.
     */
    private boolean checkmateHelper(
            final checkersPiece allyPiece,
            final checkersKing kingToCheck) {
        int oldPieceX = allyPiece.xLocation;
        int oldPieceY = allyPiece.yLocation;
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                checkersSquare squareToCheck = currentBoard.squaresList[i][j];
                if (isEnemyPieceAtDestination(i, j)) {
                    if (allyPiece.isValidSpecialMove(i, j)) {
                        if (squareToCheck.isOccupied) {
                            checkersPiece enemyPiece =
                                    squareToCheck.occupyingPiece;
                            movePiece(allyPiece, i, j);
                            if (!isKingInCheck(kingToCheck)) {
                                movePiece(allyPiece, oldPieceX, oldPieceY);
                                movePiece(enemyPiece, i, j);
                                return false;
                            }
                            movePiece(allyPiece, oldPieceX, oldPieceY);
                            movePiece(enemyPiece, i, j);
                        } else {
                            movePiece(allyPiece, i, j);
                            if (!isKingInCheck(kingToCheck)) {
                                movePiece(allyPiece, oldPieceX, oldPieceY);
                                return false;
                            }
                            movePiece(allyPiece, oldPieceX, oldPieceY);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Promotes a white piece to king.
     * @param xDest the x location of the new king.
     * @param yDest the y location of the new king.
     */
    public void whitePromote(final int xDest, final int yDest) {
        checkersKing newWhiteKing =
                new checkersKing(xDest, yDest, Color.white, currentBoard);
        currentBoard.squaresList[xDest][yDest].isOccupied = true;
        currentBoard.squaresList[xDest][yDest].occupyingPiece = newWhiteKing;

    }

    /**
     * Promotes a black piece to king.
     * @param xDest the x location of the new king.
     * @param yDest the y location of the new king.
     */
    public void blackPromote(final int xDest, final int yDest) {
        checkersKing newBlackKing =
                new checkersKing(xDest, yDest, Color.black, currentBoard);
        currentBoard.squaresList[xDest][yDest].isOccupied = true;
        currentBoard.squaresList[xDest][yDest].occupyingPiece = newBlackKing;

    }
}
