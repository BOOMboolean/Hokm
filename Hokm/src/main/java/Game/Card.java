package Game;

public class Card {
    CardSuit suit;
    Rank rank;
    public Card(CardSuit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    @Override
    public String toString() {
//        return "Card{" +
//                "suit=" + suit +
//                ", rank=" + rank +
//                '}';
        return (suit + "." + rank.toString());
    }
}
