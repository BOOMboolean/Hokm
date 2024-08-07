package Game;
//import Server.*;
import GUI.*;
public enum Rank {
    Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);
    final int rank;
    Rank(int rank) {
        this.rank = rank;
    }
    public int getRankValue() {
        return rank;
    }
    public String getName() {
        return this.name();
    }
}
