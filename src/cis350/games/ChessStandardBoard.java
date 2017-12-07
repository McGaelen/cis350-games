package cis350.games;

import java.util.Random;

/**
 * Subclass of a board. Standard version of a chess Board.
 * Has methods for creating a standard chess board
 * Populates it with regular chess pieces.
 * Can be used to create a standard game of Chess.
 */
public class ChessStandardBoard extends ChessBoard {

  /**
* Tracker for the white king for check, checkmate and game ending conditions.
*/
  private ChessKing whiteKingTracker;
  /**
  * Tracker black king for check, checkmate and game ending conditions.
  */
  private ChessKing blackKingTracker;
  
  /**
  * Returns white king tracker.
  * @return whiteKingTracker
  */
  public ChessKing getWhiteKingTracker() {
      return this.whiteKingTracker;
  }
  
  /**
  * Returns black king tracker.
  * @return blackKingTracker
  */
  public ChessKing getBlackKingTracker() {
      return this.blackKingTracker;
  }
  /**
  Method to initialize the chess board.
* @param xSquares Number of Squares on the x-axis
* @param ySquares Number of Squares on the y-axis
*/
  public ChessStandardBoard(final int xSquares, final int ySquares) {

    this.setNumXSquares(xSquares);
    this.setNumYSquares(ySquares);
    this.setTotalSquares(this.getNumXSquares() * this.getNumYSquares());
    this.setSquaresList(new ChessSquare
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
            getSquaresList()[i][j] = new ChessSquare(false, Color.black);
          } else {
            getSquaresList()[i][j] = new ChessSquare(false, Color.white);
          }
        } else {
          if (j % 2 == 0) {
            getSquaresList()[i][j] = new ChessSquare(false, Color.white);
          } else {
            getSquaresList()[i][j] = new ChessSquare(false, Color.black);
          }
        }
      }
    }
  }

    /**
     * Method to populate our chess board with standard pieces.
     * @param type Is the game type different
     */
    public void populateBoardWithPieces(final int type) {
        if (type == 3) {
        setupKnights();
        setupBishops();
        setupPawns();
        setupRooks();
        setupQueens();
        setupKings();
        } else if (type == 1) {
            PeasantsBoard();
            
        } else if (type == 0) {
            randomizeBoard();
        } else if (type == 2) {
            LegalBoard();
        }
    }

    private void LegalBoard() {
        setupKnights();
        setupBishops();
        setupPawns();
        setupRooks();
        setupQueens();
        setupKings();
        this.getSquaresList()[3][0].setIsOccupied(false);
        this.getSquaresList()[3][0].setOccupyingPiece(null);
        addWhitePawn(2, 1);
        addWhitePawn(2, 2);
        addWhitePawn(3, 2);
        addWhitePawn(3, 3);
        addWhitePawn(3, 4);
        addWhitePawn(3, 5);
        addWhitePawn(2, 5);
        addWhitePawn(2, 6);
        
    }

    private void PeasantsBoard() {
        setupKings();
        addWhitePawn(1, 0);
        addWhitePawn(1, 1);
        addWhitePawn(1, 2);
        addWhitePawn(1, 3);
        addWhitePawn(1, 4);
        addWhitePawn(1, 5);
        addWhitePawn(1, 6);
        addWhitePawn(1, 7);
        addBlackPawn(6, 4);
        addBlackKnight(7, 1);
        addBlackKnight(7, 2);
        addBlackKnight(7, 5);
        addBlackKnight(7, 6);
        
    }

    private void randomizeBoard() {
        int wpCount = 0;
        int wbCount = 0;
        int wrCount = 0;
        int wknightCount = 0;
        int wqCount = 0;
        int wkingCount = 0;
        int bpCount = 0;
        int bbCount = 0;
        int brCount = 0;
        int bknightCount = 0;
        int bqCount = 0;
        int bkingCount = 0;
        
        //Adds White Pieces
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 8; j++) {
                int k = 0;
                while(k == 0) {
                    Random rand = new Random(); 
                    int value = rand.nextInt(6);
                    if(value == 0 && wpCount != 8) {
                        addWhitePawn(i,j);
                        wpCount = wpCount + 1;
                        k = 1;
                    }
                    else if(value == 1 && wbCount != 4) {
                        addWhiteBishop(i,j);
                        wbCount = wbCount + 1;
                        k = 1;
                    }
                    else if(value == 2 && wrCount != 4) {
                        addWhiteRook(i,j);
                        wrCount = wrCount + 1;
                        k = 1;
                    }
                    else if(value == 3 && wknightCount != 4) {
                        addWhiteKnight(i,j);
                        wknightCount = wknightCount + 1;
                        k = 1;
                    }
                    else if(value == 4 && wqCount != 2) {
                        addWhiteQueen(i,j);
                        wqCount = wqCount + 1;
                        k = 1;
                    }
                    else if(value == 5 && wkingCount != 1) {
                        addWhiteKing(i,j);
                        wkingCount = wkingCount + 1;
                        k = 1;
                    }
                }
            }
        }
        
      //Adds Black Pieces
        for(int i = 6; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                int k = 0;
                while(k == 0) {
                    Random rand = new Random(); 
                    int value = rand.nextInt(6);
                    if(value == 0 && bpCount != 8) {
                        addBlackPawn(i,j);
                        bpCount = bpCount + 1;
                        k = 1;
                    }
                    else if(value == 1 && bbCount != 4) {
                        addBlackBishop(i,j);
                        bbCount = bbCount + 1;
                        k = 1;
                    }
                    else if(value == 2 && brCount != 4) {
                        addBlackRook(i,j);
                        brCount = brCount + 1;
                        k = 1;
                    }
                    else if(value == 3 && bknightCount != 4) {
                        addBlackKnight(i,j);
                        bknightCount = bknightCount + 1;
                        k = 1;
                    }
                    else if(value == 4 && bqCount != 2) {
                        addBlackQueen(i,j);
                        bqCount = bqCount + 1;
                        k = 1;
                    }
                    else if(value == 5 && bkingCount != 1) {
                        addBlackKing(i,j);
                        bkingCount = bkingCount + 1;
                        k = 1;
                    }
                }
            }
        }
        
        
    }

    private void addBlackKing(int i, int j) {
        ChessKing blackKing = new ChessKing(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(blackKing);
        blackKingTracker = blackKing;
        
    }

    private void addBlackQueen(int i, int j) {
        ChessQueen blackQueen = new ChessQueen(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(blackQueen);
        
    }

    private void addBlackKnight(int i, int j) {
        ChessKnight blackKnight = new ChessKnight(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(blackKnight);
        
    }

    private void addBlackRook(int i, int j) {
        ChessRook blackRook = new ChessRook(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(blackRook);
        
    }

    private void addBlackBishop(int i, int j) {
        ChessBishop blackBishop = new ChessBishop(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(blackBishop);
        
    }

    private void addBlackPawn(int i, int j) {
        ChessPawn newblackPawn = new ChessPawn(j, i, Color.black, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(newblackPawn);
        
    }

    private void addWhiteKing(int i, int j) {
        ChessKing whiteKing = new ChessKing(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(whiteKing);
        whiteKingTracker = whiteKing;
    }

    private void addWhiteQueen(int i, int j) {
        ChessQueen whiteQueen = new ChessQueen(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(whiteQueen);
    }

    private void addWhiteKnight(int i, int j) {
        ChessKnight whiteKnight = new ChessKnight(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(whiteKnight);
    }

    private void addWhiteRook(int i, int j) {
        ChessRook whiteRook = new ChessRook(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(whiteRook);
    }

    private void addWhiteBishop(int i, int j) {
        ChessBishop whiteBishop = new ChessBishop(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(whiteBishop);
    }

    private void addWhitePawn(int i, int j) {
        ChessPawn newWhitePawn = new ChessPawn(j, i, Color.white, this);
        this.getSquaresList()[j][i].setIsOccupied(true);
        this.getSquaresList()[j][i].setOccupyingPiece(newWhitePawn);
    }

    /**
     * Setup 8 black and 8 white pawns in their initial positions.
     */
    public void setupPawns() {
        for (int i = 0; i < 8; i++) {
            ChessPawn newWhitePawn = new ChessPawn(i, 1, Color.white, this);
            ChessPawn newBlackPawn = new ChessPawn(i, 6, Color.black, this);
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
        ChessRook whiteRookOne = new ChessRook(0, 0, Color.white, this);
        ChessRook whiteRookTwo = new ChessRook(7, 0, Color.white, this);
        ChessRook blackRookOne = new ChessRook(0, 7, Color.black, this);
        ChessRook blackRookTwo = new ChessRook(7, 7, Color.black, this);
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
        ChessBishop whiteBishopOne = new ChessBishop(2, 0, Color.white, this);
        ChessBishop whiteBishopTwo = new ChessBishop(5, 0, Color.white, this);
        ChessBishop blackBishopOne = new ChessBishop(2, 7, Color.black, this);
        ChessBishop blackBishopTwo = new ChessBishop(5, 7, Color.black, this);
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
        ChessKnight whiteKnightOne = new ChessKnight(1, 0, Color.white, this);
        ChessKnight whiteKnightTwo = new ChessKnight(6, 0, Color.white, this);
        ChessKnight blackKnightOne = new ChessKnight(1, 7, Color.black, this);
        ChessKnight blackKnightTwo = new ChessKnight(6, 7, Color.black, this);
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
        ChessQueen whiteQueen = new ChessQueen(3, 0, Color.white, this);
        ChessQueen blackQueen = new ChessQueen(3, 7, Color.black, this);
        this.getSquaresList()[3][0].setIsOccupied(true);
        this.getSquaresList()[3][7].setIsOccupied(true);
        this.getSquaresList()[3][0].setOccupyingPiece(whiteQueen);
        this.getSquaresList()[3][7].setOccupyingPiece(blackQueen);
    }

    /**
     * Setup 2 queens white and black in their initial positions.
     */
    public void setupKings() {
        ChessKing whiteKing = new ChessKing(4, 0, Color.white, this);
        ChessKing blackKing = new ChessKing(4, 7, Color.black, this);
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
