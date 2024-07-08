package Game;

import Server.ClientHandler;

import java.util.ArrayList;

public class Match {
    private Team team1;
    private Team team2;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private Game onGoingGame;

    public Match(){
        this.players = new ArrayList<>();
        this.team1 = new Team();
        this.team2 = new Team();
        setDeck(DeckMaker());
    }

    public void start(){
        onGoingGame = new Game(true,players,deck);
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
    public void addPlayer(Player player){
        this.players.add(player);
        if(players.indexOf(player) == 0){
            team1.setPlayer1(player);
        }
        if(players.indexOf(player) == 1){
            team2.setPlayer1(player);
        }
        if(players.indexOf(player) == 2){
            team1.setPlayer2(player);
        }
        if(players.indexOf(player) == 3){
            team2.setPlayer2(player);
        }

    }

    public void updateMatch(Match match) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPlayerMatch(match);
        }
    }


    public void add(Player player) {
        ArrayList<Player> temp = getPlayers();
        temp.add(player);
        setPlayers(temp);
    }
    public void setOnGoingGame(Game onGoingGame) {
        this.onGoingGame = onGoingGame;
    }

    public Game getOnGoingGame() {
        return onGoingGame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
