package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
private Match thisMatch;
private CardSuit hokm;
private Round onGoingRound;

private boolean isFirstGame;
public Game(boolean isFirstGame){
    this.onGoingRound = new Round(1);
}

    public void RoundWin(Team winningTeam){
        //tedious ifs and elses for cycling who the hakem is in progress
        //sitting placements :
        //team1's player1: north
        //team1's player2: south
        //team2's player1: east
        //team2's player1: west
        //and the hakem switches clockwise

        if(winningTeam.equals(thisMatch.getTeam1())){
            if(thisMatch.getTeam2().getPlayer1().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(true);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            } else if (thisMatch.getTeam2().getPlayer2().isHakem()) {
                thisMatch.getTeam1().getPlayer1().setHakem(true);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            }
        }
        else if(winningTeam.equals(thisMatch.getTeam2()))
        {
            if(thisMatch.getTeam1().getPlayer1().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(true);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            }
            else if(thisMatch.getTeam1().getPlayer2().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(true);
            }
        }
//        winningTeam.setScore(winningTeam.getScore()+1);
//        onGoingRound.setFirstRound(false);
    }
//    public void GameWin(Team winningTeam){
//        winningTeam.setMatchScore(winningTeam.getMatchScore()+1);
//    }


    public void DeckShuffler() {
        Collections.shuffle(thisMatch.getDeck());
    }

}
