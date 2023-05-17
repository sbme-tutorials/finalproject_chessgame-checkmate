import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameOver extends JFrame implements ActionListener {
    private JTextField player1NameInput;
    private JTextField player2NameInput;
    private JComboBox<String> playTimeComboBox;
    private JButton startGameButton;
    private BufferedImage backgroundImage;
    GameOver() {
        setTitle("Game Over");
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
        JLabel name1Label = new JLabel("Game Over");
        name1Label.setForeground(Color.WHITE);
        name1Label.setFont(new Font("Arial", Font.PLAIN, 36));
        player1Panel.add(name1Label);
        player1Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        player1Panel.setOpaque(true);
        player1Panel.setBackground(null);
        player1Panel.setForeground(Color.WHITE);


        JPanel mainPanel = new JPanel(new GridLayout(1, 1));
        mainPanel.add(player1Panel);

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