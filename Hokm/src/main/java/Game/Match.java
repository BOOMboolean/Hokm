package Game;

import java.util.ArrayList;

public class Match {
    private ArrayList<Card> deck;
    private ArrayList<Player> players ;
    private Game onGoingGame;
    public Match(String token , Player player){
        DeckMaker();
        this.players = new ArrayList<>();
        this.players.add(player);
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
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public void alaki() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName());
        }
    }
    public void addPLayer(Player player) {
        for (int i = 0; i < getPlayers().size(); i++) {
            Player player1 = getPlayers().get(i);
            if (player1 != null) {

            } else {
                // اقدامات لازم برای مواجهه با مقدار null
            }
        }
    }
}
