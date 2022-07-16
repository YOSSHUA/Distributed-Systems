package mx.itam.packages.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Deployer {
    public static void main(String[] args)  {
        // Se agregan políticas de seguridad
        System.setProperty("java.security.policy","src/mx/itam/packages/rmi/server/server.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Creamos el Registry
        try {
            // String serverAddress = "?.?.?.?"; Para acceder remotamente debemos de utilizar nuestra IP.
            String serverAddress = "localhost";
            System.setProperty("java.rmi.server.hostname", serverAddress);
            LocateRegistry.createRegistry(1099); // Levanta el servicio

            String[] stubs = {"StubImage", "StubData", "StubBio"};

            for(String stub : stubs){
                SlaveNode slaveNode = new SlaveNode();
                slaveNode.deploy(stub);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }



    }
}
