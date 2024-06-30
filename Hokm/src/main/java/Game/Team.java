package Game;

import java.util.*;
public class Team {
        private Player player1 ;
        private Player player2 ;
       // private Player Hakem ;
        public Team (Player player1, Player player2) {
            this.player1 = player1;
            this.player2 = player2;
        }
       // public void Specify_Hakem() {
        // Player[] players = {player1, player2};
         //Random random = new Random();
         //int randomIndex = random.nextInt(2);
         //setHakem(players[randomIndex]) ;
//       System.out.println("Hakem is:" + getHakem().getName());
//        }
//        public Player getHakem() {
//        return Hakem;
//    }
//        public void setHakem(Player hakem) {
//        Hakem = hakem;
//    }
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

}