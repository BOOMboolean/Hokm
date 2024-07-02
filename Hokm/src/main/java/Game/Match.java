package Game;

import java.util.ArrayList;

public class Match {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Team team1;
    private Team team2;
    private ArrayList<Card> deck;
    private Game onGoingGame;
    public Match(){
        player1 = new Player("given to by the client 1", "token", null);
        player2 = new Player("given to by the client 2", "token", null);
        player3 = new Player("given to by the client 3", "token", null);
        player4 = new Player("given to by the client 4", "token", null);

        team1 = new Team(player1 , player3);
        team2 = new Team(player2 , player4);

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
    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }
}
