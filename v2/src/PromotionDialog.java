import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class PromotionDialog extends JDialog {
    private PieceType selectedPiece;

    public PromotionDialog(GameFrame gameFrame) {
        super(gameFrame, "Pawn Promotion", true);
        setLayout(new GridLayout(1, 4));

        JButton queenButton = new JButton("Queen");
        queenButton.addActionListener(e -> {
            selectedPiece = PieceType.QUEEN;
            dispose();
        });
        add(queenButton);

        JButton rookButton = new JButton("Rook");
        rookButton.addActionListener(e -> {
            selectedPiece = PieceType.ROOK;
            dispose();
        });
        add(rookButton);

        JButton bishopButton = new JButton("Bishop");
        bishopButton.addActionListener(e -> {
            selectedPiece = PieceType.BISHOP;
            dispose();
        });
        add(bishopButton);

        JButton knightButton = new JButton("Knight");
        knightButton.addActionListener(e -> {
            selectedPiece = PieceType.KNIGHT;
            dispose();
        });
        add(knightButton);

        pack();
        setLocationRelativeTo(gameFrame);
        setResizable(false);
    }

    public PieceType getSelectedPiece() {
        return selectedPiece;
    }

    public enum PieceType {
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT
    }
}
