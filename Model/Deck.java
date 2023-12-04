package Model;

import java.util.Arrays;

public class Deck {
    private Card[] cards = new Card[52];


    public Card[] createDeck(){
        int index=0;
        String[] suit ={"Treboles","Corazones"," Picas","Diamantes"};
        for (String palo: suit   ){
            for (int i =1;i <= 13; i++){
                cards[index++]=new Card(i,palo);
            }
        }
        return cards;
    }
}
