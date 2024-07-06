package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private String serverIp;
    private int serverPort;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int PLAYER_COUNT;
    private boolean connecting;
    private boolean running;

    public Client(String serverIp, int serverPort, Socket socket, BufferedReader in, PrintWriter out) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.running = false;
        this.PLAYER_COUNT = -1;
    }
    @Override
    public void run() {
        String msg;
        try {
            while (running && ((msg = in.readLine()) != null)) {
                System.out.println("Received: " + msg);
                if (msg.equals("GAME_START")) {
//                    gameStarted = true;
                    System.out.println("Game has started!");
                } else if (msg.startsWith("PLAYER_COUNT:")) {
                    PLAYER_COUNT = Integer.valueOf(msg);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void connect() {
        connecting = true;
        try {
            socket = new Socket(serverIp, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server: " + serverIp + " :" + serverPort);
            running = true;
            connecting = false;
            new Thread(this).start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isConnecting() {
        return connecting;
    }

    public void disconnect() {
        running = false;
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public int getPlayersCount() {
        sendMessage("GET_PLAYER_COUNT");
        return this.PLAYER_COUNT;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1234);
    }
}
