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

    public void showHand() {
        System.out.println("Mano de " + playerName + ":");
        for (Card card : hand) {
            if (card != null) {
                System.out.println(card);
            }
        }
        System.out.println("Valor total: " + totalValue);
    }

    private void calculateTotalValue() {
        totalValue = 0;
        int numOfAces = 0;

        for (Card card : hand) {
            if (card != null) {
                int value = card.getValue();
                totalValue += value;
                if (value == 1) {
                    if (totalValue + 10 <= 21) {
                        totalValue += 10;
                    } else {
                        numOfAces++;
                    }
                }
            }
        }

        while (numOfAces > 0 && totalValue <= 11) {
            totalValue += 10;
            numOfAces--;
        }
    }
}