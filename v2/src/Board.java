/*
The Board class defines the chess board with a set number of rows and columns.
The class extends JPanel and overrides the paintComponent method to draw the board and the pieces on it.
It also contains methods to add and remove pieces, capture opponent pieces, check the validity of a move and get a piece object at a given location on the board.
 */


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Board class definition
public class Board extends JPanel {

    // Declare Resizeable array to hold all the pieces present on the board.
    private final ArrayList<Piece> gamePieceList = new ArrayList<>();


    public int tileSize = 80; // size of each tile in pixels
    public Piece selectedPiece; //Piece selected by the mouse

    CheckMate checkMate = new CheckMate(this);
    int rows = 8; // number of rows on the chess board
    int columns = 8; // number of columns on the chess board
    int vmCircleRadius = 30; //Valid Moves Circle Radius


    // Define two colors (Dark & Light) for the tiles
    Color color1 = new Color(20, 30, 75);
    Color color2 = new Color(225, 225, 225);

    Input input = new Input(this);//Define object from Class Input


    // Board constructor
    public Board() {
        // Set the size of the board panel
        this.setPreferredSize(new Dimension(columns * tileSize, rows * tileSize));

        //Adds the specified mouse events for the game
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPiece();//Call the method for adding pieces on the board
    }


    //Method return a piece held in a specific tile
    public Piece getPiece(int column, int row) {
        for (Piece piece : gamePieceList) {
            if ((piece.column == column) && (piece.row == row)) {
                return piece;
            }
        }
        return null; //If there is no pieces found in this tile
    }

    //Method to check if move that will be made is valid
    public boolean isValidMove(Move move) {

        //Make sure the piece eating and eaten not at the same team
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }

        //Set the Limits for the game board
        if (!(move.newColumn < 8 && move.newColumn >= 0) && !(move.newRow < 8 && move.newRow >= 0)) {
            return false;
        }

        //Check if it's piece's valid move
        if (!move.piece.isValidMovement(move.newColumn, move.newRow)) {
            return false;
        }

        //Hit another piece check (for pieces that can not bypass another piece)
        if (move.piece.moveHitsPiece(move.newColumn, move.newRow)) {
            return false;
        }

