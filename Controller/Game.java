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
    //Creamos el metodo startGame para inicializar un menu
    //en el que pedimos si va a jugar o si quiere salir del juego
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playGame = true;
        String[] playerNames;

        while (playGame) {
            String input = null;
            ViewGame.printWelcome();
            System.out.println("¿Unas partiditas mostro? (s/n)");
           //Aqui implemento un do while para cuando se ejecute por segunda vez se reestablezcan los valores
            do {
                input = scanner.nextLine().toLowerCase();
            }while (input == null || input == "");
            //Aqui implemento un if para que cuando el usuario introduzca una s el juego empieze
            if (input.equals("s")) {
                int numPlayers = 0;
                boolean validInput = false;
                while (!validInput) {
                    System.out.println("¿Cuántos vais a jugar? (entre 1 y 4):");
                    /*Implemento un control de errores para cuando se pida el numero de
                     jugadores que solo se puedan introducir numeros del 1 al 4
                    y si no es asi que lo vuelva a poder introducir el usuario*/
                    try {
                        numPlayers = scanner.nextInt();
                        if (numPlayers >= 1 && numPlayers <= 4) {
                            validInput = true;
                        } else {
                            System.out.println("¿Sabes leer?, ingresa un numero entre 1 y 4.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("¿Sabes leer?, ingresa un numero entre 1 y 4.");
                        scanner.next();
                    }
                }
                /*Aqui implemento el metodo por el cual se recogen y guarda el nombre de los player
                añadiendole a este un control de errores para que en el nombre no se puedan introducir ni numeros
                ni el nombre de dealer*/
                playerNames = new String[numPlayers];
                for (int i = 0; i < numPlayers; i++) {
                    boolean validName = false;
                    while (!validName) {
                        System.out.println("Escribe el nombre del jugador " + (i + 1));
                        String playerName = scanner.next().trim();

                        if (!playerName.matches(".*\\d+.*") && !playerName.equalsIgnoreCase("dealer")) {
                            playerNames[i] = playerName;
                            validName = true;
                        } else {
                            System.out.println("Nombre inválido. No debe contener números ni ser 'dealer'.");
                        }
                    }
                }
                /*Aqui inicio los jugadores, se baraja el mazo , el dealer reaparte las cartas
                y reseteo los valores del dealer para la siguiente ronda */
                dealer.initializePlayers(numPlayers, playerNames);
                players = dealer.getInitializedPlayers();
                deck.shuffleDeck();
                dealer.dealInitialCards(deck);
                dealer.resetDealer();
                //Inicio los metodos con los que se juega la ronda y con el que se termina la ronda
                playRound();
                endGame();
                //Implemento el metodo para cuando el usuario introduzca la n el juego se cierre
            } else if (input.equals("n")) {
                System.out.println("¡Gracias por jugar! Hasta luego.");
                playGame = false;
            } else {
                System.out.println("Opción no válida. Por favor, elige 's' para jugar o 'n' para salir.");
            }
        }
    }

    //Implemento el metodo playRound
    public void playRound() {
        for (Player player : dealer.getInitializedPlayers()) {
            if (player != null) {
                ViewGame.displayPlayerHand(player);

                Scanner scanner = new Scanner(System.in);
                boolean playerTurn = true;

                while (playerTurn) {
                    ViewGame.displayOptions();
                    /*Aqui implemento un bucle para que cuando el usuario introduzca un 1 se le reparta otra carta
                    y que cuando introduzca un 2 se salga del turno de ese jugador y pase al siguiente tambien implemento
                    un control de errores para que el usuario solo pueda introducir 1 o 2 y si no es asi se vuelva a pedir*/
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
                    //Creo este metodo para cuando el jugador se pase de 21 automaticamente salga del turno
                    // y le muestre el mensaje de que ha perdido
                    if (player.getTotalValue() > 21) {
                        playerTurn = false;
                        System.out.println("¡Has superado 21! Pierdes.");
                    }
                }
            }
        }
        //Aqui llamo al turno del dealer y a la mano del dealer justo despues de que los jugadores ya terminen sus turnos
        dealer.playDealerTurn(deck);
        ViewGame.displayDealerHand(dealer);
    }
    //En este metodo implemento la forma en la que se muestra que jugador gana la partida que jugador pierde  tambien
    // que se muestre cuando un jugador consigle BlackJack y cuando suceda un empate
    public void endGame() {
        for (Player player : dealer.getPlayers()) {
            if (player != null) {
                if (player.getTotalValue() <= 21 && (dealer.checkDealerBust() || player.getTotalValue() > dealer.calculateHandValue())) {
                    ViewGame.displayResult("¡" + player.getPlayerName() + " gana!");
                    if (player.getTotalValue() == 21 && (dealer.checkDealerBust() || player.getTotalValue() > dealer.calculateHandValue())) {
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("                          Diablo                                ");
                        System.out.println("                          " + player.getPlayerName() + "                 ");
                        System.out.println("                    consiguio BlackJack                        ");
                        System.out.println("----------------------------------------------------------------");

                    }
                } else if (player.getTotalValue() <= 21 && player.getTotalValue() == dealer.calculateHandValue()) {
                    ViewGame.displayDraw();
                } else {
                    ViewGame.displayResult("Vaya " + player.getPlayerName() + " ha perdido");
                }
            }

        }
        ViewGame.displayGameOver();
    }
}

