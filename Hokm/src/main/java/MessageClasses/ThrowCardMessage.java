package MessageClasses;

import Game.Card;

public class ThrowCardMessage extends ClientMessage{
    private String username;
    private String groupName;
    ThrowCardMessage(String username, String groupName){
        type=ClientMessageType.throwCard;
        this.username = username;
        this.groupName = groupName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    Card card;
}
