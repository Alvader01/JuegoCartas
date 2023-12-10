package Model;


public class Dealer {
    private Player[] players;
    private Card[] hand;

    public Dealer() {
        this.players = new Player[4];
        this.hand = new Card[10];
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Card[] getHand() {
        int dealerTotal = calculateHandValue();
        System.out.println("Total de puntos del dealer: " + dealerTotal);
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public void showDealerHand() {
        System.out.println("Mano del dealer:");
        for (Card card : hand) {
            if (card != null) {
                System.out.println(card);
            }
        }
        int dealerTotal = calculateHandValue();
        System.out.println("Total de puntos del dealer: " + dealerTotal);
    }

    public void initializePlayers(int numPlayers) {
        int totalPlayers = Math.min(Math.max(numPlayers, 1), 4);
        for (int i = 0; i < totalPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
    }

    public Player[] getInitializedPlayers() {
        return this.players;
    }

    public void dealInitialCards(Deck deck) {
        for (Player player : players) {
            if (player != null) {
                player.addCardToHand(deck.drawCard());
            }
        }
        for (int i = 0; i < hand.length; i++) {
            hand[i] = deck.drawCard();
        }
    }

    public void playDealerTurn(Deck deck) {
        int dealerTotal = calculateHandValue();

        while (dealerTotal < 17 && dealerTotal <= 21) {
            System.out.println("Valor actual del dealer: " + dealerTotal); // Agregar esta línea para imprimir el valor actual
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == null) {
                    hand[i] = deck.drawCard();
                    dealerTotal = calculateHandValue();
                    break;
                }
            }
        }
    }


    public boolean checkDealerBust () {
        return calculateHandValue() > 21;
    }

    public int calculateHandValue () {
        int value = 0;
        boolean hasAce = false;

        for (Card card : hand) {
            if (card != null) {
                int cardValue = card.getValue();
                if (cardValue == 1) {
                    hasAce = true;
                }
                value += (cardValue > 10) ? 10 : cardValue;
                if (hasAce && value + 10 <= 21) {
                    value += 10;
                }
            }
        }

        return value;
    }
}