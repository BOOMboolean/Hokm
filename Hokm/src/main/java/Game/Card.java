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