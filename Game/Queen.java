public class Queen extends Piece{
    Player player;
    Queen(int x, int y, Player player,Block block) {
        super(x, y, player, "queen",block);
        //reference to the player owned it
        this.player = player;
        //set the initial possible moves
        this.move_piece();
    }
    //changine the possible moves when piece moves
    @Override
    public void move_piece(){
        if(player.number == 1){
            this.possibleMoves = new int[][]{
                    {this.x+3 , this.y+3},
                    {this.x-3 , this.y+3},
                    {this.x+1 , this.y},
                    {this.x-1 , this.y},
                    {this.x , this.y-7},
                    {this.x , this.y-6},
                    {this.x , this.y-5},
                    {this.x , this.y-4},
                    {this.x , this.y-3},
                    {this.x , this.y-2},
                    {this.x , this.y-1},
                    {this.x , this.y+1},
                    {this.x , this.y+2},
                    {this.x , this.y+3},
                    {this.x , this.y+4},
                    {this.x , this.y+5},
                    {this.x , this.y+6},
                    {this.x , this.y+7},
                    {this.x-7 , this.y},
                    {this.x-6 , this.y},
                    {this.x-5 , this.y},
                    {this.x-4 , this.y},
                    {this.x-3 , this.y},
                    {this.x-2 , this.y},
                    {this.x-1 , this.y},
                    {this.x+1 , this.y},
                    {this.x+2 , this.y},
                    {this.x+3 , this.y},
                    {this.x+4 , this.y},
                    {this.x+5 , this.y},
                    {this.x+6 , this.y},
                    {this.x+7 , this.y},
            };
        }
        else if(player.number == 2){
            this.possibleMoves = new int[][]{
                    {this.x+3 , this.y-3},
                    {this.x-3 , this.y-3},
                    {this.x+1 , this.y},
                    {this.x-1 , this.y},
                    {this.x , this.y-7},
                    {this.x , this.y-6},
                    {this.x , this.y-5},
                    {this.x , this.y-4},
                    {this.x , this.y-3},
                    {this.x , this.y-2},
                    {this.x , this.y-1},
                    {this.x , this.y+1},
                    {this.x , this.y+2},
                    {this.x , this.y+3},
                    {this.x , this.y+4},
                    {this.x , this.y+5},
                    {this.x , this.y+6},
                    {this.x , this.y+7},
                    {this.x-7 , this.y},
                    {this.x-6 , this.y},
                    {this.x-5 , this.y},
                    {this.x-4 , this.y},
                    {this.x-3 , this.y},
                    {this.x-2 , this.y},
                    {this.x-1 , this.y},
                    {this.x+1 , this.y},
                    {this.x+2 , this.y},
                    {this.x+3 , this.y},
                    {this.x+4 , this.y},
                    {this.x+5 , this.y},
                    {this.x+6 , this.y},
                    {this.x+7 , this.y},
            };
        }
        this.moves++;
    }
}
