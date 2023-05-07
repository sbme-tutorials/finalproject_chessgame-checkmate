import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Player {
    public boolean paused = true;
    Piece pieces[] = new Piece[8];
    Piece eaten_pieces[] = new Piece[16];
    static int total = 0;
    int number ,eaten_pieces_num = 0;
    double remTime;
    String name;
    Timer timer;
    TimerTask task;
    JLabel timerText;
    Player(String name, double time){
        this.name = name;
        this.number = total + 1;
        total++;
        this.remTime = time;
        this.timer = new Timer();
        //this.paused = false;
        this.task = new TimerTask() {
            public void run() {
                if (Chess.activePlayer.paused == false){
                    Chess.activePlayer.remTime -= 0.01;
                    Chess.activePlayer.timerText.setText(Chess.calcTimeString2(Chess.activePlayer.remTime));
                    Chess.frame.repaint();
                }
            }
        };
        this.timer.schedule(this.task, 1000, 1000);
    }
    public void stopTime(){
        this.paused = true;
        
    }
    public void runTime(){
        this.paused = false;
    }
}




