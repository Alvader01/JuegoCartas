package Model;

public class Dealer {
        private Deck deck;

        public Dealer() {
            deck = new Deck();
        }

        public void shuffleDeck() {
            deck.shuffle();
        }

        public void dealCards(int numPlayers, int numCardsPerPlayer) {
            for (int i = 0; i < numPlayers; i++) {
                System.out.println("Mano del Jugador " + (i + 1) + ":");
                for (int j = 0; j < numCardsPerPlayer; j++) {
                    Card card = deck.getCards()[i * numCardsPerPlayer + j];
                    System.out.println(card);
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            Dealer dealer = new Dealer();
            dealer.shuffleDeck();
            dealer.dealCards(4, 5);
        }
    }

