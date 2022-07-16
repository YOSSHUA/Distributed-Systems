package mx.itam.packages.rmi.client;

import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MasterNode {

    public static void main(String args[]) throws InterruptedException {
        Task[] img = { new Task(1, 1,5),
                new Task(2, 1,10),
                new Task(3, 1,15),
                new Task(4, 1,20),
                new Task(5, 1,30),
                new Task(6, 1,5),
                new Task(7, 1,10),
                new Task(8, 1,15),
                new Task(9, 1,20),
                new Task(10, 1,30)
        };
        Task[] mine = { new Task(11, 2,5),
                new Task(12, 2,10),
                new Task(13, 2,15),
                new Task(14, 2,20),
                new Task(15, 2,30),
                new Task(16, 2,5),
                new Task(17, 2,10),
                new Task(18, 2,15),
                new Task(19, 2,20),
                new Task(20, 2,30),
                new Task(21, 2,5),
                new Task(22, 2,10),
                new Task(23, 2,15),
                new Task(24, 2,20),
                new Task(25, 2,30),
                new Task(26, 2,5),
                new Task(27, 2,10),
                new Task(28, 2,15),
                new Task(29, 2,20),
                new Task(30, 2,30)
        };
        Task[] bio = { new Task(31, 3,5),
                new Task(32, 3,10),
                new Task(33, 3,15),
                new Task(34, 3,20),
                new Task(35, 3,30),
                new Task(36, 3,5),
                new Task(37, 3,10),
                new Task(38, 3,15),
                new Task(39, 3,20),
                new Task(40, 3,30),
                new Task(41, 3,5),
                new Task(42, 3,10),
                new Task(43, 3,15),
                new Task(44, 3,20),
                new Task(45, 3,30)
        };


        System.setProperty("java.security.policy","src/mx/itam/packages/rmi/client/client.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String serverAddress = "localhost";
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            long start = System.currentTimeMillis();
            ClientThread clientThread1;
            clientThread1 = new ClientThread(img, registry);
            ClientThread clientThread2;
            clientThread2 = new ClientThread(mine, registry);
            ClientThread clientThread3;
            clientThread3 = new ClientThread(bio, registry);
            clientThread2.start();
            clientThread1.start();
            clientThread3.start();
            clientThread1.join();
            clientThread2.join();
            clientThread3.join();
            long duration = System.currentTimeMillis() - start;
            long sec = TimeUnit.MILLISECONDS.toSeconds(duration);
            System.out.println("Duration: " + sec + " seconds");
        } catch (RemoteException e) {
            e.printStackTrace();
        }



    }
}
