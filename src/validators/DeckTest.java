package validators;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameCode.Deck;
import gameCode.Card;

import org.junit.Test;

public class DeckTest {
	
	@Test
	public void deckConstructorTest() {
		Deck deck = new Deck();
		assertTrue(deck.getDeckCount() == 52);
		assertTrue(deck.getDeck().size() == 52);
		assertTrue(deck.getMemoDeck().values().size() == 4);
		
		boolean flag1 = false;
		for (ArrayList<Card> aList : deck.getMemoDeck().values())
			if (aList.size() != 13)
				flag1 = true;
		assertTrue(!flag1);
		flag1 = false;
	    boolean flag2 = false;
		String[] suits = {"spades", "hearts", "diamonds", "clubs"};
		int counter = 0;
		for (String s : suits) {
			for (int i = 1; i <= 13; i++) {
				Card card = deck.getDeck().get(counter);
				if (!card.getSuitStringValue().equals(s) || !(card.getCardIntValue() == i))
					flag1 = true;
				if (!(deck.getMemoDeck().get(s).get(i-1).getCardIntValue() == i))
					flag2 = true;
				counter++;
			}
		}
		assertTrue(!flag1);
		assertTrue(!flag2);
	}

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
