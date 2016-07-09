
public class GameBoard {
	// Creates and simulates the Texas Hold'em game board
	
	// Class fields
	private int numPlayers;
	private Deck deck;
	
	/**
	 * Constructs the playing table on which 
	 * the game is to be played
	 * 
	 * @param numberOfPlayers
	 */
	public GameBoard(int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
	}
	
	
}
