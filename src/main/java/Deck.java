import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    Deck() {
        for (int i = 2; i < 15; i++) {
            this.add(new Card('C', i));
            this.add(new Card('D', i));
            this.add(new Card('S', i));
            this.add(new Card('H', i));
        }
        Collections.shuffle(this);
    }

    // Clear the old deck and create a new deck
    void newDeck() {
        this.clear();
        for (int i = 2; i < 15; i++) {
            this.add(new Card('C', i));
            this.add(new Card('D', i));
            this.add(new Card('S', i));
            this.add(new Card('H', i));
        }
        Collections.shuffle(this);
    }

}
