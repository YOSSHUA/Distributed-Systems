package mx.itam.packages.tcpsockets_lab.server;

import java.util.List;

public class Launcher {
    public static void main(String args[]) {
        int nClientes = args.length == 0 ? 50 : Integer.parseInt(args[0]);
        int numClientes[] = {nClientes};
        for(int numCliente: numClientes) {
            System.out.println("Trying " + numCliente + " clientes");
            for(int i = 0; i < numCliente; i++) {
                ClientThread clientThread = new ClientThread();
                clientThread.start();
            }

        }
    }

}

class ClientThread extends Thread{
    public ClientThread(){

    }

    public void run(){
        int numSolicitudes[] = {500, 1000, 1500, 2000};
        String arg[] = {""};
        for(int j = 0; j < numSolicitudes.length; j++){
            TCPClient client = new TCPClient();
            client.getInfo(numSolicitudes[j]);
        }

    }

}
