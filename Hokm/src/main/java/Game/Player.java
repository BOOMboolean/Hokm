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
    private Match playerMatch;
    public Player(String name , Match match) {
        this.playerMatch = match;
        this.Name = name;
        this.isHakem = false;
    }

//        public Card playCard(Card card){
//        if(isPlayable(card,this.hand,this.playerMatch.getOnGoingGame().getOnGoingRound())) {
//            throwCard(card);
//            return card;
//        }
//        else{
//            System.out.println("try another one dumbass");
//        }
//        return null;
//    }
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
    public Card throwCard(Card card){
        //some ifs and elses have to be implemented to check whether the card can be played or not
        //the thrown card by this method will be displayed to everyone once the gui is completed
        //the card argument = a button that will be implemented in the gui
        RemoveCard(card);
        System.out.println(card.toString() + " was played");
        return  null;
    }
    public void display(Card card){
        //swing display placeholder
        return;
    }

    public CardSuit hokmSpecifier(ArrayList<Card> hand){
            CardSuit hokmChosenByHakem = null;//another swing placeholder
            for(int i = 0; i < 5; i++){
                display(hand.get(i));
            }
            return hokmChosenByHakem;

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
    public void setTeam(Team Cteam) {
      this.team = team;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
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