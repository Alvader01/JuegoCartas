package View;

import Model.Player;
import Model.Dealer;

public class ViewGame {

    public static void  printBlackJack(){
        System.out.println("########  ##          ###     ######  ##    ##       ##    ###     ######  ##    ##");
        System.out.println("##     ## ##         ## ##   ##    ## ##   ##        ##   ## ##   ##    ## ##   ##");
        System.out.println("##     ## ##        ##   ##  ##       ##  ##         ##  ##   ##  ##       ##  ##");
        System.out.println("########  ##       ##     ## ##       #####          ## ##     ## ##       #####");
        System.out.println("##     ## ##       ######### ##       ##  ##   ##    ## ######### ##       ##  ## ");
        System.out.println("##     ## ##       ##     ## ##    ## ##   ##  ##    ## ##     ## ##    ## ##   ##");
        System.out.println("########  ######## ##     ##  ######  ##    ##  ######  ##     ##  ######  ##    ##");

    }

    public static void printWelcome() {
        System.out.println("¡Bienvenido al juego de Blackjack!");
        System.out.println("¡A disfrutar y buena suerte!");
    }

    public static void displayCard(int value, String suit) {
        String cardVisual = "";
        switch (value) {
            case 1:
                cardVisual += "A";
                break;
            case 11:
                cardVisual += "J";
                break;
            case 12:
                cardVisual += "Q";
                break;
            case 13:
                cardVisual += "K";
                break;
            default:
                cardVisual += value;
                break;
        }
        System.out.println("┌───────┐");
        System.out.println("| " + cardVisual + "     |");
        System.out.println("|       |");
        System.out.println("|   " + suit + "   |");
        System.out.println("|       |");
        System.out.println("|     " + cardVisual + " |");
        System.out.println("└───────┘");
    }

    public static void displayPlayerHand(Player player) {
        System.out.println("Mano de " + player.getPlayerName() + ":");
        player.getHand().forEach(card -> displayCard(card.getValue(), card.getSuit()));
        System.out.println("Total: " + player.getTotalValue());
    }

    public static void displayDealerHand(Dealer dealer) {
        System.out.println("Mano del Dealer:");
        dealer.getHand().forEach(card -> displayCard(card.getValue(), card.getSuit()));
    }
    public static void displayResult(String result) {
        System.out.println("Resultado: " + result);
    }

    public static void displayDraw() {
        System.out.println("¡Empate!");
    }


    public static void displayOptions() {
        System.out.println("Elige una opción:");
        System.out.println("1. Pedir carta");
        System.out.println("2. Plantarse");
    }

    public static void displayGameOver() {
        System.out.println("Fin del juego. ¡Gracias por jugar!");
    }
}
