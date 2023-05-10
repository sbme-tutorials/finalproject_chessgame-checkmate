package Main;

import Pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// This class handles user input through mouse clicks and drags
public class Input extends MouseAdapter {

    // The board object that this input is associated with
    Board board;

    // Constructor that takes a board object
    public Input(Board board) {
        this.board = board;
    }

    // Method called when mouse is pressed down
    @Override
    public void mousePressed(MouseEvent e) {
        // Calculate the column and row of the tile that was clicked
        int column = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        // Get the piece object that is on that tile
        Piece pieceXY = board.getPiece(column, row);

        // If a piece was found, select it
        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        }
    }

    // Method called when mouse is dragged while pressed down
    @Override
    public void mouseDragged(MouseEvent e) {
        // If a piece is currently selected, move it to the mouse's location
        if (board.selectedPiece != null) {
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;

            // Repaint the board to show the updated piece position
            board.repaint();
        }
    }

    // Method called when mouse is released
    @Override
    public void mouseReleased(MouseEvent e) {
        // Calculate the column and row of the tile that the mouse was released on
        int column = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        // If a piece is currently selected
        if (board.selectedPiece != null) {
            // Create a Move object representing the piece's movement
            Move move = new Move(board, board.selectedPiece, column, row);

            // If it's valid move and turn, make the move on the board
            if (board.isValidMove(move) && board.validTurn(move)) {
                Move.counter++; //counter increment to switch turns
                board.makeMove(move);
            }
            // If the move is invalid, return the piece to its original position
            else {
                board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
                board.selectedPiece.xPos = board.selectedPiece.column * board.tileSize;
            }
        }

        // Deselect the piece and repaint the board
        board.selectedPiece = null;
        board.repaint();
    }
}
