package Game;

import java.util.ArrayList;

public class Match {
    private ArrayList<Card> deck;
    private Game onGoingGame;

    public Match(){

    }
    public ArrayList<Card> DeckMaker () {
        ArrayList<Card> deck = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void setOnGoingGame(Game onGoingGame) {
        this.onGoingGame = onGoingGame;
    }

    public Game getOnGoingGame() {
        return onGoingGame;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

}
