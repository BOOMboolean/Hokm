package Game;
import GUI.*;

import java.util.Objects;

public class Card {
    CardSuit suit;
    Rank rank;
    public Card(CardSuit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    @Override
    public String toString() {
//        return "Card{"
//                "suit=" + suit +
//                ", rank=" + rank +
//                '}';
        return (suit + "." + rank.toString());
    }
    public CardSuit getSuit() {
        return suit;
    }
    public static Card ConvertPlayingCards(String input) {
        Card card = new Card(CardSuit.Spades,Rank.Nine);
        String[] list = input.split("\\.");
        if (Objects.equals(list[0], CardSuit.Spades.getName())) {card.setSuit(CardSuit.Spades);}
        else if (Objects.equals(list[0], CardSuit.Clubs.getName())) {card.setSuit(CardSuit.Clubs);}
        else if (Objects.equals(list[0], CardSuit.Diamonds.getName())) {card.setSuit(CardSuit.Diamonds);}
        else {card.setSuit(CardSuit.Hearts);}
        if (Objects.equals(list[1], Rank.Ace.getName())) { card.setRank(Rank.Ace); }
        else if (Objects.equals(list[1], Rank.Two.getName())) { card.setRank(Rank.Two); }
        else if (Objects.equals(list[1], Rank.Three.getName())) { card.setRank(Rank.Three); }
        else if (Objects.equals(list[1], Rank.Four.getName())) { card.setRank(Rank.Four); }
        else if (Objects.equals(list[1], Rank.Five.getName())) { card.setRank(Rank.Five); }
        else if (Objects.equals(list[1], Rank.Six.getName())) { card.setRank(Rank.Six); }
        else if (Objects.equals(list[1], Rank.Seven.getName())) { card.setRank(Rank.Seven); }
        else if (Objects.equals(list[1], Rank.Eight.getName())) { card.setRank(Rank.Eight); }
        else if (Objects.equals(list[1], Rank.Nine.getName())) { card.setRank(Rank.Nine); }
        else if (Objects.equals(list[1], Rank.Ten.getName())) { card.setRank(Rank.Ten); }
        else if (Objects.equals(list[1], Rank.Jack.getName())) { card.setRank(Rank.Jack); }
        else if (Objects.equals(list[1], Rank.Queen.getName())) { card.setRank(Rank.Queen); }
        else  { card.setRank(Rank.King); }
        return card;
    }
    public Rank getRank() {
        return rank;
    }
    public void setRank(Rank rank) {
        this.rank = rank;
    }
    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }
}