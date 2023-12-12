package Model;

public class Card {
    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }



    @Override
    public String toString() {
        String cardValue;
        switch (value) {
            case 1:
                cardValue = "A";
                break;
            case 11:
                cardValue = "J";
                break;
            case 12:
                cardValue = "Q";
                break;
            case 13:
                cardValue = "K";
                break;
            default:
                cardValue = String.valueOf(value);
                break;
        }
        return "[" + cardValue + " " + suit + "]";
    }
}

