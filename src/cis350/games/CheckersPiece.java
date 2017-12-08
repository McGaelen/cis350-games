package cis350.games;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cis350.games.CheckersBoard.Color;

/**
 * Defines what a Checkers piece should be.
 */
public abstract class CheckersPiece {


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
    private CheckersStandardBoard currentBoard;
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
    public CheckersStandardBoard getCurrentBoard() {
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
     * Determines if the move is valid or not.
     * @param newX the requested x coordinate.
     * @param newY the requested y coordinate.
     * @return true if it's a valid move, false if not.
     */
    abstract boolean isValidSpecialMove(int newX, int newY);

    /**
     * Creates a new Checkers piece at the location given.
     * @param initX the x location of the piece.
     * @param initY the y location of the piece.
     * @param colorParam the color of the piece.
     * @param board the board that the piece belongs to.
     */
    public CheckersPiece(
            final int initX,
            final int initY,
            final Color colorParam,
            final CheckersStandardBoard board) {
        this.color = colorParam;
        board.getSquaresList()[initX][initY].setIsOccupied(true);
        board.getSquaresList()[initX][initY].setOccupyingPiece(this);
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
        CheckersSquare squareToCheck = 
                currentBoard.getSquaresList()[newX][newY];
        if (squareToCheck.getIsOccupied()) {
            return isEnemyPiece(this.color, squareToCheck.getOccupyingPiece());
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
     * @param kingToCheck the king that is possibly in check.
     * @return true if the king is in check, false if not.
     */
    public boolean isKingInCheck(final CheckersKing kingToCheck) {
        int kingXLocation = kingToCheck.getXLocation();
        int kingYLocation = kingToCheck.getYLocation();
        Color colorToCheck = kingToCheck.getColor();
        // Iterates through the squares on the board
        // and checks if enemy pieces can attack king.
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                CheckersSquare squareToCheck = 
                        currentBoard.getSquaresList()[i][j];
                if (squareToCheck.getIsOccupied()) {
                    if (isEnemyPiece(colorToCheck,
                            squareToCheck.getOccupyingPiece())) {
                        CheckersPiece enemyPiece = squareToCheck
                                .getOccupyingPiece();
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
     * @return true if the king is in danger, false if not.
     */
    private boolean isKingInDanger(final int newPieceX, final int newPieceY) {
        int oldPieceX = this.xLocation;
        int oldPieceY = this.yLocation;
        CheckersKing kingToCheck;
        CheckersSquare squareToCheck =
                currentBoard.getSquaresList()[newPieceX][newPieceY];
        if (squareToCheck.getIsOccupied()) {
            CheckersPiece pieceToCheck = squareToCheck.getOccupyingPiece();
            if (isEnemyPieceAtDestination(newPieceX, newPieceY)) {
                CheckersPiece enemyPiece = pieceToCheck;
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
     * Moves this piece to another location of the board.
     * @param pieceToMove reference to the piece to be moved.
     * @param newPieceX the new piece's requested x coordinate.
     * @param newPieceY the new piece's requested y coordinate.
     */
    private void movePiece(
            final CheckersPiece pieceToMove,
            final int newPieceX,
            final int newPieceY) {
        CheckersSquare currentSquare =
            currentBoard
            .getSquaresList()[pieceToMove.xLocation][pieceToMove.yLocation];
        CheckersSquare targetSquare =
            currentBoard.getSquaresList()[newPieceX][newPieceY];
        currentSquare.setIsOccupied(false);
        currentSquare.setOccupyingPiece(null);
        targetSquare.setIsOccupied(true);
        targetSquare.setOccupyingPiece(pieceToMove);
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
            final CheckersPiece occupyingPiece) {
        if (colorToCheck.equals(occupyingPiece.color)) {
            return false;
        }
        return true;
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
    public boolean isKingCheckmate(final CheckersKing kingToCheck) {
        if (!isKingInCheck(kingToCheck)) {
            return false;
        }
        Color colorToCheck = kingToCheck.getColor();
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                CheckersSquare squareToCheck = 
                        currentBoard.getSquaresList()[i][j];
                if (squareToCheck.getIsOccupied()) {
                    if (!isEnemyPiece(colorToCheck,
                            squareToCheck.getOccupyingPiece())) {
                        CheckersPiece allyPiece = squareToCheck
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
     * Contains helper logic to perform a checkmate check.
     * @param allyPiece another piece allied to the player.
     * @param kingToCheck the king piece to check.
     * @return true if the king is in checkmate, false if not.
     */
    private boolean checkmateHelper(
            final CheckersPiece allyPiece,
            final CheckersKing kingToCheck) {
        int oldPieceX = allyPiece.xLocation;
        int oldPieceY = allyPiece.yLocation;
        for (int i = 0; i < currentBoard.getNumXSquares(); i++) {
            for (int j = 0; j < currentBoard.getNumYSquares(); j++) {
                CheckersSquare squareToCheck = 
                        currentBoard.getSquaresList()[i][j];
                if (isEnemyPieceAtDestination(i, j)) {
                    if (allyPiece.isValidSpecialMove(i, j)) {
                        if (squareToCheck.getIsOccupied()) {
                            CheckersPiece enemyPiece =
                                    squareToCheck.getOccupyingPiece();
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
        CheckersKing newWhiteKing =
                new CheckersKing(xDest, yDest, Color.white, currentBoard);
        currentBoard.getSquaresList()[xDest][yDest].setIsOccupied(true);
        currentBoard.getSquaresList()[xDest][yDest]
                .setOccupyingPiece(newWhiteKing);

    }

    /**
     * Promotes a black piece to king.
     * @param xDest the x location of the new king.
     * @param yDest the y location of the new king.
     */
    public void blackPromote(final int xDest, final int yDest) {
        CheckersKing newBlackKing =
                new CheckersKing(xDest, yDest, Color.black, currentBoard);
        currentBoard.getSquaresList()[xDest][yDest].setIsOccupied(true);
        currentBoard.getSquaresList()[xDest][yDest]
                .setOccupyingPiece(newBlackKing);

    }
}
