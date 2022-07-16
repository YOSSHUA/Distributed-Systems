package mx.itam.packages.rmi.interfaces;

import mx.itam.packages.rmi.serializableobjects.Credential;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    public double square( int number ) throws RemoteException;
    public double power( int num1, int num2) throws RemoteException;
    public String concatenate(Credential myCredential) throws RemoteException;
}
