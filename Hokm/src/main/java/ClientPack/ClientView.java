package ClientPack;

public class ClientView { //this class is for showing messages on terminal idk if it's useful or not
    private static Client client;

    public void create() { //the type should be boolean fyi
        //get client's username and token from gui
        if (client.createRoom()) {
            //notify client that room was successfully created
        }
        //else...
    }
    public void join() {

    }
    public void throwCard() {

    }
    public void hokm() {

    }
}
