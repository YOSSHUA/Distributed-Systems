package mx.itam.packages.tcpsockets;

import java.net.*;
import java.io.*;

public class TCPClient {

    public static void main(String args[]) {

        Socket s = null;

        try {
            int serverPort = 49152;

            s = new Socket("localhost", serverPort);
            //s = new Socket("127.0.0.1", serverPort);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            Person me = new Person("Yosshua", "Mex", 2000);
            //out.writeUTF("Hello");            // UTF is a string encoding
            out.writeObject(me);

            ObjectInputStream in =  new ObjectInputStream(s.getInputStream());
            try{
                me = (Person) in.readObject();
                System.out.println("Received: " + "Name: " + me.getName() + ". Birth place: " + me.getPlace() + ". Year: " + me.getYear());
            }catch (Exception ex){
                System.out.println(ex);
            }

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
