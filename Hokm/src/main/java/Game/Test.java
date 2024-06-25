package Game;

public class Test {
    public static void main(String[] args) {
        Cards cards = new Cards();
        for (int i = 0; i < cards.getCards().size(); i++) {
            System.out.println(cards.getCards().get(i));
        }
        Rank rank = Rank.King;
        System.out.println(rank.getRankValue());
    }
}
