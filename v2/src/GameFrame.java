

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class GameFrame extends JFrame {
    private Board board;
    private JPanel infoCard;
    private JPanel p1info;
    private JPanel p2info;
    private JPanel p2timeFrame;
    private JPanel p1timeFrame;
    private JLabel p1Timer;
    private JLabel p2Timer;
    private JPanel p2NameFrame;
    private JPanel p1NameFrame;
    private JLabel p2Name;
    private JLabel p1Name;
    private JPanel p1Eaten;
    private JPanel p2Eaten;

    public GameFrame() {
        // Set the default close operation to exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the title of the window
        setTitle("Check-Mate");

        // Set the background color Background
        getContentPane().setBackground(new Color(33, 37, 55, 255));

        // Create a new JPanel with a GridBagLayout as the container for the Board panel
        JPanel boardPanel = new JPanel(new GridBagLayout());
        boardPanel.setOpaque(false);
        boardPanel.setBorder(BorderFactory.createEmptyBorder()); // remove border

        // Create a new JPanel with a FlowLayout as the container for the Board panel
        JPanel boardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        boardContainer.setOpaque(false);
        boardContainer.setBorder(BorderFactory.createEmptyBorder()); // remove border

        // Create a new Board object
        board = new Board();

        // Add the board to the board container
        boardContainer.add(board);

        // Add the board container to the center of the board panel using GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.CENTER;
        boardPanel.add(boardContainer, c);

        // Add the board panel to the center of the content pane
        add(boardPanel, BorderLayout.CENTER);

        // Create a new JPanel for the info card
        infoCard.setPreferredSize(new Dimension(200, getHeight())); // set preferred size
        infoCard.setLayout(new BoxLayout(infoCard, BoxLayout.Y_AXIS)); // align children vertically

        // Add components to the info card
        p1Name.setText("Yassien");
        p2Name.setText("Ahmed");

        infoCard.add(p1info);
        infoCard.add(p2info);
        JLabel lebel2 = new JLabel();
        lebel2.setForeground(Color.BLACK);
        ImageIcon icon;
        icon = new ImageIcon("Pieces/black/Bishop.png");
        //Image image = icon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH);
        //icon = new ImageIcon(image);
        //lebel2.setIcon(icon);
        p1Eaten.setLayout(new BoxLayout(p1Eaten, BoxLayout.Y_AXIS));
        p2Eaten.setLayout(new BoxLayout(p2Eaten, BoxLayout.Y_AXIS));
        p1Eaten.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p2Eaten.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Add the info card to the east (right) of the content pane
        add(infoCard, BorderLayout.EAST);
        repaint();

        // Set the space between the board and the info card to zero
        ((BorderLayout) getContentPane().getLayout()).setHgap(0);

        // Pack the JFrame to its minimum size
        pack();

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Set the maximum size of the JFrame to the preferred size
        setMaximumSize(getPreferredSize());

        // Set the minimum size of the JFrame to the size of the board and info card
        setMinimumSize(new Dimension(8 * 85 + 175 , 8 * 85));
    }

    public void start() {
        // Set the JFrame to be visible
        setVisible(true);
    }

    public void drawEatenPiece(String eatenPiece, boolean isWhite){
        JLabel label = new JLabel();
        label.setForeground(Color.BLACK);
        label.setText(eatenPiece);
        if(isWhite == true) p1Eaten.add(label);
        else p2Eaten.add(label);
        repaint();
        // Pack the JFrame to its minimum size
        pack();
    }
}
