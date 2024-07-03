package ServerPack;

import java.util.HashMap;

public class Room { //change the class name to Group
    private String username;
    private int token;
    private static HashMap<Integer, String> allClientsByGroupToken = new HashMap <>(); //{token1, username1} , {token1, uesrname2}
                    //four elements have the same key(token), the next four are assigned with another token
    public Room(String username, int token) {
        this.username = username;
        this.token = token;
    }
    public void setToken (int token) {
//        if (this.currentToken != 0)
//            allGroupsByToken.remove (this.currentToken);
        //CHECK HOW MANY TIMES THIS SPECIFIC TOKEN IS USED FROM THE HASHMAP
        this.token = token;
        if (token != 0)
            allClientsByGroupToken.put (token, this.username);
    }

    public String getUsername() {
        return username;
    }

    public int getToken() {
        return token;
    }
}
