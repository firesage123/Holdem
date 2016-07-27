package validators;

import static org.junit.Assert.*;

import gameCode.Card;

import org.junit.Test;

public class CardTest {
	
	@Test
	public void getCardIntValueAndSuitTest() {
		// Example cards
		Card testCard1 = new Card("jack","hearts");
		Card testCard2 = new Card("queen","spades");
		Card testCard3 = new Card("king","clubs");
		Card testCard4 = new Card("ace","diamonds");
		Card testCard5 = new Card("4","hearts");
		// getCardIntValue() tests
		assertTrue(testCard1.getCardIntValue() == 11);
		assertTrue(testCard2.getCardIntValue() == 12);
		assertTrue(testCard3.getCardIntValue() == 13);
		assertTrue(testCard4.getCardIntValue() == 14);
		assertTrue(testCard5.getCardIntValue() == 4);
		// getSuitIntValue() tests
		assertTrue(testCard1.getSuitIntValue() == 2);
		assertTrue(testCard2.getSuitIntValue() == 1);
		assertTrue(testCard3.getSuitIntValue() == 4);
		assertTrue(testCard4.getSuitIntValue() == 3);
		assertTrue(testCard5.getSuitIntValue() == 2);
	}
}
