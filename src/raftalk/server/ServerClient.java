package raftalk.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Klijenti na postojecem serveru.
 * 
 * @author milossmi
 */
public class ServerClient {

	public InetAddress address;
	private Socket socket;
	private final int ID;
	public int attempt = 0;
	private PrintWriter out;
	
	public ServerClient(InetAddress address, final int ID, Socket socket) {
		this.address = address;
		this.ID = ID;
		this.socket = socket;
		
		try {
			out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public int getID() {
		return ID;
	}
}