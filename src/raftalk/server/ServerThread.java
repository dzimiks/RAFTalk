package raftalk.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Thread klasa za server.
 * 
 * @author milossmi
 */
public class ServerThread implements Runnable {

	private Socket socket;
	private BufferedReader in;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Server.getClients().add(new ServerClient(socket.getInetAddress(), UniqueID.getID(), socket));
			
			while (true) {
				String message = in.readLine();
				
				if (message.equals("exit")) {
					for (int i = 0; i < Server.getClients().size(); i++)
						if (socket.getInetAddress() == Server.getClients().get(i).getSocket().getInetAddress())
							Server.getClients().get(i).getOut().println("exit");
					break;
				}
				
				for (ServerClient client : Server.getClients()) {
					client.getOut().println(message);
				}
			}
			
			for (int i = 0; i < Server.getClients().size(); i++)
				if (socket.getInetAddress() == Server.getClients().get(i).getSocket().getInetAddress())
					Server.getClients().remove(i);
			
			socket.close();
		}
		catch (Exception e) {
//			System.err.println("Server Thread error!");
//			e.printStackTrace();
		}
	}

	public BufferedReader getIn() {
		return in;
	}
}