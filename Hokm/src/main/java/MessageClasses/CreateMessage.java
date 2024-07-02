package MessageClasses;

public class CreateMessage extends ClientMessage{
    private String username;

    public CreateMessage(String username, int token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
