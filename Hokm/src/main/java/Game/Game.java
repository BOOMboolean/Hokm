package Game;

import java.util.*;

public class Game {
    Team team;
    Cards cards = new Cards();
    CardSuit cardSuit;
    public Game() {
        this.team = new Team(new Player("navid", "1", cards.getHand1()), new Player("amir", "2", cards.getHand2()), new Player("danial", "3", cards.getHand3()), new Player("mahta", "4", cards.getHand3()));
    }
    public void Specify_Hokm() {
        System.out.println("please set the hokm"  /* "( + team.getHakem().getName() + ")"*/);
        for (int i = 0; i < 5; i++) {
            System.out.println(cards.getHand1().get(i));
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
    }

}
