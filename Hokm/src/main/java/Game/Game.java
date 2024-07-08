package Game;

import java.util.*;

public class Game {
private  CardSuit hokm;
private Round onGoingRound;
ArrayList<Player> players = new ArrayList<>(4);
public Game(boolean isFirstGame,ArrayList<Player> players,ArrayList<Card> deck){
    DeckShuffler(deck);
    HandDistributer(deck,players);
    if(isFirstGame){
        HakemRandomiser();
    }
    HokmDeclaration();
    onGoingRound = new Round(HakemPlayer(),players);
}
    public Player HakemPlayer(){
        Player[] players = {this.players.get(0), this.players.get(1) , this.players.get(2) , this.players.get(3)};
        for(int i = 0 ; i<4 ; i++){
            if(players[i].isHakem()){
                return players[i];
            }
        }
        return null;
    }
    public void HakemRandomiser() {
        Player[] players = {this.players.get(0), this.players.get(1) , this.players.get(2) , this.players.get(3)};
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        players[randomIndex].setHakem(true);
    }
    public void HokmDeclaration() {
            setHokm(HakemPlayer().hokmSpecifier(HakemPlayer().getHand()));
        }


    public void HandDistributer(ArrayList<Card> deck , ArrayList<Player> players) {
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();
        for(int i = 0 ; i < 13 ; i++ )
        {
            hand1.add(deck.get(i));
            hand2.add(deck.get(i+13));
            hand3.add(deck.get(i+26));
            hand4.add(deck.get(i+39));
        }
        players.get(0).setHand(hand1);
        players.get(1).setHand(hand2);
        players.get(2).setHand(hand3);
        players.get(3).setHand(hand4);
    }

    public void GameWin(Team winningTeam) {

    }


    public void DeckShuffler(ArrayList<Card> deck) {
        Collections.shuffle(deck);
        players.get(0).getPlayerMatch().setDeck(deck);
    }
    public CardSuit getHokm() {
        return hokm;
    }
    public void setHokm(CardSuit hokm) {
        this.hokm = hokm;
    }
    public Round getOnGoingRound() {
        return onGoingRound;
    }

    public void setOnGoingRound(Round onGoingRound) {
        this.onGoingRound = onGoingRound;
    }
}
