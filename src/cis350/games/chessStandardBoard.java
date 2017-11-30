package cis350.games;

/**
 * Subclass of a board. Standard version of a chess Board.
 * Has methods for creating a standard chess board
 * Populates it with regular chess pieces.
 * Can be used to create a standard game of Chess.
 */
public class chessStandardBoard extends chessBoard {

  /**
* Tracker for the white king for check, checkmate and game ending conditions.
*/
  private chessKing whiteKingTracker;
  /**
  * Tracker black king for check, checkmate and game ending conditions.
  */
  private chessKing blackKingTracker;
  
  /**
  * Returns white king tracker.
  * @return whiteKingTracker
  */
  public chessKing getWhiteKingTracker() {
      return this.whiteKingTracker;
  }
  
  /**
  * Returns black king tracker.
  * @return blackKingTracker
  */
  public chessKing getBlackKingTracker() {
      return this.blackKingTracker;
  }
  /**
  Method to initialize the chess board.
* @param xSquares Number of Squares on the x-axis
* @param ySquares Number of Squares on the y-axis
*/
  public chessStandardBoard(final int xSquares, final int ySquares) {

    this.setNumXSquares(xSquares);
    this.setNumYSquares(ySquares);
    this.setTotalSquares(this.getNumXSquares() * this.getNumYSquares());
    this.setSquaresList(new chessSquare
            [this.getNumXSquares()][this.getNumYSquares()]);
    populateBoardWithSquares();
    this.whiteKingTracker = null;
    this.blackKingTracker = null;
  }

  /**
* Method to populate our board with Squares.
* General pattern of white and black squares on the board.
*/
  public void populateBoardWithSquares() {
    for (int i = 0; i < this.getNumXSquares(); i++) {
      for (int j = 0; j < this.getNumYSquares(); j++) {
        if (i % 2 == 0) {
          if (j % 2 == 0) {
            getSquaresList()[i][j] = new chessSquare(false, Color.black);
          } else {
            getSquaresList()[i][j] = new chessSquare(false, Color.white);
          }
        } else {
          if (j % 2 == 0) {
            getSquaresList()[i][j] = new chessSquare(false, Color.white);
          } else {
            getSquaresList()[i][j] = new chessSquare(false, Color.black);
          }
        }
      }
    }
  }

    /**
     * Method to populate our chess board with standard pieces.
     * @param special Is the game type different
     */
    public void populateBoardWithPieces(final boolean special) {
        setupKnights();
        setupBishops();
        setupPawns();
        setupRooks();
        setupQueens();
        setupKings();
    }

    /**
     * Setup 8 black and 8 white pawns in their initial positions.
     */
    public void setupPawns() {
        for (int i = 0; i < 8; i++) {
            chessPawn newWhitePawn = new chessPawn(i, 1, Color.white, this);
            chessPawn newBlackPawn = new chessPawn(i, 6, Color.black, this);
            this.getSquaresList()[i][1].setIsOccupied(true);
            this.getSquaresList()[i][6].setIsOccupied(true);
            this.getSquaresList()[i][1].setOccupyingPiece(newWhitePawn);
            this.getSquaresList()[i][6].setOccupyingPiece(newBlackPawn);

        }
    }

    /**
     * Setup 2 black rooks and 2 white rooks in their initial positions.
     */
    public void setupRooks() {
        chessRook whiteRookOne = new chessRook(0, 0, Color.white, this);
        chessRook whiteRookTwo = new chessRook(7, 0, Color.white, this);
        chessRook blackRookOne = new chessRook(0, 7, Color.black, this);
        chessRook blackRookTwo = new chessRook(7, 7, Color.black, this);
        this.getSquaresList()[0][0].setIsOccupied(true);
        this.getSquaresList()[7][0].setIsOccupied(true);
        this.getSquaresList()[0][0].setOccupyingPiece(whiteRookOne);
        this.getSquaresList()[7][0].setOccupyingPiece(whiteRookTwo);
        this.getSquaresList()[0][7].setIsOccupied(true);
        this.getSquaresList()[7][7].setIsOccupied(true);
        this.getSquaresList()[0][7].setOccupyingPiece(blackRookOne);
        this.getSquaresList()[7][7].setOccupyingPiece(blackRookTwo);

    }

