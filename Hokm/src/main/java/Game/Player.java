package Game;

import java.util.*;
//import ServerPack.*;
import GUI.*;
public class Player {
    private String Name;
    private boolean isHakem;
    private Team team;
    private ArrayList<Card> hand;
    private Card playingCard;
    Match playerMatch;
    public Player(String name , Match match) {
        this.Name = name;
        this.isHakem = false;
        this.playerMatch = match;
    }
    public void playCard(Card card){
        if(isPlayable(card,this.hand,this.playerMatch.getOnGoingGame().getOnGoingRound())) {
            throwCard(card);
        }
        else{
            System.out.println("try another one dumbass");
        }
    }
    public boolean isPlayable(Card card,ArrayList<Card> hand,Round onGoingRound){
    for(int i = 0 ; i<hand.size(); i++){
        if(onGoingRound.getPlayedCards().get(0).getSuit().equals(hand.get(i).getSuit())){//checks if you have the first played card's suit in your hand
            if(onGoingRound.getPlayedCards().get(0).getSuit().equals(card)){
                return true;
            }
            else {
                return false;
            }
        }
    }
    return true;
    }
    public void throwCard(Card card){
        //some ifs and elses have to be implemented to check whether the card can be played or not
        //the thrown card by this method will be displayed to everyone once the gui is completed
        //the card argument = a button that will be implemented in the gui
        RemoveCard(card);
        this.playerMatch.getOnGoingGame().getOnGoingRound().getPlayedCards().add(card);
        System.out.println(card.toString() + " was played");
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