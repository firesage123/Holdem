package gameCode;

import java.util.ArrayList;

public class PlayingTable {
	// Creates and simulates the Texas Hold'em playing table.
	
	// Class fields
	private int numPlayers;
	private Deck deck;
	private ArrayList<Player> players;
	private ArrayList<Card> communityCards;
	private ArrayList<Card> burnPile;
	
	/**
	 * Constructs the playing table on which 
	 * the game is to be played
	 * 
	 * @param numberOfPlayers
	 * @param startingMoney
	 */
	public PlayingTable(int numberOfPlayers, int startingMoney) {
		this.numPlayers = numberOfPlayers;
		this.communityCards = new ArrayList<Card>();
		this.burnPile = new ArrayList<Card>();
		
		this.deck = new Deck();
		this.deck.shuffle();
		this.players = new ArrayList<Player>();
		
		for (int i = 0; i < numberOfPlayers; i++) {
			Player temp = new Player(startingMoney);
			this.players.add(temp);
		}
	}
	
	/**
	 * Deals the top card to the intended receiver.
	 * 
	 * @param receiver
	 */
	public void dealTopCard(ArrayList<Card> receiver) {
		Card topCard = this.deck.removeTopCard();
		receiver.add(topCard);
	}
	
	/**
	 * Deals two hole cards to each Player, as 
	 * following with the rules of poker.
	 */
	public void dealHoleCards() {
		for (int i = 0; i <= 1; i++) {
			for (Player p : this.players) {
				dealTopCard(p.getHand());
			}
		}
	}
	
	/**
	 * Deals community cards according to Texas Hold'em
	 * rules (flop, turn, river)
	 * 
	 * @param roundNumber
	 */
	public void dealCommunityCards(int roundNumber) {
		dealTopCard(this.burnPile); // burn
		if (roundNumber == 1) { // flop
			for (int i = 1; i <= 3; i++)
				dealTopCard(this.communityCards);
		}
		else // Turn and river cards
			dealTopCard(this.communityCards);
	}
	
	/**
	 * Returns ArrayList of community cards.
	 */
	public ArrayList<Card> getCommunityCards() {
		return this.communityCards;
	}
	
	/**
	 * Prints a String representation of PlayingTable
	 */
	public void printPlayingTable() {
		System.out.println("Number of Players: " + this.numPlayers);
		int counter = 1;
		for (Player p : this.players) {
			System.out.println("Player info for Player " + counter + ": ");
			System.out.println("Money: " + p.getMoney());
			System.out.print("Hand: ");
			for (Card c : p.getHand())
				c.printCard();
			System.out.println();
			System.out.println();
			counter++;
		}
		System.out.println("Burn Pile: ");
		for (Card c : this.burnPile)
			c.printCard();
		System.out.println();
		System.out.println("Community Cards: ");
		for (Card c : this.communityCards)
			c.printCard();
	}
}
