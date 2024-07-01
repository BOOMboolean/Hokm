package Game;

import java.util.*;

public class Round {
    private Cards cards;
    private Card FisrtPlayedCard;
    private boolean isFirstRound;

    public Round(int roundNumber , boolean isFirstRound, Player firstThrower) {


      //  this.cards = new Cards();
      //  cards.team1.getPlayer1();
    }





    public void Specify_Hokm() {
//        System.out.println("please set the hokm" +  "(" + getHakem().getName() + ")");
        for (int i = 0; i < 5; i++) {
//            System.out.println(getHakem().getHand().get(i));  //player1 is presumably the Hakem
        }

    }
//    public void FirstGame() {
//        cards.ShowHand(cards.team.getHakem());
//        System.out.println("enter the card(suit.rank)");
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        cards.setPlayingCard(cards.ConvertPlayingCards(input));
//        RemoveCard(cards.team.getHakem(), cards.getPlayingCard());
////        System.out.println(input);
////        باید برای همه نشون داده شه
//    }
    //public void StartingRound() {
        //cards.ShowHand(getHakem());
        //System.out.println("enter the card(suit.rank)");
        //Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();
        //setFisrtPlayedCard(cards.ConvertPlayingCards(input));
        //RemoveCard(getHakem(),getFisrtPlayedCard());
        //  باید برای همه نشون داده شه
    //}
    public void ShowPlayedCard(Card playedCard){
        System.out.println(playedCard.getRank().getName() + " of " + playedCard.getSuit().getName());
    }
    public void setFisrtPlayedCard(Card firstPlayedCard) {
        FisrtPlayedCard = firstPlayedCard;
    }
    public Card getFisrtPlayedCard() {
        return FisrtPlayedCard;
    }
    public CardSuit getHOKM() {
        return null;
    }
    public void setHOKM(CardSuit HOKM) {
//        this.HOKM = HOKM;
    }
    public Cards getCards() {
        return cards;
    }
    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public void setFirstRound(boolean firstRound) {
        isFirstRound = firstRound;
    }
}