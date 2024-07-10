package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Client.Client;
import Game.Match;
import Game.Player;
import Server.Server;

public class StartingPanel {
    public StartingPanel() {
        JFrame frame = new JFrame();
        frame.setTitle("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        ImageIcon createGameIcon = new ImageIcon("Hokm\\images\\Join4.png");
        ImageIcon joinGameIcon = new ImageIcon("Hokm\\images\\Join3.png");

        JButton createGame = new JButton("Create Game", createGameIcon);
        JButton joinGame = new JButton("Join Game", joinGameIcon);
        createGame.setToolTipText("Create game for playing :)");
        joinGame.setToolTipText("Join game for playing :)");
        createGame.setFocusable(false);
        joinGame.setFocusable(false);

        createGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Frame startframe = createGame();
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

    private class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(String fileName) {
            try {
                image = ImageIO.read(new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setLayout(new GridBagLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public Frame createGame() {
        JFrame frame = new JFrame("Create Game");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("Hokm\\images\\Game.png");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("please enter your username:");
        nameLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 20));
        nameLabel.setForeground(Color.MAGENTA);
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 25));

        JButton startgame = new JButton("enter the game");
        startgame.setPreferredSize(new Dimension(150, 25));
        JButton returnMain = new JButton("Return");
        returnMain.setPreferredSize(new Dimension(150, 25));

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
                String ID = nameTextField.getText();
                Client client = new Client(ID, Server.match);

                if (client.connect()) {
                    JOptionPane info = new JOptionPane("Connected to server. \n Waiting for players to join...",JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{},null);
                    client.sendMessage(ID);

                    JDialog dialog = info.createDialog("");
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!client.isGameStarted()) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.setVisible(false);
                                    frame.setVisible(false);
                                }
                            });
                        }
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(frame, "Couldn't connect to server! Try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(startgame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(returnMain, gbc);

        frame.add(panel);
        frame.setResizable(false);

        frame.getRootPane().setDefaultButton(startgame);

        return frame;
    }

    public Frame joinGame() {
        JFrame frame = new JFrame("Join game");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("Hokm\\images\\Game.png");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("please enter your username:");
        nameLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 20));
        nameLabel.setForeground(Color.MAGENTA);
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 25));

        JButton joinButton = new JButton("join");
        joinButton.setPreferredSize(new Dimension(150, 25));
        JButton returnMain = new JButton("Return");
        returnMain.setPreferredSize(new Dimension(150, 25));

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
                Client client = new Client(ID, Server.match);

                if (client.connect()) {
                    JOptionPane info = new JOptionPane("Connected to server. \n Waiting for players to join...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    client.sendMessage(ID);

                    JDialog dialog = info.createDialog("Info");
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setModal(false);
                    dialog.setVisible(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!client.isGameStarted()) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.setVisible(false);
                                    frame.setVisible(false);
                                }
                            });
                        }
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(frame, "Couldn't connect to server! Try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(joinButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(returnMain, gbc);

        frame.add(panel);
        frame.setResizable(false);

        frame.getRootPane().setDefaultButton(joinButton);

        return frame;
    }

    public static void main(String[] args) {
        StartingPanel startingPanel = new StartingPanel();
    }
}
