package Model;

import java.util.Arrays;
import java.util.Random;

public class Deck {
    private Card[] cards = new Card[52];

    public Card[] getCards() {
        return cards;
    }

    public void createDeck() {
        int index = 0;
        String[] suit = {"♦", "♣", "♠", "♥"};
        for (String palo : suit) {
            for (int i = 1; i <= 13; i++) {
                cards[index++] = new Card(i, palo);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }
}






