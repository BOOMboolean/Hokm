package Game;

import java.util.*;
public class Team {
        private Player player1 ;
        private Player player2 ;
        private Player player3 ;
        private Player player4 ;
        private Player Hakem ;
        public Team (Player player1, Player player2, Player player3, Player player4) {
            this.player1 = player1;
            this.player2 = player2;
            this.player3 = player3;
            this.player4 = player4;
        }
        public void Specify_Hakem() {
         Player[] players = {player1, player2, player3, player4};
         Random random = new Random();
         int randomIndex = random.nextInt(4);
         setHakem(players[randomIndex]) ;
//         System.out.println("Hakem is:" + getHakem().getName());
        }
        public Player getHakem() {
        return Hakem;
    }
        public void setHakem(Player hakem) {
        Hakem = hakem;
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
        public Player getPlayer3() {
            return player3;
        }
        public void setPlayer3(Player player3) {
            this.player3 = player3;
        }
        public Player getPlayer4() {
            return player4;
        }
        public void setPlayer4(Player player4) {
            this.player4 = player4;
        }
}


