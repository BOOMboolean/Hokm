package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton createGame = new JButton("Creare Game");
        JButton joinGame = new JButton("Join Game");
        createGame.setToolTipText("Create game for playing :)");
        joinGame.setToolTipText("join game for playing :)");
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
        JFrame frame = new JFrame();
        frame.setTitle("Create Game");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JButton returnMain = new JButton("Menu");
        returnMain.setToolTipText("return first page");
        returnMain.setFocusable(true);
        panel.setLayout(new GridLayout(3,2));
        JLabel nameLabel = new JLabel("please enter your name :");
        JTextField nameTextField = new JTextField();
        JLabel ipLabel = new JLabel("Your Token is : ");
        JLabel showToken = new JLabel(Token);
        JButton startgame = new JButton("enter for game");
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
                String nameClient = nameTextField.getText();
                // یرای اسم یارو بگیر و ادامه
                frame.setVisible(false);
            }
        });
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(ipLabel);
        panel.add(showToken);
        panel.add(returnMain);
        panel.add(startgame);
        frame.add(panel);
        frame.setResizable(false);
        return frame;
    }
    public Frame joinGame () {
        JFrame frame = new JFrame("Join game");
        frame.setTitle("Create Game");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        JButton returnMain = new JButton("Menu");
        JTextField nameTextField = new JTextField();
        JLabel ipLabel = new JLabel("enter your Token : ");
        JTextField tokenTextField = new JTextField();
        returnMain.setToolTipText("return first page");
        returnMain.setFocusable(true);
        JLabel nameLabel = new JLabel("please enter your name :");
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
                String nameClient = nameTextField.getText();
                String Token = tokenTextField.getText();
                //تکن میگیره با اسم میده به تابع های دیگه
                frame.setVisible(false);
            }
        });
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(ipLabel);
        panel.add(tokenTextField);
        panel.add(returnMain);
        panel.add(joinButton);
        frame.add(panel);
        frame.setResizable(false);
        return frame;
    }
}