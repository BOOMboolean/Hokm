package Game;

import java.util.*;

public class Cards {
    private ArrayList<Card> cards;
    Team team;
    Card PlayingCard;
    ArrayList<Card> hand1 = new ArrayList<>();
    ArrayList<Card> hand2 = new ArrayList<>();
    ArrayList<Card> hand3 = new ArrayList<>();
    ArrayList<Card> hand4 = new ArrayList<>();
    private CardSuit HOKM;
    public Cards() {
          this.cards = new ArrayList<>();
          this.team = new Team(new Player("navid", "1", hand1), new Player("amir", "2", hand2), new Player("danial", "3", hand3), new Player("mahta", "4",hand4));
          //این بالا جای این پلیر ها باید اسم هایی که تو  سرور هست رو بزاریم  و بعد بازی ادامه پیدا کنه
          Hand();
          team.Specify_Hakem();
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
        if (player == team.getPlayer1()) {
            System.out.println(team.getPlayer1().getHand());
        }
        else if (player == team.getPlayer2()) {
            System.out.println(team.getPlayer2().getHand());
        }
        else if (player == team.getPlayer3()) {
            System.out.println(team.getPlayer3().getHand());
        }
        else if (player == team.getPlayer4()) {
            System.out.println(team.getPlayer4().getHand());
        }
    }
    public void ShuffleCards () {
        Collections.shuffle(getCards());
    }
    public void Hand() {
            Deck();
            for(int i = 0 ; i < 13 ; i++ )
            {
                hand1.add(cards.get(i));
                hand2.add(cards.get(i+13));
                hand3.add(cards.get(i+26));
                hand4.add(cards.get(i+39));
            }
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
    public  ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    //    public ArrayList<Card> getHand1() {
//        return hand1;
//    }
//    public void setHand1(ArrayList<Card> hand1) {
//        this.hand1 = hand1;
//    }
//    public ArrayList<Card> getHand2() {
//        return hand2;
//    }
//    public void setHand2(ArrayList<Card> hand2) {
//        this.hand2 = hand2;
//    }
//    public ArrayList<Card> getHand3() {
//        return hand3;
//    }
//    public void setHand3(ArrayList<Card> hand3) {
//        this.hand3 = hand3;
//    }
//    public ArrayList<Card> getHand4() {
//        return hand4;
//    }
//    public void setHand4(ArrayList<Card> hand4) {
//        this.hand4 = hand4;
//    }
}