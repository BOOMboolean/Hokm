package GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JFrame {
    private JPanel panel;
    private JLabel scoreLabel;

    public GamePanel(String playerName, String score) {
        setTitle("Play Game");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());

        // Top panel for score
        JPanel topPanel = new JPanel(new BorderLayout());
        scoreLabel = new JLabel("Score: " + score, SwingConstants.RIGHT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size
        topPanel.add(scoreLabel, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Center panel for players
        JPanel centerPanel = new JPanel(new GridLayout(3, 3, 100, 100));

        JLabel player1Label = new JLabel("player3", SwingConstants.CENTER);
        player1Label.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size

        JLabel player2Label = new JLabel("player4", SwingConstants.CENTER);
        player2Label.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size

        JLabel player3Label = new JLabel("player1", SwingConstants.CENTER);
        player3Label.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size

        JLabel player4Label = new JLabel("player2", SwingConstants.CENTER);
        player4Label.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size

        centerPanel.add(new JLabel());  // Empty cell
        centerPanel.add(player1Label);  // Player 1 at top center
        centerPanel.add(new JLabel());  // Empty cell

        centerPanel.add(player2Label);  // Player 2 at center left
        centerPanel.add(new JLabel());  // Empty cell
        centerPanel.add(player4Label);  // Player 4 at center right

        centerPanel.add(new JLabel());  // Empty cell
        centerPanel.add(player3Label);  // Player 3 at bottom center
        centerPanel.add(new JLabel());  // Empty cell

        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamePanel("player", "1"));
    }
}
