package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.imageio.ImageIO;

import Client.Client;
import Game.*;
import Server.*;

public class GamePanel extends JFrame {
    private JPanel panel;
    private JPanel cardPanel;
    private ArrayList<NewButton> buttons;
    private JButton button1, button2, button3, button4;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GamePanel(String massege) {
        String[] list = massege.split("/");
        setTitle("Play Game");
        setSize(1500, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the background image
        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("Hokm\\images\\Panel Background.png")); // Adjust the path to your image
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Background image not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Custom panel with background image
        panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(new BorderLayout());

        // Top panel for team labels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Make panel transparent

        // Create team labels
        JLabel team1GameScoreLabel = new JLabel("Team1 Game Score:");
        team1GameScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team1GameScoreLabel.setForeground(Color.ORANGE);

        JLabel team1RoundScoreLabel = new JLabel("Team1 Round Score:");
        team1RoundScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team1RoundScoreLabel.setForeground(Color.ORANGE);

        JLabel team2GameScoreLabel = new JLabel("Team2 Game Score:    ");
        team2GameScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team2GameScoreLabel.setForeground(Color.ORANGE);

        JLabel team2RoundScoreLabel = new JLabel("Team2 Round Score:    ");
        team2RoundScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team2RoundScoreLabel.setForeground(Color.ORANGE);

        // Create a panel for the team labels on the left
        JPanel teamPanelLeft = new JPanel(new GridLayout(3, 1));
        teamPanelLeft.setOpaque(false); // Make panel transparent
        teamPanelLeft.add(team1GameScoreLabel);
        teamPanelLeft.add(team1RoundScoreLabel);

        // Create a panel for the team labels on the right
        JPanel teamPanelRight = new JPanel(new GridLayout(3, 1));
        teamPanelRight.setOpaque(false); // Make panel transparent
        teamPanelRight.add(team2GameScoreLabel);
        teamPanelRight.add(team2RoundScoreLabel);

        topPanel.add(teamPanelLeft, BorderLayout.WEST);
        topPanel.add(teamPanelRight, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Center panel for players
        JPanel playerPanel = new JPanel(new GridBagLayout());
        playerPanel.setOpaque(false); // Make panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 180, 50, 180);

        JLabel player1Label = new JLabel(list[list.length - 1], SwingConstants.CENTER);
        player1Label.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size
        player1Label.setForeground(Color.orange); // Set font color

        gbc.gridx = 1;
        gbc.gridy = 1;
        playerPanel.add(Box.createVerticalStrut(1), gbc); // Add empty space

        gbc.gridx = 1;
        gbc.gridy = 3;
        playerPanel.add(player1Label, gbc);

        // Central panel for buttons
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setOpaque(false); // Make panel transparent
        GridBagConstraints centralGbc = new GridBagConstraints();
        centralGbc.insets = new Insets(10, 10, 10, 10);

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        centralGbc.gridx = 0;
        centralGbc.gridy = 0;
        centralPanel.add(button1, centralGbc);

        centralGbc.gridx = 1;
        centralGbc.gridy = 0;
        centralPanel.add(button2, centralGbc);

        centralGbc.gridx = 0;
        centralGbc.gridy = 1;
        centralPanel.add(button3, centralGbc);

        centralGbc.gridx = 1;
        centralGbc.gridy = 1;
        centralPanel.add(button4, centralGbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        playerPanel.add(centralPanel, gbc);

        // Panel for player hand
        cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cardPanel.setOpaque(false); // Make panel transparent
        buttons = new ArrayList<>();

        ArrayList<Card> hand = new ArrayList<>();
        if (massege.startsWith("Cards")) {
            for (int i = 1; i < list.length - 1; i++) {
                Card card = Card.ConvertPlayingCards(list[i]);
                if (!hand.contains(card)) { // Check for duplicates
                    hand.add(card);
                }
            }
        }

        // Log number of cards parsed
        System.out.println("Number of cards parsed: " + hand.size());

        // Sort hand by suit and then by rank
        Collections.sort(hand, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                int suitComparison = c1.getSuit().compareTo(c2.getSuit());
                if (suitComparison != 0) {
                    return suitComparison;
                } else {
                    return c1.getRank().compareTo(c2.getRank());
                }
            }
        });

        for (Card card : hand) {
            NewButton newButton = new NewButton(card);
            newButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size
            newButton.setForeground(Color.WHITE); // Set font color
            newButton.setContentAreaFilled(false); // Make button transparent
            newButton.setOpaque(false); // Make button transparent
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    throwCard(newButton);
                }
            });
            buttons.add(newButton);
        }

        // Timer to add buttons one by one
        Timer timer = new Timer(120, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < buttons.size()) {
                    cardPanel.add(buttons.get(index));
                    cardPanel.revalidate();
                    cardPanel.repaint();
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();

        panel.add(playerPanel, BorderLayout.CENTER);
        panel.add(cardPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // Function to be called when a card button is pressed
    private void throwCard(NewButton button) {
        String cardName = button.getCardName();
        Client.setMassege(cardName);
        cardPanel.remove(button);
        cardPanel.revalidate();
        cardPanel.repaint();
        // Change icon of one of the central buttons
        button1.setIcon(button.getIcon());
        button1.setPreferredSize(new Dimension(button1.getIcon().getIconWidth(), button1.getIcon().getIconHeight()));
        button1.setMinimumSize(new Dimension(button1.getIcon().getIconWidth(), button1.getIcon().getIconHeight()));
        button1.setMaximumSize(new Dimension(button1.getIcon().getIconWidth(), button1.getIcon().getIconHeight()));
        button1.setSize(new Dimension(button1.getIcon().getIconWidth(), button1.getIcon().getIconHeight()));
        button1.revalidate();
        button1.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamePanel("Cards/Spades-Ace/Hearts-King/Diamonds-Queen/Clubs-Jack/Hearts-Ten/Spades-Nine/Clubs-Eight/Diamonds-Seven/Hearts-Six/Spades-Five/Clubs-Four/Diamonds-Three/Hearts-Two/Player1"));
    }
}
