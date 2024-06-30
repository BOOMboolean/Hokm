package Game;

import java.util.*;

public class Game {
    private Cards cards ;
    public Cards getCards() {
        return cards;
    }
    public void setCards(Cards cards) {
        this.cards = cards;
    }
    public Game() {
        this.cards = new Cards();
    }
    public void Specify_Hokm() {
        System.out.println("please set the hokm" +  "(" + cards.team.getHakem().getName() + ")");
        for (int i = 0; i < 5; i++) {
            System.out.println(cards.team.getPlayer1().getHand().get(i));  //player1 is presumably the Hakem
        }
        while (true)
        {
            Scanner inputHakem = new Scanner(System.in);
            String test = inputHakem.nextLine();
            if (Objects.equals(test, CardSuit.Clubs.getName()))
            {
                cards.setHOKM(CardSuit.Clubs);
                break;
            }
            else if (Objects.equals(test, CardSuit.Spades.getName()))
            {
                cards.setHOKM(CardSuit.Spades);
                break;
            }
            else if (Objects.equals(test, CardSuit.Hearts.getName()))
            {
                cards.setHOKM(CardSuit.Hearts);
                break;
            }
            else if (Objects.equals(test, CardSuit.Diamonds.getName()))
            {
                cards.setHOKM(CardSuit.Diamonds);
                break;
            }
            System.out.println("Something went wrong, try again");
        }
        System.out.println("Hokm is : " + cards.getHOKM());
    }
    public void FirstGame() {
        cards.ShowHand(cards.team.getHakem());
        System.out.println("enter the card(suit.rank)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        cards.setPlayingCard(cards.ConvertPlayingCards(input));
        RemoveCard(cards.team.getHakem(), cards.getPlayingCard());
//        System.out.println(input);
//        باید برای همه نشون داده شه
    }
    public void StartGame(Player player) {
        cards.ShowHand(player);
        System.out.println("enter the card(suit.rank)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        cards.setPlayingCard(cards.ConvertPlayingCards(input));
        RemoveCard(player,cards.getPlayingCard());
    }
    public void RemoveCard (Player player , Card card) {
        ArrayList<Card> temp = new ArrayList<>();
        temp = player.getHand();
        temp.remove(card);
        player.setHand(temp);
    }
    public void ValueCheck(Card card1 , Card card2) {

    }
}