    /**
     * Setup 2 black Bishops and 2 white Bishops in their initial positions.
     */
    public void setupBishops() {
        chessBishop whiteBishopOne = new chessBishop(2, 0, Color.white, this);
        chessBishop whiteBishopTwo = new chessBishop(5, 0, Color.white, this);
        chessBishop blackBishopOne = new chessBishop(2, 7, Color.black, this);
        chessBishop blackBishopTwo = new chessBishop(5, 7, Color.black, this);
        this.getSquaresList()[2][0].setIsOccupied(true);
        this.getSquaresList()[5][0].setIsOccupied(true);
        this.getSquaresList()[2][0].setOccupyingPiece(whiteBishopOne);
        this.getSquaresList()[5][0].setOccupyingPiece(whiteBishopTwo);
        this.getSquaresList()[2][7].setIsOccupied(true);
        this.getSquaresList()[5][7].setIsOccupied(true);
        this.getSquaresList()[2][7].setOccupyingPiece(blackBishopOne);
        this.getSquaresList()[5][7].setOccupyingPiece(blackBishopTwo);
    }

    /**
     * Setup 2 black Knights and 2 white Knights in their initial positions.
     */
    public void setupKnights() {
        chessKnight whiteKnightOne = new chessKnight(1, 0, Color.white, this);
        chessKnight whiteKnightTwo = new chessKnight(6, 0, Color.white, this);
        chessKnight blackKnightOne = new chessKnight(1, 7, Color.black, this);
        chessKnight blackKnightTwo = new chessKnight(6, 7, Color.black, this);
        this.getSquaresList()[1][0].setIsOccupied(true);
        this.getSquaresList()[6][0].setIsOccupied(true);
        this.getSquaresList()[1][0].setOccupyingPiece(whiteKnightOne);
        this.getSquaresList()[6][0].setOccupyingPiece(whiteKnightTwo);
        this.getSquaresList()[1][7].setIsOccupied(true);
        this.getSquaresList()[6][7].setIsOccupied(true);
        this.getSquaresList()[1][7].setOccupyingPiece(blackKnightOne);
        this.getSquaresList()[6][7].setOccupyingPiece(blackKnightTwo);
    }

    /**
     * Setup 2 queens white and black in their initial positions.
     */
    public void setupQueens() {
        chessQueen whiteQueen = new chessQueen(3, 0, Color.white, this);
        chessQueen blackQueen = new chessQueen(3, 7, Color.black, this);
        this.getSquaresList()[3][0].setIsOccupied(true);
        this.getSquaresList()[3][7].setIsOccupied(true);
        this.getSquaresList()[3][0].setOccupyingPiece(whiteQueen);
        this.getSquaresList()[3][7].setOccupyingPiece(blackQueen);
    }

    /**
     * Setup 2 queens white and black in their initial positions.
     */
    public void setupKings() {
        chessKing whiteKing = new chessKing(4, 0, Color.white, this);
        chessKing blackKing = new chessKing(4, 7, Color.black, this);
        this.getSquaresList()[4][0].setIsOccupied(true);
        this.getSquaresList()[4][7].setIsOccupied(true);
        this.getSquaresList()[4][0].setOccupyingPiece(whiteKing);
        this.getSquaresList()[4][7].setOccupyingPiece(blackKing);
        whiteKingTracker = whiteKing;
        blackKingTracker = blackKing;
    }

    /**
     * Helper method to check if locations are on our generated board.
     * @see cis350.games.Board#inBoardBounds(int, int)
     * @param newX new x coordinate
     * @param newY new y coordinate
     * @return boolean true if move is in board bounds
     */
    public boolean inBoardBounds(final int newX, final int newY) {
        if (newX < getNumXSquares() && newY
                < getNumYSquares() && newX > -1 && newY > -1) {
            return true;
        } else {
            return false;
        }
    }

}
