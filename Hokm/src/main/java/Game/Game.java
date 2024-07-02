package Game;

import java.util.*;

public class Game {
private Match thisMatch;
private CardSuit hokm;
private int roundNumber;
public Game(boolean isFirstGame){
    DeckShuffler(thisMatch.getDeck());
    HandDistributer(thisMatch.getDeck());
    if(isFirstGame){
        HakemRandomiser();
    }
    //hokm specifier method placeholder
    new Round(true,HakemPlayer());
}
    public Player HakemPlayer(){
        Player[] players = {thisMatch.getTeam1().getPlayer1(), thisMatch.getTeam1().getPlayer2() , thisMatch.getTeam2().getPlayer1() , thisMatch.getTeam2().getPlayer2()};
        for(int i = 0 ; i<4 ; i++){
            if(players[i].isHakem()){
                return players[i];
            }
        }
        return null;
    }
    public void HakemRandomiser() {
        Player[] players = {thisMatch.getTeam1().getPlayer1(), thisMatch.getTeam1().getPlayer2() , thisMatch.getTeam2().getPlayer1() , thisMatch.getTeam2().getPlayer2()};
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        players[randomIndex].setHakem(true);
        System.out.println("Hakem is: " + players[randomIndex].getName());
    }
    public void HokmSpecifier() {
            for (int j = 0; j < 4; j++) {
                System.out.println(HakemPlayer().getHand().get(j));
            }
            while (true) {
                Scanner inputHakem = new Scanner(System.in);
                String test = inputHakem.nextLine();
                if (Objects.equals(test, CardSuit.Clubs.getName())) {
                    setHokm(CardSuit.Clubs);
                    break;
                } else if (Objects.equals(test, CardSuit.Spades.getName())) {
                    setHokm(CardSuit.Spades);
                    break;
                } else if (Objects.equals(test, CardSuit.Hearts.getName())) {
                    setHokm(CardSuit.Hearts);
                    break;
                } else if (Objects.equals(test, CardSuit.Diamonds.getName())) {
                    setHokm(CardSuit.Diamonds);
                    break;
                }
                System.out.println("Something went wrong, try again");
            }
            System.out.println("Hokm is : " + getHokm().getName());
        }


    public void HandDistributer(ArrayList<Card> deck) {
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();
        for(int i = 0 ; i < 13 ; i++ )
        {
            hand1.add(deck.get(i));
            hand2.add(deck.get(i+13));
            hand3.add(deck.get(i+26));
            hand4.add(deck.get(i+39));
        }
        thisMatch.getTeam1().getPlayer1().setHand(hand1);
        thisMatch.getTeam2().getPlayer1().setHand(hand2);
        thisMatch.getTeam1().getPlayer2().setHand(hand3);
        thisMatch.getTeam2().getPlayer2().setHand(hand4);
    }

    public void GameWin(Team winningTeam){
        //tedious ifs and elses for cycling who the hakem is in progress
        //sitting placements :
        //team1's player1: north
        //team1's player2: south
        //team2's player1: east
        //team2's player2: west
        //and the hakem switches clockwise

        if(winningTeam.equals(thisMatch.getTeam1())){
            if(thisMatch.getTeam2().getPlayer1().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(true);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            } else if (thisMatch.getTeam2().getPlayer2().isHakem()) {
                thisMatch.getTeam1().getPlayer1().setHakem(true);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            }
        }
        else if(winningTeam.equals(thisMatch.getTeam2()))
        {
            if(thisMatch.getTeam1().getPlayer1().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(true);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(false);
            }
            else if(thisMatch.getTeam1().getPlayer2().isHakem())
            {
                thisMatch.getTeam1().getPlayer1().setHakem(false);
                thisMatch.getTeam2().getPlayer1().setHakem(false);
                thisMatch.getTeam1().getPlayer2().setHakem(false);
                thisMatch.getTeam2().getPlayer2().setHakem(true);
            }
        }
    }


    public void DeckShuffler(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }
    public CardSuit getHokm() {
        return hokm;
    }
    public void setHokm(CardSuit hokm) {
        this.hokm = hokm;
    }
    public int getRoundNumber() {
        return roundNumber;
    }
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Match getThisMatch() {
        return thisMatch;
    }

}
