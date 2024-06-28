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
    public CardSuit getSuit() {
        return suit;
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