import Controller.Game;
import View.ViewGame;

public class Main {
    public static void main(String[] args) {
        ViewGame.printBlackJack();
        int numPlayers=0;
        Game game = new Game();
        game.startGame();
    }
}
