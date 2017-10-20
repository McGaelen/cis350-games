package cis350.games;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cis350.games.chessBoard.Color;

/**
 * Superclass Chess pieces have common variables and methods to execute.
 * Defines a standard piece and it's features.
 */
public abstract class chessPiece {


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
    chessStandardBoard currentBoard;
    /**
     * xLocation of piece on board.
     */
    public int xLocation;
    /**
     * yLocation of piece on board.
     */
    public int yLocation;

    /**
     *
     * @param newX Is the x-coordinate of the move
     * @param newY Is the y-coordinate of the move
     * @return true if move is valid
     */
    abstract boolean isValidSpecialMove(int newX, int newY);

    /**
     * Constructor for the Piece.
     * @param initX Is the x-coordinate of the chess piece
     * @param initY Is the y-coordinate of the chess piece
     * @param pColor Is the color of the piece
     * @param board Is the current board
     */
    public chessPiece(
            final int initX, final int initY,
            final Color pColor, final chessStandardBoard board) {
        this.color = pColor;
        board.squaresList[initX][initY].isOccupied = true;
        board.squaresList[initX][initY].occupyingPiece = this;
        this.currentBoard = board;
        this.xLocation = initX;
        this.yLocation = initY;
    }

    /**
     * Checks of the piece can move to the location [newX][newY] on the board.
     * @param newX The new x-coordinate for the move
     * @param newY The new y-coordinate for the move
     * @return true if the piece can move to the new location
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
     * Checks if the enemy piece is at the new location.
     * @param newX The new x-coordinate for the move
     * @param newY The new y-coordinate for the move
     * @return true is an enemy piece is at the new location
     */
    private boolean isEnemyPieceAtDestination(final int newX, final int newY) {
        chessSquare squareToCheck = currentBoard.squaresList[newX][newY];
        if (squareToCheck.isOccupied) {
            return isEnemyPiece(this.color, squareToCheck.occupyingPiece);
        }
        return true;
    }

    /**
     * Moves the piece to the new location.
     * @param newX The new x-coordinate for the move
     * @param newY The new y-coordinate for the move
     */
    public void executeCaptureOrMove(final int newX, final int newY) {
        movePiece(this, newX, newY);
    }

    /**
     * Checks if the King is in Check state.
     * @param kingToCheck Is the King to be checked
     * @return true if the king is in check
     */
    public boolean isKingInCheck(final chessKing kingToCheck) {
        int kingXLocation = kingToCheck.xLocation;
        int kingYLocation = kingToCheck.yLocation;
        Color colorToCheck = kingToCheck.color;
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                chessSquare squareToCheck = currentBoard.squaresList[i][j];
                if (squareToCheck.isOccupied) {
                    if (isEnemyPiece(colorToCheck,
                            squareToCheck.occupyingPiece)) {
                        chessPiece enemyPiece = squareToCheck.occupyingPiece;
                        if (enemyPiece.isValidSpecialMove(
                                kingXLocation, kingYLocation)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    /**
     * Checks if the King is in check after a move.
     * @param newPieceX The x-coordinate of the Piece
     * @param newPieceY The y-coordinate of the Piece
     * @return true if the King is in danger after the move
     */
    private boolean isKingInDanger(final int newPieceX, final int newPieceY) {
        int oldPieceX = this.xLocation;
        int oldPieceY = this.yLocation;
        chessKing kingToCheck;
        chessSquare squareToCheck
        = currentBoard.squaresList[newPieceX][newPieceY];
        if (squareToCheck.isOccupied) {
            chessPiece pieceToCheck = squareToCheck.occupyingPiece;
            if (isEnemyPieceAtDestination(newPieceX, newPieceY)) {
                chessPiece enemyPiece = pieceToCheck;
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
     * Moves the Piece.
     * @param pieceToMove is the Piece to move
     * @param newPieceX is the new x-coordinate
     * @param newPieceY is the new y-coordinate
     */
    private void movePiece(
            final chessPiece pieceToMove, final int newPieceX,
            final int newPieceY) {
        chessSquare currentSquare
        = currentBoard.squaresList
        [pieceToMove.xLocation][pieceToMove.yLocation];
        chessSquare targetSquare
        = currentBoard.squaresList[newPieceX][newPieceY];
        currentSquare.isOccupied = false;
        currentSquare.occupyingPiece = null;
        targetSquare.isOccupied = true;
        targetSquare.occupyingPiece = pieceToMove;
        pieceToMove.xLocation = newPieceX;
        pieceToMove.yLocation = newPieceY;
    }

    /**
     * Checks if the piece is an enemy piece.
     * @param colorToCheck is the color to check
     * @param occupyingPiece is the piece in question
     * @return false if the color is the same as the piece
     */
    private boolean isEnemyPiece(
            final Color colorToCheck, final chessPiece occupyingPiece) {
        if (colorToCheck.equals(occupyingPiece.color)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Draws the piece.
     * @param graphic is the image of the piece
     * @param squareSize size of the square
     * @param x is the x-coordinate
     * @param y is the y-coordinate
     */
    public void drawPiece(
            final Graphics graphic, final int squareSize,
            final int x, final int y) {
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
     * A helper method to draw the piece in the proper coordinates on the board.
     * @param graphic Is the image of the piece
     * @param squareSize is the size of the square
     * @param imageName is the image name
     * @param x is the x-coordinate
     * @param y is the y-coordinate
     */
    private void drawPieceHelper(
            final Graphics graphic, final int squareSize,
            final String imageName, final int x, final int y) {
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
                image, (squareSize * x) + widthPadding, ((7 - y) * squareSize)
                + heightPadding, imageWidth, imageHeight, null);
    }

    /**
     * Checks if the game is over.
     * @param kingToCheck is the King to check
     * @return true if the games is over
     */
    public boolean isKingCheckmate(final chessKing kingToCheck) {
        if (!isKingInCheck(kingToCheck)) {
            return false;
        }
        Color colorToCheck = kingToCheck.color;
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                chessSquare squareToCheck = currentBoard.squaresList[i][j];
                if (squareToCheck.isOccupied) {
                    if (!isEnemyPiece(colorToCheck,
                            squareToCheck.occupyingPiece)) {
                        chessPiece allyPiece = squareToCheck.occupyingPiece;
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
     * Helper method to check if any move can break the check.
     * @param allyPiece Can the ally piece take the King out of Check
     * @param kingToCheck Is the King in check
     * @return true if king is in checkmate
     */
    private boolean checkmateHelper(
            final chessPiece allyPiece, final chessKing kingToCheck) {
        int oldPieceX = allyPiece.xLocation;
        int oldPieceY = allyPiece.yLocation;
        for (int i = 0; i < currentBoard.numXSquares; i++) {
            for (int j = 0; j < currentBoard.numYSquares; j++) {
                chessSquare squareToCheck = currentBoard.squaresList[i][j];
                if (isEnemyPieceAtDestination(i, j)) {
                    if (allyPiece.isValidSpecialMove(i, j)) {
                        if (squareToCheck.isOccupied) {
                            chessPiece enemyPiece
                            = squareToCheck.occupyingPiece;
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

}



