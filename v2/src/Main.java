import javax.swing.*;

public class Main {
    public static GameFrame frame;
    public static void main(String[] args) {
        // Create a new GameFrame object
        GameFrame gameFrame = new GameFrame();
        frame = gameFrame;
        // Call the start method to make the GameFrame visible
        gameFrame.start();
    }
}
