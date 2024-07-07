package Game;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private Game onGoingGame;

    public Match(){
        setDeck(DeckMaker());

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
    public void addPlayer(Player player){
        this.players.add(player);
    }
    public void setOnGoingGame(Game onGoingGame) {
        this.onGoingGame = onGoingGame;
    }

    public Game getOnGoingGame() {
        return onGoingGame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

}
