package Controller;

import Model.Dealer;
import Model.Deck;
import Model.Player;
import View.ViewGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Dealer dealer;
    private Deck deck;
    private int numPlayers;
    private Player[] players;

    public Game() {
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playGame = true;
        String[] playerNames;

        while (playGame) {
            String input = null;
            ViewGame.printWelcome();
            System.out.println("¿Unas partiditas mostro? (s/n)");
            do {
                input = scanner.nextLine().toLowerCase();
            }while (input == null || input == "");

            if (input.equals("s")) {
                System.out.println("¿Cuántos vais a jugar? (entre 1 y 4):");
                numPlayers = scanner.nextInt();
                while (numPlayers < 1 || numPlayers > 4) {
                    try {
                        numPlayers = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("¿No sabes leer? Pon un número entre 1 y 4:");
                        scanner.nextLine();
                    }
                }

                playerNames = new String[numPlayers];
                for (int i = 0; i < numPlayers; i++) {
                    System.out.println("Escribe el nombre del jugador " + (i + 1));
                    playerNames[i] = scanner.next();
                }
                dealer.initializePlayers(numPlayers, playerNames);
                players = dealer.getInitializedPlayers();
                deck.shuffleDeck();
                dealer.dealInitialCards(deck);

                playRound();
                endGame();
            } else if (input.equals("n")) {
                System.out.println("¡Gracias por jugar! Hasta luego.");
                playGame = false;
            } else {
                System.out.println("Opción no válida. Por favor, elige 's' para jugar o 'n' para salir.");
            }
        }
    }


    public void playRound() {
        for (Player player : dealer.getInitializedPlayers()) {
            if (player != null) {
                ViewGame.displayPlayerHand(player);

                Scanner scanner = new Scanner(System.in);
                boolean playerTurn = true;

                while (playerTurn) {
                    ViewGame.displayOptions();

                    if (scanner.hasNextInt()) {
                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                player.addCardToHand(deck.drawCard());
                                ViewGame.displayPlayerHand(player);
                                break;
                            case 2:
                                playerTurn = false;
                                break;
                            default:
                                System.out.println("Por favor, elige una opción válida (1 o 2).");
                                break;
                        }
                    } else {
                        System.out.println("Por favor, introduce un número válido.");
                        scanner.next();
                    }

                    if (player.getTotalValue() > 21) {
                        playerTurn = false;
                        System.out.println("¡Has superado 21! Pierdes.");
                    }
                }
            }
        }
        dealer.playDealerTurn(deck);
        ViewGame.displayDealerHand(dealer);
    }

    public void endGame() {
        for (Player player : dealer.getPlayers()) {
            if (player != null) {
                if (player.getTotalValue() <= 21 && (dealer.checkDealerBust() || player.getTotalValue() > dealer.calculateHandValue())) {
                    ViewGame.displayResult("¡" + player.getPlayerName() + " gana!");
                    if (player.getTotalValue() == 21 && (dealer.checkDealerBust() || player.getTotalValue() > dealer.calculateHandValue())) {
                        System.out.println("El diablo " + player.getPlayerName() + " consiguio BlackJack");
                    }
                } else if (player.getTotalValue() <= 21 && player.getTotalValue() == dealer.calculateHandValue()) {
                    ViewGame.displayDraw();
                } else {
                    ViewGame.displayResult("Vaya " + player.getPlayerName() + " perdio todo lo que le quedaba");
                }
            }

        }
        ViewGame.displayGameOver();
    }
}

