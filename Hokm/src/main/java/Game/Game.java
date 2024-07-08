package Game;

import java.util.*;

public class Game {
private  CardSuit hokm;
private Round onGoingRound;
ArrayList<Player> players = new ArrayList<>(4);


    public void GameWin(Team winningTeam) {
        winningTeam.setGameScore(winningTeam.getGameScore()+1);
        if(winningTeam.equals(players.get(0).getPlayerMatch().getTeam1())){
            if(players.get(0).getPlayerMatch().getTeam2().getPlayer1().isHakem()){
                players.get(0).getPlayerMatch().getTeam1().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam1().getPlayer2().setHakem(true);
                players.get(0).getPlayerMatch().getTeam2().getPlayer2().setHakem(false);
            }
            else if(players.get(0).getPlayerMatch().getTeam2().getPlayer2().isHakem()){
                players.get(0).getPlayerMatch().getTeam1().getPlayer1().setHakem(true);
                players.get(0).getPlayerMatch().getTeam2().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam1().getPlayer2().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer2().setHakem(false);
            }
        }
        else if(winningTeam.equals(players.get(0).getPlayerMatch().getTeam2())){
            if(players.get(0).getPlayerMatch().getTeam1().getPlayer1().isHakem()){
                players.get(0).getPlayerMatch().getTeam1().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer1().setHakem(true);
                players.get(0).getPlayerMatch().getTeam1().getPlayer2().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer2().setHakem(false);
            }
            else if(players.get(0).getPlayerMatch().getTeam1().getPlayer2().isHakem()){
                players.get(0).getPlayerMatch().getTeam1().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer1().setHakem(false);
                players.get(0).getPlayerMatch().getTeam1().getPlayer2().setHakem(false);
                players.get(0).getPlayerMatch().getTeam2().getPlayer2().setHakem(true);
            }
        }
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
