package MessageClasses;

public enum ClientMessageType {
    create,
    join,
    throwCard,
    hokm;
    public String getName() {
        return this.name();
    }
}
