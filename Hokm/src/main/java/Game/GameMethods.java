package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameMethods {
    public static Card winnerCard(ArrayList<Card> playedCards,CardSuit hokm){
        Card winningCard = playedCards.get(0);
        for(int i = 1 ; i < playedCards.size(); i++){
            if(playedCards.get(i).getSuit().equals(hokm)){
                if(winningCard.getSuit().equals(hokm)){
                    if(playedCards.get(i).getRank().getRankValue() > winningCard.getRank().getRankValue()){
                        winningCard = playedCards.get(i);
                    }
                }
                else{
                    winningCard = playedCards.get(i);
                }
            }
            else{
                if(!winningCard.getSuit().equals(hokm)){
                    if(playedCards.get(i).getRank().getRankValue() >winningCard.getRank().getRankValue()){
                        winningCard = playedCards.get(i);
                    }
                }
            }
        }
        return winningCard;
    }
    public static Team winnerTeam(ArrayList<Card> playedCards , CardSuit hokm , Team team1 , Team team2){
        if(team1.getPlayer1().getPlayingCard().equals(winnerCard(playedCards,hokm)) ||team1.getPlayer2().getPlayingCard().equals(winnerCard(playedCards,hokm)) ){
            return team1;
        }
        else if (team2.getPlayer1().getPlayingCard().equals(winnerCard(playedCards, hokm)) ||team2.getPlayer1().getPlayingCard().equals(winnerCard(playedCards, hokm))) {
            return team2;
        }
        return null;
    }
    public static void HandDistributer(ArrayList<Card> deck , Team team1 , Team team2) {
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();
        for(int i = 0 ; i < 13 ; i++ )
        {
            hand1.add(deck.get(i));
            hand2.add(deck.get(i+13));
            hand3.add(deck.get(i+26));
            hand4.add(deck.get(i+39));
        }
        team1.getPlayer1().setHand(hand1);
        team2.getPlayer1().setHand(hand2);
        team1.getPlayer2().setHand(hand3);
        team2.getPlayer2().setHand(hand4);
    }
    public static ArrayList<Card> deckShuffler(ArrayList<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
    public static void hakemRandomiser(Team team1 , Team team2){
        Player[] players = {team1.getPlayer1(),team2.getPlayer1(),team1.getPlayer2(),team2.getPlayer2()};
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        players[randomIndex].setHakem(true);
    }
    public static Player hakemPlayer(Team team1 , Team team2){
        Player[] players = {team1.getPlayer1(),team2.getPlayer1(),team1.getPlayer2(),team2.getPlayer2()};
        for(int i = 0 ; i<4 ; i++){
            if(players[i].isHakem()){
                return players[i];
            }
        }
        return null;
    }
}


