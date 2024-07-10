package Server;

import Client.Client;
import GUI.GamePanel;
import Game.Card;
import Game.GameMethods;
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

import static Server.Server.broadcast;
import static Server.Server.getMatch;

public class Server {
    private static final int MAX_PLAYERS = 4;
    private static final int port = 4001;
    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_PLAYERS);
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    public static Match match = new Match();
    private static boolean isOpened = true;
    public static Match getMatch() {
        return match;
    }
    public static void setMatch(Match match) {
        Server.match = match;
    }
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for clients..."); //System.out.println is for logging

            while(true) {
                if (clients.size() < MAX_PLAYERS) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("A new client has joined.");
                    ClientHandler clientHandler = new ClientHandler(clientSocket,clients.size());
                    clients.add(clientHandler);
                    Client.setMatch(Server.match);
                    pool.execute(clientHandler);
                    broadcastPlayerCount();
                    broadcastPlayerIDs();
                } else if(clients.size() == MAX_PLAYERS && isOpened) {
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
    public static Player specifyPlayer(String clientName) {
        if (clientName.equals(Server.match.getTeam1().getPlayer1().getName())) {
            return Server.match.getTeam1().getPlayer1();
        } else if (clientName.equals(Server.match.getTeam1().getPlayer2().getName())) {
            return Server.match.getTeam1().getPlayer2();
        } else if (clientName.equals(Server.match.getTeam2().getPlayer1().getName())) {
            return  Server.match.getTeam2().getPlayer1();
        } else if (clientName.equals(Server.match.getTeam2().getPlayer2().getName())) {
            return  Server.match.getTeam2().getPlayer2();
        }
        return null;
    }
    public static int specifyClientIndex(String clientName) {
        if (clientName.equals(Server.getClients().get(0))) {
            return 0;
        } else if (clientName.equals(Server.getClients().get(1))) {
            return 1;
        } else if (clientName.equals(Server.getClients().get(2))) {
            return  2;
        } else if (clientName.equals(Server.getClients().get(3))) {
            return  3;
        }
        return -1;
    }
    public static void broadcastPlayerCount() {
        synchronized (clients) {
            System.out.println("Number of clients present: " + clients.size());
            for(ClientHandler client : clients)
                client.sendMessage("Number of clients present: " + clients.size());
        }
    }
    public static void broadcast(String massege) {
        synchronized (clients) {
//            System.out.println(massege);
            for(ClientHandler client : clients)
                client.sendMessage("TH" + massege);
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
    public static List<ClientHandler> getClients() {
        return clients;
    }
}



class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private static String massege;
    private int index;

    public static String getMassege() {
        return massege;
    }

    public void setMassege(String Massege) {
            massege = Massege;
    }

    public ClientHandler(Socket socket, int index) {
        this.clientSocket = socket;
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.name = in.readLine();
            synchronized (Server.match) {
                Player player = new Player(this.name, Server.match);
                switch (index) {
                    case 0:
                        Server.match.getTeam1().setPlayer1(player);
                        break;
                    case 1:
                        Server.match.getTeam1().setPlayer2(player);
                        break;
                    case 2:
                        Server.match.getTeam2().setPlayer1(player);
                        break;
                    case 3:
                        Server.match.getTeam2().setPlayer2(player);
                        break;
                    default:
                        System.out.println("Hi");
                }
//                Server.match.getPlayers().add(player);
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
            boolean isFirstTime = true;
            String msg = in.readLine();
            setMassege(msg);
            String[] commend = msg.split("/");
//            while((msg = in.readLine()) != null) {
//                if (commend[0].equals("GET_PANEL") && isFirstTime) {
//                new GamePanel(msg);
//                    isFirstTime = false;
//                SwingUtilities.invokeLater(() ->    new GamePanel(getMassege()));
//                }

//            }
            boolean isFirstGAme = true;
            boolean flag = true;
            while(flag) {
                if (commend[0].equals("SHOW_HAND") && isFirstGAme) {
                    GameMethods.handDistributer(Server.match.getDeck(), Server.match.getTeam1(), Server.match.getTeam2());
                    getHand(commend[1]);
                    isFirstGAme = false;
                } else if (commend[0].equals("THROW/")){
//                    broadcast(commend[1]);
                    sendMessage("THROW" + commend[1]);
                } else if (commend[0].equals("no")) {
                    sendMessage("yes");
                    flag = false;
                }
//                else {
//                    System.out.println("Hi");
//                }
//                switch (commend[0]) {
//                    case "GET_HAKEM":
//                        getHakemName();
//                        break;
//                    case "GET_HOKM":
//                        getHokm();
//                        break;
//                    case "JOIN_MATCH":
//                        joinMatch();
//                        break;
//                    case "CREATE_MATCH":
//                        createMatch();
//                        break;
//                    case "SPECIFY_HOKM":
//                        specifyHokm();
//                        break;
//                    case"SET_HOKM":
//                        setHokm();
//                        break;
//                    case "THROW_CARD":
//                        throwCard();
//                        break;
//                    case "PLAYER_COUNT":
//                        sendMessage(String.valueOf(Server.getPlayerCount()));
//                        break;
//                    case "GET_TEAM_SCORE":
//                        getTeamScore();
//                        break;
//                    case "GET_ROUND_SCORE":
//                        getRoundScore();
//                        break;
//                    case "SHOW_HANDk":
//                        getHand(commend[1]);
//                        break;
//                    default:
//                        System.out.println("______________Invalid request________________");
//                        break;
//                }
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
    private void setHokm() {
    }
    private void getHand(String clientName) {
        // return hand to client
        Player player = Server.specifyPlayer(clientName);
        String handToString = "Cards";
        for (int i = 0; i < player.getHand().size(); i++) {
            handToString += "/" + player.getHand().get(i).toString();
        }
        sendMessage(handToString + "/" + player.getName());
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