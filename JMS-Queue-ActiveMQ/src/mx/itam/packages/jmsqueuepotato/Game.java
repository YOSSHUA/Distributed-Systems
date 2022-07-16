package mx.itam.packages.jmsqueuepotato;

public class Game {
    public static void main(String[] args){
        int id1 = 1, id2 = 2;
        Papa papa = new Papa((int)(Math.random()*10 + 1));
        System.out.println("Starting game with " + papa.getTimer() + " sec. in the potato");
        Jugador j1 = new Jugador(id1, id2);
        Jugador j2 = new Jugador(id2, id1);
        j1.start();
        j2.start();
        if(Math.random()<0.5){
            j1.produceMessages(papa);
        }else{
            j2.produceMessages(papa);
        }

    }
}
