package mx.itam.packages.tcpsockets_lab.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TCPClient {
    public void getInfo(int numSolicitudes){
        List<Long> ans = new ArrayList<Long>();
        Socket s = null;
        int serverPort = 49152;
        Random rand = new Random();
        int upperBound = 5;

        try {
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            //s = new Socket("127.0.0.1", serverPort);
            List<Double> times = new ArrayList<Double>() ;
            for(int i = 0; i < numSolicitudes; i++) {
                Double startTime = Double.valueOf(System.currentTimeMillis());

                out.writeInt(rand.nextInt(upperBound) + 1);            // UTF is a string encoding
                String data = in.readUTF();
                Double spentTime = System.currentTimeMillis() - startTime;
                times.add(spentTime);
                //System.out.println("Received: " + data);
            }
            out.writeInt(-1);
            String data = in.readUTF();
            //System.out.println("Received: " + data);
            Double prom = avg(times);
            Double std = stdDev(times);
            System.out.println(numSolicitudes + " " + prom + " " + std);
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
    private Double avg(List<Double> list){
        int n = list.size();
        if(n == 0)
            return 0.0;
        Double ans =  0.0;
        for(int i = 0; i < n; i++){
            ans+= list.get(i);
        }
        return ans/n;
    }
    private double stdDev(List<Double> list){
        double sum = 0.0;
        double num = 0.0;
        for (int i=0; i < list.size(); i++)
            sum+=list.get(i);
        double mean = sum / list.size();
        for (int i = 0; i < list.size(); i++)
            num += Math.pow((list.get(i) - mean), 2);
        return Math.sqrt(num / list.size());
    }
    public static void main(String args[]) {
        int numSolicitudes = args.length == 0 ? 100 : Integer.parseInt(args[0]);
        Socket s = null;
        int serverPort = 49152;
        Random rand = new Random();
        int upperBound = 5;

        try {
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            //s = new Socket("127.0.0.1", serverPort);
            List<Long> times = new ArrayList<Long>() ;
            for(int i = 0; i < numSolicitudes; i++) {
                long startTime = System.currentTimeMillis();

                out.writeInt(rand.nextInt(upperBound) + 1);            // UTF is a string encoding
                String data = in.readUTF();
                long spentTime = System.currentTimeMillis() - startTime;
                times.add(spentTime);
                //System.out.println("Received: " + data);
            }
            out.writeInt(-1);
            String data = in.readUTF();
            //System.out.println("Received: " + data);

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
