package game.suite;

/***********************************************************************
 * Enums for achievement in each game.
 * @author Gaelen
 * @version 12/7/17
 **********************************************************************/
public enum Achievement {
    /* Connect Four Achievements */
	
	/** diagonal achievement win. */
    C4_DIAGONAL_WIN,
    
    /** win under moves achievement. */
    C4_WIN_UNDER_MOVES_COUNT,
    
    /** full board achievement. */
    C4_FULL_BOARD,
    /* ------------------------ */


    /** Tic Tac Toe Achievements. */
    
    /** tie achievement. */
    TTT_TIE,
    
    /** win three moves achievement. */
    TTT_WIN_THREE,
    
    /** win full board achievement. */
    TTT_WIN_FULL_BOARD,
    

    /* ------------------------ */


    /* Chess Achievements */
    
    /** first win achievement. */
    CHESS_FIRST_WIN,
    
    /** complete random game type achievement. */
    CHESS_COMPLETE_RANDOM_GAME,
    
    /** complete legals game type achievement. */
    CHESS_COMPLETE_LEGALS_GAME,
    
    /** complete peasants game type achievement. */
    CHESS_COMPLETE_PEASANTS_GAME,
    /* ------------------------ */


    /* Checkers Achievements */
    
    /** first win achievement. */
    CHECKERS_FIRST_WIN,
    
    /** 3 win streak achievement. */
    CHECKERS_WIN_STREAK_3,
    
    /** 5 win streak achievement. */
    CHECKERS_WIN_STREAK_5
    /* ------------------------ */
    
}