        //Check if the move will make or keep the King Checked
        return !checkMate.isKingChecked(move);
    }


    //Method to check if two pieces are on same team or not
    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            //Make sure there is a piece on the specified tile
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    //Method to set the turns of play
    public boolean validTurn() {
        return Move.counter % 2 == 0 == selectedPiece.isWhite;
    }


    //Method that to get the King
    Piece findKing(boolean isWhite) {
        for (Piece piece : gamePieceList) {
            if (isWhite == piece.isWhite && piece.name.equals("King")) {
                return piece;
            }
        }
        return null;
    }


    //Method to move a piece
    public void makeMove(Move move) {
        // Update the position of the piece being moved
        move.piece.column = move.newColumn;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newColumn * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move.capture); // Capture enemy's piece presented in the selected tile

        //Pawn Promotion if it reaches the End Row
        if (move.piece.name.equals("Pawn") && move.piece.row == move.piece.rowEnd) {
            pawnPromote(move);
        }


        //Open the option for King Castling if it's King first move
        if (move.piece.name.equals("King") && move.piece.isFirstMove) {
            kingCastle(move);
        }

        //Set the 'isFirstMove' boolean to false
        move.piece.isFirstMove = false;
    }

    public void capture(Piece piece) {
        //Method to remove the eaten piece from the game
        //Remove the piece from the resizeable array
        gamePieceList.remove(piece);
    }

    //Method to Castle the King with the rook
    //Method to Promote the pawn to Queen
    public void pawnPromote(Move move) {
        //Add Queen
        gamePieceList.add(new Queen(this, move.newColumn, move.newRow, move.piece.isWhite));
        //gamePieceList.remove(move.capture);
        capture(move.piece);
    }


    private void kingCastle(Move move) {

        // If the king is castling to the right
        if (move.piece.column == 6) {
            // Get the rook piece located at the right edge of the board
            Piece rook = getPiece(7, move.piece.row);
            // Move the rook to the left of the king
            rook.column = 5;
            rook.xPos = rook.column * tileSize;
        } else if (move.piece.column == 2) {
            // Get the rook piece located at the right edge of the board
            Piece rook = getPiece(0, move.piece.row);
            // Move the rook to the left of the king
            rook.column = 3;
            rook.xPos = rook.column * tileSize;
        }
    }

    //Put the pieces on the board
    public void addPiece() {
        //Adding Pieces to the Resizeable Game array

        //Black Pawns
        for (int c = 0; c < columns; c++) {
            gamePieceList.add(new Pawn(this, c, 1, false));
        }
        //White Pawns
        for (int c = 0; c < columns; c++) {
            gamePieceList.add(new Pawn(this, c, 6, true));
        }
        //Black King
        gamePieceList.add(new King(this, 4, 0, false));
        //White King
        gamePieceList.add(new King(this, 4, 7, true));
        //Black Queen
        gamePieceList.add(new Queen(this, 3, 0, false));
        //White Queen
        gamePieceList.add(new Queen(this, 3, 7, true));
        //Black Bishop
        gamePieceList.add(new Bishop(this, 5, 0, false));
        gamePieceList.add(new Bishop(this, 2, 0, false));
        //White Bishop
        gamePieceList.add(new Bishop(this, 5, 7, true));
        gamePieceList.add(new Bishop(this, 2, 7, true));
        //Black Knights
        gamePieceList.add(new Knight(this, 1, 0, false));
        gamePieceList.add(new Knight(this, 6, 0, false));
        //White Knights
        gamePieceList.add(new Knight(this, 1, 7, true));
        gamePieceList.add(new Knight(this, 6, 7, true));
        //Black Rook
        gamePieceList.add(new Rook(this, 0, 0, false));
        gamePieceList.add(new Rook(this, 7, 0, false));
        //White Rook
        gamePieceList.add(new Rook(this, 0, 7, true));
        gamePieceList.add(new Rook(this, 7, 7, true));
    }

    // Override the paintComponent method to draw the game
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                // Set the color of the current square based on its position on the board
                g2d.setColor((c + r) % 2 == 0 ? color2 : color1);
                // Fill the current square with the selected color
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }

        //Make a shape for valid moves of the selected piece with  circle
        if (this.selectedPiece != null) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    //Check if it's valid Move
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {

                        //Check if it's valid turn to play
                        if (validTurn()) {

                            //Check if there is an enemy's piece on this tile
                            if (getPiece(c, r) == null) {

                                // Set the fill color to green and fill the circle
                                g2d.setColor(new Color(20, 150, 0, 255));
                                g2d.fillRoundRect(c * tileSize + 25, r * tileSize + 25, vmCircleRadius, vmCircleRadius, vmCircleRadius, vmCircleRadius);

                                // Set the stroke color to black and draw the round rectangle with a border
                                g2d.setStroke(new BasicStroke(1));
                                g2d.setColor(Color.BLACK);
                                g2d.drawRoundRect(c * tileSize + 25, r * tileSize + 25, vmCircleRadius, vmCircleRadius, vmCircleRadius, vmCircleRadius);
                            }
                            //If there is enemy piece on this tile
                            else {

                                //Set the fill color to red and fill the tile
                                g2d.setColor(Color.BLACK);
                                g2d.setStroke(new BasicStroke(5));
                                g2d.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);
                                g2d.setColor(new Color(126, 0, 0));
                                g2d.fillRect(c * tileSize + 2, r * tileSize + 2, tileSize - 4, tileSize - 4);
                            }
                        }
                        //if it's not valid turn
                        else {

                            //Check if there is an enemy's piece on this tile
                            if (getPiece(c, r) == null) {

                                // Set the fill color to yellow and fill the circle
                                g2d.setColor(new Color(255, 248, 46, 255));
                                g2d.fillRoundRect(c * tileSize + 25, r * tileSize + 25, vmCircleRadius, vmCircleRadius, vmCircleRadius, vmCircleRadius);

                                // Set the stroke color to yellow and draw the round rectangle with a border
                                g2d.setStroke(new BasicStroke(1));
                                g2d.setColor(new Color(255, 248, 46));
                                g2d.drawRoundRect(c * tileSize + 25, r * tileSize + 25, vmCircleRadius, vmCircleRadius, vmCircleRadius, vmCircleRadius);

                            }
                            //If there is enemy piece on this tile
                            else {

                                //Set the fill color to yellow and fill the tile
                                g2d.setColor(Color.BLACK);
                                g2d.setStroke(new BasicStroke(5));
                                g2d.drawRect(c * tileSize, r * tileSize, tileSize, tileSize);
                                g2d.setColor(new Color(255, 248, 46));
                                g2d.fillRect(c * tileSize + 2, r * tileSize + 2, tileSize - 4, tileSize - 4);
                            }
                        }
                    }
                }
            }
        }
        //Draw each piece on the Board
        for (Piece piece : gamePieceList) {
            piece.paint(g2d);
        }
    }
}
