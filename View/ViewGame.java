package View;

import Model.Card;
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
        System.out.println("-----------------------------------------------");
        System.out.println("                                               ");
        System.out.println(" Bienvenido al juego de Blackjack de Alvader   ");
        System.out.println("                                               ");
        System.out.println("    Te deseo buena suerte,es muy adictivo      ");
        System.out.println("                                               ");
        System.out.println("-----------------------------------------------");
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
        for (Card card : player.getHand()) {
            if (card != null) {
                displayCard(card.getValue(), card.getSuit());
            }
        }
        System.out.println("Total: " + player.getTotalValue());
    }

    public static void displayDealerHand(Dealer dealer) {
        System.out.println("Mano del Dealer:");
        for (Card card : dealer.getHand()) {
            if (card != null) {
                displayCard(card.getValue(), card.getSuit());
            }
        }
    }
    public static void displayResult(String result) {
        System.out.println("Resultado: " + result);
    }

    public static void displayDraw() {
        System.out.println("Vaya, parece que tenemos un empate");
    }


    public static void displayOptions() {
        System.out.println("Elige una opción:");
        System.out.println("1. Pedir carta");
        System.out.println("2. Plantarse");
    }

    public static void displayGameOver() {
        System.out.println("************************************");
        System.out.println("*                                  *");
        System.out.println("*Fin del juego  ¡Gracias por jugar!*");
        System.out.println("*                                  *");
        System.out.println("************************************");
    }
}

