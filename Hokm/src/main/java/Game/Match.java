package Game;

import java.util.ArrayList;

public class Match {
    private ArrayList<Card> deck;
    private Game onGoingGame;

    public Match(Player player1 , Player player2 , Player player3 , Player player4){

        DeckMaker();
        this.onGoingGame = new Game(true);
    }

    public void DeckMaker () {
        ArrayList<Card> deck = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        setDeck(deck);
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
