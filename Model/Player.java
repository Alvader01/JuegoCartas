package Model;
import Model.Dealer;
public class Player {
    private String playerName;
    private Card[] hand;
    private int totalValue;

    public Player(String playerName) {
        this.playerName = playerName;
        this.hand = new Card[10];
        this.totalValue = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }


    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public void addCardToHand(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                calculateTotalValue();
                break;
            }
        }
    }


    private void calculateTotalValue() {
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
    }
}