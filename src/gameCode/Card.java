package gameCode;

public class Card { 
	// Implements a single playing Card
	
	// Class fields
	private String value;
	private String suit;
	
	/**
	 * Card Constructor
	 * @param value
	 * @param suit
	 */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	/**
	 * @return Returns the value of the card
	 */
	public String getCardStringValue() {
		return this.value;
	}
	
	/**
	 * @return Returns the suit of the card
	 */
	public String getSuitStringValue() {
		return this.suit;
	}
	
	/** 
	 * Following convention is used for face cards:
	 * Jack - 11, Queen - 12, King - 13, Ace - 14
	 *
	 * @return numeric value of Card
	 */
	public int getCardIntValue() {
		int result;
		if (this.value.equalsIgnoreCase("J"))
			result = 11;
		else if (this.value.equalsIgnoreCase("Q"))
			result = 12;
		else if (this.value.equalsIgnoreCase("K"))
			result = 13;
		else if (this.value.equalsIgnoreCase("A"))
			result = 14;
		else
			result = Integer.parseInt(this.value);
		return result;
	}
	
	/**
	 * Following convention is used:
	 * Spades - 1, Hearts - 2, Diamonds - 3, Clubs - 4
	 * 
	 * @return numeric value of suit of the Card
	 */
	public int getSuitIntValue() {
		int result;
		if (this.suit.equalsIgnoreCase("S"))
			result = 1;
		else if (this.suit.equalsIgnoreCase("H"))
			result = 2;
		else if (this.suit.equalsIgnoreCase("D"))
			result = 3;
		else if (this.suit.equalsIgnoreCase("C"))
			result = 4;
		else // Invalid suit input
			result = -1;
		return result;
	}
	
	/**
	 * Prints a String representation of a Playing Card
	 */
	public void printCard() {
		System.out.print(this.getCardIntValue() + "-" + this.getSuitStringValue() + " ");
	}
}

