package ServerPack;

import java.util.HashMap;

public class Room { //change the class name to Group
    private String groupName;
    private int currentToken;
    private static HashMap<Integer, Room> allGroupsByToken = new HashMap <>();

    public Room(String groupName, int currentToken) {
        this.groupName = groupName;
        this.currentToken = currentToken;
    }
    public void setCurrentToken (int currentToken) {
//        if (this.currentToken != 0)
//            allGroupsByToken.remove (this.currentToken);
        this.currentToken = currentToken;
        if (currentToken != 0)
            allGroupsByToken.put (currentToken, this);
    }

    public String getGroupName() {
        return groupName;
    }

    public int getCurrentToken() {
        return currentToken;
    }
}
