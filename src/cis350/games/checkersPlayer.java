package cis350.games;

//import cis350.games.Board;
import cis350.games.checkersBoard.Color;

/**
* Class representing a checkers Player.
*
*/
public class checkersPlayer {

    /**
     * Global variables of a checkers Player
     * - Name
     * - Color being played.
     * - Score of the player in game.
     */
    /**
     * Player name.
     */
    public String playerName;
    /**
     * Player color.
     */
    Color playerColor;
    /**
     * Player Score.
     */
    public int playerScore;

    /**
     * Constructor to add a new player to the game.
     * @param cPlayerName This is the Player Name.
     * @param cPlayerColor This is the Player Color.
     */
    public checkersPlayer(final String cPlayerName, final Color cPlayerColor) {
        this.playerName = cPlayerName;
        this.playerColor = cPlayerColor;
        this.playerScore = 0;

    }
}

