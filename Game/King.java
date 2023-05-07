public class King extends Piece {
    Player player;
    King(int x, int y, Player player,Block block) {
        super(x, y, player, "king",block);
        //reference to the player owned it
        this.player = player;
        this.move_piece();
    }
    //changine the possible moves when piece moves
    @Override
    public void move_piece(){
        this.moves++;
    }
}
