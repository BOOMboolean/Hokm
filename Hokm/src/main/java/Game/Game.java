package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
private ArrayList<Card> deck;
private CardSuit hokm;
private Round onGoingRound;
public Game(){
    this.onGoingRound = new Round(1);
}
    private Player player1 = new Player("given to by the client 1", "client id 1", null);
    private Player player2 = new Player("given to by the client 2", "client id 2", null);
    private Player player3 = new Player("given to by the client 3", "client id 3", null);
    private Player player4 = new Player("given to by the client 4", "client id 4", null);
    private Team team1 = new Team(player1 , player3);

    private Team team2 = new Team(player2 , player4);
    public void RoundWin(Team winningTeam){
        //tedious ifs and elses for cycling who the hakem is in progress
        //sitting placements :
        //team1's player1: north
        //team1's player2: south
        //team2's player1: east
        //team2's player1: west
        //and the hakem switches clockwise

        if(winningTeam.equals(this.team1)){
            if(this.team2.getPlayer1().isHakem())
            {
                this.team1.getPlayer1().setHakem(false);
                this.team2.getPlayer1().setHakem(false);
                this.team1.getPlayer2().setHakem(true);
                this.team2.getPlayer2().setHakem(false);
            } else if (this.team2.getPlayer2().isHakem()) {
                this.team1.getPlayer1().setHakem(true);
                this.team2.getPlayer1().setHakem(false);
                this.team1.getPlayer2().setHakem(false);
                this.team2.getPlayer2().setHakem(false);
            }
        }
        else if(winningTeam.equals(this.team2))
        {
            if(this.team1.getPlayer1().isHakem())
            {
                this.team1.getPlayer1().setHakem(false);
                this.team2.getPlayer1().setHakem(true);
                this.team1.getPlayer2().setHakem(false);
                this.team2.getPlayer2().setHakem(false);
            }
            else if(this.team1.getPlayer2().isHakem())
            {
                this.team1.getPlayer1().setHakem(false);
                this.team2.getPlayer1().setHakem(false);
                this.team1.getPlayer2().setHakem(false);
                this.team2.getPlayer2().setHakem(true);
            }
        }
        winningTeam.setScore(winningTeam.getScore()+1);
        setFirstRound(false);
    }
    public void GameWin(Team winningTeam){
        winningTeam.setMatchScore(winningTeam.getMatchScore()+1);
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

    public void DeckShuffler() {
        Collections.shuffle(getDeck());
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
