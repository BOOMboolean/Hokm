package Game;

import java.util.*;

public class Round {
    private Card FisrtPlayedCard;
    private boolean isFirstRound;
    private ArrayList<Card> playedCards = new ArrayList<>(4);
    private Game onGoingGame;

    public Round( ){

    }

    public void cardsBeingPlayed(){

    }
    public Card WinnerCard(ArrayList<Card> playedCards){
        CardSuit firsPlayedSuit = playedCards.get(0).getSuit();
        Card winningCard = playedCards.get(0);
        for (int i = 1; i < playedCards.size();i++)
        {
            if(playedCards.get(i).getSuit().equals(firsPlayedSuit)){
                if(playedCards.get(i).getRank().getRankValue() > winningCard.getRank().getRankValue()){
                    winningCard = playedCards.get(i);
                }
            }
            else{
//                if(playedCards.get(i).getSuit().equals(onGoingGame.getHokm())){
//                    for (int j = 0; j < i; j++) {
//                        if(playedCards.get(j).getSuit().equals(onGoingGame.getHokm())) {
//                            if (playedCards.get(i).getRank().getRankValue() > playedCards.get(j).getRank().getRankValue()) {
//                                winningCard = playedCards.get(i);
//                            }
//                        }
//                    }
//                }
            }
        }
        return winningCard;
    }

    public void RoundWin(Card winningCard , Player winningPlayer) {
        if (winningCard.equals(winningPlayer.getPlayingCard())) {
            winningPlayer.getTeam().setRoundScore(winningPlayer.getTeam().getRoundScore() + 1);
        }
    }



    public void ShowPlayedCard(Card playedCard){
        System.out.println(playedCard.getRank().getName() + " of " + playedCard.getSuit().getName());
    }
    public void setFisrtPlayedCard(Card firstPlayedCard) {
        FisrtPlayedCard = firstPlayedCard;
    }
    public Card getFisrtPlayedCard() {
        return FisrtPlayedCard;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public void setFirstRound(boolean firstRound) {
        isFirstRound = firstRound;
    }

    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(ArrayList<Card> playedCards) {
        this.playedCards = playedCards;
    }
}