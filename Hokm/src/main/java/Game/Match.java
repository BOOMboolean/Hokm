package Game;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Card> deck;
    public Match(){
        this.team1 = new Team();
        this.team2 = new Team();
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

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
