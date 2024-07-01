package Game;

import java.util.*;
import Server.*;
import GUI.*;
public class Player {
    private String Name;
    private String ID;
    private boolean isHakem;
    Team team;
    private ArrayList<Card> hand;
    private Card playingCard;
    public Round onGoingRound;
    public Player(String name , String Id, ArrayList<Card> hand) {
        this.Name = name;
        this.ID = Id;
        this.hand = hand;
        this.isHakem = false;
    }
    public void RemoveCard (Card card) {
        ArrayList<Card> temp = new ArrayList<>();
        temp = getHand();
        temp.remove(card);
        setHand(temp);
    }
    public void Throw (Card card){
        RemoveCard(card);
        onGoingRound.ShowPlayedCard(card);
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
}