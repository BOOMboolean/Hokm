package Server;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.Random;

public class Server {
    private ServerSocket ss;
    private int token;
    public Server(ServerSocket ss, int token) {
        this.ss = ss;
        this.token = token;
    }

    public Server() {
    }

    public void setToken(int token) {
        this.token = token;
    }
    public boolean authenticateClient(int clientToken) {
        return clientToken == this.token;
    }

    public void startServer() {
        try {
            while(!ss.isClosed()) {
                Socket socket = ss.accept();
                System.out.println("A new client has joined!");
                ClientHandler clientHandler = new ClientHandler(socket, token);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeServerSocket() {
        try {
            if (ss != null)
                ss.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Server started. Waiting for clients...");

        Random random = new Random();
        int token = random.nextInt(5000) + 1;
        System.out.println("The server token is: " + token + '\n');
        Server server = new Server(ss, token);
        server.startServer();
    }
}
