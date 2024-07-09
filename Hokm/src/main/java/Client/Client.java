package Client;

import GUI.GamePanel;
import GUI.StartingPanel;
import Game.Match;
import Game.Player;
import Server.Server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client implements Runnable {
    private Socket socket;
    private String serverIP;
    private BufferedReader in;
    private PrintWriter out;
    private int PLAYER_COUNT;
    private boolean running;
    private boolean gameStarted;
    private String name;
    private static Match match;
    public Client(String name, Match match) {
        this.match = match;
        this.name = name;
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
        setMatch(Server.getMatch());
        String msg;
        try {
            while (running && ((msg = in.readLine()) != null)) {
                System.out.println("Received: " + msg);
                if (msg.equals("GAME_START")) {
                      setGameStarted(true);
//                    sendMessage("GET_PANEL");
                    sendMessage("SHOW_HAND");

                    System.out.println("Game has started!");
                } else if (msg.startsWith("PLAYER_COUNT:")) {
                    PLAYER_COUNT = Integer.valueOf(msg);
                } else if (msg.startsWith("Cards")) {
                    new GamePanel(getName() , getMatch());
                    sendMessage("Fuck");
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
                socket = new Socket("192.168.10.140", 4000);
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
        if (Objects.equals(message, getName()) && out != null) {
            out.println(message);
        }
        else   {
            out.println(message + "/" +  getName());
        }
    }
    public int getPlayersCount() {
        sendMessage("GET_PLAYER_COUNT");
        return this.PLAYER_COUNT;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static Match getMatch() {
        return match;
    }
    public static void setMatch(Match Match) {
        match = Match;
    }
}
