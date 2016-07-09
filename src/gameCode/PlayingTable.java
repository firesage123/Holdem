package gameCode;

public class PlayingTable {
	// Creates and simulates the Texas Hold'em playing table.
	
	// Class fields
	private int numPlayers;
	private Deck deck;
	
	/**
	 * Constructs the playing table on which 
	 * the game is to be played
	 * 
	 * @param numberOfPlayers
	 */
	public PlayingTable(int numberOfPlayers) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
	}
	
	
}
