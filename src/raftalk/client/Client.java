package raftalk.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/**
 * Klijent ovog programa.
 * 
 * @author dzimiks
 */
public class Client {

	private Socket socket;
	private String name;
	private String address;
	private int port;
	private InetAddress ip;
	private Thread send;
	private int ID = -1;
	private PrintWriter out;
	private BufferedReader in;
	private Color color;
	private Random rand;

	public Client(Client client) {
		this.name = client.name;
		this.address = client.address;
		this.port = client.port;
		this.rand = new Random();
		this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.rand = new Random();
		this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	/**
	 * Otvara socket konekciju i postavlja osnove funkcionalnosti.
	 * 
	 * @return true - ako je sve u redu
	 * @throws IOEXception 
	 */
	public boolean openConnection() {
		try {
			this.socket = new Socket(address, port);
			this.ip = InetAddress.getByName(address);
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Prihvata prosledjenu poruku.
	 * 
	 * @return primljena poruka
	 * @throws IOException
	 */
	public String receive() {
		String message = null;
		
		try {
			message = in.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	/**
	 * Prosledjuje poruku.
	 * 
	 * @param message - poruka koja se salje
	 */
	public void send(final String message) {
		send = new Thread("Send") {
			public void run() {
				out.println(message);
			}
		};
	
		send.start();
	}
	
	/**
	 * Zatvara socket.
	 */
	public void close() {
		new Thread() {
			public void run() {
				synchronized (socket) {
					try {
						socket.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public Thread getSend() {
		return send;
	}

	public void setSend(Thread send) {
		this.send = send;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}