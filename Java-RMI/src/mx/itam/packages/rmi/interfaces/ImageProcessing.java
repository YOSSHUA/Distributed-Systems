package mx.itam.packages.rmi.interfaces;

import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessing extends Remote {
    public Task executeImageTask(Task task) throws RemoteException;
}
