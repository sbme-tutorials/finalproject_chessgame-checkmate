package Main;

import javax.swing.*;
import java.awt.*;

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

        // Add the info card to the east (right) of the content pane
        add(infoCard, BorderLayout.EAST);

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
}
