package mx.itam.packages.rmi.client;

import mx.itam.packages.rmi.interfaces.BioInformatics;
import mx.itam.packages.rmi.interfaces.Compute;
import mx.itam.packages.rmi.interfaces.DataMining;
import mx.itam.packages.rmi.interfaces.ImageProcessing;
import mx.itam.packages.rmi.serializableobjects.Credential;
import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ClientThread extends Thread{
    Task[] tareas;
    Registry registry;
    public ClientThread(Task[] tareas, Registry registry) {
        this.tareas = tareas;
        this.registry =  registry;
    }

    @Override
    public void run() {
        try {

            ImageProcessing rmiImg = (ImageProcessing) registry.lookup("StubImage");
            BioInformatics rmiBio = (BioInformatics) registry.lookup("StubBio");
            DataMining rmiData = (DataMining) registry.lookup("StubData");
            for(Task t : tareas) {
                if (t.getRequirementId() == 1) {
                    Task ans = rmiImg.executeImageTask(t);
                }if (t.getRequirementId() == 2) {
                    Task ans = rmiData.executeDataTask(t);
                }else {
                    //StubData
                    Task ans = rmiBio.executeBioTask(t);
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
}
