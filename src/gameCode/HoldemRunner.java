package gameCode;

public class HoldemRunner {
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		deck.printDeck();
		System.out.println(deck.getDeckCount());
	}
}
