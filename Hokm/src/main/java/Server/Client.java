package Server;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String ID;

    public Client(Socket socket, String ID) {
        try {
            this.socket = socket;
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.ID = ID;
        } catch (IOException e) {
            closeEverything(socket, br, bw);
        }
    }
    public void sendMessage() {
        try {
            bw.write(ID);
            bw.newLine();
            bw.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String cardToPlay = scanner.nextLine();
                bw.write(ID + ": " + cardToPlay);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, br, bw);
        }
    }
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGp;
                while (socket.isConnected()) {
                    try {
                        messageFromGp = br.readLine();
                        System.out.println(messageFromGp);
                    } catch (IOException e) {
                        closeEverything(socket, br, bw);
                    }
                }
            }
        }).start();
    }
    public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw) {
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

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1- Create    2- Join");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Enter your username: ");
            Scanner s = new Scanner(System.in);
            String ID = s.nextLine();
            System.out.println("Please check your server and enter its token: ");

            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket, ID);

            client.listenForMessage();
            client.sendMessage();
        }
        else {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter your username: ");
                String ID = s.nextLine();
//                System.out.println("Enter the IP: ");
//                String IP = s.nextLine();
                System.out.println("Enter token: ");

                Socket socket = new Socket("localhost", 1234);
                Client client = new Client(socket, ID);

                client.listenForMessage();
                client.sendMessage();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
