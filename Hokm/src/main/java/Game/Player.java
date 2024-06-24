package Game;

public class Player {
    private static String Name;
    private static String ID;
    public Player(String name , String Id) {
        this.Name = name;
        this.ID = Id;
    }
    public Player() {

    }
    public static void setID(String ID) {
        Player.ID = ID;
    }    public static void setName(String name) {
        Name = name;
    }
    public static String getID() {
        return ID;
    }
    public static String getName() {
        return Name;
    }
}
