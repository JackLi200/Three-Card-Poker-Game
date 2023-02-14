import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class MyTest {
	static Card c2;
	static Card d2;
	ArrayList<Card> usedCard;
	ArrayList<Card> playerCard;
	ArrayList<Card> dealerCard;

	@BeforeAll
	static void init() {
		c2 = new Card('C', 2);
		d2 = new Card('D', 2);
	}

	@BeforeEach
	void beforeEach() {
		usedCard = new ArrayList<>();
		playerCard = new ArrayList<>();
		dealerCard = new ArrayList<>();
	}

	@Test
	void cardTest() {
		assertEquals('C', c2.suit);
		assertEquals(2, c2.value);
	}

	@Test
	void DeckConstTest() {
		Deck myDeck = new Deck();
		assertEquals(52, myDeck.size());
	}

	@Test
	void DeckConstDiffTest() {
		Deck myDeck = new Deck();
		assertNotEquals(c2, myDeck.get(0));
		assertNotEquals(d2, myDeck.get(1));
	}

	@Test
	void DeckNewDeckSizeTest() {
		Deck myDeck = new Deck();
		assertNotEquals(c2, myDeck.get(0));
		assertNotEquals(d2, myDeck.get(1));
		assertEquals(52, myDeck.size());
		myDeck.newDeck();
		assertEquals(52, myDeck.size());

	}

	@Test
	void DeckNewDeckDiffTest() {
		Deck myDeck = new Deck();
		Card test1 = myDeck.get(0);
		Card test2 = myDeck.get(1);
		myDeck.newDeck();
		assertNotEquals(test1, myDeck.get(0));
		assertNotEquals(test2, myDeck.get(1));
	}

	@Test
	void DealerConstTest() {
		Dealer dealer = new Dealer();
		assertNotEquals(c2, dealer.theDeck.get(0));
		assertNotEquals(d2, dealer.theDeck.get(1));
		assertEquals(52, dealer.theDeck.size());
	}

	@Test
	void dealHandSizeTest() {
		Dealer dealer = new Dealer();
		assertEquals(52, dealer.theDeck.size());
		dealer.dealHand();
		assertEquals(49, dealer.theDeck.size());

	}

	@Test
	void dealHandDiffTest() {
		Dealer dealer = new Dealer();
		Card one = dealer.theDeck.get(0);
		Card two = dealer.theDeck.get(1);
		Card three = dealer.theDeck.get(2);
		ArrayList<Card> hand;
		hand = dealer.dealHand();
		assertEquals(one, hand.get(0));
		assertEquals(two, hand.get(1));
		assertEquals(three, hand.get(2));

		assertNotEquals(one, dealer.theDeck.get(0));
		assertNotEquals(two, dealer.theDeck.get(1));
		assertNotEquals(three, dealer.theDeck.get(2));
	}

	@Test
	void dealHandMultiTest() {
		Dealer dealer = new Dealer();
		for (int i = 0; i < 6; i++) {
			usedCard.addAll(dealer.dealHand());
		}
		assertEquals(18, usedCard.size());
		assertEquals(34, dealer.theDeck.size());
	}

	@Test
	void dealHandIfTest() {
		Dealer dealer = new Dealer();
		for (int i = 0; i < 6; i++) {
			usedCard.addAll(dealer.dealHand());
		}
		usedCard.addAll(dealer.dealHand());
		assertEquals(21, usedCard.size());
		assertEquals(49, dealer.theDeck.size());
	}

	@Test
	void dealHandContainTest() {
		Dealer dealer = new Dealer();
		for (int i = 0; i < 6; i++) {
			usedCard.addAll(dealer.dealHand());
		}
		assertFalse(dealer.theDeck.containsAll(usedCard));
	}

	@Test
	void CardLogicEvalHandTest0() {
		usedCard.add(new Card('C', 2));
		usedCard.add(new Card('D', 5));
		usedCard.add(new Card('S', 8));
		assertEquals(0, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();
		usedCard.add(new Card('C', 2));
		usedCard.add(new Card('C', 9));
		usedCard.add(new Card('D', 14));
		assertEquals(0, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void CardLogicEvalHandTest1() {
		usedCard.add(new Card('C', 10));
		usedCard.add(new Card('C', 9));
		usedCard.add(new Card('C', 8));
		assertEquals(1, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();

		usedCard.add(new Card('D', 14));
		usedCard.add(new Card('D', 13));
		usedCard.add(new Card('D', 12));
		assertEquals(1, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void CardLogicEvalHandTest2() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 11));
		usedCard.add(new Card('H', 11));
		assertEquals(2, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();

		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('C', 2));
		assertEquals(2, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void CardLogicEvalHandTest3() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 12));
		usedCard.add(new Card('H', 13));
		assertEquals(3, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();

		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 3));
		usedCard.add(new Card('C', 4));
		assertEquals(3, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void CardLogicEvalHandTest4() {
		usedCard.add(new Card('C', 14));
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('C', 3));
		assertEquals(4, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();

		usedCard.add(new Card('D', 10));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 14));
		assertEquals(4, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void CardLogicEvalHandTest5() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 11));
		usedCard.add(new Card('H', 13));
		assertEquals(5, ThreeCardLogic.evalHand(usedCard));

		usedCard.clear();

		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('C', 4));
		assertEquals(5, ThreeCardLogic.evalHand(usedCard));
	}

	@Test
	void cardLogicPPWinningTest0() {
		usedCard.add(new Card('C', 2));
		usedCard.add(new Card('D', 5));
		usedCard.add(new Card('S', 8));
		assertEquals(0, ThreeCardLogic.evalPPWinnings(usedCard, 20));

		usedCard.clear();
		usedCard.add(new Card('C', 2));
		usedCard.add(new Card('C', 9));
		usedCard.add(new Card('D', 14));
		assertEquals(0, ThreeCardLogic.evalPPWinnings(usedCard, 200));
	}

	@Test
	void cardLogicPPWinningTest1() {
		usedCard.add(new Card('C', 10));
		usedCard.add(new Card('C', 9));
		usedCard.add(new Card('C', 8));
		assertEquals(820, ThreeCardLogic.evalPPWinnings(usedCard, 20));

		usedCard.clear();
		usedCard.add(new Card('D', 14));
		usedCard.add(new Card('D', 13));
		usedCard.add(new Card('D', 12));
		assertEquals(12300, ThreeCardLogic.evalPPWinnings(usedCard, 300));
	}

	@Test
	void cardLogicPPWinningTest2() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 11));
		usedCard.add(new Card('H', 11));
		assertEquals(620, ThreeCardLogic.evalPPWinnings(usedCard, 20));

		usedCard.clear();
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('C', 2));
		assertEquals(9300, ThreeCardLogic.evalPPWinnings(usedCard, 300));
	}

	@Test
	void cardLogicPPWinningTest3() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 12));
		usedCard.add(new Card('H', 13));
		assertEquals(700, ThreeCardLogic.evalPPWinnings(usedCard, 100));

		usedCard.clear();
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 3));
		usedCard.add(new Card('C', 4));
		assertEquals(7000, ThreeCardLogic.evalPPWinnings(usedCard, 1000));
	}

	@Test
	void cardLogicPPWinningTest4() {
		usedCard.add(new Card('C', 14));
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('C', 3));
		assertEquals(600, ThreeCardLogic.evalPPWinnings(usedCard, 150));

		usedCard.clear();
		usedCard.add(new Card('D', 10));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 14));
		assertEquals(6000, ThreeCardLogic.evalPPWinnings(usedCard, 1500));
	}

	@Test
	void cardLogicPPWinningTest5() {
		usedCard.add(new Card('C', 11));
		usedCard.add(new Card('D', 11));
		usedCard.add(new Card('H', 13));
		assertEquals(300, ThreeCardLogic.evalPPWinnings(usedCard, 150));

		usedCard.clear();
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('D', 2));
		usedCard.add(new Card('C', 4));
		assertEquals(500, ThreeCardLogic.evalPPWinnings(usedCard, 250));
	}

	@Test	// no one wins when dealer has less than queen high
	void cardLogicCompareNoQueenHighTest() {
		playerCard.add(new Card('C', 8));
		playerCard.add(new Card('H', 5));
		playerCard.add(new Card('D', 2));

		dealerCard.add(new Card('C', 2));
		dealerCard.add(new Card('H', 8));
		dealerCard.add(new Card('D', 5));
		assertEquals(0, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test	// draw when same cards
	void cardLogicCompareHighTest1() {
		playerCard.add(new Card('C', 14));
		playerCard.add(new Card('H', 12));
		playerCard.add(new Card('D', 10));

		dealerCard.add(new Card('C', 10));
		dealerCard.add(new Card('H', 14));
		dealerCard.add(new Card('D', 12));
		assertEquals(0, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test	// player first high higher
	void cardLogicCompareHighTest2() {
		playerCard.add(new Card('C', 15));
		playerCard.add(new Card('H', 12));
		playerCard.add(new Card('D', 10));

		dealerCard.add(new Card('C', 9));
		dealerCard.add(new Card('H', 14));
		dealerCard.add(new Card('D', 12));
		assertEquals(2, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test	// dealer second high higher
	void cardLogicCompareHighTest3() {
		playerCard.add(new Card('C', 15));
		playerCard.add(new Card('H', 12));
		playerCard.add(new Card('D', 9));

		dealerCard.add(new Card('C', 10));
		dealerCard.add(new Card('H', 15));
		dealerCard.add(new Card('D', 12));
		assertEquals(1, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare1Test() {
		playerCard.add(new Card('C', 14));
		playerCard.add(new Card('C', 13));
		playerCard.add(new Card('C', 12));

		dealerCard.add(new Card('D', 13));
		dealerCard.add(new Card('D', 12));
		dealerCard.add(new Card('D', 11));

		assertEquals(2, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare1Test2() {
		playerCard.add(new Card('C', 14));
		playerCard.add(new Card('C', 13));
		playerCard.add(new Card('C', 12));

		dealerCard.add(new Card('D', 14));
		dealerCard.add(new Card('D', 13));
		dealerCard.add(new Card('D', 12));

		assertEquals(0, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare2Test() {
		playerCard.add(new Card('D', 13));
		playerCard.add(new Card('D', 12));
		playerCard.add(new Card('D', 11));

		dealerCard.add(new Card('C', 13));
		dealerCard.add(new Card('D', 13));
		dealerCard.add(new Card('H', 13));

		assertEquals(2, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare2Test2() {
		playerCard.add(new Card('D', 12));
		playerCard.add(new Card('D', 12));
		playerCard.add(new Card('D', 12));

		dealerCard.add(new Card('C', 13));
		dealerCard.add(new Card('D', 13));
		dealerCard.add(new Card('H', 13));

		assertEquals(1, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare3Test() {
		playerCard.add(new Card('D', 12));
		playerCard.add(new Card('C', 13));
		playerCard.add(new Card('H', 14));

		dealerCard.add(new Card('D', 13));
		dealerCard.add(new Card('C', 12));
		dealerCard.add(new Card('H', 11));

		assertEquals(2, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare3Test2() {
		dealerCard.add(new Card('D', 12));
		dealerCard.add(new Card('C', 13));
		dealerCard.add(new Card('H', 14));

		playerCard.add(new Card('D', 13));
		playerCard.add(new Card('D', 13));
		playerCard.add(new Card('C', 11));

		assertEquals(1, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare4Test() {
		dealerCard.add(new Card('D', 2));
		dealerCard.add(new Card('D', 5));
		dealerCard.add(new Card('D', 8));

		playerCard.add(new Card('C', 8));
		playerCard.add(new Card('C', 5));
		playerCard.add(new Card('C', 2));

		assertEquals(0, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare4Test2() {
		dealerCard.add(new Card('D', 2));
		dealerCard.add(new Card('D', 5));
		dealerCard.add(new Card('D', 8));

		playerCard.add(new Card('C', 8));
		playerCard.add(new Card('D', 8));
		playerCard.add(new Card('C', 2));

		assertEquals(1, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare5Test() {
		dealerCard.add(new Card('H', 8));
		dealerCard.add(new Card('H', 14));
		dealerCard.add(new Card('S', 8));

		playerCard.add(new Card('C', 8));
		playerCard.add(new Card('D', 14));
		playerCard.add(new Card('C', 8));

		assertEquals(0, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}

	@Test
	void cardLogicCompare5Test2() {
		dealerCard.add(new Card('H', 8));
		dealerCard.add(new Card('H', 13));
		dealerCard.add(new Card('S', 8));

		playerCard.add(new Card('C', 8));
		playerCard.add(new Card('D', 14));
		playerCard.add(new Card('C', 8));

		assertEquals(2, ThreeCardLogic.compareHands(dealerCard, playerCard));
	}
}
