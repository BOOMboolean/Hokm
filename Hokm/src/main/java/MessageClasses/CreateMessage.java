package MessageClasses;

public class CreateMessage extends ClientMessage{
    private String username;
    private String groupName;

    public CreateMessage(String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
