package Game;

import java.util.*;

public class Game {
    private Cards cards ;
    private CardSuit HOKM;
    private Player Hakem;
    private Card PlayingCard;
    public Player getHakem() {
        return Hakem;
    }
    public void setHakem(Player hakem) {
        Hakem = hakem;
    }
    public void setPlayingCard(Card playingCard) {
        PlayingCard = playingCard;
    }
    public Card getPlayingCard() {
        return PlayingCard;
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
    public Game() {
        this.cards = new Cards();
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
    public void StartGame() {
        cards.ShowHand(getHakem());
        System.out.println("enter the card(suit.rank)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        setPlayingCard(cards.ConvertPlayingCards(input));
        RemoveCard(getHakem(),getPlayingCard());
        //  باید برای همه نشون داده شه
    }
    public void RemoveCard (Player player , Card card) {
        ArrayList<Card> temp = new ArrayList<>();
        temp = player.getHand();
        temp.remove(card);
        player.setHand(temp);
    }

    public void Throw (Player player , Card card){

    }
    public boolean SuitCheck (Card firstCard , Card newCard){
        if(newCard.getSuit() == firstCard.getSuit()){
            return true;
        }
        else{
            return false;
        }
    }
}