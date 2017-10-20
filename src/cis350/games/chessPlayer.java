package cis350.games;

//import cis350.games.Board;
import cis350.games.chessBoard.Color;

/**
* Class representing a Chess Player.
*
*/
public class chessPlayer {


    /**
    * Player name.
    */
    public String playerName;
    /**
     * Player color.
     */
    Color playerColor;
    /**
     * Player score.
     */
    public int playerScore;

    /**
     * Constructor to add a new player to the game.
     * @param cPlayerName This is the Player name
     * @param cPlayerColor This is the Player color
     */
    public chessPlayer(final String cPlayerName, final Color cPlayerColor) {
        this.playerName = cPlayerName;
        this.playerColor = cPlayerColor;
        this.playerScore = 0;

    }
}

