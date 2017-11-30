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
    private String playerName;
    /**
     * Player color.
     */
    private Color playerColor;
    /**
     * Player Score.
     */
    private int playerScore;

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
    
    /**
     * Returns player name.
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets player name.
     * @param playerName player name
     */
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns player color.
     * @return playerColor
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * Sets player color.
     * @param playerColor player color
     */
    public void setPlayerColor(final Color playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Returns player score.
     * @return playerScore
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Sets player score.
     * @param playerScore player's score
     */
    public void setPlayerScore(final int playerScore) {
        this.playerScore = playerScore;
    }
}

