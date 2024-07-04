package MessageClasses;

import Game.Player;

public class CreateMessage extends ClientMessage{
//    private String username;
    private String groupName;

    public CreateMessage(String username, String groupName) {
        this.type = ClientMessageType.create;
//        this.username = username;
        this.groupName = groupName;
        Player player = new Player(username);
        setPlayer(player);
    }
//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
}
