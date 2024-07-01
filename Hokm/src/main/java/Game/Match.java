package Game;

import java.util.ArrayList;

public class Match {
    private Player player1 = new Player("given to by the client 1", "token", null);
    private Player player2 = new Player("given to by the client 2", "token", null);
    private Player player3 = new Player("given to by the client 3", "token", null);
    private Player player4 = new Player("given to by the client 4", "token", null);
    private Team team1 = new Team(player1 , player3);
    private Team team2 = new Team(player2 , player4);
    private ArrayList<Card> deck;
    public Match(){
        DeckMaker();
        Game newGame = new Game(true);
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
    public ArrayList<Card> getDeck() {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }
}
