package Game;

import java.util.*;
public class Player {
    private String Name;
    private String ID;
    Team team;
    private ArrayList<Card> Hand;
    public Player(String name , String Id, ArrayList<Card> Hand) {
        this.Name = name;
        this.ID = Id;
        this.Hand = Hand;
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
        return Hand;
    }
    public void setHand(ArrayList<Card> hand) {
        Hand = hand;
    }
}
