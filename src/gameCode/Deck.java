package gameCode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Deck {	
	// Implements a standard deck of 52 playing Cards
	
	// Static fields
	private final static int TOTAL_CARD_COUNT = 52;
	
	// Suits and Card values
	private final static String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};
	private final static String[] CARD_VALUES = {"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
	
	// Class fields
	private int current_card_count;
	private ArrayList<Card> deck; // Standard deck
	private Hashtable<String, ArrayList<Card>> memo_deck; // Keeps track of which cards are still present
	
	/**
	 * Constructs the card deck
	 */
	public Deck() {
		this.current_card_count = TOTAL_CARD_COUNT;
		deck = new ArrayList<Card>();
		memo_deck = new Hashtable<String, ArrayList<Card>>();
		for (String suit : SUITS) {
			ArrayList<Card> temp_cards = new ArrayList<Card>();
			for (String cv : CARD_VALUES) {
				Card c = new Card(cv, suit);
				temp_cards.add(c);
				deck.add(c);
			}
			memo_deck.put(suit, temp_cards);
			temp_cards.clear();
		}
	}
	
	/**
	 * @return current number of Cards in the Deck
	 */
	public int getDeckCount() {
		return this.current_card_count;
	}
	
	/**
	 * Removes and returns the top Card from the Deck, 
	 * updating the array and card count accordingly
	 * 
	 * @return Top card of deck
	 */
	public Card removeTopCard() {
		Card topCard = this.deck.remove(0);
		this.current_card_count--;
		this.memo_deck.get(topCard.getSuitStringValue()).remove(topCard);
		return topCard;
	}
	
	/**
	 * Shuffles deck of cards using the Durstenfeld variant
	 * of the 1938 Fisher-Yates shuffling algorithm.
	 */
	public void shuffle() {
		Random rand = new Random();
		for (int i = deck.size()-1; i > 0; i--) {
			int j = rand.nextInt(i+1);
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	}
	
	/**
	 * Prints a visualizable representation of the deck
	 */
	public void printDeck() {
		System.out.println("Current Deck of Cards:");
		for (Card c : deck) {
			System.out.print(c.getSuitStringValue() + c.getCardIntValue() + " ");
		}
		System.out.println();
	}
}

