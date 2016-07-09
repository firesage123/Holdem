package gameCode;

import java.util.ArrayList;

public class PlayingTable {
	// Creates and simulates the Texas Hold'em playing table.
	
	// Class fields
	private int numPlayers;
	private Deck deck;
	private ArrayList<Player> players;
	
	/**
	 * Constructs the playing table on which 
	 * the game is to be played
	 * 
	 * @param numberOfPlayers
	 * @param startingMoney
	 */
	public PlayingTable(int numberOfPlayers, int startingMoney) {
		this.numPlayers = numberOfPlayers;
		this.deck = new Deck();
		this.deck.shuffle();
		this.players = new ArrayList<Player>();
		
		for (int i = 0; i < numberOfPlayers; i++) {
			Player temp = new Player(startingMoney);
			this.players.add(temp);
		}
	}
	
	/**
	 * Gives the top card to the intended Player
	 * 
	 * @param intendedPlayer
	 */
	public void dealTopCard(Player intendedPlayer) {
		Card topCard = this.deck.removeTopCard();
		intendedPlayer.addCard(topCard);
	}
	
	/**
	 * Deals two cards to each Player, as 
	 * following with the rules of poker.
	 */
	public void deal() {
		for (int i = 0; i <= 1; i++) {
			for (Player p : this.players) {
				dealTopCard(p);
			}
		}
	}
}
