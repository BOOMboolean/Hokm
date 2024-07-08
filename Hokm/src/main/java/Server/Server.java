package Server;

import GUI.GamePanel;
import Game.Match;
import Game.Player;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int MAX_PLAYERS = 4;
    private static final int port = 4000;
    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_PLAYERS);
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    public static Match match;
    private static boolean isOpened = true;

    public static void main(String[] args) {
        match = new Match();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for clients..."); //System.out.println is for logging

            while(true) {
                if (clients.size() < MAX_PLAYERS) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("A new client has joined.");
                    ClientHandler clientHandler = new ClientHandler(clientSocket,clients.size());
                    clients.add(clientHandler);
                    pool.execute(clientHandler);
                    broadcastPlayerCount();
                    broadcastPlayerIDs();
                } else if(clients.size() == MAX_PLAYERS && isOpened) {
                    SwingUtilities.invokeLater(() -> new GamePanel());
                    isOpened = false;
                } else {
                    try {
//                        match = new Match();
                        Socket tmpSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(tmpSocket.getOutputStream(), true);
                        out.println("There's no room for you! Try again later.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void broadcastPlayerCount() {
        synchronized (clients) {
            System.out.println("Number of clients present: " + clients.size());
            for(ClientHandler client : clients)
                client.sendMessage("Number of clients present: " + clients.size());
        }
    }
    public static void broadcastPlayerIDs() {
        synchronized (clients) {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < clients.size(); i++) {
                message.append(clients.get(i).getName());
                if (i != clients.size() - 1) message.append(',');
            }
            for (ClientHandler client:clients)
                client.sendMessage(message.toString());

            if (clients.size() == MAX_PLAYERS) {
                broadcastStart();
            }
        }
    }
    public static void broadcastStart() {
        synchronized (clients) {
            String message = "GAME_START";
            System.out.println(message);
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }
    public static int getPlayerCount() {
        return clients.size();
    }
    public static void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("A client has been disconnected.");
        broadcastPlayerCount();
    }

}
class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
//    private int index;

    public ClientHandler(Socket socket, int index) {
        this.clientSocket = socket;
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.name = in.readLine();
            synchronized (Server.match) {
                Player player = new Player(this.name, Server.match);
                Server.match.getPlayers().add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }
    @Override
    public void run() {
        try {
            String msg;
            while((msg = in.readLine()) != null) {
                switch (msg) {
                    case "GET_HAKEM":
                        getHakemName();
                        break;
                    case "GET_HOKM":
                        getHokm();
                        break;
                    case "JOIN_MATCH":
                        joinMatch();
                        break;
                    case "CREATE_MATCH":
                        createMatch();
                        break;
                    case "SPECIFY_HOKM":
                        specifyHokm();
                        break;
                    case "THROW_CARD":
                        throwCard();
                        break;
                    case "PLAYER_COUNT":
                        sendMessage(String.valueOf(Server.getPlayerCount()));
                        break;
                    case "GET_TEAM_SCORE":
                        getTeamScore();
                        break;
                    case "GET_ROUND_SCORE":
                        getRoundScore();
                        break;
                    default:
                        System.out.println("___________Invalid request________________");
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.removeClient(this);
        }
    }
    private void getRoundScore() {

    }
    private void getTeamScore() {

    }
    private void throwCard() {

    }
    private void specifyHokm() {

    }
    private void createMatch() {

    }
    private void joinMatch() {

    }
    private void getHokm() {

    }
    private void getHakemName() {
    }
}