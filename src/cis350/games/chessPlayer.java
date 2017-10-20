package cis350.games;

//import cis350.games.Board;
import cis350.games.chessBoard.Color;

/**
* Class representing a Chess Player.
*
*/
public class chessPlayer {
	
	/**
	 * Global variables of a chess Player
	 * - Name
	 * - Color being played.
	 * - Score of the player in game.
	 */
	public String playerName;
	Color playerColor;
	public int playerScore;

	/**
	 * Constructor to add a new player to the game.
	 * @param playerName
	 * @param playerColor
	 */
	public chessPlayer(String playerName, Color playerColor) {
		this.playerName = playerName;
		this.playerColor = playerColor;
		this.playerScore = 0;
		
	}
}

