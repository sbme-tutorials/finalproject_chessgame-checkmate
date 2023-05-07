

public class Pawn extends Piece{
    Player player;
    Pawn(int x, int y, Player player,Block block) {
        super(x, y, player, "pawn",block);
        //reference to the player owned it
        this.player = player;
        //set the initial possible moves
        this.move_piece(true);
    }

    //changine the possible moves when piece moves
    @Override
    public void move_piece(Boolean UpdateOnly){
        if(this.player.number == 1){
            if(this.moves == 0){
                this.possibleMoves = new int[][]{
                            {((Chess.getBlock(this.x-1,this.y+1) != null ? Chess.getBlock(this.x-1,this.y+1).piece.value != null : false) ? this.x - 1 : this.x) , this.y+1},
                            {this.x , this.y+1},
                            {((Chess.getBlock(this.x+1,this.y+1) != null ? Chess.getBlock(this.x+1,this.y+1).piece.value != null : false) ? this.x + 1 : this.x) , this.y+1},
                            {this.x , this.y+2}
                        };
            }
            else{
                this.possibleMoves = new int[][]{
                            {((Chess.getBlock(this.x-1,this.y+1) != null ? Chess.getBlock(this.x-1,this.y+1).piece.value != null : false )? this.x - 1 : this.x) , this.y+1},
                            {this.x , this.y+1},
                            {((Chess.getBlock(this.x-1,this.y+1) != null ? Chess.getBlock(this.x-1,this.y+1).piece.value != null : false )? this.x + 1 : this.x) , this.y+1},
                        };
            }
        }
        else if(this.player.number == 2){
            if(this.moves == 0){
                this.possibleMoves = new int[][]{
                            {((Chess.getBlock(this.x-1,this.y-1) != null ? Chess.getBlock(this.x-1,this.y-1).piece.value != null : false )? this.x - 1 : this.x) , this.y-1},
                            {this.x , this.y-1},
                            {((Chess.getBlock(this.x+1,this.y-1) != null ? Chess.getBlock(this.x+1,this.y-1).piece.value != null : false )? this.x + 1 : this.x) , this.y-1},
                            {this.x , this.y-2}
                        };
            }
            else{
                this.possibleMoves = new int[][]{
                            {((Chess.getBlock(this.x-1,this.y-1) != null ? Chess.getBlock(this.x-1,this.y-1).piece.value != null : false )? this.x - 1 : this.x) , this.y-1},
                            {this.x , this.y-1},
                            {((Chess.getBlock(this.x+1,this.y-1) != null ? Chess.getBlock(this.x+1,this.y-1).piece.value != null : false )? this.x + 1 : this.x) , this.y-1}
                        };
            }
        }
        if(!UpdateOnly)
        this.moves++;
    }
}
