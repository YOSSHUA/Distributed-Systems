package mx.itam.packages.ipmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * @author Octavio Gutierrez
 */
public class MulticastSenderPeer {

    public static void main(String args[]) throws IOException {
        MulticastSocket socket = null;
        InetAddress group = null;
        try {
            group = InetAddress.getByName("228.5.6.7"); // destination multicast group
            socket = new MulticastSocket(49155);
            socket.joinGroup(group);
            //s.setTimeToLive(10);
            System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());
            String currentDate = (new Date()).toString();
            byte [] m = currentDate.getBytes();
            DatagramPacket messageOut =
                    new DatagramPacket(m, m.length, group, 49155);
            while(true) {
                System.out.println("Sending current time");
                socket.send(messageOut);
                Thread.sleep(2000);
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            if (socket != null) {
                if(group != null)
                    socket.leaveGroup(group);
                socket.close();
            }
        }
    }
}
