package Game;

import java.util.*;

public class Cards {
    private ArrayList<Card> cards;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private ArrayList<Card> hand3;
    private ArrayList<Card> hand4;
    private CardSuit HOKM;
    public Cards()
    {
        this.cards = new ArrayList<>();
        this.hand1 = new ArrayList<>();
        this.hand2 = new ArrayList<>();
        this.hand3 = new ArrayList<>();
        this.hand4 = new ArrayList<>();
        Hand();
    }
    public void Deck() {
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
    public CardSuit getHOKM() {
        return HOKM;
    }
    public void setHOKM(CardSuit HOKM) {
        this.HOKM = HOKM;
    }
    public void ShuffleCards () {
        Collections.shuffle(getCards());
    }
    public void Hand() {
            Deck();
            for(int i = 0 ; i < 13 ; i++ )
            {
                hand1.add(cards.get(i));
                hand2.add(cards.get(i+13));
                hand3.add(cards.get(i+26));
                hand4.add(cards.get(i+39));
            }
        }
    public  ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public ArrayList<Card> getHand1() {
        return hand1;
    }
    public void setHand1(ArrayList<Card> hand1) {
        this.hand1 = hand1;
    }
    public ArrayList<Card> getHand2() {
        return hand2;
    }
    public void setHand2(ArrayList<Card> hand2) {
        this.hand2 = hand2;
    }
    public ArrayList<Card> getHand3() {
        return hand3;
    }
    public void setHand3(ArrayList<Card> hand3) {
        this.hand3 = hand3;
    }
    public ArrayList<Card> getHand4() {
        return hand4;
    }
    public void setHand4(ArrayList<Card> hand4) {
        this.hand4 = hand4;
    }
}