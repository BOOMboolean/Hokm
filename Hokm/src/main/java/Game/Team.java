package Game;

import java.util.*;
//import Server.*;
import GUI.*;
public class Team {
        private Player player1 ;
        private Player player2 ;
        private int roundScore;
        private int gameScore;

       // private Player Hakem ;
        public Team (Player player1, Player player2) {
            this.player1 = player1;
            this.player2 = player2;
            this.roundScore = 0;
            this.gameScore= 0;
        }
        public void addRoundScore(Team team){
            int i = team.getRoundScore();
            i++;
            team.setRoundScore(i);
        }
        public void addGameScore(Team team){
        int i = team.getGameScore();
        i++;
        team.setGameScore(i);
    }
        public Player getPlayer1() {
            return player1;
        }
        public void setPlayer1(Player player1) {
            this.player1 = player1;
        }
        public Player getPlayer2() {
            return player2;
        }
        public void setPlayer2(Player player2) {
            this.player2 = player2;
        }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        gameScore = gameScore;
    }
}