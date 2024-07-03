package ClientPack;

import MessageClasses.CreateMessage;
import MessageClasses.JoinMessage;
import MessageClasses.ServerMessage;
import com.google.gson.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream recieveBuffer;
    private DataOutputStream sendBuffer;
    private String username;
    private int token;
    private String groupName;
    private ServerMessage lastServerMessage;
    private static final Gson gsonAgent = new GsonBuilder().create();
    private String serverIP;
    private int serverPort;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    public Client() {};

    private boolean establishConnection() {
        try {
            socket = new Socket("localhost", 5000);
            sendBuffer = new DataOutputStream(
                    socket.getOutputStream()
            );
            recieveBuffer = new DataInputStream(
                    socket.getInputStream()
            );
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private boolean endConnection() {
        if(socket == null) return true;
        try {
            socket.close();
            recieveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String recieveResponse() {
        try {
            return recieveBuffer.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getToken() {
        return token;
    }

    public ServerMessage getLastServerMessage() {
        return lastServerMessage;
    }

    public boolean createRoom() {
        CreateMessage msg = new CreateMessage(username, groupName);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(msg));
            lastServerMessage = gsonAgent.fromJson(
                    recieveResponse(), ServerMessage.class);
            endConnection();
            boolean success = lastServerMessage.wasSuccessfull();
            if (success) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean joinGame() {
        JoinMessage msg = new JoinMessage(username, token);
        try {
            establishConnection();
            sendMessage(gsonAgent.toJson(msg));
            lastServerMessage = gsonAgent.fromJson(
                    recieveResponse(), ServerMessage.class);
            endConnection();
            boolean success = lastServerMessage.wasSuccessfull();
            if (success) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
    }

}

