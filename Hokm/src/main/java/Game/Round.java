package Game;

import java.util.*;

public class Round {
    private Card FisrtPlayedCard;
    private boolean isFirstRound;
    private ArrayList<Player> playerTurn = new ArrayList<>(4);
    private ArrayList<Card> playedCards = new ArrayList<>(4);
    public Round(Player lastRoundWinner , ArrayList<Player> players){
        this.playedCards.clear();
        int index = players.indexOf(lastRoundWinner);
        for (int i = 0; i < 4 ;i++ ){
            if(index >= 4){
                playerTurn.set(i,players.get(index % 4));
                index ++;
            }
            else
            {
                playerTurn.set(i,players.get(index));
                index ++;
            }
        }
        roundBeingPlayed(playerTurn);
    }

    public void roundBeingPlayed(ArrayList<Player> playerTurn){
        Card cardFromPlayer = null; //temporary placeholder for the card we will be getting from the player
        for(int i = 0; i<4 ;i++){
            playerTurn.get(i).setPlayingCard(cardFromPlayer);
            this.playedCards.set(i,playerTurn.get(i).playCard(playerTurn.get(i).getPlayingCard()));
        }
        RoundWin(WinnerCard(playedCards),playerTurn);
        if()
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
                if(playedCards.get(i).getSuit().equals(playerTurn.get(0).getPlayerMatch().getOnGoingGame().getHokm())){
                    for (int j = 0; j < i; j++) {
                        if(playedCards.get(j).getSuit().equals(playerTurn.get(0).getPlayerMatch().getOnGoingGame().getHokm())) {
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

    public void RoundWin(Card winningCard , ArrayList<Player> playerTurn) {
        Player winningPlayer = null;
        for(int i = 0 ; i<playerTurn.size(); i++){
            if(winningCard.equals(playerTurn.get(i).getPlayingCard())){
                winningPlayer = playerTurn.get(i);
                winningPlayer.getTeam().setRoundScore(winningPlayer.getTeam().getRoundScore()+1);
            }
        }
        if(winningPlayer.getTeam().getRoundScore() >= 7){
            winningPlayer.getTeam().setGameScore(winningPlayer.getTeam().getGameScore()+1);
            winningPlayer.getPlayerMatch().setOnGoingGame(new Game());
        }
        winningPlayer.getPlayerMatch().getOnGoingGame().setOnGoingRound(new Round(winningPlayer,winningPlayer.getPlayerMatch().getPlayers()));
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

    public ArrayList<Player> getPlayerturn() {
        return playerTurn;
    }

    public void setPlayerturn(ArrayList<Player> playerturn) {
        this.playerTurn = playerturn;
    }
}