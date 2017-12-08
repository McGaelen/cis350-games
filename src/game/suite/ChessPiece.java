package game.suite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.suite.ChessBoard.Color;

/**
 * Superclass Chess pieces have common variables and methods to execute.
 * Defines a standard piece and it's features.
 */
public abstract class ChessPiece {


    /**
     * Piece specific name will be stored here.
     */
    private String nameOfPiece;
    /**
     * Black or White piece.
     */
    private Color color;
    /**
     * Reference to the board this piece is on to indirectly access squaresList.
     */
    private ChessStandardBoard currentBoard;
    /**
     * xLocation of piece on board.
     */
    private int xLocation;
    /**
     * yLocation of piece on board.
     */
    private int yLocation;
    
    /**
     * Returns the board.
     * @return currentBoard
     */
    public ChessStandardBoard getCurrentBoard() {
        return this.currentBoard;
    }
    
    /**
     * Returns the xLocation of the piece.
     * @return xLocation
     */
    public int getXLocation() {
        return this.xLocation;
    }
    
    /**
     * Sets the xLocation of the piece.
     * @param x X location of the piece
     */
    public void setXLocation(final int x) {
        this.xLocation = x;
    }
    
    /**
     * Returns the yLocation of the piece.
     * @return yLocation
     */
    public int getYLocation() {
        return this.yLocation;
    }
    
    /**
     * Sets the yLocation of the piece.
     * @param y Y location of the piece
     */
    public void setYLocation(final int y) {
        this.yLocation = y;
    }

    /**
     * Returns the name of the piece.
     * @return nameOfPiece
     */
    public String getNameOfPiece() {
        return this.nameOfPiece;
    }
    
    /**
     * Sets the name of the piece.
     * @param name name of the piece
     */
    public void setNameOfPiece(final String name) {
        this.nameOfPiece = name;
    }
    
    /**
     * Returns the color of the piece.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Sets the color of the piece.
     * @param col color of the piece
     */
    public void setColor(final Color col) {
        this.color = col;
    }
    
    /**
     *
     * @param newX Is the x-coordinate of the move
     * @param newY Is the y-coordinate of the move
     * @return true if move is valid
     */
    abstract boolean isValidSpecialMove(int newX, int newY);

    /**
     * Constructor for the Piece.
     * @param initX Is the x-coordinate of the Chess piece
     * @param initY Is the y-coordinate of the Chess piece
     * @param pColor Is the color of the piece
     * @param board Is the current board
     */
    public ChessPiece(
            final int initX, final int initY,
            final Color pColor, final ChessStandardBoard board) {
        this.color = pColor;
        board.getSquaresList()[initX][initY].setIsOccupied(true);
        board.getSquaresList()[initX][initY].setOccupyingPiece(this);
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
        ChessSquare squareToCheck = currentBoard.getSquaresList()[newX][newY];
        if (squareToCheck.getIsOccupied()) {
            return isEnemyPiece(this.color, squareToCheck.getOccupyingPiece());
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
    public boolean isKingInCheck(final ChessKing kingToCheck) {
        int kingXLocation = kingToCheck.getXLocation();
        int kingYLocation = kingToCheck.getYLocation();
        Color colorToCheck = kingToCheck.getColor();
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                ChessSquare squareToCheck = currentBoard.getSquaresList()[i][j];
                if (squareToCheck.getIsOccupied()) {
                    if (isEnemyPiece(colorToCheck,
                            squareToCheck.getOccupyingPiece())) {
                        ChessPiece enemyPiece = squareToCheck
                                .getOccupyingPiece();
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
        ChessKing kingToCheck;
        ChessSquare squareToCheck
        = currentBoard.getSquaresList()[newPieceX][newPieceY];
        if (squareToCheck.getIsOccupied()) {
            ChessPiece pieceToCheck = squareToCheck.getOccupyingPiece();
            if (isEnemyPieceAtDestination(newPieceX, newPieceY)) {
                ChessPiece enemyPiece = pieceToCheck;
                if (this.color.equals(Color.white)) {
                    if (currentBoard.getWhiteKingTracker() == null) {
                        return false;
                    }
                    kingToCheck = currentBoard.getWhiteKingTracker();
                } else {
                    if (currentBoard.getBlackKingTracker() == null) {
                        return false;
                    }
                    kingToCheck = currentBoard.getBlackKingTracker();
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
                if (currentBoard.getWhiteKingTracker() == null) {
                    return false;
                }
                kingToCheck = currentBoard.getWhiteKingTracker();
            } else {
                if (currentBoard.getBlackKingTracker() == null) {
                    return false;
                }
                kingToCheck = currentBoard.getBlackKingTracker();
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
            final ChessPiece pieceToMove, final int newPieceX,
            final int newPieceY) {
        ChessSquare currentSquare
        = currentBoard.getSquaresList()
        [pieceToMove.xLocation][pieceToMove.yLocation];
        ChessSquare targetSquare
        = currentBoard.getSquaresList()[newPieceX][newPieceY];
        currentSquare.setIsOccupied(false);
        currentSquare.setOccupyingPiece(null);
        targetSquare.setIsOccupied(true);
        targetSquare.setOccupyingPiece(pieceToMove);
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
            final Color colorToCheck, final ChessPiece occupyingPiece) {
        if (colorToCheck.equals(occupyingPiece.color)) {
            return false;
        }
        return true;
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
    public boolean isKingCheckmate(final ChessKing kingToCheck) {
        if (!isKingInCheck(kingToCheck)) {
            return false;
        }
        Color colorToCheck = kingToCheck.getColor();
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                ChessSquare squareToCheck = currentBoard.getSquaresList()[i][j];
                if (squareToCheck.getIsOccupied()) {
                    if (!isEnemyPiece(colorToCheck,
                            squareToCheck.getOccupyingPiece())) {
                        ChessPiece allyPiece = squareToCheck
                                .getOccupyingPiece();
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
            final ChessPiece allyPiece, final ChessKing kingToCheck) {
        int oldPieceX = allyPiece.xLocation;
        int oldPieceY = allyPiece.yLocation;
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                ChessSquare squareToCheck = currentBoard.getSquaresList()[i][j];
                if (isEnemyPieceAtDestination(i, j)) {
                    if (allyPiece.isValidSpecialMove(i, j)) {
                        if (squareToCheck.getIsOccupied()) {
                            ChessPiece enemyPiece
                            = squareToCheck.getOccupyingPiece();
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



