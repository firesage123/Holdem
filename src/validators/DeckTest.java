package validators;

import static org.junit.Assert.*;

import gameCode.Deck;
import gameCode.Card;

import org.junit.Test;

public class DeckTest {
	
	@Test
	public void deckConstructorTest() {
		Deck deck = new Deck();
		assertTrue(deck.getDeckCount() == 52);
		assertTrue(deck.getDeck().size() == 52);
		
		boolean flag = false;
		String[] suits = {"spades", "hearts", "diamonds", "clubs"};
		int counter = 0;
		for (String s : suits) {
			for (int i = 1; i <= 13; i++) {
				Card card = deck.getDeck().get(counter);
				if (!card.getSuitStringValue().equals(s) || !(card.getCardIntValue() == i))
					flag = true;
				counter++;
			}
		}
		assertTrue(!flag);
	}

	@Test
	public void removeTopCardTest() {
		Deck deck = new Deck();
		deck.shuffle();
		Card temp = deck.getDeck().get(0);
		Card topCard = deck.removeTopCard();
		assertTrue(deck.getDeckCount() == 51);
		assertTrue(temp.equals(topCard));
	}

}
