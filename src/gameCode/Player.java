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
	 * Adds a specific card to the Player's hand
	 * 
	 * @param card - card dealt to player
	 */
	public void addCard(Card card) {
		if (this.hand.size() < 2)
			this.hand.add(card);
		else 
			System.out.println("Hand is full.");
	}
}
