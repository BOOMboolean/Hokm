package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Game.*;
import Server.*;

public class StartingPanel {
    public StartingPanel() {
        JFrame frame = new JFrame();
        frame.setTitle("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JButton createGame = new JButton("Create Game");
        JButton joinGame = new JButton("Join Game");
        createGame.setToolTipText("Create game for playing :)");
        joinGame.setToolTipText("Join game for playing :)");
        createGame.setFocusable(false);
        joinGame.setFocusable(false);
        createGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Frame startframe = createGame("Token number");
                startframe.setVisible(true);
            }
        });
        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Frame joinframe = joinGame();
                joinframe.setVisible(true);
            }
        });
        panel.add(createGame);
        panel.add(joinGame);
        frame.add(panel);
        frame.setVisible(true);
    }
    public Frame createGame(String Token) {
        JFrame frame = new JFrame("Create Game");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JButton returnMain = new JButton("Return");
        returnMain.setToolTipText("return first page");
        returnMain.setFocusable(true);
        panel.setLayout(new GridLayout(3,2));
        JLabel nameLabel = new JLabel("please enter your username :");
        JTextField nameTextField = new JTextField();
        JLabel ipLabel = new JLabel("Enter the server's token: ");
        JTextField clientToken = new JTextField();
        JButton startgame = new JButton("enter the game");
        returnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StartingPanel();
            }
        });
        startgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ID = nameTextField.getText();
                    String strToken = clientToken.getText();
                    Integer token = Integer.valueOf(strToken);
                    System.out.println(token);

                    Socket socket = new Socket("localhost", 1234);
                    Client client = new Client(socket, ID);

                    client.listenForMessage();
                    client.sendMessage();

                    frame.dispose();
                    System.out.println("Returning back to terminal");
                    frame.setVisible(false);
                } catch (UnknownHostException eh) {
                    eh.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(ipLabel);
        panel.add(clientToken);
        panel.add(returnMain);
        panel.add(startgame);
        frame.add(panel);
        frame.setResizable(false);
        return frame;
    }
    public Frame joinGame () {
        JFrame frame = new JFrame("Join game");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        JButton returnMain = new JButton("Return");
        JTextField nameTextField = new JTextField();
        JLabel ipLabel = new JLabel("enter your Token : ");
        JTextField clientToken = new JTextField();
        returnMain.setToolTipText("return to first page");
        returnMain.setFocusable(true);
        JLabel nameLabel = new JLabel("please enter your username :");
        JButton joinButton = new JButton("join");
        returnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StartingPanel();
            }
        });
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = nameTextField.getText();
                String strToken = clientToken.getText();
                Integer token = Integer.valueOf(strToken);
                try {
                    Socket socket = new Socket("localhost", 1234);
                    Client client = new Client(socket, ID);

                    client.listenForMessage();
                    client.sendMessage();

                    frame.dispose();
                    System.out.println("Returning back to terminal");
                    frame.setVisible(false);
                } catch (UnknownHostException eh) {
                    eh.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                frame.setVisible(false);
            }
        });
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(ipLabel);
        panel.add(clientToken);
        panel.add(returnMain);
        panel.add(joinButton);
        frame.add(panel);
        frame.setResizable(false);
        return frame;
    }
}