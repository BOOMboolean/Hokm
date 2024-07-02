package Game;

import java.util.*;

public class Round {
    private Card FisrtPlayedCard;
    private boolean isFirstRound;
    private ArrayList<Card> playedCards = new ArrayList<>(4);
    private Game onGoingGame;

    public Round(boolean isFirstRound, Player firstThrower) {
        playedCards.set(0,firstThrower.cardThrow(firstThrower.getPlayingCard()));
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
                if(playedCards.get(i).getSuit().equals(onGoingGame.getHokm())){
                    for (int j = 0; j < i; j++) {
                        if(playedCards.get(j).getSuit().equals(onGoingGame.getHokm())) {
                            if (playedCards.get(i).getRank().getRankValue() > playedCards.get(j).getRank().getRankValue()) {
                                winningCard = playedCards.get(i);
                            }
                        }
                    }
                }
            }
        }
        return winningCard;
    }


    public void RoundWin(Card winningCard){
        Player winningPlayer = new Player(null,null,null);//this nigga doesn't exist yet
        if(winningCard.equals(onGoingGame.getThisMatch().getTeam1().getPlayer1().getPlayingCard())) {
            onGoingGame.getThisMatch().getTeam1().setRoundScore(onGoingGame.getThisMatch().getTeam1().getRoundScore()+1);
            winningPlayer = onGoingGame.getThisMatch().getTeam1().getPlayer1();
        }
        else if(winningCard.equals(onGoingGame.getThisMatch().getTeam1().getPlayer2().getPlayingCard())) {
            onGoingGame.getThisMatch().getTeam1().setRoundScore(onGoingGame.getThisMatch().getTeam1().getRoundScore()+1);
            winningPlayer = onGoingGame.getThisMatch().getTeam1().getPlayer2();
        }
        else if(winningCard.equals(onGoingGame.getThisMatch().getTeam2().getPlayer1().getPlayingCard())) {
            onGoingGame.getThisMatch().getTeam2().setRoundScore(onGoingGame.getThisMatch().getTeam2().getRoundScore()+1);
            winningPlayer = onGoingGame.getThisMatch().getTeam2().getPlayer1();
        }
        else if(winningCard.equals(onGoingGame.getThisMatch().getTeam2().getPlayer2().getPlayingCard())) {
            onGoingGame.getThisMatch().getTeam2().setRoundScore(onGoingGame.getThisMatch().getTeam2().getRoundScore()+1);
            winningPlayer = onGoingGame.getThisMatch().getTeam2().getPlayer2();
        }
        new Round(false,winningPlayer);
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
}