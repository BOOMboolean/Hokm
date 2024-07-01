package Game;

import java.util.*;

public class Round {
    private Cards cards;
    private Card FisrtPlayedCard;
    private boolean isFirstRound;

    public Round(int roundNumber) {
        this.isFirstRound = true;
        HandDistributer();

      //  this.cards = new Cards();
      //  cards.team1.getPlayer1();
    }

    public void HandDistributer() {

        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();
        for(int i = 0 ; i < 13 ; i++ )
        {
//            hand1.add(deck.get(i));
//            hand2.add(deck.get(i+13));
//            hand3.add(deck.get(i+26));
//            hand4.add(deck.get(i+39));
        }
//        team1.getPlayer1().setHand(hand1);
//        team1.getPlayer2().setHand(hand2);
//        team2.getPlayer1().setHand(hand3);
//        team2.getPlayer2().setHand(hand4);
    }


     public void HakemRandomiser() {
         Player[] players = {cards.team1.getPlayer1(), cards.team1.getPlayer2() , cards.team2.getPlayer1() , cards.team2.getPlayer2()};
         Random random = new Random();
         int randomIndex = random.nextInt(4);
         players[randomIndex].setHakem(true);
//       System.out.println("Hakem is:" + getHakem().getName());
        }
    public void Specify_Hokm() {
//        System.out.println("please set the hokm" +  "(" + getHakem().getName() + ")");
        for (int i = 0; i < 5; i++) {
//            System.out.println(getHakem().getHand().get(i));  //player1 is presumably the Hakem
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