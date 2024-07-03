package Game;

import java.util.*;
//import ServerPack.*;
import GUI.*;
public class Player {
    private String Name;
    private String ID;
    private boolean isHakem;
    private Team team;
    private ArrayList<Card> hand;
    private Card playingCard;
    private Match playerMatch;
    public Player(String name) {
        this.Name = name;
        this.isHakem = false;
    }
    public void throwCard(Card card){
        //some ifs and elses have to be implemented to check whether the card can be played or not
        //the thrown card by this method will be displayed to everyone once the gui is completed
        //the card argument = a button that will be implemented in the gui
    }
    public boolean winnerOrNot(Player player){
       if(playerMatch.getOnGoingGame().getOnGoingRound().WinnerCard(playerMatch.getOnGoingGame().getOnGoingRound().getPlayedCards()).equals(player.getPlayingCard())){
           return true;
       }
       else{
           return false;
       }
    }

    public void RemoveCard (Card card) {
        ArrayList<Card> temp = new ArrayList<>();
        temp = getHand();
        temp.remove(card);
        setHand(temp);
    }
    public Card cardThrow(Card card){

        return null;
    }
    public void ShowHand() {
            System.out.println(this.hand);
    }
    public Card getPlayingCard() {
        return playingCard;
    }
    public void setPlayingCard(Card playingCard) {
        this.playingCard = playingCard;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public Team getTeam() {
      return team;
    }
    public void setTeam(Team team) {
      this.team = team;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public void setHand(ArrayList<Card> hand) {
        hand = hand;
    }

    public void setHakem(boolean hakem) {
        isHakem = hakem;
    }

    public boolean isHakem() {
        return isHakem;
    }

    public Match getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(Match playerMatch) {
        this.playerMatch = playerMatch;
    }
}