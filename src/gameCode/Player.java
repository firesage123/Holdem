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
	private boolean in;
	private int playerId;
	private int totalBet;
	
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
		this.in = true;
		this.totalBet = 0;
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
	 * @param table
	 * @param amount - amount player wants to bet
	 * @param highestBet - the highest bet on the table
	 * @return newhigh
	 */
	public int bet(PlayingTable table, int amount, int highestBet) {
		int actualAmount;
		int newhigh = highestBet;
		// Cannot call current bet and/or goes all-in
		if (this.money <= highestBet || amount == this.money) {
			actualAmount = this.money;
			this.totalBet += this.money;
			this.money = 0;
			System.out.println("Player " + this.playerId + " is all-in!");
		}
		// Otherwise
		else {
			this.money -= amount;
			actualAmount = amount;
			this.totalBet += amount;
			if (amount == highestBet || this.totalBet == highestBet)
				System.out.println("Player " + this.playerId + " calls!");
			else {
				System.out.println("Player " + this.playerId + " raises by " + (this.totalBet - highestBet) + "!");
				newhigh = amount;
			}
		}
		table.addToPot(actualAmount);
		return newhigh;
	}
	
	/**
	 * Player folds
	 */
	public void fold() {
		this.fold = true;
	}
	
	/**
	 * Returns a player's fold status
	 * 
	 * @return fold
	 */
	public boolean isFolded() {
		return this.fold;
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
		this.totalBet = 0;
	}
	
	/**
	 * Simulates a player's turn
	 * 
	 * @param table
	 * @param highestBet - the highest bet amount on table
	 * @return newhigh
	 */
	public int turn(PlayingTable table, int highestBet) {
		System.out.print("Player " + this.playerId + "'s turn: ");
		if (highestBet > this.totalBet)
			return this.bet(table, highestBet-totalBet, highestBet);
		else
			return highestBet;
	}
	
	/**
	 * Returns whether the player is still in the game
	 * 
	 * @return in
	 */
	public boolean getInStatus() {
		return this.in;
	}
	
	/**
	 * Sets the player's in status
	 * 
	 * @param inStatus
	 */
	public void setInStatus(boolean inStatus) {
		this.in = inStatus;
	}
	
	/**
	 * Returns the player's id
	 * 
	 * @return playerId
	 */
	public int getPlayerId() {
		return this.playerId;
	}
	
	/**
	 * Sets the player's id
	 * 
	 * @param playerId
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	/**
	 * Returns the player's totalBet
	 * 
	 * @return totalBet
	 */
	public int getTotalBet() {
		return this.totalBet;
	}
	
	/**
	 * Sets the player's totalbet
	 * 
	 * @param total
	 */
	public void setTotalBet(int total) {
		this.totalBet = total;
	}
}
