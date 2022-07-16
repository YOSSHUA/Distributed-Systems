package mx.itam.packages.rmi.interfaces;

import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BioInformatics extends Remote {
    public Task executeBioTask(Task task) throws RemoteException;
}
