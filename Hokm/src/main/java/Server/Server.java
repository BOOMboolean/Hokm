package Server;

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
    private static final int port = 1234;
    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_PLAYERS);
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started"); //System.out.println is for logging

            while(true) {
                if (clients.size() < MAX_PLAYERS) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("A new client has joined.");
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    pool.execute(clientHandler);
                    broadcastPlayerCount();
                } else {
                    try {
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
            for (ClientHandler client : clients) {
                client.sendMessage("Number of clients present: " + clients.size());
            }

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


    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                this.out = new PrintWriter(clientSocket.getOutputStream(), true); // Initialize PrintWriter here
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendMessage(String msg) {
            out.println(msg);
        }
        @Override
        public void run() {
            try {
                String msg;
                while((msg = in.readLine()) != null) {
                    if (msg.equals("PLAYER_COUNT"))
                        sendMessage(String.valueOf(Server.getPlayerCount()));
                    else
                        sendMessage("Invalid request");
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
    }
}
