package Client;

import GUI.StartingPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private Socket socket;
    private String serverIP;
    private BufferedReader in;
    private PrintWriter out;
    private int PLAYER_COUNT;
    private boolean running;
    private boolean gameStarted;

    public Client() {
        this.running = false;
        this.PLAYER_COUNT = -1;
    }
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
    public boolean isGameStarted() {
        return gameStarted;
    }

    @Override
    public void run() {
        String msg;
        try {
            while (running && ((msg = in.readLine()) != null)) {
                System.out.println("Received: " + msg);
                if (msg.equals("GAME_START")) {
                    setGameStarted(true);
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
    public boolean connect() {
            try {
                socket = new Socket("localhost", 4000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Connected to server.");
                running = true;
                new Thread(this).start();
                return true;
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + e.getMessage());
                running = false;
                return false;
            } catch (IOException e) {
                System.err.println("I/O Error: " + e.getMessage());
                running = false;
                return false;
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

    public void startGame() {

    }
}
