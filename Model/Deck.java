package Model;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int top;

    public Deck() {
        this.cards = new Card[52];
        this.top = 0;
        initializeDeck();
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    private void initializeDeck() {
        String[] suits = {"♦", "♣", "♠", "♥"};
        int index = 0;

        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards[index++] = new Card(value, suit);
            }
        }
        shuffleDeck();
    }

    public void shuffleDeck() {
        Random random = new Random();

        for (int i = cards.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            Card temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    public Card drawCard() {
        if (top >= cards.length) {
            // Reinitialize deck if it's empty
            initializeDeck();
            top = 0;
        }
        return cards[top++];
    }
}






