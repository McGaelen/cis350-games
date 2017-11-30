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
    private String playerName;
    /**
     * Player color.
     */
    private Color playerColor;
    /**
     * Player score.
     */
    private int playerScore;

    /**
     * Constructor to add a new player to the game.
     * @param cPlayerName This is the Player name
     * @param cPlayerColor This is the Player color
     */
    public chessPlayer(final String cPlayerName, final Color cPlayerColor) {
        this.setPlayerName(cPlayerName);
        this.setPlayerColor(cPlayerColor);
        this.setPlayerScore(0);

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

