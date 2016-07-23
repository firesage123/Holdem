package gameCode;

import java.util.ArrayList;

public class Player {
	// Implements the players of the game
	
	// Class fields
	private ArrayList<Card> hand;
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
	 * Returns Player's amount of money
	 */
	public int getMoney() {
		return this.money;
	}
}
