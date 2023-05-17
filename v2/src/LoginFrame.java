import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField player1NameInput;
    private JTextField player2NameInput;
    private JComboBox<String> playTimeComboBox;
    private JButton startGameButton;
    private BufferedImage backgroundImage;
    LoginFrame() {
        setTitle("Chess Game Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("v2/src/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom JPanel that draws the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the content pane to the custom JPanel
        setContentPane(panel);
        JPanel player1Panel = new JPanel();
        JLabel name1Label = new JLabel("Player 1 Name: ");
        name1Label.setForeground(Color.WHITE);
        name1Label.setFont(new Font("Arial", Font.PLAIN, 16));
        player1Panel.add(name1Label);
        player1Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        player1NameInput = new JTextField(15);
        player1Panel.setOpaque(true);
        player1Panel.setBackground(Color.BLACK);
        player1Panel.setForeground(Color.WHITE);

        player1Panel.add(player1NameInput);

        JPanel player2Panel = new JPanel();
        JLabel name2Label = new JLabel("Player 2 Name: ");
        name2Label.setForeground(Color.WHITE);
        name2Label.setFont(new Font("Arial", Font.PLAIN, 16));
        player2Panel.add(name2Label);
        player2Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        player2NameInput = new JTextField(15);
        player2Panel.add(player2NameInput);
        player2Panel.setOpaque(true);
        player2Panel.setBackground(Color.BLACK);
        player2Panel.setForeground(Color.WHITE);

        JPanel playTimePanel = new JPanel();
        playTimePanel.setBackground(Color.BLACK);
        JLabel timerLabel = new JLabel("Play Time (in minutes): ");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timerLabel.setForeground(Color.WHITE);
        playTimePanel.add(timerLabel);

        // Create a combo box with items 1, 2, and 3
        String[] playTimeValues = {"1", "2", "3"};
        playTimeComboBox = new JComboBox<String>(playTimeValues);
        playTimePanel.add(playTimeComboBox);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(this);
        startGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startGameButton.setBackground(Color.WHITE);
        startGameButton.setForeground(Color.BLACK);
        startGameButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(player1Panel);
        mainPanel.add(player2Panel);
        mainPanel.add(playTimePanel);
        mainPanel.add(startGameButton);
        add(mainPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            String player1Name = player1NameInput.getText();
            String player2Name = player2NameInput.getText();
            String theme = (String) playTimeComboBox.getSelectedItem();
            GameFrame.p1NameText = player1Name;
            GameFrame.p2NameText = player2Name;
            GameFrame.theme = theme;
            // Create a new GameFrame object
            GameFrame gameFrame = new GameFrame();
            Main.frame = gameFrame;
            // Call the start method to make the GameFrame visible
            gameFrame.start();
            // set the promotion choice for each player's pawn
            dispose();
        }
    }
}