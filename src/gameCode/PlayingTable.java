package gameCode;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayingTable {
	// Creates and simulates the Texas Hold'em playing table.
	
	// Class fields
	private int numPlayers;
	private Deck deck;
	private ArrayList<Player> players;
	private ArrayList<Card> communityCards;
	private ArrayList<Card> burnPile;
	
	// Scores
	private final int STRAIGHT_FLUSH = 800;
	private final int FOUR_KIND = 700;
	private final int FULL_HOUSE = 600;
	private final int FLUSH = 500;
	private final int STRAIGHT = 400;
	private final int THREE_KIND = 300;
	private final int TWO_PAIR = 200;
	private final int PAIR = 100;
	private final int HIGH_CARD = 0;
	
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
		System.out.println();
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
			hand_evaluations[counter] = evaluateHand(p.getHand(), this.communityCards);
			System.out.println("Player " + (counter + 1) + ": " + hand_evaluations[counter]);
			counter++;
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
		for (int i = 0; i < hand.size(); i++)
			buckets.add(new ArrayList<Card>());
		
		// Distributes cards into buckets (based on card value)
		for (Card c : hand) 
			buckets.get((int)Math.ceil(c.getCardIntValue()/3.0)-1).add(c);
		
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
	 * @param hand - player's hole cards
	 * @param community - 5-card community set
	 * 
	 * @return evaluation - represents best hand possible
	 */
	public int evaluateHand(ArrayList<Card> hand, ArrayList<Card> community) {
		int[] scores = new int[10];
		int counter = 0;
		ArrayList<Card> comboHand = new ArrayList<Card>(hand);
		for(int firstCard = 0; firstCard < 3; firstCard++) {
			comboHand.add(community.get(firstCard));
		    for(int secondCard = firstCard + 1; secondCard < 4; secondCard++) {
		    	comboHand.add(community.get(secondCard));
		        for(int thirdCard = secondCard + 1; thirdCard < 5; thirdCard++) {
		            comboHand.add(community.get(thirdCard));
		            ArrayList<Card> sorted_hand = sortHand(comboHand);
		            int highscore = highCardScore(hand);
		            int score1 = 0;
		            if (checkFlush(sorted_hand))
		            	score1 += this.FLUSH + highscore;
		            if (checkStraight(sorted_hand))
		            	score1 += this.STRAIGHT + highscore;
		            if (score1 == this.FLUSH + this.STRAIGHT)
		            	score1 = this.STRAIGHT_FLUSH + highscore;
		            
		            int score2 = this.evaluatePairs(sorted_hand);
		            if (score2 == 0)
		            	score2 += highscore;
		            if (score1 > score2)
		            	scores[counter] = score1;
		            else
		            	scores[counter] = score2;
		            counter++;
		            comboHand.remove(community.get(thirdCard));
		        }
		        comboHand.remove(community.get(secondCard));
		    }
		    comboHand.remove(community.get(firstCard));
		}
		int evaluation = 0;
		for (int score : scores)
			if (score > evaluation)
				evaluation = score;
		return evaluation;
	}
	
	/**
	 * Checks for duplicated values in hand.
	 * If duplicates exist, sort them by suit (S->H->D->C)
	 * 
	 * @param hand - sorted 5-card hand
	 * @return result - a tuple (x,y) where x = duplicated value, y = # of times duplicated
	 */
	public HashMap<Integer,Integer> checkDuplicatesAndRefresh(ArrayList<Card> hand) {
		HashMap<Integer,Integer> result = new HashMap<Integer,Integer>();
		for (int i = 0; i < hand.size()-1; i++) {
			if (hand.get(i).getCardIntValue() == hand.get(i+1).getCardIntValue()) {
				if (result.containsKey(hand.get(i).getCardIntValue()))
					result.put(hand.get(i).getCardIntValue(), result.get(i)+1);
				else
					result.put(hand.get(i).getCardIntValue(), 2);
			}
		}
		if (result.keySet().size() != 0) {
			for (Integer key : result.keySet()) {
				int numDups = result.get(key);
				int index = 0;
				for (int i = 0; i < hand.size(); i++) {
					if (hand.get(i).getCardIntValue() == key) {
						index = i;
						break;
					}
					for (int j = index; j < index+numDups; j++) {
						Card current = hand.get(j);
						int k = j - 1;
						while (k >= 0 && hand.get(k).getSuitIntValue() > current.getSuitIntValue()) {
							hand.set(k+1, hand.get(k));
							k = k - 1;
						}
						hand.set(k+1, current);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Checks if a given sorted hand has a straight
	 * 
	 * @param hand
	 * @return result
	 */
	public boolean checkStraight(ArrayList<Card> hand) {
		boolean result = true;
		for (int i = 0; i < hand.size()-1; i++)
			if (hand.get(i).getCardIntValue()+1 != hand.get(i+1).getCardIntValue())
				result = false;
		return result;
	}
	
	/**
	 * Checks if a given sorted hand has a flush
	 * 
	 * @param hand
	 * @return result
	 */
	public boolean checkFlush(ArrayList<Card> hand) {
		boolean result = true;
		for (int i = 0; i < hand.size()-1; i++)
			if (hand.get(i).getSuitIntValue() != hand.get(i+1).getSuitIntValue())
				result = false;
		return result;
	}
	
	/**
	 * Evaluates a hand based on 'pair' card combinations
	 * 
	 * @param hand
	 * @return
	 */
	public int evaluatePairs(ArrayList<Card> hand) {
		HashMap<Integer,Integer> dups = checkDuplicatesAndRefresh(hand);
		HashMap<Integer,Integer> com_dups = checkDuplicatesAndRefresh(this.communityCards);
		if (dups.keySet().size() == 1) {
			if (dups.values().contains(4))
				for (Integer key : dups.keySet())
					return this.FOUR_KIND + key;
			else if (dups.values().contains(3))
				for (Integer key : dups.keySet())
					return this.THREE_KIND + key;
			else
				for (Integer key : dups.keySet())
					return this.PAIR + key;
		}
		else if (dups.keySet().size() == 2) {
			int hole_score = 0;
			int max = 0;
			for (Integer key : dups.keySet())
				if (key > max)
					max = key;
			hole_score = max;
			if (dups.values().contains(3))
				return this.FULL_HOUSE + hole_score;
			else {
				int uncommon = 0;
				for (Integer key : dups.keySet())
					if (!com_dups.keySet().contains(key))
						uncommon = key;
				return this.TWO_PAIR + uncommon;
			}
		}
		return this.HIGH_CARD;
	}
	
	/**
	 * Returns max card value
	 * 
	 * @param hand
	 * @return max_score
	 */
	public int highCardScore(ArrayList<Card> hand) {
		int max_score = 0;
		for (Card c : hand) {
			if (c.getCardIntValue() > max_score)
				max_score = c.getCardIntValue();
		}
		return max_score;
	}
}
