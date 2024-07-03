package ServerPack;

import ClientPack.Client;
import Game.Player;
import MessageClasses.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server extends Thread {
    private static ServerSocket server;
    private static ArrayList<Socket> connections;
    private DataOutputStream sendBuffer;
    private DataInputStream receiveBuffer;
    private static final Gson gsonAgent = new GsonBuilder().create();

    private static final String INTERNAL_ERROR = "internal server error";

    private static boolean setupServer(int portNumber) {
        try {
            server = new ServerSocket(portNumber);
            connections = new ArrayList <Socket>();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void listen() throws IOException {
        Socket socket;
        while (true) {
            socket = server.accept();
            synchronized (connections) {
                connections.add(socket);
                connections.notify();
            }
        }
    }
    @Override
    public void run() {
        Socket socket;
        while (true) {
            socket = null;
            synchronized (connections) {
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = connections.get(0);
                connections.remove(0);
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }
    private int generateToken() {
        Random random = new Random();
        int token = random.nextInt(5000) + 1001;
        return token;
    }
    private ClientMessage extractClientMessage(String clientStr) {
        try {
            ClientMessage clientMessage = gsonAgent.fromJson(clientStr, ClientMessage.class);
            switch (clientMessage.getType()) {
                case create:
                    return gsonAgent.fromJson(clientStr, CreateMessage.class);
                case join:
                    return gsonAgent.fromJson(clientStr, JoinMessage.class);
                case throwCard:
                    return gsonAgent.fromJson(clientStr, ThrowCardMessage.class);
                case hokm:
                    return gsonAgent.fromJson(clientStr, HokmMessage.class);
                default:
                    return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }


    private boolean sendMessage(boolean success, String problem) {
        ServerMessage failureMessage = new ServerMessage(success, problem);
        String failureString = gsonAgent.toJson(failureMessage);
        try {
            sendBuffer.writeUTF(failureString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean sendFailure(String problem) {
        return sendMessage(false, problem);
    }

    private boolean sendSuccess(String info) {
        return sendMessage(true, info);
    }

    /** methods for linking server and client**/
    public void registerNewClient(CreateMessage msg) {
        Room room = new Room(msg.getUsername(), msg.getToken());
//        sendSuccess(room.getToken());     IDK HOW THIS WORKS
    }

    private void handleConnection(Socket socket) {
        String clientRequest;

        try {
            receiveBuffer = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            sendBuffer = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream())
            );


            clientRequest = receiveBuffer.readUTF();
            ClientMessage msg = extractClientMessage(clientRequest);

            Client client = new Client();
            if (msg instanceof CreateMessage) {
                registerNewClient((CreateMessage) msg);
            }
            else if (msg instanceof JoinMessage) {
                joinPlayer((JoinMessage) msg);
            } else if (msg instanceof ThrowCardMessage) {
                throwCard((ThrowCardMessage) msg);
            } else if (msg instanceof HokmMessage)
                logoutClient((HokmMessage) msg);
            else
                sendFailure(INTERNAL_ERROR);

            sendBuffer.close();
            receiveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void throwCard(ThrowCardMessage msg) {
        msg.getToken();
        Player player ;
        player.throwCard();
    }

    private void joinPlayer(JoinMessage msg) {
    }

    public static void main(String[] args) {
        try {
            Server.setupServer(5000);
            new Server().start();
            new Server().listen();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            e.printStackTrace();
        }
    }
}
