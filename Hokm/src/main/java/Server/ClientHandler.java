package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import Game.*;
import GUI.*;
public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String clientID;
    private int token;
    public ClientHandler(Socket socket, int token) {
        try {
            this.socket = socket;
            this.token = token;
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientID = br.readLine();
            clients.add(this);
            broadcastInput("SERVER: " + clientID + " has entered the game!");
        }
        catch (IOException e) {
            closeEverything(socket, br, bw);
        }
    }

    @Override
    public void run() {
        try {
            boolean verified = false;
            while (!verified) { //verifying the input token from client
                String strClientToken = br.readLine();
                String[] splited = strClientToken.split(" ");
                Integer clientToken = Integer.valueOf(splited[1]);
                System.out.println(strClientToken);

                if (clientToken == token) {
                    verified = true;
                    bw.write("Authentication successful. You are now connected.\n");
                    bw.flush();
                } else {
                    bw.write("Invalid token. Please try again.\n");
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputFromClient;
        while(socket.isConnected()) {
            try{
                inputFromClient = br.readLine();
                broadcastInput(inputFromClient);
            }
            catch (IOException e) {
                closeEverything(socket, br, bw);
                break;
            }
        }
    }
    public void broadcastInput(String messageToSend) {
        for (ClientHandler clientHandler: clients) {
            try {
                if (!clientHandler.clientID.equals(clientID)) {
                    clientHandler.bw.write(messageToSend);
                    clientHandler.bw.newLine();
                    clientHandler.bw.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, br, bw);
            }
        }
    }
    public void removeClient() {
        clients.remove(this);
        broadcastInput("SERVER: " + clientID + " has left the game.");
    }
    public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw) {
        removeClient();
        try {
            if(br != null)
                br.close();
            if (bw != null)
                bw.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
