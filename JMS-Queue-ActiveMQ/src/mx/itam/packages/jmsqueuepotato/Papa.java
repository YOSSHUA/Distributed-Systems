package mx.itam.packages.jmsqueuepotato;

import java.io.Serializable;

public class Papa implements Serializable {
    public int timer;

    public Papa(int timer){
        this.timer = timer;
    }
    public void decrementTimer(){
        timer--;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
