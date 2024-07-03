package MessageClasses;

public class JoinMessage extends ClientMessage{
    private String username;

    public JoinMessage(String username, int token) {
        this.type = ClientMessageType.join;
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
}
