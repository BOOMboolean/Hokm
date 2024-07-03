package MessageClasses;

public class ClientMessage {
    protected int token;
    protected ClientMessageType type;

    public int getToken() {
        return token;
    }
    public ClientMessageType getType() {
        return type;
    }

    public void setToken(int token) {
        this.token = token;
    }
    public void setType(ClientMessageType type) {
        this.type = type;
    }
    public void Convert(String input) {
        if (input.equals(ClientMessageType.throwCard.getName())){
            setType(ClientMessageType.throwCard);
        }
        if (input.equals(ClientMessageType.hokm.getName())){
            setType(ClientMessageType.hokm);
        }
        if (input.equals(ClientMessageType.create.getName())){
            setType(ClientMessageType.create);
        }
        if (input.equals(ClientMessageType.join.getName())){
            setType(ClientMessageType.join);
        }
    }
}