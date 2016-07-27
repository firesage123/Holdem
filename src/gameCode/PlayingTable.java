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
	
	/**
	 * Determines which player has won the round,
	 * based on each player's hand.
	 * 
	 * For a detailed explanation behind this method,
	 * please consult the Holdem Github Wiki page:
	 * https://github.com/firesage123/Holdem/wiki
	 * 
	 */
	public void determineWinner() {
		// Sort each player's hand with the community cards (combined seven cards)
		int[] hand_evaluations = new int[this.numPlayers];
		int counter = 0;
		for (Player p : this.players) {
			ArrayList<Card> complete_set = new ArrayList<Card>(p.getHand());
			complete_set.addAll(this.communityCards);
			
			ArrayList<Card> sorted_set = sortHand(complete_set);
			p.setSortedValueHand(sorted_set);
			hand_evaluations[counter] = evaluateHand(p.getSortedValueHand());
		}
		int max = hand_evaluations[0];
		int max_index = 0;
		for (int i = 1; i < hand_evaluations.length; i++) {
			if (hand_evaluations[i] > max) {
				max = hand_evaluations[i];
				max_index = i;
			}
		}
		System.out.println("Winner is: Player " + (max_index+1) + "!");
	}
	
	/**
	 * Implements an optimized bucket sort algorithm
	 * 
	 * For a detailed explanation and reasonings
	 * behind this method, please consult the 
	 * Holdem Github Wiki page:
	 * https://github.com/firesage123/Holdem/wiki
	 * 
	 * @param hand
	 * @return result
	 */
	public ArrayList<Card> sortHand(ArrayList<Card> hand) {
		// Create buckets
		ArrayList<ArrayList<Card>> buckets = new ArrayList<ArrayList<Card>>();
		for (int i = 1; i <= 7; i++)
			buckets.add(new ArrayList<Card>());
		
		// Distributes cards into buckets (based on card value)
		for (Card c : hand) 
			buckets.get(c.getCardIntValue()/2 - 1).add(c);
		
		// Merge buckets into a single list
		ArrayList<Card> merged_list = new ArrayList<Card>();
		for (ArrayList<Card> bucket : buckets)
			merged_list.addAll(bucket);
		
		// Uses insertion sort to sort merged buckets - see reasoning in wiki page
		for (int i = 1; i < merged_list.size(); i++) {
			Card current = merged_list.get(i);
			int j = i - 1;
			while (j >= 0 && merged_list.get(j).getCardIntValue() > current.getCardIntValue()) {
				merged_list.set(j+1, merged_list.get(j));
				j = j - 1;
			}
			merged_list.set(j+1, current);
		}
		
		return merged_list; 
	}
	
	/**
	 * Evaluation function for a player's possible hands
	 * 
	 * @param set - set of seven cards of which a player
	 * 				can get the highest five-card hand
	 * @return evaluation - represents best hand possible
	 */
	public int evaluateHand(ArrayList<Card> set) {
		/*To be implemented*/
		return 0;
	}
}
