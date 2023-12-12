package Model;


public class Dealer {
    private Player[] players;
    private Card[] hand;
    private int totalValue;

    public Dealer() {
        this.players = new Player[4];
        this.hand = new Card[10];
        this.totalValue = 0;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Card[] getHand() {


        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }


    public void initializePlayers(int numPlayers, String[] playerNames) {
        int totalPlayers = Math.min(Math.max(numPlayers, 1), 4);
        // Reinicia el array de jugadores para que al jugar la segunda vez no se queden guardados los jugadores
        players = new Player[4];

        for (int i = 0; i < totalPlayers; i++) {
            if (i < playerNames.length) {
                players[i] = new Player(playerNames[i]);
            } else {
                players[i] = new Player("Jugador" + (i + 1));
            }
        }
    }

    public Player[] getInitializedPlayers() {
        return this.players;
    }

    public void dealInitialCards(Deck deck) {
        for (Player player : players) {
            if (player != null) {
                player.addCardToHand(deck.drawCard());
                player.addCardToHand(deck.drawCard());
            }
        }
    }

    public void playDealerTurn(Deck deck) {
        int dealerTotal = calculateHandValue();

        while (dealerTotal < 17) {
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == null) {
                    hand[i] = deck.drawCard();
                    dealerTotal = calculateHandValue();
                    break;
                }
            }
        }
    }
    public void resetDealer() {
        hand = new Card[10]; // Reinicia la mano del dealer
        totalValue = 0; // Reinicia el valor total del dealer
    }


    public boolean checkDealerBust() {
        return calculateHandValue() > 21;
    }

    public int calculateHandValue() {
        totalValue = 0;
        for (Card card : hand) {
            if (card != null) {
                int value = card.getValue();
                if (value == 1 && totalValue < 11) {
                    totalValue += 11;
                } else if (value == 11 || value == 12 || value == 13) {
                    totalValue += 10;
                } else {
                    totalValue += value;
                }

            }
        }

        return totalValue;
    }
}



