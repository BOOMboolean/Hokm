package Game;

import java.util.*;

public class Game {
    Cards cards ;
    public Game() {
        this.cards = new Cards();
    }
    public void Specify_Hokm() {
        System.out.println("please set the hokm" +  "(" + cards.team.getHakem().getName() + ")");
        for (int i = 0; i < 5; i++) {
            System.out.println(cards.team.getPlayer1().getHand().get(i));
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
    public void StartGame() {
        cards.ShowHand(cards.team.getHakem());
        System.out.println("enter the card(suit.rank)");
        Scanner scanner = new Scanner(System.in);
        String inputHakem = scanner.nextLine();
        cards.ConvertPlayingCards(inputHakem);

    }
}