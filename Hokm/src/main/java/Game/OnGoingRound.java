package Game;

import java.util.*;

public class OnGoingRound {
    private Cards cards ;
    private CardSuit HOKM;
    private Player Hakem;
    private Card FisrtPlayedCard;
    private boolean isFirstRound;
    private Team team1;
    private Team team2;
    public OnGoingRound(boolean isFirstRound) {
        this.isFirstRound = isFirstRound;

     //   this.cards = new Cards();
      //  cards.team1.getPlayer1();
    }
    public void FirstGameStartUp(){

    }
    public void roundWin(){
        if(/*any team reaching the score of 7*/){
            new OnGoingRound(true);
        }
        else {
            new OnGoingRound(false);
        }

    }
     public void Specify_Hakem() {
         Player[] players = {cards.team1.getPlayer1(), cards.team1.getPlayer2() , cards.team2.getPlayer1() , cards.team2.getPlayer2()};
         Random random = new Random();
         int randomIndex = random.nextInt(2);
         setHakem(players[randomIndex]);
//       System.out.println("Hakem is:" + getHakem().getName());
        }
    public void Specify_Hokm() {
        System.out.println("please set the hokm" +  "(" + getHakem().getName() + ")");
        for (int i = 0; i < 5; i++) {
            System.out.println(getHakem().getHand().get(i));  //player1 is presumably the Hakem
        }
        while (true)
        {
            Scanner inputHakem = new Scanner(System.in);
            String test = inputHakem.nextLine();
            if (Objects.equals(test, CardSuit.Clubs.getName()))
            {
                setHOKM(CardSuit.Clubs);
                break;
            }
            else if (Objects.equals(test, CardSuit.Spades.getName()))
            {
                setHOKM(CardSuit.Spades);
                break;
            }
            else if (Objects.equals(test, CardSuit.Hearts.getName()))
            {
                setHOKM(CardSuit.Hearts);
                break;
            }
            else if (Objects.equals(test, CardSuit.Diamonds.getName()))
            {
                setHOKM(CardSuit.Diamonds);
                break;
            }
            System.out.println("Something went wrong, try again");
        }
        System.out.println("Hokm is : " + getHOKM().getName());
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
    public Player getHakem() {
        return Hakem;
    }
    public void setHakem(Player hakem) {
        Hakem = hakem;
    }
    public void setFisrtPlayedCard(Card firstPlayedCard) {
        FisrtPlayedCard = firstPlayedCard;
    }
    public Card getFisrtPlayedCard() {
        return FisrtPlayedCard;
    }
    public CardSuit getHOKM() {
        return HOKM;
    }
    public void setHOKM(CardSuit HOKM) {
        this.HOKM = HOKM;
    }
    public Cards getCards() {
        return cards;
    }
    public void setCards(Cards cards) {
        this.cards = cards;
    }





}