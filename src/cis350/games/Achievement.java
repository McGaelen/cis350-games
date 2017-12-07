package cis350.games;

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
    
    /** win three moves achievement */
    TTT_WIN_THREE,
    
    /** win full board achievement */
    TTT_WIN_FULL_BOARD,
    

    /* ------------------------ */


    /* Chess Achievements */
    CHESS_FIRST_WIN,
    CHESS_COMPLETE_RANDOM_GAME,
    CHESS_COMPLETE_LEGALS_GAME,
    CHESS_COMPLETE_PEASANTS_GAME,
    /* ------------------------ */


    /* Checkers Achievements */
    CHECKERS_FIRST_WIN,
    CHECKERS_WIN_STREAK_3,
    CHECKERS_WIN_STREAK_5
    /* ------------------------ */
}
