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
	 * Player name
	 */
	public String playerName;
	/**
	 * Player color
	 */
	Color playerColor;
	/**
	 * Player Score
	 */
	public int playerScore;

	/**
	 * Constructor to add a new player to the game.
	 * @param playerName
	 * @param playerColor
	 */
	public checkersPlayer(String playerName, Color playerColor) {
		this.playerName = playerName;
		this.playerColor = playerColor;
		this.playerScore = 0;
		
	}
}

