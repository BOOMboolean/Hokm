package Game;

import java.util.*;

public class Cards {
    private ArrayList<Card> cards;
    Team team1;
    Team team2;

    public Cards() {
          this.cards = new ArrayList<>();
          //this.team1 = new Team(new Player("navid", "1", hand1), new Player("amir", "2", hand2));
          //this.team2 = new Team( new Player("danial", "3", hand3), new Player("mahta", "4",hand4));
          //این بالا جای این پلیر ها باید اسم هایی که تو  سرور هست رو بزاریم  و بعد بازی ادامه پیدا کنه
          Hand();
//          team.Specify_Hakem();
    }
    public void Deck() {
        CreateCards();
        ShuffleCards();
    }
    public void CreateCards () {
        ArrayList<Card> card = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                card.add(new Card(suit, rank));
            }
        }
        setCards(card);
    }
    public Card ConvertPlayingCards (String input) {
        Card card = null;
        String[] list = input.split("\\.");
        if (Objects.equals(list[0], CardSuit.Spades.getName())) {card.setSuit(CardSuit.Spades);}
        if (Objects.equals(list[0], CardSuit.Clubs.getName())) {card.setSuit(CardSuit.Clubs);}
        if (Objects.equals(list[0], CardSuit.Diamonds.getName())) {card.setSuit(CardSuit.Diamonds);}
        if (Objects.equals(list[0], CardSuit.Hearts.getName())) {card.setSuit(CardSuit.Hearts);}
        if (Objects.equals(list[1], Rank.Ace.getName())) { card.setRank(Rank.Ace); }
        if (Objects.equals(list[1], Rank.Two.getName())) { card.setRank(Rank.Two); }
        if (Objects.equals(list[1], Rank.Three.getName())) { card.setRank(Rank.Three); }
        if (Objects.equals(list[1], Rank.Four.getName())) { card.setRank(Rank.Four); }
        if (Objects.equals(list[1], Rank.Five.getName())) { card.setRank(Rank.Five); }
        if (Objects.equals(list[1], Rank.Six.getName())) { card.setRank(Rank.Six); }
        if (Objects.equals(list[1], Rank.Seven.getName())) { card.setRank(Rank.Seven); }
        if (Objects.equals(list[1], Rank.Eight.getName())) { card.setRank(Rank.Eight); }
        if (Objects.equals(list[1], Rank.Nine.getName())) { card.setRank(Rank.Nine); }
        if (Objects.equals(list[1], Rank.Ten.getName())) { card.setRank(Rank.Ten); }
        if (Objects.equals(list[1], Rank.Jack.getName())) { card.setRank(Rank.Jack); }
        if (Objects.equals(list[1], Rank.Queen.getName())) { card.setRank(Rank.Queen); }
        if (Objects.equals(list[1], Rank.King.getName())) { card.setRank(Rank.King); }
        return card;
    }
    public void ShowHand(Player player) {
        if (player == team1.getPlayer1()) {
            System.out.println(team1.getPlayer1().getHand());
        }
        else if (player == team1.getPlayer2()) {
            System.out.println(team1.getPlayer2().getHand());
        }
        else if (player == team2.getPlayer1()) {
            System.out.println(team2.getPlayer1().getHand());
        }
        else if (player == team2.getPlayer2()) {
            System.out.println(team2.getPlayer2().getHand());
        }
    }
    public void ShuffleCards () {
        Collections.shuffle(getCards());
    }
    public void Hand() {
            Deck();
        ArrayList<Card> hand1 = new ArrayList<>();
        ArrayList<Card> hand2 = new ArrayList<>();
        ArrayList<Card> hand3 = new ArrayList<>();
        ArrayList<Card> hand4 = new ArrayList<>();
            for(int i = 0 ; i < 13 ; i++ )
            {
                hand1.add(cards.get(i));
                hand2.add(cards.get(i+13));
                hand3.add(cards.get(i+26));
                hand4.add(cards.get(i+39));
            }
            team1.getPlayer1().setHand(hand1);
            team1.getPlayer2().setHand(hand2);
            team2.getPlayer1().setHand(hand3);
            team2.getPlayer2().setHand(hand4);
        }
    public  ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public Team getTeam1() {
        return team1;
    }
    public void setTeam1(Team team) {
        this.team1 = team;
    }
    public Team getTeam2() {
        return team2;
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}