package mx.itam.packages.tcpsockets;

import java.net.*;
import java.io.*;

public class TCPServer {

	public static void main(String args[]) {
		try {
			int serverPort = 49152;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				System.out.println("Waiting for messages...");
				Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
				Connection c = new Connection(clientSocket);
				c.start();
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}

}

class Connection extends Thread {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			Person me = (Person) in.readObject();
			System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
			out.writeObject(me);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}


