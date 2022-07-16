package mx.itam.packages.rmi.server;

import mx.itam.packages.rmi.interfaces.BioInformatics;
import mx.itam.packages.rmi.interfaces.Compute;
import mx.itam.packages.rmi.interfaces.DataMining;
import mx.itam.packages.rmi.interfaces.ImageProcessing;
import mx.itam.packages.rmi.serializableobjects.Credential;
import mx.itam.packages.rmi.serializableobjects.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SlaveNode implements BioInformatics, Compute, DataMining, ImageProcessing {
    public SlaveNode() throws RemoteException{
        super();
    }
    @Override
    public double square(int number) throws RemoteException {
        return number*number;
    }

    @Override
    public double power(int num1, int num2){
        return java.lang.Math.pow(num1,num2);
    }

    @Override
    public String concatenate(Credential myCredential) throws RemoteException {
        return "Name: " + myCredential.getName() + " Lugar de nacimiento: " + myCredential.getEstado();
    }

    @Override
    public Task executeBioTask(Task task) {
        try {
            Thread.sleep(task.getLength() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setOutput(task.getTaskId());
        return task;
    }

    @Override
    public Task executeDataTask(Task task) {
        try {
            Thread.sleep(task.getLength()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setOutput(task.getTaskId());
        return task;
    }

    @Override
    public Task executeImageTask(Task task) {
        try {
            Thread.sleep(task.getLength()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setOutput(task.getTaskId());
        return task;
    }
    public  void deploy(String stub){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            if(stub == "StubImage"){
                ImageProcessing stubImage = (ImageProcessing) UnicastRemoteObject.exportObject(this,0);
                registry.rebind(stub, stubImage);
            }else if(stub == "StubData"){
                DataMining stubData = (DataMining) UnicastRemoteObject.exportObject(this,0);
                registry.rebind(stub, stubData);
            }else{
                BioInformatics stubBio = (BioInformatics) UnicastRemoteObject.exportObject(this,0);
                registry.rebind(stub, stubBio);
            }
        }catch (Exception ex){
            System.out.println("Error " + ex);
        }
    }
}
