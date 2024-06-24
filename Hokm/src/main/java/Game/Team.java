package Game;

public class Team extends Player {
        private static Player player1 ;
        private static Player player2 ;
        public Team () {
            super();
            this.player1 = new Player("","");
            this.player2 = new Player("","");
        }
    public static Player getPlayer1() {
        return player1;
    }
    public static Player getPlayer2() {
        return player2;
    }
    public static void setPlayer1(Player player1) {
        Team.player1 = player1;
    }
    public static void setPlayer2(Player player2) {
        Team.player2 = player2;
    }
}
