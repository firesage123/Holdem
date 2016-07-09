package validators;

import static org.junit.Assert.*;
import gameCode.Deck;
import gameCode.Card;

import org.junit.Test;

public class DeckTest {

	@Test
	public void removeTopCardTest() {
		Deck deck = new Deck();
		deck.shuffle();
		Card temp = deck.getDeck().get(0);
		Card topCard = deck.removeTopCard();
		assertTrue(deck.getDeckCount() == 51);
		assertTrue(temp.equals(topCard));
		assertTrue((deck.getMemoDeck().get(topCard.getSuitStringValue())).size() == 12);
	}

}
