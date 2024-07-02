package Client;

import MessageClasses.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream recieveBuffer;
    private DataOutputStream sendBuffer;
    private String username;
    private String clientToken;
    private ServerMessage lastServerMessage;

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

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getUsername() {
        return username;
    }

    public String getClientToken() {
        return clientToken;
    }

    public ServerMessage getLastServerMessage() {
        return lastServerMessage;
    }


}

