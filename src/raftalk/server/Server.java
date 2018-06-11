package raftalk.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server ovog programa.
 * 
 * @author milossmi
 */
public class Server {

	private static ArrayList<ServerClient> clients;
	
	public Server(int port) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(port);
		clients = new ArrayList<>();
		
		while (true) {
			Socket socket = serverSocket.accept();
			new Thread(new ServerThread(socket)).start();
		}
	}

	public static ArrayList<ServerClient> getClients() {
		return clients;
	}
}