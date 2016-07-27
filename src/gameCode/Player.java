package gameCode;

import java.util.ArrayList;

public class Player {
	// Implements the players of the game
	
	// Class fields
	private ArrayList<Card> hand;
	private ArrayList<Card> sorted_hand;
	private int money;
	
	/**
	 * Constructs a Player Object
	 * 
	 * @param startingMoney
	 */
	public Player(int startingMoney) {
		this.money = startingMoney;
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Returns the Player's hand
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	/**
	 * Returns a sorted (by card value) ArrayList
	 * of a Player's cards 
	 * 
	 * For a detailed explanation behind this method,
	 * please consult the Holdem Github Wiki page:
	 * https://github.com/firesage123/Holdem/wiki
	 * 
	 * @return hand
	 */
	public ArrayList<Card> getSortedValueHand() {
		return this.sorted_hand;
	}
	public void setSortedValueHand(ArrayList<Card> sortedHand) {
		this.sorted_hand = sortedHand;
	}
	
	/**
	 * Returns a sorted (by suit value) array
	 * based on the Player's hand
	 * 
	 * For a detailed explanation behind this method,
	 * please consult the Holdem Github Wiki page:
	 * https://github.com/firesage123/Holdem/wiki
	 * 
	 * @return hand_suits
	 */
	public int[] getSortedSuitHand() {
		int[] hand_suits = new int[2];
		int val1 = this.hand.get(0).getSuitIntValue();
		int val2 = this.hand.get(1).getSuitIntValue();
		
		hand_suits[0] = Math.min(val1,val2);
		hand_suits[1] = Math.max(val1, val2);
		
		return hand_suits;
	}
	
	/**
	 * Returns Player's amount of money
	 */
	public int getMoney() {
		return this.money;
	}
}
