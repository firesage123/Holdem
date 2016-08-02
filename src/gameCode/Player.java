package gameCode;

import java.util.ArrayList;

public class Player {
	// Implements the players of the game
	
	// Class fields
	private ArrayList<Card> hand;
	private ArrayList<Card> sorted_hand;
	private int money;
	private boolean smallBlind;
	private boolean bigBlind;
	private boolean fold;
	private int playerId;
	
	/**
	 * Constructs a Player Object
	 * 
	 * @param startingMoney
	 */
	public Player(int startingMoney) {
		this.money = startingMoney;
		this.hand = new ArrayList<Card>();
		this.smallBlind = false;
		this.bigBlind = false;
		this.fold = false;
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
	 * 
	 * @return money
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Adds specified amount to Player's bank
	 * 
	 * @param amount
	 */
	public void addMoney(int amount) {
		this.money += amount;
	}
	
	/**
	 * Sets the player's small blind status
	 * 
	 * @param smallBlind
	 */
	public void setSmallBlind(boolean smallBlind) {
		this.smallBlind = smallBlind;
	}
	
	/**
	 * Sets the player's big blind status
	 * 
	 * @param bigBlind
	 */
	public void setBigBlind(boolean bigBlind) {
		this.bigBlind = bigBlind;
	}
	
	/**
	 * Return small blind status
	 * 
	 * @return smallBlind 
	 */
	public boolean isSmallBlind() {
		return this.smallBlind;
	}
	
	/**
	 * Returns big blind status
	 * 
	 * @return bigBlind
	 */
	public boolean isBigBlind() {
		return this.bigBlind;
	}
	
	/**
	 * Player bets money, adds to pot
	 * Goes all-in if not enough money
	 * 
	 * @param amount
	 */
	public void bet(PlayingTable table, int amount) {
		int actualAmount;
		if (this.money > amount) {
			this.money -= amount;
			actualAmount = amount;
		}
		else {
			actualAmount = this.money;
			this.money = 0;
		}
		table.addToPot(actualAmount);
	}
	
	/**
	 * Player folds
	 */
	public void fold() {
		this.fold = true;
	}
	
	/**
	 * Clears the players hand and sets blind status to false
	 */
	public void clearForRound() {
		if (this.fold == true)
			this.fold = false;
		this.hand = new ArrayList<Card>();
		this.sorted_hand = new ArrayList<Card>();
		this.setBigBlind(false);
		this.setSmallBlind(false);
	}
	
	/**
	 * Returns a Player's id
	 * 
	 * @return playerId
	 */
	public int getPlayerId() {
		return this.playerId;
	}
	
	/**
	 * Sets a Player's id
	 * 
	 * @param playerId
	 */
	public void setPlayerId(int id) {
		this.playerId = id;
	}
	
	/**
	 * 
	 */
	public void turn() {
		
	}
}
