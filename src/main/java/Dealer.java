import java.util.ArrayList;

public class Dealer {
    Deck theDeck;

    ArrayList<Card> dealersHand;

    Dealer() {
        theDeck = new Deck();
        dealersHand = new ArrayList<>();
    }

    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();
        if (theDeck.size() <= 34) {
            theDeck.newDeck();
        }
        hand.add(theDeck.remove(0));
        hand.add(theDeck.remove(0));
        hand.add(theDeck.remove(0));

        return hand;
    }
}
