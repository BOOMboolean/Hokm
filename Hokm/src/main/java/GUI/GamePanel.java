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
import Game.*;

public class GamePanel extends JFrame {
    private JPanel panel;
    private JPanel cardPanel;
    private ArrayList<NewButton> buttons;

    public GamePanel(String playerName) {
        setTitle("Play Game");
        setSize(1500, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the background image
        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("Hokm\\images\\carpet5.jpg")); // Adjust the path to your image
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Background image not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Custom panel with background image
        panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(new BorderLayout());

        // Top panel for score and team labels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Make panel transparent

        // Create team labels
        // JLabel team1Label = new JLabel("team 1:");
        // team1Label.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size
        // team1Label.setForeground(Color.WHITE); // Set font color

        // JLabel team2Label = new JLabel("team 2:");
        // team2Label.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size
        // team2Label.setForeground(Color.WHITE); // Set font color

        // Create additional team score labels
        JLabel team1GameScoreLabel = new JLabel("Team1 Game Score:    ");
        team1GameScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team1GameScoreLabel.setForeground(Color.YELLOW);

        JLabel team1RoundScoreLabel = new JLabel("Team1 Round Score:   ");
        team1RoundScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team1RoundScoreLabel.setForeground(Color.YELLOW);

        JLabel team2GameScoreLabel = new JLabel("Team2 Game Score:    ");
        team2GameScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team2GameScoreLabel.setForeground(Color.YELLOW);

        JLabel team2RoundScoreLabel = new JLabel("Team2 Round Score:    ");
        team2RoundScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        team2RoundScoreLabel.setForeground(Color.YELLOW);

        // Create a panel for the team labels on the left
        JPanel teamPanelLeft = new JPanel(new GridLayout(3, 1));
        teamPanelLeft.setOpaque(false); // Make panel transparent
        // teamPanelLeft.add(team1Label);
        teamPanelLeft.add(team1GameScoreLabel);
        teamPanelLeft.add(team1RoundScoreLabel);

        // Create a panel for the team labels on the right
        JPanel teamPanelRight = new JPanel(new GridLayout(3, 1));
        teamPanelRight.setOpaque(false); // Make panel transparent
        // teamPanelRight.add(team2Label);
        teamPanelRight.add(team2GameScoreLabel);
        teamPanelRight.add(team2RoundScoreLabel);

        topPanel.add(teamPanelLeft, BorderLayout.WEST);
        topPanel.add(teamPanelRight, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Center panel for players
        JPanel playerPanel = new JPanel(new GridBagLayout());
        playerPanel.setOpaque(false); // Make panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(80, 180, 80, 180);

        JLabel player1Label = new JLabel("player1", SwingConstants.CENTER);
        player1Label.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size
        player1Label.setForeground(Color.orange); // Set font color

        JLabel player2Label = new JLabel("player2", SwingConstants.CENTER);
        player2Label.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size
        player2Label.setForeground(Color.orange); // Set font color

        JLabel player3Label = new JLabel("player3", SwingConstants.CENTER);
        player3Label.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size
        player3Label.setForeground(Color.orange); // Set font color

        JLabel player4Label = new JLabel("player4", SwingConstants.CENTER);
        player4Label.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size
        player4Label.setForeground(Color.orange); // Set font color

        // Add player labels to the grid
        gbc.gridx = 1;
        gbc.gridy = 0;
        playerPanel.add(player3Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        playerPanel.add(Box.createVerticalStrut(50), gbc); // Add empty space

        gbc.gridx = 0;
        gbc.gridy = 2;
        playerPanel.add(player4Label, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        playerPanel.add(player2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        playerPanel.add(player1Label, gbc);

        // Panel for player hand
        cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cardPanel.setOpaque(false); // Make panel transparent
        buttons = new ArrayList<>();

        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.Hearts, Rank.Five));
        hand.add(new Card(CardSuit.Clubs, Rank.Six));
        hand.add(new Card(CardSuit.Diamonds, Rank.Five));
        hand.add(new Card(CardSuit.Spades, Rank.Ten));
        hand.add(new Card(CardSuit.Diamonds, Rank.Jack));
        hand.add(new Card(CardSuit.Hearts, Rank.Queen));
        hand.add(new Card(CardSuit.Hearts, Rank.Ace));
        hand.add(new Card(CardSuit.Clubs, Rank.Queen));
        hand.add(new Card(CardSuit.Diamonds, Rank.Ace));
        hand.add(new Card(CardSuit.Spades, Rank.Six));
        hand.add(new Card(CardSuit.Diamonds, Rank.Seven));
        hand.add(new Card(CardSuit.Clubs, Rank.King));
        hand.add(new Card(CardSuit.Diamonds, Rank.Six));

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

        for (int i = 0; i < hand.size(); i++) {
            NewButton newButton = new NewButton(hand.get(i));
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
        cardPanel.remove(button);
        cardPanel.revalidate();
        cardPanel.repaint();
        // Call your custom function here
        // For example: performThrowCardAction(button);
    }

    //delete the main method when you're done
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamePanel("player"));
    }
}