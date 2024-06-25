package Game;

import java.util.*;

public class Cards {
    private  ArrayList<Card> cards = new ArrayList<>();
    public  ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public Cards() {
        CreateCards();
        ShuffleCards();
    }
    public void CreateCards () {
        ArrayList<Card> card = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                card.add(new Card(suit, rank));
            }
        }
        setCards(card);
    }
    public void ShuffleCards () {
        Collections.shuffle(getCards());
    }
}
//private static String[][] cards = new String[4][13];
//public static String[][] getCards() {
//    return cards;
//}
//public void setCards(String[][] cards) {
//    this.cards = cards;
//}
//
//
//public static String[][] ShuffleTheCards(String[][] cards) {
//    Random random = new Random();
//    for (int i = 0; i < 4 ; i++) {
//        for (int j = 0 ; j < 13 ; j++) {
//            int RandomNumberForChangeRank = random.nextInt(13);
//            int RandomNumberForChangeSuit = random.nextInt(4);
//            String temp = cards[i][j];
//            cards[i][j] = cards[RandomNumberForChangeSuit][RandomNumberForChangeRank];
//            cards[RandomNumberForChangeSuit][RandomNumberForChangeRank] = temp;
//        }
//    }
//    return cards;
//}