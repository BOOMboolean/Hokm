package Game;
//import Server.*;
import GUI.*;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player1 = new Player(scanner.nextLine());
        Player player2 = new Player(scanner.nextLine());
        Player player3 = new Player(scanner.nextLine());
        Player player4 = new Player(scanner.nextLine());

        Match match = new Match(player1,player2,player3,player4);
    }
}